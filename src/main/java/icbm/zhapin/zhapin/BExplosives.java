package icbm.zhapin.zhapin;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.api.ICamouflageMaterial;
import icbm.core.ICBMTab;
import icbm.core.di.BICBM;
import icbm.zhapin.ICBMExplosion;
import icbm.zhapin.render.RHZhaPin;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import universalelectricity.core.vector.Vector3;
import universalelectricity.core.vector.VectorHelper;

public class BExplosives extends BICBM implements ICamouflageMaterial {
    public static final IIcon[] ICON_TOP;
    public static final IIcon[] ICON_SIDE;
    public static final IIcon[] ICON_BOTTOM;

    public BExplosives() {
        super("explosives", Material.tnt);
        this.setHardness(0.0f);
        this.setStepSound(BExplosives.soundTypeGrass);
        this.setCreativeTab((CreativeTabs) ICBMTab.INSTANCE);
    }

    private static byte determineOrientation(
        final World world,
        final int x,
        final int y,
        final int z,
        final EntityLivingBase entityLiving
    ) {
        if (entityLiving != null) {
            if (MathHelper.abs((float) ((Entity) entityLiving).posX - x) < 2.0f
                && MathHelper.abs((float) ((Entity) entityLiving).posZ - z) < 2.0f) {
                final double var5 = ((Entity) entityLiving).posY + 1.82
                    - ((Entity) entityLiving).yOffset;

                if (var5 - y > 2.0) {
                    return 1;
                }

                if (y - var5 > 0.0) {
                    return 0;
                }
            }

            final int rotation
                = MathHelper.floor_double(
                      ((Entity) entityLiving).rotationYaw * 4.0f / 360.0f + 0.5
                  )
                & 0x3;
            return (byte
            ) ((rotation == 0)
                   ? 2
                   : ((rotation == 1) ? 5
                                      : ((rotation == 2) ? 3 : ((rotation == 3) ? 4 : 0)))
            );
        }

        return 0;
    }

