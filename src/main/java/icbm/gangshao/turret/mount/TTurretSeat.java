package icbm.gangshao.turret.mount;

import calclavia.lib.CalculationHelper;
import icbm.gangshao.turret.TTurretBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.multiblock.IMultiBlock;

public abstract class TTurretSeat extends TTurretBase implements IMultiBlock {
    protected ESeat entityFake;

    public TTurretSeat() {
        this.entityFake = null;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (this.entityFake != null
            && this.entityFake.riddenByEntity instanceof EntityPlayer) {
            final EntityPlayer mountedPlayer
                = (EntityPlayer) this.entityFake.riddenByEntity;

            if (((Entity) mountedPlayer).rotationPitch > super.maxPitch) {
                ((Entity) mountedPlayer).rotationPitch = super.maxPitch;
            }

            if (((Entity) mountedPlayer).rotationPitch < super.minPitch) {
                ((Entity) mountedPlayer).rotationPitch = super.minPitch;
            }

            final float rotationPitch = ((Entity) mountedPlayer).rotationPitch;
            super.wantedRotationPitch = rotationPitch;
            super.currentRotationPitch = rotationPitch;
            final float rotationYaw = ((Entity) mountedPlayer).rotationYaw;
            super.wantedRotationYaw = rotationYaw;
            super.currentRotationYaw = rotationYaw;
        }
    }

    public MovingObjectPosition rayTrace(final double distance) {
        return CalculationHelper.doCustomRayTrace(
            this.worldObj,
            this.getMuzzle(),
            super.wantedRotationYaw,
            super.wantedRotationPitch,
            true,
            distance
        );
    }

    @Override
    public boolean onActivated(final EntityPlayer entityPlayer) {
        if (entityPlayer.isSneaking()) {
            this.tryActivateWeapon();
        } else {
            if (this.entityFake != null) {
                if (this.entityFake.riddenByEntity instanceof EntityPlayer) {
                    final EntityPlayer mountedPlayer
                        = (EntityPlayer) this.entityFake.riddenByEntity;

                    if (entityPlayer == mountedPlayer) {
                        if (!this.worldObj.isRemote) {
                            this.tryActivateWeapon();
                        }
                        return true;
                    }
                }

            }

            this.mount(entityPlayer);
        }

        return true;
    }

    public void mount(final EntityPlayer entityPlayer) {
        if (!this.worldObj.isRemote) {
            if (this.entityFake == null) {
                this.entityFake = new ESeat(
                    this.worldObj,
                    new Vector3(this.xCoord + 0.5, this.yCoord + 1.2, this.zCoord + 0.5),
                    this,
                    true
                );
                this.worldObj.spawnEntityInWorld((Entity) this.entityFake);
            }

            ((Entity) entityPlayer).rotationYaw = super.currentRotationYaw;
            ((Entity) entityPlayer).rotationPitch = super.currentRotationPitch;
            entityPlayer.mountEntity((Entity) this.entityFake);
        }
    }

    public void tryActivateWeapon() {
        if (this.canActivateWeapon()) {
            this.onWeaponActivated();
        }
    }

    @Override
    public boolean canApplyPotion(final PotionEffect par1PotionEffect) {
        return false;
    }
}
