package icbm.zhapin.zhapin.ex;

import java.util.List;

import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import universalelectricity.core.vector.Vector3;

public class ExMutateLiving extends ZhaPin {
    public ExMutateLiving(final String name, final int ID, final int tier) {
        super(name, ID, tier);
    }

    @Override
    public boolean doBaoZha(
        final World worldObj,
        final Vector3 position,
        final Entity explosionSource,
        final int radius,
        final int callCount
    ) {
        if (!worldObj.isRemote) {
            final AxisAlignedBB bounds = AxisAlignedBB.getBoundingBox(
                position.x - radius,
                position.y - radius,
                position.z - radius,
                position.x + radius,
                position.y + radius,
                position.z + radius
            );
            final List<EntityLivingBase> entitiesNearby
                = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, bounds);

            for (final EntityLivingBase entity : entitiesNearby) {
                if (entity instanceof EntityPig) {
                    final EntityPigZombie newEntity = new EntityPigZombie(worldObj);
                    ((Entity) newEntity).preventEntitySpawning = true;
                    newEntity.setPosition(
                        ((Entity) entity).posX,
                        ((Entity) entity).posY,
                        ((Entity) entity).posZ
                    );
                    entity.setDead();
                } else {
                    if (!(entity instanceof EntityVillager)) {
                        continue;
                    }

                    final EntityZombie newEntity2 = new EntityZombie(worldObj);
                    ((Entity) newEntity2).preventEntitySpawning = true;
                    newEntity2.setPosition(
                        ((Entity) entity).posX,
                        ((Entity) entity).posY,
                        ((Entity) entity).posZ
                    );
                    entity.setDead();
                }
            }
        }

        return false;
    }

    @Override
    public float getRadius() {
        return 0.0f;
    }

    @Override
    public double getEnergy() {
        return 0.0;
    }
}
