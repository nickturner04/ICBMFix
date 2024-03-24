package icbm.core;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BSulfurOre extends Block {
    public BSulfurOre() {
        super(Material.rock);
        this.setBlockName("icbm:oreSulfur");
        this.setHardness(3.0f);
        this.setCreativeTab((CreativeTabs) ICBMTab.INSTANCE);
    }

    @Override
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        this.blockIcon = p_149651_1_.registerIcon("icbm:oreSulfur");
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return MainBase.itSulfur;
    }

    @Override
    public int quantityDropped(final Random par1Random) {
        return 3 + par1Random.nextInt(3);
    }

    @Override
    public int quantityDroppedWithBonus(final int par1, final Random par2Random) {
        return this.quantityDropped(par2Random) + par2Random.nextInt(par1 + 1);
    }
}
