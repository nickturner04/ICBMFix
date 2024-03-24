package icbm.gangshao;

import net.minecraft.item.ItemStack;

public interface IAmmunition {
    boolean canDrop(final int p0);

    ItemStack onDroppedIntoWorld(final ItemStack p0);

    ProjectileType getType(final ItemStack p0);

    int getDamage();
}
