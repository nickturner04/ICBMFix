package icbm.zhapin;

import java.util.EnumSet;

import atomicscience.api.poison.Poison;
import icbm.zhapin.po.PChuanRanDu;
import icbm.zhapin.po.PDaDu;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.potion.CustomPotionEffect;

public class Du extends Poison {
    private boolean isContagious;

    public Du(final String name, final int id, final boolean isContagious) {
        super(name, id);
        this.isContagious = isContagious;
    }

    @Override
    protected void doPoisonEntity(
        final Vector3 emitPosition,
        final EntityLivingBase entity,
        final EnumSet<Poison.ArmorType> armorWorn,
        final int amplifier
    ) {
        if (this.isContagious) {
            entity.addPotionEffect((PotionEffect
            ) new CustomPotionEffect(PChuanRanDu.INSTANCE.getId(), 900, amplifier, null));
            entity.addPotionEffect((PotionEffect
            ) new CustomPotionEffect(Potion.blindness.id, 300, amplifier));
        } else {
            entity.addPotionEffect((PotionEffect
            ) new CustomPotionEffect(PDaDu.INSTANCE.getId(), 600, amplifier, null));
            entity.addPotionEffect((PotionEffect
            ) new CustomPotionEffect(Potion.confusion.id, 600, amplifier));
        }

        entity.addPotionEffect((PotionEffect
        ) new CustomPotionEffect(Potion.hunger.id, 600, amplifier));
        entity.addPotionEffect((PotionEffect
        ) new CustomPotionEffect(Potion.weakness.id, 700, amplifier));
        entity.addPotionEffect((PotionEffect
        ) new CustomPotionEffect(Potion.digSlowdown.id, 1200, amplifier));
    }
}
