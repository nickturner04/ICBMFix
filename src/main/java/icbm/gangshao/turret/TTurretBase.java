package icbm.gangshao.turret;

import java.util.HashMap;

import calclavia.lib.CalculationHelper;
import calclavia.lib.render.ITagRender;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import icbm.gangshao.ICBMSentry;
import icbm.gangshao.ISentry;
import icbm.gangshao.damage.EntityTileDamagable;
import icbm.gangshao.damage.IHealthTile;
import icbm.gangshao.packet.PacketTurret;
import icbm.gangshao.platform.TTurretPlatform;
import icbm.gangshao.task.LookHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;
import universalelectricity.core.block.IVoltage;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.tile.TileEntityAdvanced;

public abstract class TTurretBase
    extends TileEntityAdvanced implements ITagRender, IVoltage, ISentry, IHealthTile {
    public float maxPitch;
    public float minPitch;
    protected boolean allowFreePitch;
    private ForgeDirection platformDirection;
    public LookHelper lookHelper;
    protected boolean speedUpRotation;
    public int health;
    public int baseFiringDelay;
    public int tickSinceFired;
    public int minFiringDelay;
    private EntityTileDamagable damageEntity;
    public float wantedRotationYaw;
    public float wantedRotationPitch;
    public float currentRotationYaw;
    public float currentRotationPitch;
    public int lastRotateTick;

    public TTurretBase() {
        this.maxPitch = 35.0f;
        this.minPitch = -35.0f;
        this.allowFreePitch = false;
        this.platformDirection = ForgeDirection.DOWN;
        this.lookHelper = new LookHelper(this);
        this.speedUpRotation = false;
        this.health = -1;
        this.baseFiringDelay = 10;
        this.tickSinceFired = 0;
        this.minFiringDelay = 5;
        this.wantedRotationPitch = 0.0f;
        this.currentRotationPitch = 0.0f;
        this.lastRotateTick = 0;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (this.tickSinceFired > 0) {
            --this.tickSinceFired;
        }

        if (!this.worldObj.isRemote && !this.isInvul() && this.getDamageEntity() == null
            && this.getHealth() > 0) {
            this.setDamageEntity(new EntityTileDamagable(this));
            this.worldObj.spawnEntityInWorld((Entity) this.getDamageEntity());
        }

        this.updateRotation();
    }

    public void onTurretPacket(PacketTurret.Type type, NBTTagCompound data) {
        if (type == PacketTurret.Type.ROTATION) {
            this.setRotation(data.getFloat("yaw"), data.getFloat("pitch"));
        } else if (type == PacketTurret.Type.STATS) {
            this.health = data.getInteger("health");
        }
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound nbt = pkt.func_148857_g();

        this.readFromNBT(nbt);
    }

    public PacketTurret getStatsPacket() {
        return PacketTurret.stats(new Vector3(this), this.health);
    }

    public PacketTurret getRotationPacket() {
        return PacketTurret.rotation(
            new Vector3(this), this.wantedRotationYaw, this.wantedRotationPitch
        );
    }

    @Override
    public Packet getDescriptionPacket() {
        final NBTTagCompound nbt = new NBTTagCompound();
        this.writeToNBT(nbt);

        return new S35PacketUpdateTileEntity(
            this.xCoord, this.yCoord, this.zCoord, this.getBlockMetadata(), nbt
        );
    }

    @Override
    public void writeToNBT(final NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setFloat("yaw", this.wantedRotationYaw);
        nbt.setFloat("pitch", this.wantedRotationPitch);
        nbt.setFloat("cYaw", this.currentRotationYaw);
        nbt.setFloat("cPitch", this.currentRotationPitch);
        nbt.setInteger("dir", this.platformDirection.ordinal());
        nbt.setInteger("health", this.getHealth());
    }

    @Override
    public void readFromNBT(final NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.wantedRotationYaw = nbt.getFloat("yaw");
        this.wantedRotationPitch = nbt.getFloat("pitch");
        this.currentRotationYaw = nbt.getFloat("cYaw");
        this.currentRotationPitch = nbt.getFloat("cPitch");
        this.platformDirection = ForgeDirection.getOrientation(nbt.getInteger("dir"));

        if (nbt.hasKey("health")) {
            this.health = nbt.getInteger("health");
        }
    }

    public abstract double getFiringRequest();

    public boolean isRunning() {
        return this.getPlatform() != null && this.getPlatform().isRunning()
            && this.isAlive();
    }

    public TTurretPlatform getPlatform() {
        final TileEntity tileEntity = this.worldObj.getTileEntity(
            this.xCoord + this.platformDirection.offsetX,
            this.yCoord + this.platformDirection.offsetY,
            this.zCoord + this.platformDirection.offsetZ
        );

        if (tileEntity instanceof TTurretPlatform) {
            return (TTurretPlatform) tileEntity;
        }

        return null;
    }

    public void destroy(final boolean doExplosion) {
        if (doExplosion) {
            if (!this.isInvalid()) {
                this.worldObj.setBlockToAir(this.xCoord, this.yCoord, this.zCoord);
                this.worldObj.createExplosion(
                    (Entity) this.getDamageEntity(),
                    (double) this.xCoord,
                    (double) this.yCoord,
                    (double) this.zCoord,
                    2.0f,
                    true
                );
            } else {
                this.worldObj.setBlockToAir(this.xCoord, this.yCoord, this.zCoord);
            }
        } else if (!this.worldObj.isRemote) {
            this.getBlockType().dropBlockAsItem(
                this.worldObj,
                this.xCoord,
                this.yCoord,
                this.zCoord,
                this.getBlockMetadata(),
                0
            );
            this.worldObj.setBlockToAir(this.xCoord, this.yCoord, this.zCoord);
        }
    }

    public int getBarrels() {
        return 1;
    }

    public void setDeploySide(final ForgeDirection side) {
        this.platformDirection = side.getOpposite();
    }

    @Override
    public float addInformation(final HashMap map, final EntityPlayer player) {
        map.put(this.getName(), 8978312);
        return 1.0f;
    }

    @Override
    public String getName() {
        return new ItemStack(this.getBlockType(), 1, this.getBlockMetadata())
                   .getDisplayName()
            + " " + this.getHealth() + "/" + this.getMaxHealth();
    }

    @Override
    public Vector3 getMuzzle() {
        return this.getCenter().add(Vector3.multiply(
            CalculationHelper.getDeltaPositionFromRotation(
                this.currentRotationYaw, this.currentRotationPitch
            ),
            1.0
        ));
    }

    @Override
    public void onWeaponActivated() {
        this.tickSinceFired += this.getFireDelay();
    }

    public int getFireDelay() {
        return this.baseFiringDelay;
    }

    public abstract int getMaxHealth();

    public boolean isInvul() {
        return false;
    }

    @Override
    public int getHealth() {
        if (this.health == -1) {
            this.health = this.getMaxHealth();
        }

        return this.health;
    }

    @Override
    public void setHealth(int i, final boolean increase) {
        if (increase) {
            i += this.health;
        }

        this.health = Math.min(Math.max(i, 0), this.getMaxHealth());

        if (!this.worldObj.isRemote) {
            ICBMSentry.channel.sendToAllAround(
                this.getStatsPacket(),
                new TargetPoint(
                    this.worldObj.provider.dimensionId,
                    this.xCoord,
                    this.yCoord,
                    this.zCoord,
                    100.0
                )
            );
        }
    }

    @Override
    public boolean isAlive() {
        return this.getHealth() > 0 || this.isInvul();
    }

    @Override
    public boolean onDamageTaken(final DamageSource source, final int amount) {
        if (this.isInvul()) {
            return false;
        }

        if (source != null && source.equals(DamageSource.onFire)) {
            return true;
        }

        this.health -= amount;

        if (this.health <= 0) {
            this.destroy(true);
        } else {
            ICBMSentry.channel.sendToAllAround(
                this.getStatsPacket(),
                new TargetPoint(
                    this.worldObj.provider.dimensionId,
                    this.xCoord,
                    this.yCoord,
                    this.zCoord,
                    100.0
                )
            );
        }

        return true;
    }

    public EntityTileDamagable getDamageEntity() {
        return this.damageEntity;
    }

    public void setDamageEntity(final EntityTileDamagable damageEntity) {
        this.damageEntity = damageEntity;
    }

    public void updateRotation() {
        final float yawDifference = Math.abs(
            LookHelper.getAngleDif(this.currentRotationYaw, this.wantedRotationYaw)
        );

        if (yawDifference > 0.001f) {
            final float speedYaw = Math.min(this.getRotationSpeed(), yawDifference);

            if (this.currentRotationYaw > this.wantedRotationYaw) {
                this.currentRotationYaw -= speedYaw;
            } else {
                this.currentRotationYaw += speedYaw;
            }

            if (Math.abs(this.currentRotationYaw - this.wantedRotationYaw)
                <= speedYaw + 0.1) {
                this.currentRotationYaw = this.wantedRotationYaw;
            }
        }

        final float pitchDifference = Math.abs(
            LookHelper.getAngleDif(this.currentRotationPitch, this.wantedRotationPitch)
        );

        if (pitchDifference > 0.001f) {
            final float speedPitch = Math.min(this.getRotationSpeed(), pitchDifference);

            if (this.currentRotationPitch > this.wantedRotationPitch) {
                this.currentRotationPitch -= speedPitch;
            } else {
                this.currentRotationPitch += speedPitch;
            }

            if (Math.abs(this.currentRotationPitch - this.wantedRotationPitch)
                <= speedPitch + 0.1) {
                this.currentRotationPitch = this.wantedRotationPitch;
            }
        }

        if (Math.abs(this.currentRotationPitch - this.wantedRotationPitch) <= 0.001f
            && Math.abs(this.currentRotationYaw - this.wantedRotationYaw) <= 0.001f) {
            ++this.lastRotateTick;
        }

        this.currentRotationPitch
            = MathHelper.wrapAngleTo180_float(this.currentRotationPitch);
        this.wantedRotationYaw = MathHelper.wrapAngleTo180_float(this.wantedRotationYaw);
        this.wantedRotationPitch
            = MathHelper.wrapAngleTo180_float(this.wantedRotationPitch);
    }

    public float getRotationSpeed() {
        return Float.MAX_VALUE;
    }

    @Override
    public void setRotation(final float yaw, final float pitch) {
        this.wantedRotationYaw = MathHelper.wrapAngleTo180_float(yaw);

        if (!this.allowFreePitch) {
            this.wantedRotationPitch = Math.max(
                Math.min(MathHelper.wrapAngleTo180_float(pitch), this.maxPitch),
                this.minPitch
            );
        } else {
            this.wantedRotationPitch = MathHelper.wrapAngleTo180_float(pitch);
        }
    }

    public void rotateTo(final float wantedRotationYaw, final float wantedRotationPitch) {
        if (!this.worldObj.isRemote && this.lastRotateTick > 0
            && (this.wantedRotationYaw != wantedRotationYaw
                || this.wantedRotationPitch != wantedRotationPitch)) {
            this.setRotation(wantedRotationYaw, wantedRotationPitch);
            this.lastRotateTick = 0;

            if (!this.worldObj.isRemote) {
                ICBMSentry.channel.sendToAllAround(
                    this.getRotationPacket(),
                    new TargetPoint(
                        this.worldObj.provider.dimensionId,
                        this.xCoord,
                        this.yCoord,
                        this.zCoord,
                        50.0
                    )
                );
            }
        }
    }

    public void cancelRotation() {
        this.setRotation(this.currentRotationYaw, this.currentRotationPitch);
    }

    public void drawParticleStreamTo(final Vector3 endPosition) {
        if (this.worldObj.isRemote) {
            final Vector3 startPosition = this.getMuzzle();
            final Vector3 direction = CalculationHelper.getDeltaPositionFromRotation(
                this.currentRotationYaw, this.currentRotationPitch
            );
            final double xoffset = 0.0;
            final double yoffset = 0.0;
            final double zoffset = 0.0;
            Vector3 horzdir = direction.normalize();
            horzdir.y = 0.0;
            horzdir = horzdir.normalize();

            for (double cx = startPosition.x + direction.x * xoffset
                     - direction.y * horzdir.x * yoffset - horzdir.z * zoffset,
                        cy = startPosition.y + direction.y * xoffset
                     + (1.0 - Math.abs(direction.y)) * yoffset,
                        cz = startPosition.z + direction.x * xoffset
                     - direction.y * horzdir.x * yoffset + horzdir.x * zoffset,
                        dx = endPosition.x - cx,
                        dy = endPosition.y - cy,
                        dz = endPosition.z - cz,
                        ratio = Math.sqrt(dx * dx + dy * dy + dz * dz);
                 Math.abs(cx - endPosition.x) > Math.abs(dx / ratio);
                 cx += dx * 0.1 / ratio, cy += dy * 0.1 / ratio, cz += dz * 0.1 / ratio) {
                this.worldObj.spawnParticle("townaura", cx, cy, cz, 0.0, 0.0, 0.0);
            }
        }
    }

    public abstract void renderShot(final Vector3 p0);

    public abstract void playFiringSound();

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return TileEntity.INFINITE_EXTENT_AABB;
    }

    public Vector3 getCenter() {
        return new Vector3(this).add(0.5);
    }
}
