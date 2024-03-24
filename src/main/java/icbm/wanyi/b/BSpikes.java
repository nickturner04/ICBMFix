package icbm.wanyi.b;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.core.ICBMTab;
import icbm.core.di.BICBM;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BSpikes extends BICBM {
    private IIcon iconPoison;
    private IIcon iconFlammable;

    public BSpikes() {
        super("spikes", Material.cactus);
        this.setBlockTextureName("icbm:spikes");
        this.setHardness(1.0f);
        this.setCreativeTab((CreativeTabs) ICBMTab.INSTANCE);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister iconRegister) {
        super.registerBlockIcons(iconRegister);
        this.iconPoison = iconRegister.registerIcon(
            this.getUnlocalizedName().replace("tile.", "") + "Poison"
        );
        this.iconFlammable = iconRegister.registerIcon(
            this.getUnlocalizedName().replace("tile.", "") + "Flammable"
        );
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(
        final World par1World, final int x, final int y, final int z
    ) {
        return AxisAlignedBB.getBoundingBox(
            (double) x,
            (double) y,
            (double) z,
            (double) (x + 1),
            (double) (y + 0.5f),
            (double) (z + 1)
        );
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(
        final World par1World, final int par2, final int par3, final int par4
    ) {
        return null;
    }

    @Override
    public IIcon getIcon(final int par1, final int metadata) {
        if (metadata == 2) {
            return this.iconFlammable;
        }

        if (metadata == 1) {
            return this.iconPoison;
        }

        return this.blockIcon;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getRenderType() {
        return 1;
    }

    @Override
    public int getMobilityFlag() {
        return 0;
    }

    @Override
    public boolean canPlaceBlockAt(
        final World par1World, final int par2, final int par3, final int par4
    ) {
        return this.canBlockStay(par1World, par2, par3, par4);
    }

    @Override
    public void onNeighborBlockChange(
        final World par1World,
        final int par2,
        final int par3,
        final int par4,
        final Block par5
    ) {
        if (!this.canBlockStay(par1World, par2, par3, par4)) {
            this.dropBlockAsItem(
                par1World,
                par2,
                par3,
                par4,
                par1World.getBlockMetadata(par2, par3, par4),
                0
            );
            par1World.setBlock(par2, par3, par4, Blocks.air, 0, 2);
        }
    }

    @Override
    public boolean
    canBlockStay(final World par1World, final int par2, final int par3, final int par4) {
        return par1World.isSideSolid(
                   par2 - 1, par3, par4, ForgeDirection.getOrientation(5)
               )
            || par1World.isSideSolid(
                par2 + 1, par3, par4, ForgeDirection.getOrientation(4)
            )
            || par1World.isSideSolid(
                par2, par3, par4 - 1, ForgeDirection.getOrientation(3)
            )
            || par1World.isSideSolid(
                par2, par3, par4 + 1, ForgeDirection.getOrientation(2)
            )
            || par1World.isSideSolid(
                par2, par3 + 1, par4, ForgeDirection.getOrientation(1)
            )
            || par1World.isSideSolid(
                par2, par3 - 1, par4, ForgeDirection.getOrientation(0)
            )
            || par1World.getBlock(par2 - 1, par3, par4) == Blocks.piston_head
            || par1World.getBlock(par2 + 1, par3, par4) == Blocks.piston_head
            || par1World.getBlock(par2, par3, par4 - 1) == Blocks.piston_head
            || par1World.getBlock(par2, par3, par4 + 1) == Blocks.piston_head
            || par1World.getBlock(par2, par3 + 1, par4) == Blocks.piston_head
            || par1World.getBlock(par2, par3 - 1, par4) == Blocks.piston_head
            || par1World.getBlock(par2 - 1, par3, par4) == Blocks.piston
            || par1World.getBlock(par2 + 1, par3, par4) == Blocks.piston
            || par1World.getBlock(par2, par3, par4 - 1) == Blocks.piston
            || par1World.getBlock(par2, par3, par4 + 1) == Blocks.piston
            || par1World.getBlock(par2, par3 + 1, par4) == Blocks.piston
            || par1World.getBlock(par2, par3 - 1, par4) == Blocks.piston
            || par1World.getBlock(par2 - 1, par3, par4) == Blocks.sticky_piston
            || par1World.getBlock(par2 + 1, par3, par4) == Blocks.sticky_piston
            || par1World.getBlock(par2, par3, par4 - 1) == Blocks.sticky_piston
            || par1World.getBlock(par2, par3, par4 + 1) == Blocks.sticky_piston
            || par1World.getBlock(par2, par3 + 1, par4) == Blocks.sticky_piston
            || par1World.getBlock(par2, par3 - 1, par4) == Blocks.sticky_piston;
    }

    @Override
    public void onEntityCollidedWithBlock(
        final World par1World,
        final int par2,
        final int par3,
        final int par4,
        final Entity par5Entity
    ) {
        if (par5Entity instanceof EntityLiving) {
            par5Entity.attackEntityFrom(DamageSource.cactus, 1);

            if (par1World.getBlockMetadata(par2, par3, par4) == 1) {
                ((EntityLiving) par5Entity)
                    .addPotionEffect(new PotionEffect(Potion.poison.id, 140, 0));
            } else if (par1World.getBlockMetadata(par2, par3, par4) == 2) {
                ((EntityLiving) par5Entity).setFire(7);
            }
        }
    }

    @Override
    public void getSubBlocks(
        final Item par1, final CreativeTabs par2CreativeTabs, final List par3List
    ) {
        for (int i = 0; i < 3; ++i) {
            par3List.add(new ItemStack((Block) this, 1, i));
        }
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return null;
    }
}
