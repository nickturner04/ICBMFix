package icbm.wanyi.b;

import icbm.api.ICamouflageMaterial;
import icbm.api.explosion.IEMPBlock;
import icbm.api.explosion.IExplosive;
import icbm.core.ICBMTab;
import icbm.core.di.BICBM;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import universalelectricity.core.vector.Vector3;

public class BCamouflage extends BICBM implements IEMPBlock {
    public BCamouflage() {
        super("camouflage", Material.cloth);
        this.setHardness(0.3f);
        this.setResistance(1.0f);
        this.setStepSound(Block.soundTypeCloth);
        this.setCreativeTab((CreativeTabs) ICBMTab.INSTANCE);
    }

    @Override
    public void
    onEMP(final World world, final Vector3 position, final IExplosive empExplosive) {
        final TileEntity tileEntity = position.getTileEntity((IBlockAccess) world);

        if (tileEntity instanceof TCamouflage) {
            ((TCamouflage) tileEntity).setCamoBlock(Blocks.air, 0);
            ((TCamouflage) tileEntity).setTransparent(false);
            world.markBlockRangeForRenderUpdate(
                position.intX(),
                position.intY(),
                position.intZ(),
                position.intX(),
                position.intY(),
                position.intZ()
            );
        }
    }

    @Override
    public IIcon getIcon(
        final IBlockAccess par1IBlockAccess,
        final int x,
        final int y,
        final int z,
        final int side
    ) {
        final TileEntity t = par1IBlockAccess.getTileEntity(x, y, z);

        if (t != null && t instanceof TCamouflage) {
            final TCamouflage tileEntity = (TCamouflage) t;

            if (tileEntity.getTransparent(ForgeDirection.getOrientation(side))) {
                return Blocks.glass.getBlockTextureFromSide(side);
            }

            final Block block = tileEntity.getCamoBlock();

            if (block != null) {
                try {
                    final IIcon blockIcon = tileEntity.getCamoBlock().getIcon(
                        side, tileEntity.getJiaMetadata()
                    );

                    if (blockIcon != null) {
                        return blockIcon;
                    }
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return this.blockIcon;
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
        try {
            if (par5EntityPlayer.getCurrentEquippedItem() != null) {
                final Item item = par5EntityPlayer.getCurrentEquippedItem().getItem();
                final Block block = Block.getBlockFromItem(item);

                if (block != Blocks.air && block != this
                    && (block instanceof ICamouflageMaterial
                        || (block.isNormalCube()
                            && (block.getRenderType() == 0 || block.getRenderType() == 31)
                        ))) {
                    ((TCamouflage) par1World.getTileEntity(x, y, z))
                        .setCamoBlock(
                            block,
                            par5EntityPlayer.getCurrentEquippedItem().getItemDamage()
                        );
                    par1World.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
                    return true;
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return false;
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
        final TileEntity t = par1World.getTileEntity(x, y, z);

        if (t != null && t instanceof TCamouflage) {
            ((TCamouflage) par1World.getTileEntity(x, y, z))
                .setTransparent(ForgeDirection.getOrientation(side));
            par1World.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
        }

        return true;
    }

    @Override
    public boolean onSneakUseWrench(
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
        final TileEntity t = par1World.getTileEntity(x, y, z);

        if (t != null && t instanceof TCamouflage) {
            ((TCamouflage) par1World.getTileEntity(x, y, z)).setSolid();
        }

        return true;
    }

    @Override
    public int colorMultiplier(
        final IBlockAccess par1IBlockAccess, final int x, final int y, final int z
    ) {
        try {
            final TileEntity tileEntity = par1IBlockAccess.getTileEntity(x, y, z);

            if (tileEntity instanceof TCamouflage) {
                final Block block = ((TCamouflage) tileEntity).getCamoBlock();

                if (block != null) {
                    return block.colorMultiplier(par1IBlockAccess, x, y, x);
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return 16777215;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(
        final World par1World, final int x, final int y, final int z
    ) {
        final TileEntity t = par1World.getTileEntity(x, y, z);

        if (t != null && t instanceof TCamouflage && ((TCamouflage) t).getSolid()) {
            return super.getCollisionBoundingBoxFromPool(par1World, x, y, z);
        }

        return null;
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
    public boolean shouldSideBeRendered(
        final IBlockAccess par1IBlockAccess,
        final int par2,
        final int par3,
        final int par4,
        final int par5
    ) {
        final Block var6 = par1IBlockAccess.getBlock(par2, par3, par4);
        return var6 != this
            && super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
    }

    @Override
    public TileEntity createNewTileEntity(final World var1, int meta) {
        return new TCamouflage();
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon("icbm:camouflage");
    }
}
