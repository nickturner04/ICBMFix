package icbm.zhapin.po;

import java.util.List;

import icbm.zhapin.ICBMExplosion;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import universalelectricity.core.vector.Vector3;

public class PChuanRanDu extends PICBM {
    public static PChuanRanDu INSTANCE;

    public PChuanRanDu(
        final int id, final boolean isBadEffect, final int color, final String name
    ) {
        super(id, isBadEffect, color, name);
        this.setIconIndex(6, 0);
    }

    @Override
    public void performEffect(final EntityLivingBase entityLiving, final int amplifier) {
        if (!(entityLiving instanceof EntityZombie)
            && !(entityLiving instanceof EntityPigZombie)) {
            entityLiving.attackEntityFrom(DamageSource.magic, 1);
        }

        if (!ICBMExplosion.shiBaoHu(
                ((Entity) entityLiving).worldObj,
                new Vector3((Entity) entityLiving),
                ZhaPin.ZhaPinType.QUAN_BU,
                ZhaPin.chemical
            )) {
            final int r = 13;
            final AxisAlignedBB entitySurroundings = AxisAlignedBB.getBoundingBox(
                ((Entity) entityLiving).posX - r,
                ((Entity) entityLiving).posY - r,
                ((Entity) entityLiving).posZ - r,
                ((Entity) entityLiving).posX + r,
                ((Entity) entityLiving).posY + r,
                ((Entity) entityLiving).posZ + r
            );
            final List<EntityLivingBase> entities
                = entityLiving.worldObj.getEntitiesWithinAABB(
                    EntityLivingBase.class, entitySurroundings
                );

            for (final EntityLivingBase entity : entities) {
                if (entity != null) {
                    if (entity instanceof EntityPig) {
                        final EntityPigZombie var2
                            = new EntityPigZombie(((Entity) entity).worldObj);
                        var2.setLocationAndAngles(
                            ((Entity) entity).posX,
                            ((Entity) entity).posY,
                            ((Entity) entity).posZ,
                            ((Entity) entity).rotationYaw,
                            ((Entity) entity).rotationPitch
                        );
                        ((Entity) entity).worldObj.spawnEntityInWorld((Entity) var2);
                        entity.setDead();
                    } else if (entity instanceof EntityVillager) {
                        final EntityZombie var3
                            = new EntityZombie(((Entity) entity).worldObj);
                        var3.setLocationAndAngles(
                            ((Entity) entity).posX,
                            ((Entity) entity).posY,
                            ((Entity) entity).posZ,
                            ((Entity) entity).rotationYaw,
                            ((Entity) entity).rotationPitch
                        );
                        ((Entity) entity).worldObj.spawnEntityInWorld((Entity) var3);
                        entity.setDead();
                    }

                    ICBMExplosion.DU_CHUAN_RAN.poisonEntity(
                        new Vector3((Entity) entityLiving), entity
                    );
                }
            }
        }
    }

    @Override
    public boolean isReady(final int duration, final int amplifier) {
        return duration % 40 == 0;
    }
}
