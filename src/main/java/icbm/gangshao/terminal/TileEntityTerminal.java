package icbm.gangshao.terminal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import calclavia.lib.TileEntityUniversalRunnable;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import icbm.gangshao.ICBMSentry;
import icbm.gangshao.access.AccessLevel;
import icbm.gangshao.access.UserAccess;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import universalelectricity.core.vector.Vector3;

public abstract class TileEntityTerminal
    extends TileEntityUniversalRunnable implements ITerminal {
    protected List<String> terminalOutput;
    private final List<UserAccess> users;
    public static final int SCROLL_SIZE = 15;
    private int scroll;
    public final Set<EntityPlayer> playersUsing;

    public TileEntityTerminal() {
        this.terminalOutput = new ArrayList<>();
        this.users = new ArrayList<>();
        this.scroll = 0;
        this.playersUsing = new HashSet<>();
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!this.worldObj.isRemote && super.ticks % 3L == 0L) {
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        }
    }

    public abstract String getChannel();

    @Override
    public Packet getDescriptionPacket() {
        final NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);

        return new S35PacketUpdateTileEntity(
            this.xCoord, this.yCoord, this.zCoord, this.getBlockMetadata(), nbt
        );
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound nbt = pkt.func_148857_g();

        this.readFromNBT(nbt);
    }

    public void sendTerminalOutputToClients() {
        ICBMSentry.channel.sendToAllAround(
            new TerminalOutputPacket(new Vector3(this), this.terminalOutput),
            new TargetPoint(
                this.worldObj.provider.dimensionId,
                this.xCoord,
                this.yCoord,
                this.zCoord,
                16
            )
        );
    }

    public void
    sendCommandToServer(final EntityPlayer entityPlayer, final String cmdInput) {
        if (this.worldObj.isRemote) {
            ICBMSentry.channel.sendToServer(new TerminalCommandPacket(new Vector3(this), cmdInput));
        }
    }

    @Override
    public AccessLevel getUserAccess(final String username) {
        for (int i = 0; i < this.users.size(); ++i) {
            if (this.users.get(i).username.equalsIgnoreCase(username)) {
                return this.users.get(i).level;
            }
        }

        return AccessLevel.NONE;
    }

    public boolean canUserAccess(final String username) {
        return this.getUserAccess(username).ordinal() >= AccessLevel.USER.ordinal();
    }

    @Override
    public List<UserAccess> getUsers() {
        return this.users;
    }

    @Override
    public List<UserAccess> getUsersWithAcess(final AccessLevel level) {
        final List<UserAccess> players = new ArrayList<>();

        for (int i = 0; i < this.users.size(); ++i) {
            final UserAccess ref = this.users.get(i);

            if (ref.level == level) {
                players.add(ref);
            }
        }

        return players;
    }

    @Override
    public boolean
    addUserAccess(final String player, final AccessLevel lvl, final boolean save) {
        this.removeUserAccess(player);
        final boolean bool = this.users.add(new UserAccess(player, lvl, save));
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        return bool;
    }

    @Override
    public boolean removeUserAccess(final String player) {
        final List<UserAccess> removeList = new ArrayList<>();

        for (int i = 0; i < this.users.size(); ++i) {
            final UserAccess ref = this.users.get(i);

            if (ref.username.equalsIgnoreCase(player)) {
                removeList.add(ref);
            }
        }

        if (removeList != null && removeList.size() > 0) {
            final boolean bool = this.users.removeAll(removeList);
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
            return bool;
        }

        return false;
    }

    @Override
    public List<String> getTerminalOuput() {
        return this.terminalOutput;
    }

    @Override
    public boolean addToConsole(String msg) {
        if (!this.worldObj.isRemote) {
            msg.trim();

            if (msg.length() > 23) {
                msg = msg.substring(0, 22);
            }

            this.getTerminalOuput().add(msg);
            this.sendTerminalOutputToClients();
            return true;
        }

        return false;
    }

    @Override
    public void scroll(final int amount) {
        this.setScroll(this.scroll + amount);
    }

    @Override
    public void setScroll(final int length) {
        this.scroll = Math.max(Math.min(length, this.getTerminalOuput().size()), 0);
    }

    @Override
    public int getScroll() {
        return this.scroll;
    }

    public void readFromNBT(final NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.users.clear();
        final NBTTagList userList = nbt.getTagList("Users", 10);

        for (int i = 0; i < userList.tagCount(); ++i) {
            final NBTTagCompound var4 = (NBTTagCompound) userList.getCompoundTagAt(i);
            this.users.add(UserAccess.loadFromNBT(var4));
        }
    }

    public void writeToNBT(final NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        final NBTTagList usersTag = new NBTTagList();

        for (int player = 0; player < this.users.size(); ++player) {
            final UserAccess access = this.users.get(player);

            if (access != null && access.shouldSave) {
                final NBTTagCompound accessData = new NBTTagCompound();
                access.writeToNBT(accessData);
                usersTag.appendTag((NBTBase) accessData);
            }
        }

        nbt.setTag("Users", (NBTBase) usersTag);
    }
}
