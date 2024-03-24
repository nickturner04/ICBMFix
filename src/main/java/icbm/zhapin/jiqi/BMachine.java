package icbm.zhapin.jiqi;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.core.di.BICBM;
import icbm.zhapin.ICBMExplosion;
import icbm.zhapin.render.RHJiQi;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import universalelectricity.core.UniversalElectricity;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.TranslationHelper;
import universalelectricity.prefab.implement.IRedstoneReceptor;
import universalelectricity.prefab.implement.IRotatable;
import universalelectricity.prefab.implement.ITier;
import universalelectricity.prefab.multiblock.IBlockActivate;
import universalelectricity.prefab.multiblock.IMultiBlock;

public class BMachine extends BICBM {
    public BMachine() {
        super("machine", UniversalElectricity.machine);
    }

    @Override
    public boolean canProvidePower() {
        return true;
    }

    @Override
    public void
    onBlockAdded(final World par1World, final int x, final int y, final int z) {
        this.isBeingPowered(par1World, x, y, z);
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

    public static boolean canBePlacedAt(
        final World world,
        final int x,
        final int y,
        final int z,
        final int metadata,
        final int direction
    ) {
        switch (metadata) {
            case 0: {
                if (direction == 0 || direction == 2) {
                    return world.getBlock(x, y, z) == Blocks.air
                        && world.getBlock(x + 1, y, z) == Blocks.air
                        && world.getBlock(x + 1, y + 1, z) == Blocks.air
                        && world.getBlock(x + 1, y + 2, z) == Blocks.air
                        && world.getBlock(x - 1, y, z) == Blocks.air
                        && world.getBlock(x - 1, y + 1, z) == Blocks.air
                        && world.getBlock(x - 1, y + 2, z) == Blocks.air;
                }

                if (direction == 1 || direction == 3) {
                    return world.getBlock(x, y, z) == Blocks.air
                        && world.getBlock(x, y, z + 1) == Blocks.air
                        && world.getBlock(x, y + 1, z + 1) == Blocks.air
                        && world.getBlock(x, y + 2, z + 1) == Blocks.air
                        && world.getBlock(x, y, z - 1) == Blocks.air
                        && world.getBlock(x, y + 1, z - 1) == Blocks.air
                        && world.getBlock(x, y + 2, z - 1) == Blocks.air;
                }

                return world.getBlock(x, y - 1, z).getMaterial().isSolid()
                    && world.getBlock(x, y, z) == Blocks.air
                    && world.getBlock(x, y + 1, z) == Blocks.air
                    && world.getBlock(x, y + 2, z) == Blocks.air;
            }

            case 2: {
                return world.getBlock(x, y - 1, z).getMaterial().isSolid()
                    && world.getBlock(x, y, z) == Blocks.air
                    && world.getBlock(x, y + 1, z) == Blocks.air
                    && world.getBlock(x, y + 2, z) == Blocks.air;
            }

            case 3: {
                return world.getBlock(x, y - 1, z).getMaterial().isSolid()
                    && world.getBlock(x, y, z) == Blocks.air
                    && world.getBlock(x, y + 1, z) == Blocks.air
                    && world.getBlock(x + 1, y + 1, z) == Blocks.air
                    && world.getBlock(x - 1, y + 1, z) == Blocks.air
                    && world.getBlock(x, y + 1, z + 1) == Blocks.air
                    && world.getBlock(x, y + 1, z - 1) == Blocks.air
                    && world.getBlock(x + 1, y + 1, z + 1) == Blocks.air
                    && world.getBlock(x - 1, y + 1, z - 1) == Blocks.air
                    && world.getBlock(x + 1, y + 1, z - 1) == Blocks.air
                    && world.getBlock(x - 1, y + 1, z + 1) == Blocks.air;
            }

            case 4: {
                return world.getBlock(x, y, z) == Blocks.air
                    && world.getBlock(x, y + 1, z) == Blocks.air;
            }

            default: {
                return world.getBlock(x, y - 1, z).getMaterial().isSolid();
            }
        }
    }

    @Override
    public boolean
    canBlockStay(final World world, final int x, final int y, final int z) {
        int direction = 0;
        final TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (tileEntity instanceof IRotatable) {
            direction = ((IRotatable) tileEntity)
                            .getDirection((IBlockAccess) world, x, y, z)
                            .ordinal();
        }

        return canBePlacedAt(world, x, y, z, world.getBlockMetadata(x, y, z), direction);
    }

    @Override
    public void onNeighborBlockChange(
        final World par1World, final int x, final int y, final int z, final Block par5
    ) {
        this.isBeingPowered(par1World, x, y, z);
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
        if (par5EntityPlayer.inventory.getCurrentItem() != null) {
            if (par5EntityPlayer.inventory.getCurrentItem().getItem()
                == ICBMExplosion.itLeiSheZhiBiao) {
                return false;
            }

            if (par5EntityPlayer.inventory.getCurrentItem().getItem()
                == ICBMExplosion.itLeiDaQiang) {
                return false;
            }
        }

        final TileEntity tileEntity = par1World.getTileEntity(x, y, z);
        return tileEntity != null && tileEntity instanceof IBlockActivate
            && ((IBlockActivate) tileEntity).onActivated(par5EntityPlayer);
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
        return this.onMachineActivated(
            par1World, x, y, z, par5EntityPlayer, side, hitX, hitY, hitZ
        );
    }

    public void
    isBeingPowered(final World par1World, final int x, final int y, final int z) {
        final TileEntity tileEntity = par1World.getTileEntity(x, y, z);

        if (tileEntity instanceof IRedstoneReceptor) {
            if (par1World.isBlockIndirectlyGettingPowered(x, y, z)) {
                ((IRedstoneReceptor) tileEntity).onPowerOn();
            } else {
                ((IRedstoneReceptor) tileEntity).onPowerOff();
            }
        }
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
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

        if (tileEntity != null) {
            this.dropBlockAsItem(
                par1World,
                x,
                y,
                z,
                new ItemStack(ICBMExplosion.bMachine, 1, getMachineId(tileEntity))
            );

            if (tileEntity instanceof IMultiBlock) {
                ((IMultiBlock) tileEntity).onDestroy(tileEntity);
            }
        }

        super.breakBlock(par1World, x, y, z, par5, par6);
    }

    @Override
    public TileEntity createNewTileEntity(final World var1, final int metadata) {
        if (JiQi.get(metadata) != null) {
            try {
                return JiQi.get(metadata).tileEntity.newInstance();
            } catch (final InstantiationException e) {
                e.printStackTrace();
            } catch (final IllegalAccessException e2) {
                e2.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public int quantityDropped(final Random par1Random) {
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderType() {
        return RHJiQi.ID;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public void getSubBlocks(
        final Item par1, final CreativeTabs par2CreativeTabs, final List par3List
    ) {
        for (int i = 0; i < JiQi.values().length + 6; ++i) {
            par3List.add(new ItemStack((Block) this, 1, i));
        }
    }

    @Override
    public ItemStack getPickBlock(
        final MovingObjectPosition target,
        final World par1World,
        final int x,
        final int y,
        final int z
    ) {
        final TileEntity tileEntity = par1World.getTileEntity(x, y, z);
        return new ItemStack(ICBMExplosion.bMachine, 1, getMachineId(tileEntity));
    }

    @Override
    public int damageDropped(final int metadata) {
        return metadata;
    }

    public static int getMachineId(final TileEntity tileEntity) {
        int itemMetadata = 0;

        if (tileEntity != null) {
            final int metadata = tileEntity.getBlockMetadata();

            if (tileEntity instanceof ITier) {
                itemMetadata = ((ITier) tileEntity).getTier() + metadata * 3;
            } else {
                itemMetadata = 9 + metadata - 3;
            }
        }

        return itemMetadata;
    }

    public static String getMachineName(final TileEntity tileEntity) {
        return TranslationHelper.getLocal(
            "icbm.machine." + getMachineId(tileEntity) + ".name"
        );
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {}

    public enum JiQi {
        FaSheDi("FaSheDi", 0, TLauncherPlatform.class),
        FaSheShiMuo("FaSheShiMuo", 1, TLauncherControlPanel.class),
        FaSheJia("FaSheJia", 2, TLauncher.class),
        LeiDaTai("LeiDaTai", 3, TRadarTower.class),
        DianCiQi("DianCiQi", 4, TEmpTower.class),
        XiaoFaSheQi("XiaoFaSheQi", 5, TCruiseLauncher.class),
        YinDaoQi("YinDaoQi", 6, TMissileCoordinator.class);

        public Class<? extends TileEntity> tileEntity;

        private JiQi(
            final String name,
            final int ordinal,
            final Class<? extends TileEntity> tileEntity
        ) {
            this.tileEntity = tileEntity;
        }

        public static JiQi get(final int id) {
            if (id < values().length && id >= 0) {
                return values()[id];
            }

            return null;
        }
    }
}
