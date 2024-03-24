package icbm.gangshao.damage;

import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

public interface IHealthTile {
    boolean onDamageTaken(final DamageSource p0, final int p1);

    boolean isAlive();

    int getHealth();

    void setHealth(final int p0, final boolean p1);

    int getMaxHealth();

    boolean canApplyPotion(final PotionEffect p0);
}
