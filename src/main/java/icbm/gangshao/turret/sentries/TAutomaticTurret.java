package icbm.gangshao.turret.sentries;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import icbm.api.sentry.IAATarget;
import icbm.gangshao.IAmmunition;
import icbm.gangshao.IAutoSentry;
import icbm.gangshao.ICBMSentry;
import icbm.gangshao.ProjectileType;
import icbm.gangshao.damage.TileDamageSource;
import icbm.gangshao.packet.PacketTurret;
import icbm.gangshao.packet.PacketTurret.Type;
import icbm.gangshao.platform.TTurretPlatform;
import icbm.gangshao.task.TaskManager;
import icbm.gangshao.task.TaskSearchTarget;
import icbm.gangshao.turret.TTurretBase;
import icbm.gangshao.turret.upgrades.ItPaoTaiUpgrades;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.INpc;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import universalelectricity.core.vector.Vector3;

public abstract class TAutomaticTurret extends TTurretBase implements IAutoSentry {
    public Entity target;
    public boolean targetPlayers;
    public boolean targetAir;
    public boolean targetHostile;
    public boolean targetFriendly;
    public boolean canTargetAir;
    public final TaskManager taskManager;
    public int baseTargetRange;
    public int maxTargetRange;
    public float rotationSpeed;
    public ProjectileType projectileType;

    public TAutomaticTurret() {
        this.targetPlayers = false;
        this.targetAir = false;
        this.targetHostile = false;
        this.targetFriendly = false;
        this.canTargetAir = false;
        this.taskManager = new TaskManager(this);
        this.baseTargetRange = 20;
        this.maxTargetRange = 90;
        this.rotationSpeed = 3.0f;
        this.projectileType = ProjectileType.CONVENTIONAL;
    }

