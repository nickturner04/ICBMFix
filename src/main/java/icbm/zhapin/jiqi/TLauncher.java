package icbm.zhapin.jiqi;

import icbm.core.MainBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.implement.IRotatable;
import universalelectricity.prefab.implement.ITier;
import universalelectricity.prefab.multiblock.IMultiBlock;
import universalelectricity.prefab.multiblock.TileEntityMulti;
import universalelectricity.prefab.tile.TileEntityAdvanced;

public class TLauncher
    extends TileEntityAdvanced implements ITier, IMultiBlock, IRotatable {
    private int tier;
    private byte orientation;

    public TLauncher() {
        this.tier = 0;
        this.orientation = 3;
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound nbt = pkt.func_148857_g();

        this.orientation = nbt.getByte("orientation");
        this.tier = nbt.getInteger("tier");
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();

        nbt.setByte("orientation", this.orientation);
        nbt.setInteger("tier", this.tier);

        return new S35PacketUpdateTileEntity(
            this.xCoord, this.yCoord, this.zCoord, this.getBlockMetadata(), nbt
        );
    }

    public int getInaccuracy() {
        switch (this.tier) {
            default: {
                return 15;
            }

            case 1: {
                return 7;
            }

            case 2: {
                return 0;
            }
        }
    }

    @Override
    public boolean canUpdate() {
        return false;
    }

    @Override
    public void readFromNBT(final NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        this.tier = par1NBTTagCompound.getInteger("tier");
    }

    @Override
    public void writeToNBT(final NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("tier", this.tier);
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
    public void onDestroy(final TileEntity callingBlock) {
        this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, Blocks.air, 0, 3);
        this.worldObj.setBlock(
            this.xCoord, this.yCoord + 1, this.zCoord, Blocks.air, 0, 3
        );
        this.worldObj.setBlock(
            this.xCoord, this.yCoord + 2, this.zCoord, Blocks.air, 0, 3
        );
    }

    @Override
    public boolean onActivated(final EntityPlayer par5EntityPlayer) {
        return false;
    }

    @Override
    public void onCreate(final Vector3 position) {
        this.worldObj.setBlock(
            position.intX(), position.intY() + 1, position.intZ(), MainBase.bJia, 0, 2
        );
        ((TileEntityMulti) this.worldObj.getTileEntity(
             position.intX(), position.intY() + 1, position.intZ()
         ))
            .setMainBlock(position);
        this.worldObj.setBlock(
            position.intX(), position.intY() + 2, position.intZ(), MainBase.bJia, 0, 2
        );
        ((TileEntityMulti) this.worldObj.getTileEntity(
             position.intX(), position.intY() + 2, position.intZ()
         ))
            .setMainBlock(position);
    }

    @Override
    public ForgeDirection
    getDirection(final IBlockAccess world, final int x, final int y, final int z) {
        return ForgeDirection.getOrientation((int) this.orientation);
    }

    @Override
    public void setDirection(
        final World world,
        final int x,
        final int y,
        final int z,
        final ForgeDirection facingDirection
    ) {
        this.orientation = (byte) facingDirection.ordinal();
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return TileEntity.INFINITE_EXTENT_AABB;
    }
}
