package icbm.zhapin.po;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.DamageSource;

public class PDaDu extends PICBM {
    public static PDaDu INSTANCE;

    public PDaDu(
        final int id, final boolean isBadEffect, final int color, final String name
    ) {
        super(id, isBadEffect, color, name);
        this.setIconIndex(6, 0);
    }

    @Override
    public void
    performEffect(final EntityLivingBase par1EntityLiving, final int amplifier) {
        if (!(par1EntityLiving instanceof EntityZombie)
            && !(par1EntityLiving instanceof EntityPigZombie)) {
            par1EntityLiving.attackEntityFrom(DamageSource.magic, 1);
        }
    }

    @Override
    public boolean isReady(final int duration, final int amplifier) {
        return duration % 40 == 0;
    }
}
