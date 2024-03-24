package icbm.gangshao.turret.sentries;

import calclavia.lib.CalculationHelper;
import universalelectricity.core.vector.Vector3;

public class TMachineGunTurret extends TAutomaticTurret {
    public TMachineGunTurret() {
        super.targetPlayers = true;
        super.targetHostile = true;
        super.baseTargetRange = 13;
        super.maxTargetRange = 25;
        super.rotationSpeed = 2.0f;
        super.baseFiringDelay = 18;
        super.minFiringDelay = 10;
    }

    @Override
    public double getVoltage() {
        return 240.0;
    }

    @Override
    public int getMaxHealth() {
        return 200;
    }

    @Override
    public double getFiringRequest() {
        return 1000.0;
    }

    @Override
    public void playFiringSound() {
        this.worldObj.playSoundEffect(
            (double) this.xCoord,
            (double) this.yCoord,
            (double) this.zCoord,
            "icbm:machinegun",
            5.0f,
            1.0f
        );
    }

    @Override
    public Vector3 getMuzzle() {
        return this.getCenter().add(Vector3.multiply(
            CalculationHelper.getDeltaPositionFromRotation(
                super.currentRotationYaw, super.currentRotationPitch
            ),
            1.0
        ));
    }

    @Override
    public Vector3 getCenter() {
        return new Vector3(this).add(new Vector3(0.5, 0.65, 0.5));
    }
}
