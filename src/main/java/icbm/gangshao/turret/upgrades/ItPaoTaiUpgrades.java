package icbm.gangshao.turret.upgrades;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.core.di.ItICBM;
import icbm.gangshao.ITurretUpgrade;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItPaoTaiUpgrades extends ItICBM implements ITurretUpgrade {
    public static final IIcon[] ICONS;

    public ItPaoTaiUpgrades() {
        super("turretUpgrades");
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(final int damage) {
        return damage;
    }

    @Override
    public String getUnlocalizedName(final ItemStack itemStack) {
        return "item.icbm:"
            + TurretUpgradeType.values()[itemStack.getItemDamage()].iconName;
    }

    @Override
    public IIcon getIconFromDamage(final int i) {
        return ItPaoTaiUpgrades.ICONS[i];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister iconRegister) {
        for (int i = 0; i < TurretUpgradeType.values().length; ++i) {
            ItPaoTaiUpgrades.ICONS[i] = iconRegister.registerIcon(
                "icbm:" + TurretUpgradeType.values()[i].iconName
            );
        }
    }

    @Override
    public void getSubItems(
        final Item par1, final CreativeTabs par2CreativeTabs, final List par3List
    ) {
        for (int i = 0; i < TurretUpgradeType.values().length; ++i) {
            par3List.add(new ItemStack((Item) this, 1, i));
        }
    }

    @Override
    public TurretUpgradeType getType(final ItemStack itemstack) {
        return TurretUpgradeType.values()[itemstack.getItemDamage()];
    }

    static {
        ICONS = new IIcon[TurretUpgradeType.values().length];
    }

    public enum TurretUpgradeType {
        RANGE("RANGE", 0, "targetCard"),
        COLLECTOR("COLLECTOR", 1, "shellCollector");

        String iconName;

        private TurretUpgradeType(
            final String name2, final int ordinal, final String name
        ) {
            this.iconName = name;
        }
    }
}