    @Override
    public void onTurretPacket(Type type, NBTTagCompound data) {
        super.onTurretPacket(type, data);

        if (type == PacketTurret.Type.SHOT) {
            Vector3 target = Vector3.readFromNBT(data.getCompoundTag("target"));

            this.renderShot(target);

            super.currentRotationYaw = data.getFloat("yaw");
            super.currentRotationPitch = data.getFloat("pitch");

            this.playFiringSound();
        }
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!this.worldObj.isRemote && this.isRunning()) {
            this.taskManager.onUpdate();

            if (!this.taskManager.hasTasks()) {
                this.taskManager.addTask(new TaskSearchTarget());
            }
        }
    }

    @Override
    public float getRotationSpeed() {
        return this.rotationSpeed;
    }

    @Override
    public AxisAlignedBB getTargetingBox() {
        return AxisAlignedBB.getBoundingBox(
            this.xCoord - this.getDetectRange(),
            (double) (this.yCoord - 5),
            this.zCoord - this.getDetectRange(),
            this.xCoord + this.getDetectRange(),
            (double) (this.yCoord + 5),
            this.zCoord + this.getDetectRange()
        );
    }

    @Override
    public Entity getTarget() {
        return this.target;
    }

    @Override
    public void setTarget(final Entity target) {
        this.target = target;
    }

    @Override
    public boolean isValidTarget(final Entity entity) {
        if (entity != null && !entity.isDead && !entity.isEntityInvulnerable()
            && this.getCenter().distanceTo(new Vector3(entity))
                <= this.getDetectRange()) {
            final float[] rotations = super.lookHelper.getDeltaRotations(
                new Vector3(entity).add(new Vector3(0.0, entity.getEyeHeight(), 0.0))
            );

            if (((rotations[1] <= super.maxPitch && rotations[1] >= super.minPitch)
                 || super.allowFreePitch)
                && super.lookHelper.canEntityBeSeen(entity)) {
                if (this.targetAir && this.canTargetAir && this.isAir(entity)) {
                    return true;
                }

                if (this.targetPlayers
                    && (entity instanceof EntityPlayer
                        || entity.riddenByEntity instanceof EntityPlayer)) {
                    EntityPlayer player;

                    if (entity.riddenByEntity instanceof EntityPlayer) {
                        player = (EntityPlayer) entity.riddenByEntity;
                    } else {
                        player = (EntityPlayer) entity;
                    }

                    if (!player.capabilities.isCreativeMode && this.getPlatform() != null
                        && !this.getPlatform().canUserAccess(player.getDisplayName())) {
                        return true;
                    }
                }

                if (this.targetHostile && entity instanceof IMob && !this.isAir(entity)) {
                    return true;
                }

                if (this.targetFriendly
                    && (entity instanceof IAnimals || entity instanceof INpc
                        || entity instanceof IMerchant)
                    && !this.isAir(entity)) {
                    return true;
                }
            }
        }

        return false;
    }

    protected boolean isAir(final Entity entity) {
        return (entity instanceof IMob && entity instanceof EntityFlying)
            || (entity instanceof IAATarget && ((IAATarget) entity).canBeTargeted(this))
            || entity instanceof EntityWither || entity instanceof EntityDragon;
    }

    @Override
    public boolean canActivateWeapon() {
        return this.isValidTarget(this.target) && this.getPlatform() != null
            && super.lookHelper.isLookingAt(this.target, 5.0f)
            && super.tickSinceFired == 0
            && this.getPlatform().wattsReceived >= this.getFiringRequest()
            && (this.getPlatform().hasAmmunition(this.projectileType) != null
                || this.projectileType == ProjectileType.UNKNOWN);
    }

    public void sendShotToClient(final Vector3 position) {
        ICBMSentry.channel.sendToAllAround(
            PacketTurret.shot(
                new Vector3(this),
                position,
                super.currentRotationYaw,
                super.currentRotationPitch
            ),
            new TargetPoint(
                this.worldObj.provider.dimensionId,
                this.xCoord,
                this.yCoord,
                this.zCoord,
                40.0
            )
        );
    }

    @Override
    public void onWeaponActivated() {
        super.onWeaponActivated();

        if (!this.worldObj.isRemote && this.onFire()) {
            this.sendShotToClient(this.getTargetPosition());
            this.playFiringSound();
            this.getPlatform().wattsReceived = Math.max(
                this.getPlatform().wattsReceived - this.getFiringRequest(), 0.0
            );
        }
    }

    @Override
    public void renderShot(final Vector3 target) {
        this.drawParticleStreamTo(target);
    }

    protected boolean onFire() {
        if (!this.worldObj.isRemote) {
            final ItemStack ammoStack
                = this.getPlatform().hasAmmunition(this.projectileType);

            if (this.getPlatform() != null && ammoStack != null) {
                boolean fired = false;
                final IAmmunition bullet = (IAmmunition) ammoStack.getItem();

                if (this.target instanceof EntityLiving) {
                    final TTurretPlatform platform = this.getPlatform();
                    platform.wattsReceived -= this.getFiringRequest();

                    if (bullet.getType(ammoStack) == ProjectileType.CONVENTIONAL) {
                        this.target.attackEntityFrom(
                            (DamageSource) TileDamageSource.doBulletDamage(this),
                            bullet.getDamage()
                        );
                    }

                    fired = true;
                } else if (this.target instanceof IAATarget) {
                    if (this.worldObj.rand.nextFloat() > 0.2) {
                        final int damage = ((IAATarget) this.target).doDamage(8);

                        if (damage == -1 && this.worldObj.rand.nextFloat() > 0.7) {
                            ((IAATarget) this.target).destroyCraft();
                        } else if (damage <= 0) {
                            ((IAATarget) this.target).destroyCraft();
                        }
                    }

                    fired = true;
                }

                if (fired && this.projectileType != ProjectileType.UNKNOWN
                    && this.getPlatform().useAmmunition(ammoStack)) {
                    boolean drop = true;

                    if (this.getPlatform().getUpgradeCount(
                            ItPaoTaiUpgrades.TurretUpgradeType.COLLECTOR
                        ) > 0
                        && this.getPlatform().addStackToInventory(
                            ICBMSentry.bulletShell.copy()
                        )) {
                        drop = false;
                    }

                    if (drop && this.worldObj.rand.nextFloat() < 0.9) {
                        final Vector3 spawnPos = this.getMuzzle();
                        final EntityItem entityShell = new EntityItem(
                            this.worldObj,
                            spawnPos.x,
                            spawnPos.y,
                            spawnPos.z,
                            ICBMSentry.bulletShell.copy()
                        );
                        entityShell.delayBeforeCanPickup = 20;
                        this.worldObj.spawnEntityInWorld((Entity) entityShell);
                    }
                }

                return fired;
            }
        }

        return false;
    }

    @Override
    public void writeToNBT(final NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setBoolean("targetPlayers", this.targetPlayers);
        nbt.setBoolean("targetAir", this.targetAir);
        nbt.setBoolean("targetHostile", this.targetHostile);
        nbt.setBoolean("targetFriendly", this.targetFriendly);
    }

    @Override
    public void readFromNBT(final NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        if (nbt.hasKey("targetPlayers")) {
            this.targetPlayers = nbt.getBoolean("targetPlayers");
        }

        if (nbt.hasKey("targetAir")) {
            this.targetAir = nbt.getBoolean("targetAir");
        }

        if (nbt.hasKey("targetHostile")) {
            this.targetHostile = nbt.getBoolean("targetHostile");
        }

        if (nbt.hasKey("targetFriendly")) {
            this.targetFriendly = nbt.getBoolean("targetFriendly");
        }
    }

    @Override
    public double getDetectRange() {
        if (this.getPlatform() != null) {
            return Math.min(
                this.baseTargetRange
                    + (this.maxTargetRange - this.baseTargetRange)
                        * (this.getPlatform().getUpgradeCount(
                               ItPaoTaiUpgrades.TurretUpgradeType.RANGE
                           )
                           / 64.0f),
                (float) this.maxTargetRange
            );
        }

        return this.baseTargetRange;
    }

    @Override
    public boolean canApplyPotion(final PotionEffect par1PotionEffect) {
        return false;
    }

    public Vector3 getTargetPosition() {
        if (this.getTarget() != null) {
            return new Vector3(this.getTarget())
                .add(new Vector3(0.0, this.getTarget().getEyeHeight(), 0.0));
        }

        return null;
    }
}
