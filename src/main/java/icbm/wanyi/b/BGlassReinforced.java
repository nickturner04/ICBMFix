package icbm.wanyi.b;

import atomicscience.api.IAntiPoisonBlock;
import atomicscience.api.poison.Poison;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.core.ICBMTab;
import icbm.core.di.BICBM;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BGlassReinforced extends BICBM implements IAntiPoisonBlock {
    public BGlassReinforced() {
        super("glassReinforced", Material.glass);
        this.setBlockTextureName("icbm:glassReinforced");
        this.setResistance(48.0f);
        this.setCreativeTab((CreativeTabs) ICBMTab.INSTANCE);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass() {
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(
        final IBlockAccess par1IBlockAccess,
        final int par2,
        final int par3,
        final int par4,
        final int par5
    ) {
        final Block i1 = par1IBlockAccess.getBlock(par2, par3, par4);
        return i1 != this
            && super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    protected boolean canSilkHarvest() {
        return true;
    }

    @Override
    public boolean isPoisonPrevention(
        final World par1World, final int x, final int y, final int z, final Poison type
    ) {
        return true;
    }

    @Override
    public boolean hasTileEntity(final int metadata) {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return null;
    }
}
