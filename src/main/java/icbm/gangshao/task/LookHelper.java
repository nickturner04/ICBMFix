package icbm.gangshao.task;

import icbm.gangshao.turret.TTurretBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import universalelectricity.core.vector.Vector3;

public class LookHelper {
    public static final int PITCH_DISPLACEMENT = 0;
    TTurretBase sentry;

    public LookHelper(final TTurretBase turret) {
        this.sentry = turret;
    }

    public void lookAt(final Vector3 target) {
        this.sentry.rotateTo(
            getYaw(this.sentry.getMuzzle(), target),
            getPitch(this.sentry.getMuzzle(), target)
        );
    }

    public float[] getDeltaRotations(final Vector3 target) {
        return new float[] { getYaw(this.sentry.getMuzzle(), target),
                             getPitch(this.sentry.getMuzzle(), target) };
    }

    public void lookAtEntity(final Entity entity) {
        this.lookAt(
            Vector3.add(new Vector3(entity), new Vector3(0.0, entity.getEyeHeight(), 0.0))
        );
    }

    public boolean isLookingAt(final Vector3 target, final float allowedError) {
        final float yaw = getYaw(this.sentry.getCenter(), target);
        final float pitch = getPitch(this.sentry.getCenter(), target);
        return Math.abs(getAngleDif(this.sentry.currentRotationYaw, yaw)) <= allowedError
            && Math.abs(getAngleDif(this.sentry.currentRotationPitch, pitch))
            <= allowedError;
    }

    public boolean isLookingAt(final Entity entity, final float allowedError) {
        return this.isLookingAt(
            new Vector3(entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ),
            allowedError
        );
    }

    public static float getPitch(final Vector3 position, final Vector3 target) {
        final Vector3 difference = Vector3.subtract(target, position);
        final double verticleDistance = MathHelper.sqrt_double(
            difference.x * difference.x + difference.z * difference.z
        );
        return -MathHelper.wrapAngleTo180_float(
            (float
            ) (Math.atan2(difference.y, verticleDistance) * 180.0 / 3.141592653589793)
            + 0.0f
        );
    }

    public static float getYaw(final Vector3 position, final Vector3 target) {
        final Vector3 difference = Vector3.subtract(target, position);
        return MathHelper.wrapAngleTo180_float(
            (float) (Math.atan2(difference.z, difference.x) * 180.0 / 3.141592653589793)
            - 90.0f
        );
    }

    public static float getAngleDif(final float angleOne, final float angleTwo) {
        final double dif = Math.max(angleOne, angleTwo) - Math.min(angleOne, angleTwo);
        return MathHelper.wrapAngleTo180_float((float) dif);
    }

    public boolean canPositionBeSeen(final Vector3 target) {
        return this.sentry.getWorldObj().rayTraceBlocks(
                   this.sentry.getMuzzle().toVec3(), target.toVec3()
               )
            == null;
    }

    public boolean canEntityBeSeen(final Entity entity) {
        final Vector3 target = Vector3.add(
            new Vector3(entity), new Vector3(0.0, entity.getEyeHeight(), 0.0)
        );
        return this.canPositionBeSeen(target);
    }
}
