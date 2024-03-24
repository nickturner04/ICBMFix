package icbm.core.di;

import icbm.core.ICBMTab;
import net.minecraft.creativetab.CreativeTabs;
import universalelectricity.core.item.ItemElectric;

public abstract class ItElectricICBM extends ItemElectric {
    public ItElectricICBM(final String name) {
        super();
        this.setUnlocalizedName("icbm:" + name);
        this.setCreativeTab((CreativeTabs) ICBMTab.INSTANCE);
    }
}
