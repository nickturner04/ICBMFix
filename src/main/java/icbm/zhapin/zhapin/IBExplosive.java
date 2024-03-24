package icbm.zhapin.zhapin;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class IBExplosive extends ItemBlock {
    public IBExplosive(final Block id) {
        super(id);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    public boolean placeBlockAt(
        final ItemStack itemStack,
        final EntityPlayer player,
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
        if (!world.setBlock(x, y, z, this.field_150939_a, 0, 3)) {
            return false;
        }

        if (world.getBlock(x, y, z) == this.field_150939_a) {
            ((TExplosive) world.getTileEntity(x, y, z)).explosiveId
                = itemStack.getItemDamage();
            this.field_150939_a.onBlockPlacedBy(world, x, y, z, player, itemStack);
            this.field_150939_a.onPostBlockPlaced(world, x, y, z, metadata);
        }

        return true;
    }

    public int getMetadata(final int damage) {
        return damage;
    }

    public String getUnlocalizedName(final ItemStack itemstack) {
        return this.getUnlocalizedName() + "."
            + ZhaPin.list[itemstack.getItemDamage()].getUnlocalizedName();
    }

    public String getUnlocalizedName() {
        return "icbm.explosive";
    }
}
