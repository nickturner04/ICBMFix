package icbm.wanyi.b;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class IBConcrete extends ItemBlock {
    public IBConcrete(Block block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    public int getMetadata(final int damage) {
        return damage;
    }

    public String getUnlocalizedName(final ItemStack itemstack) {
        switch (itemstack.getItemDamage()) {
            case 1: {
                return this.getUnlocalizedName() + "Compact";
            }

            case 2: {
                return this.getUnlocalizedName() + "Reinforced";
            }

            default: {
                return this.getUnlocalizedName();
            }
        }
    }
}
