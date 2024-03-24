package icbm.zhapin.daodan;

import icbm.api.IMissileLockable;
import icbm.api.explosion.ExplosionEvent;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.MinecraftForge;
import universalelectricity.core.vector.Vector3;

public class DAntiBallistic extends MissileBase {
    public static final int ABMRange = 30;

    protected DAntiBallistic(final String name, final int ID, final int tier) {
        super(name, ID, tier);
    }

    @Override
    public void update(final EMissile missileObj) {
        if (missileObj.lockedTarget == null) {
            final AxisAlignedBB bounds = AxisAlignedBB.getBoundingBox(
                missileObj.posX - 30.0,
                missileObj.posY - 30.0,
                missileObj.posZ - 30.0,
                missileObj.posX + 30.0,
                missileObj.posY + 30.0,
                missileObj.posZ + 30.0
            );
            final Entity nearestEntity = missileObj.worldObj.findNearestEntityWithinAABB(
                EMissile.class, bounds, (Entity) missileObj
            );

            if (nearestEntity instanceof IMissileLockable) {
                if (((IMissileLockable) nearestEntity).canLock(missileObj)) {
                    missileObj.lockedTarget = nearestEntity;
                    missileObj.didTargetLockBefore = true;
                    missileObj.worldObj.playSoundAtEntity(
                        (Entity) missileObj, "icbm:targetlocked", 5.0f, 0.9f
                    );
                }
            } else {
                missileObj.motionX = missileObj.xDiff / missileObj.flightDuration;
                missileObj.motionZ = missileObj.zDiff / missileObj.flightDuration;

                if (missileObj.didTargetLockBefore) {
                    missileObj.explode();
                }
            }

            return;
        }

        Vector3 guJiDiDian = new Vector3(missileObj.lockedTarget);

        if (missileObj.lockedTarget.isDead) {
            missileObj.explode();
            return;
        }

        if (missileObj.lockedTarget instanceof IMissileLockable) {
            guJiDiDian
                = ((IMissileLockable) missileObj.lockedTarget).getPredictedPosition(4);
        }

        missileObj.motionX = (guJiDiDian.x - missileObj.posX) * 0.30000001192092896;
        missileObj.motionY = (guJiDiDian.y - missileObj.posY) * 0.30000001192092896;
        missileObj.motionZ = (guJiDiDian.z - missileObj.posZ) * 0.30000001192092896;
    }

    @Override
    public void onExplode(final EMissile missileObj) {
        missileObj.worldObj.createExplosion(
            (Entity) missileObj,
            missileObj.posX,
            missileObj.posY,
            missileObj.posZ,
            6.0f,
            true
        );
        MinecraftForge.EVENT_BUS.post(new ExplosionEvent.PostExplosionEvent(
            missileObj.worldObj, missileObj.posX, missileObj.posY, missileObj.posZ, this
        ));
    }

    @Override
    public float getRadius() {
        return 6.0f;
    }

    @Override
    public double getEnergy() {
        return 100.0;
    }

    @Override
    public boolean isCruise() {
        return true;
    }
}