    @Override
    public void setBlockBoundsBasedOnState(
        final IBlockAccess par1IBlockAccess, final int x, final int y, final int z
    ) {
        final TileEntity tileEntity = par1IBlockAccess.getTileEntity(x, y, z);

        if (tileEntity != null && tileEntity instanceof TExplosive
            && ((TExplosive) tileEntity).explosiveId == ZhaPin.sMine.getID()) {
            this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.2f, 1.0f);
            return;
        }

        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public void setBlockBoundsForItemRender() {
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(
        final World par1World, final int x, final int y, final int z
    ) {
        final TileEntity tileEntity = par1World.getTileEntity(x, y, z);

        if (tileEntity != null && tileEntity instanceof TExplosive
            && ((TExplosive) tileEntity).explosiveId == ZhaPin.sMine.getID()) {
            return AxisAlignedBB.getBoundingBox(
                x + this.minX,
                y + this.minY,
                z + this.minZ,
                x + this.maxX,
                y + 0.2,
                z + this.maxZ
            );
        }

        return super.getCollisionBoundingBoxFromPool(par1World, x, y, z);
    }

    @Override
    public void onBlockPlacedBy(
        final World world,
        final int x,
        final int y,
        final int z,
        final EntityLivingBase entityLiving,
        final ItemStack itemStack
    ) {
        final int explosiveID = ((TExplosive) world.getTileEntity(x, y, z)).explosiveId;

        if (!world.isRemote
            && ICBMExplosion.shiBaoHu(
                world, new Vector3(x, y, z), ZhaPin.ZhaPinType.ZHA_DAN, explosiveID
            )) {
            this.dropBlockAsItem(world, x, y, z, explosiveID, 0);
            world.setBlock(x, y, z, Blocks.air, 0, 2);
            return;
        }

        world.setBlockMetadataWithNotify(
            x,
            y,
            z,
            VectorHelper
                .getOrientationFromSide(
                    ForgeDirection.getOrientation((int
                    ) determineOrientation(world, x, y, z, entityLiving)),
                    ForgeDirection.NORTH
                )
                .ordinal(),
            2
        );

        if (world.isBlockIndirectlyGettingPowered(x, y, z)) {
            yinZha(world, x, y, z, explosiveID, 0);
        }

        for (byte i = 0; i < 6; ++i) {
            final Vector3 position = new Vector3(x, y, z);
            position.modifyPositionFromSide(ForgeDirection.getOrientation((int) i));
            final Block block = position.getBlock((IBlockAccess) world);

            if (block == Blocks.fire || block == Blocks.flowing_lava
                || block == Blocks.lava) {
                yinZha(world, x, y, z, explosiveID, 2);
            }
        }

        if (entityLiving != null) {
            FMLLog.fine(
                entityLiving.getCommandSenderName() + " placed "
                    + ZhaPin.list[explosiveID].getExplosiveName() + " in: " + x + ", " + y
                    + ", " + z + ".",
                new Object[0]
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
        final int explosiveID
            = ((TExplosive) par1IBlockAccess.getTileEntity(x, y, z)).explosiveId;
        return this.getIcon(side, explosiveID);
    }

    @Override
    public IIcon getIcon(final int side, final int explosiveID) {
        if (side == 0) {
            return BExplosives.ICON_BOTTOM[ZhaPin.list[explosiveID].getTier() - 1];
        }

        if (side == 1) {
            return BExplosives.ICON_TOP[explosiveID];
        }

        return BExplosives.ICON_SIDE[explosiveID];
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(final IIconRegister iconRegister) {
        for (int i = 0; i < ZhaPin.E_SI_ID; ++i) {
            BExplosives.ICON_TOP[i] = iconRegister.registerIcon(
                "icbm:explosive_" + ZhaPin.list[i].getUnlocalizedName() + "_top"
            );
            BExplosives.ICON_SIDE[i] = iconRegister.registerIcon(
                "icbm:explosive_" + ZhaPin.list[i].getUnlocalizedName() + "_side"
            );
        }

        for (int tier = 0; tier < 4; ++tier) {
            BExplosives.ICON_BOTTOM[tier]
                = iconRegister.registerIcon("icbm:explosive_bottom_" + (tier + 1));
        }
    }

    @Override
    public void
    onBlockAdded(final World par1World, final int x, final int y, final int z) {
        super.onBlockAdded(par1World, x, y, z);
        par1World.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
    }

    @Override
    public void onNeighborBlockChange(
        final World world, final int x, final int y, final int z, final Block block
    ) {
        final int explosiveID = ((TExplosive) world.getTileEntity(x, y, z)).explosiveId;

        if (world.isBlockIndirectlyGettingPowered(x, y, z)) {
            yinZha(world, x, y, z, explosiveID, 0);
        } else if (block == Blocks.fire || block == Blocks.flowing_lava || block == Blocks.lava) {
            yinZha(world, x, y, z, explosiveID, 2);
        }
    }

    public static void yinZha(
        final World world,
        final int x,
        final int y,
        final int z,
        final int explosiveID,
        final int causeOfExplosion
    ) {
        if (!world.isRemote
            && !ICBMExplosion.shiBaoHu(
                world, new Vector3(x, y, z), ZhaPin.ZhaPinType.ZHA_DAN, explosiveID
            )) {
            final TileEntity tileEntity = world.getTileEntity(x, y, z);

            if (tileEntity != null && tileEntity instanceof TExplosive) {
                ((TExplosive) tileEntity).exploding = true;
                ZhaPin.list[explosiveID].spawnZhaDan(
                    world,
                    new Vector3(x, y, z),
                    ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z)),
                    (byte) causeOfExplosion
                );
                world.setBlock(x, y, z, Blocks.air, 0, 2);
            }
        }
    }

    @Override
    public void onBlockDestroyedByExplosion(
        final World par1World,
        final int x,
        final int y,
        final int z,
        final Explosion explosion
    ) {
        if (par1World.getTileEntity(x, y, z) != null) {
            final int explosiveID
                = ((TExplosive) par1World.getTileEntity(x, y, z)).explosiveId;
            yinZha(par1World, x, y, z, explosiveID, 1);
        }
    }

    @Override
    public boolean onBlockActivated(
        final World par1World,
        final int x,
        final int y,
        final int z,
        final EntityPlayer par5EntityPlayer,
        final int par6,
        final float par7,
        final float par8,
        final float par9
    ) {
        final TileEntity tileEntity = par1World.getTileEntity(x, y, z);

        if (par5EntityPlayer.getCurrentEquippedItem() != null) {
            if (par5EntityPlayer.getCurrentEquippedItem().getItem()
                == Items.flint_and_steel) {
                final int explosiveID = ((TExplosive) tileEntity).explosiveId;
                yinZha(par1World, x, y, z, explosiveID, 0);
                return true;
            }

            if (this.isUsableWrench(
                    par5EntityPlayer, par5EntityPlayer.getCurrentEquippedItem(), x, y, z
                )) {
                byte change = 3;

                switch (par1World.getBlockMetadata(x, y, z)) {
                    case 0: {
                        change = 2;
                        break;
                    }

                    case 2: {
                        change = 5;
                        break;
                    }

                    case 5: {
                        change = 3;
                        break;
                    }

                    case 3: {
                        change = 4;
                        break;
                    }

                    case 4: {
                        change = 1;
                        break;
                    }

                    case 1: {
                        change = 0;
                        break;
                    }
                }

                par1World.setBlockMetadataWithNotify(
                    x, y, z, ForgeDirection.getOrientation((int) change).ordinal(), 3
                );
                par1World.notifyBlockChange(x, y, z, this);
                return true;
            }
        }

        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderType() {
        return RHZhaPin.ID;
    }

    @Override
    public ItemStack getPickBlock(
        final MovingObjectPosition target,
        final World world,
        final int x,
        final int y,
        final int z
    ) {
        if (world.getTileEntity(x, y, z) != null) {
            final int explosiveID
                = ((TExplosive) world.getTileEntity(x, y, z)).explosiveId;
            return new ItemStack(this, 1, explosiveID);
        }

        return null;
    }

    @Override
    public void breakBlock(
        final World world,
        final int x,
        final int y,
        final int z,
        final Block par5,
        final int par6
    ) {
        final TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (tileEntity != null && tileEntity instanceof TExplosive
            && !((TExplosive) tileEntity).exploding) {
            final int explosiveID = ((TExplosive) tileEntity).explosiveId;
            final Item id
                = this.getItemDropped(world.getBlockMetadata(x, y, z), world.rand, 0);
            this.dropBlockAsItem(world, x, y, z, new ItemStack(id, 1, explosiveID));
        }

        super.breakBlock(world, x, y, z, par5, par6);
    }

    @Override
    public int quantityDropped(final Random par1Random) {
        return 0;
    }

    @Override
    public void getSubBlocks(
        final Item par1, final CreativeTabs par2CreativeTabs, final List par3List
    ) {
        for (int i = 0; i < ZhaPin.E_SI_ID; ++i) {
            par3List.add(new ItemStack((Block) this, 1, i));
        }

        par3List.add(new ItemStack((Block) this, 1, ZhaPin.sMine.getID()));
    }

    @Override
    public TileEntity createNewTileEntity(final World var1, int meta) {
        return new TExplosive();
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    static {
        ICON_TOP = new IIcon[ZhaPin.list.length];
        ICON_SIDE = new IIcon[ZhaPin.list.length];
        ICON_BOTTOM = new IIcon[4];
    }
}
