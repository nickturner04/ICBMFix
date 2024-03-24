package icbm.zhapin.daodan;

import java.util.List;

import icbm.core.di.ItICBM;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItMissile extends ItICBM {
    public ItMissile(final String name) {
        super(name);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setMaxStackSize(1);
    }

    public int getMetadata(final int damage) {
        return damage;
    }

    public String getUnlocalizedName(final ItemStack itemStack) {
        if (itemStack.getItemDamage() < ZhaPin.list.length) {
            return this.getUnlocalizedName() + "."
                + ZhaPin.list[itemStack.getItemDamage()].getUnlocalizedName();
        }

        return "";
    }

    public String getUnlocalizedName() {
        return "icbm.missile";
    }

    @Override
    public void getSubItems(
        final Item par1, final CreativeTabs par2CreativeTabs, final List par3List
    ) {
        for (int i = 0; i < ZhaPin.E_SI_ID; ++i) {
            par3List.add(new ItemStack((Item) this, 1, i));
        }
    }
}
