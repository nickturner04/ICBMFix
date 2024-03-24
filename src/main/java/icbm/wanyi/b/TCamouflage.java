package icbm.wanyi.b;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TCamouflage extends TileEntity {
    private Block camoBlock;
    private int camoMetadata;
    private boolean isSolid;
    private final boolean[] transparentSides;

    public TCamouflage() {
        this.camoBlock = Blocks.air;
        this.camoMetadata = 0;
        this.isSolid = true;
        this.transparentSides
            = new boolean[] { false, false, false, false, false, false };
    }

    public boolean canUpdate() {
        return false;
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound nbt = pkt.func_148857_g();

        this.setCamoBlock(
            Block.getBlockById(nbt.getInteger("camoId")), nbt.getInteger("camoMeta")
        );
        this.isSolid = nbt.getBoolean("isSolid");

        for (int i = 0; i < this.transparentSides.length; i++) {
            this.transparentSides[i] = nbt.getBoolean("transparent" + i);
        }
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();

        nbt.setInteger("camoId", Block.getIdFromBlock(this.camoBlock));
        nbt.setInteger("camoMeta", this.camoMetadata);
        nbt.setBoolean("isSolid", this.isSolid);

        for (int i = 0; i < this.transparentSides.length; i++) {
            nbt.setBoolean("transparent" + i, this.transparentSides[i]);
        }

        return new S35PacketUpdateTileEntity(
            this.xCoord, this.yCoord, this.zCoord, this.getBlockMetadata(), nbt
        );
    }

    public boolean getSolid() {
        return this.isSolid;
    }

    public void setSolid(final boolean isSolid) {
        this.isSolid = isSolid;

        if (!this.worldObj.isRemote) {
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        }
    }

    public void setSolid() {
        this.setSolid(!this.isSolid);
    }

    public Block getCamoBlock() {
        return this.camoBlock;
    }

    public int getJiaMetadata() {
        return this.camoMetadata;
    }

    public void setCamoBlock(final Block block, final int metadata) {
        if (this.camoBlock != block || this.camoMetadata != metadata) {
            this.camoBlock = block;
            this.camoMetadata = Math.max(metadata, 0);

            if (!this.worldObj.isRemote) {
                this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
            }
        }
    }

    public boolean getTransparent(final ForgeDirection direction) {
        return direction.ordinal() < this.transparentSides.length
            && this.transparentSides[direction.ordinal()];
    }

    public void
    setTransparent(final ForgeDirection direction, final boolean isTransparent) {
        if (direction.ordinal() < this.transparentSides.length) {
            this.transparentSides[direction.ordinal()] = isTransparent;

            if (!this.worldObj.isRemote) {
                this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
            }
        }
    }

    public void setTransparent(final ForgeDirection direction) {
        this.setTransparent(direction, !this.getTransparent(direction));
    }

    public void setTransparent(final boolean isTransparent) {
        for (int i = 0; i < this.transparentSides.length; ++i) {
            this.setTransparent(ForgeDirection.getOrientation(i), isTransparent);
        }
    }

    @Override
    public void readFromNBT(final NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        this.camoBlock = Block.getBlockById(par1NBTTagCompound.getInteger("camoBlock"));
        this.camoMetadata = par1NBTTagCompound.getInteger("camoMetadata");

        for (int i = 0; i < this.transparentSides.length; ++i) {
            this.transparentSides[i] = par1NBTTagCompound.getBoolean("transparent" + i);
        }

        this.isSolid = par1NBTTagCompound.getBoolean("isSolid");
    }

    @Override
    public void writeToNBT(final NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("camoBlock", Block.getIdFromBlock(this.camoBlock));
        par1NBTTagCompound.setInteger("camoMetadata", this.camoMetadata);

        for (int i = 0; i < this.transparentSides.length; ++i) {
            par1NBTTagCompound.setBoolean("transparent" + i, this.transparentSides[i]);
        }

        par1NBTTagCompound.setBoolean("isSolid", this.isSolid);
    }
}
