package icbm.gangshao;

import icbm.gangshao.turret.upgrades.ItPaoTaiUpgrades;
import net.minecraft.item.ItemStack;

public interface ITurretUpgrade {
    ItPaoTaiUpgrades.TurretUpgradeType getType(final ItemStack p0);
}
