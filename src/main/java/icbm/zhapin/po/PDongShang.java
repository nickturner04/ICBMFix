package icbm.zhapin.po;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;

public class PDongShang extends PICBM {
    public static PDongShang INSTANCE;

    public PDongShang(
        final int id, final boolean isBadEffect, final int color, final String name
    ) {
        super(id, isBadEffect, color, name);
        this.setIconIndex(6, 0);
    }

    @Override
    public void
    performEffect(final EntityLivingBase par1EntityLiving, final int amplifier) {
        if (par1EntityLiving instanceof EntityPlayer) {
            ((EntityPlayer) par1EntityLiving).addExhaustion(3.0f * (amplifier + 1));
        }

        if (par1EntityLiving.isBurning()) {
            par1EntityLiving.extinguish();
            par1EntityLiving.removePotionEffect(super.id);
        }

        if (((Entity) par1EntityLiving)
                .worldObj.getBlock(
                    MathHelper.floor_double(((Entity) par1EntityLiving).posX),
                    MathHelper.floor_double(((Entity) par1EntityLiving).posY) - 1,
                    MathHelper.floor_double(((Entity) par1EntityLiving).posZ)
                )
            == Blocks.ice) {
            par1EntityLiving.attackEntityFrom(DamageSource.magic, 2);
        }

        if (par1EntityLiving.getHealth() < 6) {
            par1EntityLiving.attackEntityFrom(DamageSource.magic, 999999999);
        }
    }

    @Override
    public boolean isReady(final int duration, final int amplifier) {
        return duration % 20 == 0;
    }
}
