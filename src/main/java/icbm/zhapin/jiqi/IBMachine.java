package icbm.zhapin.jiqi;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import universalelectricity.prefab.implement.ITier;

public class IBMachine extends ItemBlock {
    public IBMachine(final Block block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(final int damage) {
        return damage;
    }

    @Override
    public String getUnlocalizedName(final ItemStack itemstack) {
        return this.getUnlocalizedName() + "." + itemstack.getItemDamage();
    }

    @Override
    public String getUnlocalizedName() {
        return "icbm.machine";
    }

    @Override
    public boolean placeBlockAt(
        final ItemStack itemStack,
        final EntityPlayer entityPlayer,
        final World world,
        final int x,
        final int y,
        final int z,
        final int side,
        final float hitX,
        final float hitY,
        final float hitZ,
        final int metadata
    ) {
        int jiQiMetadata;

        if (itemStack.getItemDamage() < 3) {
            jiQiMetadata = 0;
        } else if (itemStack.getItemDamage() < 6) {
            jiQiMetadata = 1;
        } else if (itemStack.getItemDamage() < 9) {
            jiQiMetadata = 2;
        } else {
            jiQiMetadata = itemStack.getItemDamage() - 6;
        }

        final int direction
            = MathHelper.floor_double(
                  ((Entity) entityPlayer).rotationYaw * 4.0f / 360.0f + 0.5
              )
            & 0x3;

        if (BMachine.canBePlacedAt(world, x, y, z, jiQiMetadata, direction)) {
            if (world.setBlock(x, y, z, this.field_150939_a, jiQiMetadata, 3)) {
                if (world.getBlock(x, y, z) == this.field_150939_a) {
                    if (itemStack.getItemDamage() < 9) {
                        final ITier tileEntity = (ITier) world.getTileEntity(x, y, z);

                        if (tileEntity != null) {
                            if (itemStack.getItemDamage() < 3) {
                                tileEntity.setTier(itemStack.getItemDamage());
                            } else if (itemStack.getItemDamage() < 6) {
                                tileEntity.setTier(itemStack.getItemDamage() - 3);
                            } else if (itemStack.getItemDamage() < 9) {
                                tileEntity.setTier(itemStack.getItemDamage() - 6);
                            }
                        }
                    }

                    this.field_150939_a.onBlockPlacedBy(
                        world, x, y, z, entityPlayer, itemStack
                    );
                }

                return true;
            }
        }

        return false;
    }
}
