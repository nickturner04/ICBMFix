package icbm.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ICBMTab extends CreativeTabs {
    public static final ICBMTab INSTANCE;
    public static ItemStack itemStack;

    public ICBMTab() {
        super(CreativeTabs.getNextID(), "ICBM");
    }

    @Override
    public Item getTabIconItem() {
        if (ICBMTab.itemStack == null) {
            return Item.getItemFromBlock(Blocks.tnt);
        }

        return ICBMTab.itemStack.getItem();
    }

    public ItemStack getIconItemStack() {
        if (ICBMTab.itemStack == null) {
            ICBMTab.itemStack = new ItemStack(Blocks.tnt);
        }

        return ICBMTab.itemStack;
    }

    static {
        INSTANCE = new ICBMTab();
    }
}
