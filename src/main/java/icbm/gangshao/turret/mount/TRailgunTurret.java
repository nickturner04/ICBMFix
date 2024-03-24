package icbm.gangshao.turret.mount;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import calclavia.lib.CalculationHelper;
import icbm.api.explosion.IExplosive;
import icbm.core.MainBase;
import icbm.gangshao.ICBMSentry;
import icbm.gangshao.ProjectileType;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.implement.IRedstoneReceptor;
import universalelectricity.prefab.multiblock.IMultiBlock;
import universalelectricity.prefab.multiblock.TileEntityMulti;

public class TRailgunTurret extends TTurretSeat implements IRedstoneReceptor {
    private int gunChargingTicks;
    private boolean redstonePowerOn;
    private boolean isAntimatter;
    private float explosionSize;
    private int explosionDepth;
    private int endTicks;

    public TRailgunTurret() {
        this.gunChargingTicks = 0;
        this.redstonePowerOn = false;
        this.endTicks = 0;
        super.baseFiringDelay = 80;
        super.minFiringDelay = 50;
        super.maxPitch = 60.0f;
        super.minPitch = -60.0f;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (this.getPlatform() != null) {
            if (this.redstonePowerOn) {
                this.tryActivateWeapon();
            }

            if (this.gunChargingTicks > 0) {
                ++this.gunChargingTicks;

                if (this.gunChargingTicks >= this.getFireDelay()) {
                    this.onFire();
                    this.gunChargingTicks = 0;
                }
            }

            if (this.worldObj.isRemote && this.endTicks-- > 0) {
                final MovingObjectPosition objectMouseOver = this.rayTrace(2000.0);

                if (objectMouseOver != null && objectMouseOver.hitVec != null) {
                    this.drawParticleStreamTo(new Vector3(objectMouseOver.hitVec));
                }
            }
        }
    }

    @Override
    public void tryActivateWeapon() {
        if (this.canActivateWeapon() && this.gunChargingTicks == 0) {
            this.onWeaponActivated();
        }
    }

    public void onFire() {
        if (!this.worldObj.isRemote) {
            while (this.explosionDepth > 0) {
                final MovingObjectPosition objectMouseOver = this.rayTrace(2000.0);

                if (objectMouseOver != null
                    && !ICBMSentry.isProtected(
                        this.worldObj,
                        new Vector3(objectMouseOver),
                        ICBMSentry.FLAG_RAILGUN
                    )) {
                    if (this.isAntimatter) {
                        final int radius = 50;
                        final AxisAlignedBB bounds = AxisAlignedBB.getBoundingBox(
                            (double) (objectMouseOver.blockX - radius),
                            (double) (objectMouseOver.blockY - radius),
                            (double) (objectMouseOver.blockZ - radius),
                            (double) (objectMouseOver.blockX + radius),
                            (double) (objectMouseOver.blockY + radius),
                            (double) (objectMouseOver.blockZ + radius)
                        );
                        final List<Entity> missilesNearby
                            = this.worldObj.getEntitiesWithinAABB(Entity.class, bounds);

                        for (final Entity entity : missilesNearby) {
                            if (entity instanceof IExplosive) {
                                entity.setDead();
                            }
                        }
                    }

                    final Vector3 blockPosition = new Vector3(objectMouseOver.hitVec);
                    final Block block
                        = blockPosition.getBlock((IBlockAccess) this.worldObj);

                    if (block != null
                        && block.getBlockHardness(
                               this.worldObj,
                               blockPosition.intX(),
                               blockPosition.intY(),
                               blockPosition.intZ()
                           ) != -1.0f) {
                        this.worldObj.setBlock(
                            objectMouseOver.blockX,
                            objectMouseOver.blockY,
                            objectMouseOver.blockZ,
                            Blocks.air,
                            0,
                            2
                        );
                    }

                    final Entity responsibleEntity = (super.entityFake != null)
                        ? super.entityFake.riddenByEntity
                        : null;
                    this.worldObj.newExplosion(
                        responsibleEntity,
                        blockPosition.x,
                        blockPosition.y,
                        blockPosition.z,
                        this.explosionSize,
                        true,
                        true
                    );
                }

                --this.explosionDepth;
            }
        }
    }

    @Override
    public void renderShot(final Vector3 target) {
        this.endTicks = 20;
    }

    @Override
    public void playFiringSound() {
        this.worldObj.playSoundEffect(
            (double) this.xCoord,
            (double) this.yCoord,
            (double) this.zCoord,
            "icbm:railgun",
            5.0f,
            1.0f
        );
    }

    @Override
    public double getVoltage() {
        return 220.0;
    }

    @Override
    public void onDestroy(final TileEntity callingBlock) {
        this.worldObj.setBlockToAir(this.xCoord, this.yCoord, this.zCoord);
        this.worldObj.setBlockToAir(this.xCoord, this.yCoord + 1, this.zCoord);
    }

    @Override
    public void onCreate(final Vector3 position) {
        this.worldObj.setBlock(
            position.intX(), position.intY() + 1, position.intZ(), MainBase.bJia, 0, 2
        );
        ((TileEntityMulti) this.worldObj.getTileEntity(
             position.intX(), position.intY() + 1, position.intZ()
         ))
            .setMainBlock(position);
    }

    @Override
    public Vector3 getCenter() {
        return new Vector3(this).add(new Vector3(0.5, 1.5, 0.5));
    }

    @Override
    public Vector3 getMuzzle() {
        return this.getCenter().add(Vector3.multiply(
            CalculationHelper.getDeltaPositionFromRotation(
                super.currentRotationYaw, super.currentRotationPitch
            ),
            1.6
        ));
    }

    @Override
    public void onPowerOn() {
        this.redstonePowerOn = true;
    }

    @Override
    public void onPowerOff() {
        this.redstonePowerOn = false;
    }

    @Override
    public double getFiringRequest() {
        return 1000000.0;
    }

    @Override
    public void onWeaponActivated() {
        super.onWeaponActivated();
        this.gunChargingTicks = 1;
        this.redstonePowerOn = false;
        this.isAntimatter = false;
        final ItemStack ammoStack
            = this.getPlatform().hasAmmunition(ProjectileType.RAILGUN);

        if (ammoStack != null) {
            if (ammoStack.equals(ICBMSentry.antimatterBullet)
                && this.getPlatform().useAmmunition(ammoStack)) {
                this.isAntimatter = true;
            } else {
                this.getPlatform().useAmmunition(ammoStack);
            }
        }

        this.getPlatform().wattsReceived = 0.0;
        this.explosionSize = 5.0f;
        this.explosionDepth = 5;

        if (this.isAntimatter) {
            this.explosionSize = 8.0f;
            this.explosionDepth = 10;
        }

        this.worldObj.playSoundEffect(
            (double) this.xCoord,
            (double) this.yCoord,
            (double) this.zCoord,
            "icbm:railgun",
            5.0f,
            1.0f
        );
    }

    @Override
    public boolean canActivateWeapon() {
        return this.getPlatform() != null
            && this.getPlatform().hasAmmunition(ProjectileType.RAILGUN) != null
            && this.getPlatform().wattsReceived >= this.getFiringRequest();
    }

    @Override
    public float addInformation(final HashMap map, final EntityPlayer player) {
        super.addInformation(map, player);
        return 2.0f;
    }

    @Override
    public int getMaxHealth() {
        return 450;
    }
}
