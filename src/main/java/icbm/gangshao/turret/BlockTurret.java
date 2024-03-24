package icbm.gangshao.turret;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.core.ICBMTab;
import icbm.core.di.BICBM;
import icbm.gangshao.ICBMSentry;
import icbm.gangshao.damage.EntityTileDamagable;
import icbm.gangshao.render.BlockRenderingHandler;
import icbm.gangshao.turret.mount.TRailgunTurret;
import icbm.gangshao.turret.sentries.TAATurret;
import icbm.gangshao.turret.sentries.TLaserTurret;
import icbm.gangshao.turret.sentries.TMachineGunTurret;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import universalelectricity.core.UniversalElectricity;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.block.BlockAdvanced;
import universalelectricity.prefab.implement.IRedstoneReceptor;
import universalelectricity.prefab.implement.IRotatable;
import universalelectricity.prefab.multiblock.IBlockActivate;
import universalelectricity.prefab.multiblock.IMultiBlock;

public class BlockTurret extends BICBM {
    public BlockTurret() {
        super("turret", UniversalElectricity.machine);
        this.setCreativeTab((CreativeTabs) ICBMTab.INSTANCE);
        this.setHardness(100.0f);
        this.setResistance(50.0f);
    }

    @Override
    public void setBlockBoundsBasedOnState(
        final IBlockAccess world, final int x, final int y, final int z
    ) {
        final TileEntity ent = world.getTileEntity(x, y, z);

        if (ent instanceof TTurretBase) {
            final EntityTileDamagable dEnt = ((TTurretBase) ent).getDamageEntity();

            if (dEnt != null) {
                this.setBlockBounds(0.2f, 0.0f, 0.2f, 0.8f, 0.4f, 0.8f);
            } else {
                this.setBlockBounds(0.2f, 0.0f, 0.2f, 0.8f, 0.8f, 0.8f);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(final IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon("icbm:machine");
    }

    @Override
    public void onBlockPlacedBy(
        final World world,
        final int x,
        final int y,
        final int z,
        final EntityLivingBase par5EntityLiving,
        final ItemStack itemStack
    ) {
        final int angle
            = MathHelper.floor_double(
                  ((Entity) par5EntityLiving).rotationYaw * 4.0f / 360.0f + 0.5
              )
            & 0x3;
        final TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (tileEntity instanceof IRotatable) {
            final IRotatable rotatableEntity = (IRotatable) tileEntity;

            switch (angle) {
                case 0: {
                    rotatableEntity.setDirection(
                        world, x, y, z, ForgeDirection.getOrientation(3)
                    );
                    break;
                }

                case 1: {
                    rotatableEntity.setDirection(
                        world, x, y, z, ForgeDirection.getOrientation(4)
                    );
                    break;
                }

                case 2: {
                    rotatableEntity.setDirection(
                        world, x, y, z, ForgeDirection.getOrientation(2)
                    );
                    break;
                }

                case 3: {
                    rotatableEntity.setDirection(
                        world, x, y, z, ForgeDirection.getOrientation(5)
                    );
                    break;
                }
            }
        }

        if (tileEntity instanceof IMultiBlock) {
            ((IMultiBlock) tileEntity).onCreate(new Vector3(x, y, z));
        }
    }

    @Override
    public boolean onUseWrench(
        final World world,
        final int x,
        final int y,
        final int z,
        final EntityPlayer entityPlayer,
        final int side,
        final float hitX,
        final float hitY,
        final float hitZ
    ) {
        final TileEntity ent = world.getTileEntity(x, y, z);

        if (ent instanceof TTurretBase) {
            final Random random = new Random();
            ((TTurretBase) ent).setHealth(5 + random.nextInt(7), true);
            return true;
        }

        return false;
    }

    @Override
    public boolean onMachineActivated(
        final World world,
        final int x,
        final int y,
        final int z,
        final EntityPlayer entityPlayer,
        final int side,
        final float hitX,
        final float hitY,
        final float hitZ
    ) {
        final TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (tileEntity instanceof IBlockActivate) {
            return ((IBlockActivate) tileEntity).onActivated(entityPlayer);
        }

        final Block block = world.getBlock(x, y - 1, z);
        return block instanceof BlockAdvanced
            && ((BlockAdvanced) block)
                   .onMachineActivated(
                       world, x, y - 1, z, entityPlayer, side, hitX, hitY, hitZ
                   );
    }

    @Override
    public void onNeighborBlockChange(
        final World world, final int x, final int y, final int z, Block block
    ) {
        final TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (tileEntity instanceof TTurretBase) {
            if (this.canBlockStay(world, x, y, z)) {
                if (tileEntity instanceof IRedstoneReceptor) {
                    if (world.isBlockIndirectlyGettingPowered(x, y, z)) {
                        ((IRedstoneReceptor) tileEntity).onPowerOn();
                    } else {
                        ((IRedstoneReceptor) tileEntity).onPowerOff();
                    }
                }
            } else if (tileEntity != null) {
                ((TTurretBase) tileEntity).destroy(false);
            }
        }
    }

    @Override
    public void breakBlock(
        final World par1World,
        final int x,
        final int y,
        final int z,
        final Block par5,
        final int par6
    ) {
        final TileEntity tileEntity = par1World.getTileEntity(x, y, z);

        if (tileEntity instanceof IMultiBlock) {
            ((IMultiBlock) tileEntity).onDestroy(tileEntity);
        }

        super.breakBlock(par1World, x, y, z, par5, par6);
    }

    @Override
    public TileEntity createNewTileEntity(final World world, final int meta) {
        if (meta < TurretType.values().length) {
            try {
                return TurretType.values()[meta].tileEntity.newInstance();
            } catch (final Exception e) {
                e.printStackTrace();
            }
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
    public int damageDropped(final int metadata) {
        return metadata;
    }

    @Override
    public boolean
    canPlaceBlockAt(final World world, final int x, final int y, final int z) {
        return super.canPlaceBlockAt(world, x, y, z) && this.canBlockStay(world, x, y, z);
    }

    @Override
    public boolean
    canBlockStay(final World world, final int x, final int y, final int z) {
        return world.getBlock(x, y - 1, z) == ICBMSentry.blockPlatform;
    }

    @Override
    public void
    getSubBlocks(final Item par1, final CreativeTabs par2CreativeTabs, final List list) {
        for (int i = 0; i < TurretType.values().length; ++i) {
            list.add(new ItemStack(par1, 1, i));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderType() {
        return BlockRenderingHandler.ID;
    }

    public enum TurretType {
        GUN(TMachineGunTurret.class),
        RAILGUN(TRailgunTurret.class),
        AA(TAATurret.class),
        LASER(TLaserTurret.class);

        public Class<? extends TileEntity> tileEntity;

        private TurretType(final Class<? extends TileEntity> tile) {
            this.tileEntity = tile;
        }
    }
}
