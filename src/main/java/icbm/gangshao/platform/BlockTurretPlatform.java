package icbm.gangshao.platform;

import java.util.Random;

import icbm.core.ICBMTab;
import icbm.core.di.BICBM;
import icbm.gangshao.IAmmunition;
import icbm.gangshao.ICBMSentry;
import icbm.gangshao.ISpecialAccess;
import icbm.gangshao.access.AccessLevel;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import universalelectricity.core.UniversalElectricity;

public class BlockTurretPlatform extends BICBM {
    public BlockTurretPlatform() {
        super("turretPlatform", UniversalElectricity.machine);
        this.setHardness(100.0f);
        this.setResistance(50.0f);
        this.setCreativeTab((CreativeTabs) ICBMTab.INSTANCE);
        super.requireSidedTextures = true;
    }

    @Override
    public IIcon getIcon(final int side, final int metadata) {
        return (side == 0) ? super.iconBottom
                           : ((side == 1) ? super.iconTop : super.iconSide);
    }

    @Override
    public void onBlockPlacedBy(
        final World world,
        final int x,
        final int y,
        final int z,
        final EntityLivingBase entity,
        final ItemStack itemStack
    ) {
        if (entity instanceof EntityPlayer && !world.isRemote) {
            final TileEntity ent = world.getTileEntity(x, y, z);

            if (ent instanceof ISpecialAccess) {
                ((ISpecialAccess) ent)
                    .addUserAccess(
                        ((EntityPlayer) entity).getDisplayName(), AccessLevel.OWNER, true
                    );
            }
        }
    }

    @Override
    public boolean onMachineActivated(
        final World world,
        final int x,
        final int y,
        final int z,
        final EntityPlayer player,
        final int side,
        final float hitX,
        final float hitY,
        final float hitZ
    ) {
        final TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (!(tileEntity instanceof TTurretPlatform)) {
            return false;
        }

        if (player.getCurrentEquippedItem() != null
            && side == ((TTurretPlatform) tileEntity).deployDirection.ordinal()
            && player.getCurrentEquippedItem().getItem()
                == Item.getItemFromBlock(ICBMSentry.blockTurret)) {
            return false;
        }

        if (((TTurretPlatform) tileEntity).getTurret(false) != null && !world.isRemote) {
            player.openGui((Object) ICBMSentry.instance, 0, world, x, y, z);
        }

        return true;
    }

    @Override
    public TileEntity createNewTileEntity(final World var1, int meta) {
        return new TTurretPlatform();
    }

    @Override
    public void dropEntireInventory(
        final World world,
        final int x,
        final int y,
        final int z,
        final Block par5,
        final int par6
    ) {
        final TileEntity tileEntity = world.getTileEntity(x, y, z);

        if (tileEntity != null && tileEntity instanceof IInventory) {
            final IInventory inventory = (IInventory) tileEntity;

            for (int slot = 0; slot < inventory.getSizeInventory(); ++slot) {
                ItemStack itemStack = inventory.getStackInSlot(slot);

                if (itemStack != null) {
                    boolean flag = true;
                    final Item item = itemStack.getItem();

                    if (item instanceof IAmmunition) {
                        if (((IAmmunition) item).canDrop(itemStack.getItemDamage())) {
                            flag = true;
                            itemStack = ((IAmmunition) item)
                                            .onDroppedIntoWorld(itemStack.copy());
                        } else {
                            flag = false;
                        }
                    }

                    if (flag) {
                        final Random random = new Random();
                        final float var8 = random.nextFloat() * 0.8f + 0.1f;
                        final float var9 = random.nextFloat() * 0.8f + 0.1f;
                        final float var10 = random.nextFloat() * 0.8f + 0.1f;

                        while (itemStack.stackSize > 0) {
                            int var11 = random.nextInt(21) + 10;

                            if (var11 > itemStack.stackSize) {
                                var11 = itemStack.stackSize;
                            }

                            final ItemStack itemStack2 = itemStack;
                            itemStack2.stackSize -= var11;
                            final EntityItem var12 = new EntityItem(
                                world,
                                (double) (x + var8),
                                (double) (y + var9),
                                (double) (z + var10),
                                new ItemStack(
                                    itemStack.getItem(), var11, itemStack.getItemDamage()
                                )
                            );

                            if (itemStack.hasTagCompound()) {
                                var12.getEntityItem().setTagCompound(
                                    (NBTTagCompound) itemStack.getTagCompound().copy()
                                );
                            }

                            final float var13 = 0.05f;
                            ((Entity) var12).motionX
                                = (float) random.nextGaussian() * var13;
                            ((Entity) var12).motionY
                                = (float) random.nextGaussian() * var13 + 0.2f;
                            ((Entity) var12).motionZ
                                = (float) random.nextGaussian() * var13;
                            world.spawnEntityInWorld((Entity) var12);
                        }
                    }
                }
            }
        }
    }
}
