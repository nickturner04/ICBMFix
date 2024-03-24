package icbm.core.di;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.core.ICBMTab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import universalelectricity.prefab.block.BlockAdvanced;
import universalelectricity.prefab.implement.IRedstoneProvider;

public abstract class BICBM extends BlockAdvanced {
    protected IIcon iconTop;
    protected IIcon iconSide;
    protected IIcon iconBottom;
    protected boolean requireSidedTextures;

    public BICBM(final String name, final Material material) {
        super(material);
        this.requireSidedTextures = false;
        this.setBlockName("icbm:" + name);
        this.setCreativeTab((CreativeTabs) ICBMTab.INSTANCE);
    }

    @Override
    public int damageDropped(final int metadata) {
        return metadata;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister iconRegister) {
        super.registerBlockIcons(iconRegister);

        if (this.requireSidedTextures) {
            this.iconTop = iconRegister.registerIcon(
                this.getUnlocalizedName().replace("tile.", "") + "_top"
            );
            this.iconSide = iconRegister.registerIcon(
                this.getUnlocalizedName().replace("tile.", "") + "_side"
            );
            this.iconBottom = iconRegister.registerIcon(
                this.getUnlocalizedName().replace("tile.", "") + "_bottom"
            );
        }
    }

    @Override
    public int isProvidingStrongPower(
        final IBlockAccess par1IBlockAccess,
        final int x,
        final int y,
        final int z,
        final int side
    ) {
        final TileEntity tileEntity = par1IBlockAccess.getTileEntity(x, y, z);

        if (tileEntity instanceof IRedstoneProvider) {
            return ((IRedstoneProvider) tileEntity)
                       .isPoweringTo(ForgeDirection.getOrientation(side))
                ? 15
                : 0;
        }

        return 0;
    }

    @Override
    public int isProvidingWeakPower(
        final IBlockAccess par1IBlockAccess,
        final int x,
        final int y,
        final int z,
        final int side
    ) {
        final TileEntity tileEntity = par1IBlockAccess.getTileEntity(x, y, z);

        if (tileEntity instanceof IRedstoneProvider) {
            return ((IRedstoneProvider) tileEntity)
                       .isIndirectlyPoweringTo(ForgeDirection.getOrientation(side))
                ? 15
                : 0;
        }

        return 0;
    }
}
