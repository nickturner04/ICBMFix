package icbm.gangshao.turret.sentries;

import calclavia.lib.CalculationHelper;
import icbm.gangshao.ICBMSentry;
import net.minecraft.util.AxisAlignedBB;
import universalelectricity.core.vector.Vector3;

public class TAATurret extends TAutomaticTurret {
    public TAATurret() {
        super.targetAir = true;
        super.canTargetAir = true;
        super.baseTargetRange = 80;
        super.maxTargetRange = 120;
        super.rotationSpeed = 9.0f;
        super.minFiringDelay = 8;
        super.baseFiringDelay = 15;
        super.minPitch = 40.0f;
        super.maxPitch = 90.0f;
        super.allowFreePitch = true;
    }

    @Override
    public AxisAlignedBB getTargetingBox() {
        return AxisAlignedBB.getBoundingBox(
            this.xCoord - this.getDetectRange(),
            this.yCoord - this.getDetectRange(),
            this.zCoord - this.getDetectRange(),
            this.xCoord + this.getDetectRange(),
            this.yCoord + this.getDetectRange(),
            this.zCoord + this.getDetectRange()
        );
    }

    @Override
    public double getVoltage() {
        return 240.0;
    }

    @Override
    public double getFiringRequest() {
        return 8000.0;
    }

    @Override
    public int getMaxHealth() {
        return 180;
    }

    @Override
    public void playFiringSound() {
        this.worldObj.playSoundEffect(
            (double) this.xCoord,
            (double) this.yCoord,
            (double) this.zCoord,
            "icbm:aagun",
            5.0f,
            1.0f
        );
    }

    @Override
    public void renderShot(final Vector3 target) {
        final Vector3 center = this.getCenter();
        ICBMSentry.proxy.renderBeam(
            this.worldObj,
            Vector3.add(
                center,
                CalculationHelper
                    .getDeltaPositionFromRotation(
                        super.currentRotationYaw - 25.0f,
                        super.currentRotationPitch * 1.4f
                    )
                    .multiply(1.15)
            ),
            target,
            1.0f,
            1.0f,
            1.0f,
            5
        );
        ICBMSentry.proxy.renderBeam(
            this.worldObj,
            Vector3.add(
                center,
                CalculationHelper
                    .getDeltaPositionFromRotation(
                        super.currentRotationYaw + 25.0f,
                        super.currentRotationPitch * 1.4f
                    )
                    .multiply(1.15)
            ),
            target,
            1.0f,
            1.0f,
            1.0f,
            5
        );
    }

    @Override
    public Vector3 getCenter() {
        return new Vector3(this).add(new Vector3(0.5, 0.75, 0.5));
    }

    @Override
    public Vector3 getMuzzle() {
        return new Vector3(this)
            .add(new Vector3(0.5, 0.75, 0.5))
            .add(CalculationHelper
                     .getDeltaPositionFromRotation(
                         super.currentRotationYaw, super.currentRotationPitch
                     )
                     .multiply(1.0));
    }
}
