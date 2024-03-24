package icbm.wanyi.b;

import icbm.core.di.BICBM;
import icbm.wanyi.ICBMContraption;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import universalelectricity.core.UniversalElectricity;

public class BProximityDetector extends BICBM {
    public BProximityDetector() {
        super("proximityDetector", UniversalElectricity.machine);
        super.requireSidedTextures = true;
    }

    @Override
    public IIcon getIcon(final int side, final int metadata) {
        return (side == 0) ? super.iconBottom
                           : ((side == 1) ? super.iconTop : super.iconSide);
    }

    @Override
    public boolean onMachineActivated(
        final World par1World,
        final int x,
        final int y,
        final int z,
        final EntityPlayer par5EntityPlayer,
        final int side,
        final float hitX,
        final float hitY,
        final float hitZ
    ) {
        par5EntityPlayer.openGui(
            (Object) ICBMContraption.instance, 4, par1World, x, y, z
        );
        return true;
    }

    @Override
    public boolean onUseWrench(
        final World par1World,
        final int x,
        final int y,
        final int z,
        final EntityPlayer par5EntityPlayer,
        final int side,
        final float hitX,
        final float hitY,
        final float hitZ
    ) {
        final TileEntity tileEntity = par1World.getTileEntity(x, y, z);

        if (tileEntity instanceof TProximityDetector) {
            ((TProximityDetector) tileEntity).isInverted
                = !((TProximityDetector) tileEntity).isInverted;
            return true;
        }

        return false;
    }

    @Override
    public boolean canProvidePower() {
        return true;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(final World world, int meta) {
        return new TProximityDetector();
    }
}
