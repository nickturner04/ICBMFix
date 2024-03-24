package icbm.zhapin.zhapin;

import icbm.api.explosion.IExplosive;
import icbm.api.explosion.IExplosiveContainer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import universalelectricity.prefab.implement.IRotatable;

public class TExplosive extends TileEntity implements IExplosiveContainer, IRotatable {
    public boolean exploding;
    public int explosiveId;

    public TExplosive() {
        this.exploding = false;
        this.explosiveId = 0;
    }

    @Override
    public boolean canUpdate() {
        return false;
    }

    @Override
    public void readFromNBT(final NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        this.explosiveId = par1NBTTagCompound.getInteger("explosiveID");
    }

    @Override
    public void writeToNBT(final NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("explosiveID", this.explosiveId);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound nbt = pkt.func_148857_g();

        this.explosiveId = nbt.getInteger("explosiveID");
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();

        nbt.setInteger("explosiveID", this.explosiveId);

        return new S35PacketUpdateTileEntity(
            this.xCoord, this.yCoord, this.zCoord, this.getBlockMetadata(), nbt
        );
    }

    @Override
    public ForgeDirection
    getDirection(final IBlockAccess world, final int x, final int y, final int z) {
        return ForgeDirection.getOrientation(this.getBlockMetadata());
    }

    @Override
    public void setDirection(
        final World world,
        final int x,
        final int y,
        final int z,
        final ForgeDirection facingDirection
    ) {
        this.worldObj.setBlockMetadataWithNotify(
            this.xCoord, this.yCoord, this.zCoord, facingDirection.ordinal(), 2
        );
    }

    @Override
    public IExplosive getExplosiveType() {
        return ZhaPin.list[this.explosiveId];
    }
}
