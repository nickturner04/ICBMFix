package icbm.wanyi;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class IBSpikes extends ItemBlock {
    public IBSpikes(Block block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    public int getMetadata(final int damage) {
        return damage;
    }

    public String getUnlocalizedName(final ItemStack itemstack) {
        return this.getUnlocalizedName() + "." + itemstack.getItemDamage();
    }

    public String getUnlocalizedName() {
        return "tile.icbm:spikes";
    }
}
