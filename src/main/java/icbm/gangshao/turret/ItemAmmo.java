package icbm.gangshao.turret;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.core.di.ItICBM;
import icbm.gangshao.IAmmunition;
import icbm.gangshao.ProjectileType;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemAmmo extends ItICBM implements IAmmunition {
    public static final IIcon[] ICONS;

    public ItemAmmo() {
        super("ammunition");
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(final int damage) {
        return damage;
    }

    @Override
    public String getUnlocalizedName(final ItemStack itemStack) {
        return "item.icbm:" + AmmoType.values()[itemStack.getItemDamage()].iconName;
    }

    @Override
    public IIcon getIconFromDamage(final int i) {
        return ItemAmmo.ICONS[i];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister iconRegister) {
        for (int i = 0; i < AmmoType.values().length; ++i) {
            ItemAmmo.ICONS[i]
                = iconRegister.registerIcon("icbm:" + AmmoType.values()[i].iconName);
        }
    }

    @Override
    public ProjectileType getType(final ItemStack itemStack) {
        if (itemStack.getItemDamage() < AmmoType.values().length) {
            return AmmoType.values()[itemStack.getItemDamage()].type;
        }

        return null;
    }

    @Override
    public boolean canDrop(final int meta) {
        return meta != AmmoType.BULLETINF.ordinal();
    }

    @Override
    public ItemStack onDroppedIntoWorld(final ItemStack stack) {
        return stack;
    }

    @Override
    public int getEntityLifespan(final ItemStack itemStack, final World world) {
        if (itemStack != null
            && itemStack.getItemDamage() == AmmoType.BULLETINF.ordinal()) {
            return 40;
        }

        return super.getEntityLifespan(itemStack, world);
    }

    @Override
    public void getSubItems(
        final Item par1, final CreativeTabs par2CreativeTabs, final List par3List
    ) {
        for (int i = 0; i < AmmoType.values().length; ++i) {
            par3List.add(new ItemStack((Item) this, 1, i));
        }
    }

    @Override
    public int getDamage() {
        return 8;
    }

    static {
        ICONS = new IIcon[AmmoType.values().length];
    }

    public enum AmmoType {
        SHELL("SHELL", 0, "bulletShell", ProjectileType.UNKNOWN, true),
        BULLET("BULLET", 1, "bullet", ProjectileType.CONVENTIONAL, true),
        BULLETRAIL("BULLETRAIL", 2, "bulletRailgun", ProjectileType.RAILGUN, true),
        BULLETANTI("BULLETANTI", 3, "bulletAntimatter", ProjectileType.RAILGUN, true),
        BULLETINF("BULLETINF", 4, "bulletInfinite", ProjectileType.CONVENTIONAL, false);

        public String iconName;
        public ProjectileType type;
        public boolean consume;

        private AmmoType(
            final String name,
            final int ordinal,
            final String iconName,
            final ProjectileType type,
            final boolean consume
        ) {
            this.iconName = iconName;
            this.type = type;
            this.consume = consume;
        }
    }
}
