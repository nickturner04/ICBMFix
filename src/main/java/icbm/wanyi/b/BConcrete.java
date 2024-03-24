package icbm.wanyi.b;

import java.util.List;

import atomicscience.api.IAntiPoisonBlock;
import atomicscience.api.poison.Poison;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.core.di.BICBM;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BConcrete extends BICBM implements IAntiPoisonBlock {
    private IIcon iconCompact;
    private IIcon iconReinforced;

    public BConcrete() {
        super("concrete", Material.rock);
        this.setBlockTextureName("icbm:concrete");
        this.setHardness(3.8f);
        this.setResistance(50.0f);
        this.setStepSound(BConcrete.soundTypeMetal);
    }

    @Override
    public IIcon getIcon(final int side, final int metadata) {
        switch (metadata) {
            case 1: {
                return this.iconCompact;
            }

            case 2: {
                return this.iconReinforced;
            }

            default: {
                return this.blockIcon;
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister iconRegister) {
        super.registerBlockIcons(iconRegister);
        this.iconCompact = iconRegister.registerIcon(
            this.getUnlocalizedName().replace("tile.", "") + "Compact"
        );
        this.iconReinforced = iconRegister.registerIcon(
            this.getUnlocalizedName().replace("tile.", "") + "Reinforced"
        );
    }

    @Override
    public float getExplosionResistance(
        final Entity par1Entity,
        final World world,
        final int x,
        final int y,
        final int z,
        final double explosionX,
        final double explosionY,
        final double explosionZ
    ) {
        final int metadata = world.getBlockMetadata(x, y, z);

        switch (metadata) {
            case 1: {
                return 38.0f;
            }

            case 2: {
                return 48.0f;
            }

            default: {
                return this.getExplosionResistance(par1Entity);
            }
        }
    }

    @Override
    public void getSubBlocks(
        final Item par1, final CreativeTabs par2CreativeTabs, final List par3List
    ) {
        for (int i = 0; i < 3; ++i) {
            par3List.add(new ItemStack(par1, 1, i));
        }
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
