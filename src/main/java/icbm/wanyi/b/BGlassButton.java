package icbm.wanyi.b;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.core.ICBMTab;
import net.minecraft.block.BlockButton;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class BGlassButton extends BlockButton {
    public BGlassButton() {
        super(true);
        this.setTickRandomly(true);
        this.setBlockName("icbm:glassButton");
        this.setStepSound(BGlassButton.soundTypeGlass);
        this.setCreativeTab((CreativeTabs) ICBMTab.INSTANCE);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister par1IconRegister) {
        this.blockIcon
            = par1IconRegister.registerIcon(this.getUnlocalizedName().replace("tile.", "")
            );
    }

    @Override
    public int quantityDropped(final Random par1Random) {
        return 0;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }
}
