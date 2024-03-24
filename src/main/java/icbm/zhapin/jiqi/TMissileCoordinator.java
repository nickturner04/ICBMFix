package icbm.zhapin.jiqi;

import calclavia.lib.TileEntityUniversalRunnable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import universalelectricity.prefab.implement.IRotatable;

public class TMissileCoordinator
    extends TileEntityUniversalRunnable implements IRotatable {
    private byte facingDirection;

    public TMissileCoordinator() {
        this.facingDirection = 3;
    }

    @Override
    public void readFromNBT(final NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        this.facingDirection = par1NBTTagCompound.getByte("facingDirection");
    }

    @Override
    public void writeToNBT(final NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setByte("facingDirection", this.facingDirection);
    }

    @Override
    public ForgeDirection
    getDirection(final IBlockAccess world, final int x, final int y, final int z) {
        return ForgeDirection.getOrientation((int) this.facingDirection);
    }

    @Override
    public void setDirection(
        final World world,
        final int x,
        final int y,
        final int z,
        final ForgeDirection facingDirection
    ) {
        this.facingDirection = (byte) facingDirection.ordinal();
    }
}
