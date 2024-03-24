package icbm.core.di;

import icbm.core.ICBMTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItICBM extends Item {
    public ItICBM(final String name) {
        super();
        this.setUnlocalizedName("icbm:" + name);
        this.setCreativeTab((CreativeTabs) ICBMTab.INSTANCE);
    }
}
