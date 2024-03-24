package icbm.wanyi.b;

import java.util.Random;

import icbm.core.ICBMTab;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

public class BGlassPressurePlate extends BlockPressurePlate {
    public BGlassPressurePlate() {
        super(
            "icbm:glassPressurePlate",
            Material.glass,
            BlockPressurePlate.Sensitivity.everything
        );
        this.setTickRandomly(true);
        this.setResistance(1.0f);
        this.setHardness(0.3f);
        this.setStepSound(BGlassPressurePlate.soundTypeGlass);
        this.setBlockName("icbm:glassPressurePlate");
        this.setCreativeTab((CreativeTabs) ICBMTab.INSTANCE);
    }

    @Override
    protected void func_150062_a(
        final World par1World,
        final int par2,
        final int par3,
        final int par4,
        final int par5
    ) {
        super.func_150062_a(par1World, par2, par3, par4, par5);
        final int i1 = this.func_150065_e(par1World, par2, par3, par4);
        final boolean flag2 = i1 > 0;

        if (par5 != i1) {
            par1World.setBlockMetadataWithNotify(
                par2, par3, par4, this.func_150066_d(i1), 2
            );
            par1World.markBlockRangeForRenderUpdate(par2, par3, par4, par2, par3, par4);
        }

        if (flag2) {
            par1World.scheduleBlockUpdate(
                par2, par3, par4, this, this.tickRate(par1World)
            );
        }
    }

    @Override
    public int tickRate(final World world) {
        return 10;
    }

    @Override
    public int quantityDropped(final Random par1Random) {
        return 0;
    }

    @Override
    public int getRenderBlockPass() {
        return 1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getMobilityFlag() {
        return 1;
    }
}
