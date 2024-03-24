package icbm.gangshao.turret.sentries;

import calclavia.lib.CalculationHelper;
import icbm.api.sentry.IAATarget;
import icbm.gangshao.ICBMSentry;
import icbm.gangshao.ProjectileType;
import icbm.gangshao.damage.TileDamageSource;
import icbm.gangshao.platform.TTurretPlatform;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import universalelectricity.core.vector.Vector3;

public class TLaserTurret extends TAutomaticTurret {
    public float barrelRotation;
    public float barrelRotationVelocity;

    public TLaserTurret() {
        super.targetPlayers = true;
        super.targetHostile = true;
        super.baseTargetRange = 20;
        super.maxTargetRange = 35;
        super.rotationSpeed = 3.0f;
        super.baseFiringDelay = 12;
        super.minFiringDelay = 5;
        super.projectileType = ProjectileType.UNKNOWN;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (this.worldObj.isRemote) {
            this.barrelRotation = MathHelper.wrapAngleTo180_float(
                this.barrelRotation + this.barrelRotationVelocity
            );
            this.barrelRotationVelocity
                = Math.max(this.barrelRotationVelocity - 0.1f, 0.0f);
        }
    }

    @Override
    public double getFiringRequest() {
        return 20000.0;
    }

    @Override
    public double getVoltage() {
        return 480.0;
    }

    @Override
    public int getMaxHealth() {
        return 130;
    }

    @Override
    public void playFiringSound() {
        this.worldObj.playSoundEffect(
            (double) this.xCoord,
            (double) this.yCoord,
            (double) this.zCoord,
            "icbm:lasershot",
            5.0f,
            1.0f - this.worldObj.rand.nextFloat() * 0.2f
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
                        super.currentRotationYaw - 6.0f, super.currentRotationPitch * 1.4f
                    )
                    .multiply(1.2)
            ),
            target,
            1.0f,
            0.4f,
            0.4f,
            5
        );
        ICBMSentry.proxy.renderBeam(
            this.worldObj,
            Vector3.add(
                center,
                CalculationHelper
                    .getDeltaPositionFromRotation(
                        super.currentRotationYaw + 6.0f, super.currentRotationPitch * 1.4f
                    )
                    .multiply(1.2)
            ),
            target,
            1.0f,
            0.4f,
            0.4f,
            5
        );
        ++this.barrelRotationVelocity;
    }

    @Override
    protected boolean onFire() {
        if (!this.worldObj.isRemote && this.getPlatform() != null) {
            if (super.target instanceof EntityLiving) {
                final TTurretPlatform platform = this.getPlatform();
                platform.wattsReceived -= this.getFiringRequest();
                super.target.attackEntityFrom(
                    (DamageSource) TileDamageSource.doLaserDamage(this), 2
                );
                super.target.setFire(3);
                return true;
            }

            if (super.target instanceof IAATarget) {
                if (this.worldObj.rand.nextFloat() > 0.2) {
                    final int damage = ((IAATarget) super.target).doDamage(10);

                    if (damage == -1 && this.worldObj.rand.nextFloat() > 0.7) {
                        ((IAATarget) super.target).destroyCraft();
                    } else if (damage <= 0) {
                        ((IAATarget) super.target).destroyCraft();
                    }
                }

                return true;
            }
        }

        return false;
    }
}
