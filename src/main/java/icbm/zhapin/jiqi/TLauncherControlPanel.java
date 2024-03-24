package icbm.zhapin.jiqi;

import dan200.computercraft.api.peripheral.IPeripheral;
import icbm.api.IMissile;
import icbm.api.LauncherType;
import icbm.zhapin.ICBMExplosion;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.implement.IRotatable;
import universalelectricity.prefab.implement.ITier;
import universalelectricity.prefab.multiblock.IBlockActivate;

public class TLauncherControlPanel
    extends TLauncherController implements IBlockActivate, ITier, IRotatable {
    private boolean isPowered;
    private byte direction;
    private int tier;
    public TLauncherPlatform faSheDi;
    public short height;

    public TLauncherControlPanel() {
        this.isPowered = false;
        this.direction = 3;
        this.tier = 0;
        this.faSheDi = null;
        this.height = 3;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!this.isDisabled()) {
            if (this.faSheDi == null) {
                for (byte i = 2; i < 6; ++i) {
                    final Vector3 position
                        = new Vector3(this.xCoord, this.yCoord, this.zCoord);
                    position.modifyPositionFromSide(ForgeDirection.getOrientation((int) i)
                    );
                    final TileEntity tileEntity = this.worldObj.getTileEntity(
                        position.intX(), position.intY(), position.intZ()
                    );

                    if (tileEntity != null && tileEntity instanceof TLauncherPlatform) {
                        this.faSheDi = (TLauncherPlatform) tileEntity;
                        this.direction = i;
                    }
                }
            } else if (this.faSheDi.isInvalid()) {
                this.faSheDi = null;
            }

            if (this.isPowered) {
                this.isPowered = false;
                this.launch();
            }
        }

        if (!this.worldObj.isRemote) {
            if (super.ticks % 10L == 0L) {
                this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
            }
        }
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();

        nbt.setDouble("joules", this.getJoules());
        nbt.setByte("direction", this.direction);
        nbt.setInteger("tier", this.tier);
        nbt.setInteger("frequency", this.getFrequency());
        nbt.setShort("height", this.height);
        nbt.setInteger("disabledTicks", this.disabledTicks);
        if (super.target != null)
            nbt.setTag("target", super.target.writeToNBT(new NBTTagCompound()));

        return new S35PacketUpdateTileEntity(
            this.xCoord, this.yCoord, this.zCoord, this.getBlockMetadata(), nbt
        );
    }

    @Override
    public void placeMissile(final ItemStack itemStack) {
        if (this.faSheDi != null && !this.faSheDi.isInvalid()) {
            this.faSheDi.setInventorySlotContents(0, itemStack);
        }
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound nbt = pkt.func_148857_g();

        this.setJoules(nbt.getDouble("joules"));
        this.direction = nbt.getByte("direction");
        this.tier = nbt.getInteger("tier");
        this.setFrequency(nbt.getInteger("frequency"));
        this.height = nbt.getShort("height");
        this.disabledTicks = nbt.getInteger("disabledTicks");
        if (nbt.hasKey("target"))
            super.target = Vector3.readFromNBT(nbt.getCompoundTag("target"));
    }

    @Override
    public boolean canLaunch() {
        return this.faSheDi != null && !this.isDisabled() && this.faSheDi.daoDan != null
            && this.getJoules() >= this.getMaxJoules()
            && this.faSheDi.isInRange(super.target);
    }

    @Override
    public void launch() {
        if (this.canLaunch()) {
            this.setJoules(0.0);
            this.faSheDi.launchMissile(super.target.clone(), this.height);
        }
    }

    @Override
    public String getStatus() {
        String color = "§4";
        String status = "Idle";

        if (this.isDisabled()) {
            status = "Disabled";
        } else if (this.faSheDi == null) {
            status = "Not connected!";
        } else if (this.getJoules() < this.getMaxJoules()) {
            status = "Insufficient electricity!";
        } else if (this.faSheDi.daoDan == null) {
            status = "Missile silo is empty!";
        } else if (super.target == null) {
            status = "Target is invalid!";
        } else if (this.faSheDi.shiTaiJin(super.target)) {
            status = "Target too close!";
        } else if (this.faSheDi.shiTaiYuan(super.target)) {
            status = "Target too far!";
        } else {
            color = "§2";
            status = "Ready to launch!";
        }

        return color + status;
    }

    @Override
    public void readFromNBT(final NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.tier = nbt.getInteger("tier");
        this.direction = nbt.getByte("facingDirection");
        this.height = nbt.getShort("height");
    }

    @Override
    public void writeToNBT(final NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("tier", this.tier);
        nbt.setByte("facingDirection", this.direction);
        nbt.setShort("height", this.height);
    }

    @Override
    public double getVoltage() {
        switch (this.getTier()) {
            default: {
                return 120.0;
            }

            case 1: {
                return 240.0;
            }

            case 2: {
                return 480.0;
            }
        }
    }

    @Override
    public void onPowerOn() {
        this.isPowered = true;
    }

    @Override
    public void onPowerOff() {
        this.isPowered = false;
    }

    @Override
    public int getTier() {
        return this.tier;
    }

    @Override
    public void setTier(final int tier) {
        this.tier = tier;
    }

    @Override
    public ForgeDirection
    getDirection(final IBlockAccess world, final int x, final int y, final int z) {
        return ForgeDirection.getOrientation((int) this.direction);
    }

    @Override
    public void setDirection(
        final World world,
        final int x,
        final int y,
        final int z,
        final ForgeDirection facingDirection
    ) {
        this.direction = (byte) facingDirection.ordinal();
    }

    @Override
    public double getMaxJoules() {
        switch (this.getTier()) {
            case 0: {
                return 400000.0;
            }

            case 1: {
                return 60000.0;
            }

            default: {
                return 800000.0;
            }
        }
    }

    @Override
    public boolean onActivated(final EntityPlayer entityPlayer) {
        entityPlayer.openGui(
            (Object) ICBMExplosion.instance,
            2,
            this.worldObj,
            this.xCoord,
            this.yCoord,
            this.zCoord
        );
        return true;
    }

    @Override
    public LauncherType getLauncherType() {
        return LauncherType.TRADITIONAL;
    }

    @Override
    public IMissile getMissile() {
        if (this.faSheDi != null) {
            return this.faSheDi.getContainingMissile();
        }

        return null;
    }

    @Override
    public boolean equals(IPeripheral other) {
        return this == other;
    }
}
