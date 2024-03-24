package icbm.zhapin.daodan;

import java.util.Random;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import icbm.api.ILauncherContainer;
import icbm.api.IMissile;
import icbm.api.IMissileLockable;
import icbm.api.RadarRegistry;
import icbm.api.explosion.IExplosive;
import icbm.api.sentry.IAATarget;
import icbm.core.MainBase;
import icbm.zhapin.ICBMExplosion;
import icbm.zhapin.jiqi.TCruiseLauncher;
import icbm.zhapin.zhapin.ZhaPin;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import universalelectricity.core.vector.Vector2;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.TranslationHelper;

public class EMissile extends Entity
    implements IMissileLockable, IEntityAdditionalSpawnData, IMissile, IAATarget {
    public static final float THRUST = 0.012f;
    public int missileId;
    public int skyHeight;
    public Vector3 target;
    public Vector3 origin;
    public Vector3 launcherPos;
    public boolean exploded;
    public int explosionHeight;
    public int flyingTicks;
    public double xDiff;
    public double yDiff;
    public double zDiff;
    public double targetDistance;
    public float flightDuration;
    public float acceleration;
    public int protectionTicks;
    private ForgeChunkManager.Ticket chunkTicket;
    public Entity lockedTarget;
    public boolean didTargetLockBefore;
    public int targetEntityId;
    public int missileCount;
    public double missileHeight;
    private boolean setExplode;
    private boolean setNormalExplode;
    public MissileType missileType;
    public Vector3 smallMissileMotion;
    private double startHeight;
    protected final IUpdatePlayerListBox sound;

    public EMissile(final World par1World) {
        super(par1World);
        this.missileId = 0;
        this.skyHeight = 200;
        this.target = null;
        this.origin = null;
        this.launcherPos = null;
        this.exploded = false;
        this.explosionHeight = 0;
        this.flyingTicks = -1;
        this.protectionTicks = 2;
        this.didTargetLockBefore = false;
        this.targetEntityId = -1;
        this.missileCount = 0;
        this.missileHeight = 2.0;
        this.missileType = MissileType.MISSILE;
        this.smallMissileMotion = new Vector3();
        this.startHeight = 3.0;
        this.setSize(1.0f, 1.0f);
        super.renderDistanceWeight = 3.0;
        super.isImmuneToFire = true;
        super.ignoreFrustumCheck = true;
        this.sound
            = ((super.worldObj != null) ? ICBMExplosion.proxy.getDaoDanShengYin(this)
                                        : null);
    }

    public EMissile(
        final World par1World,
        final Vector3 diDian,
        final Vector3 faSheQiDiDian,
        final int missileId
    ) {
        this(par1World);
        this.missileId = missileId;
        this.origin = diDian;
        this.launcherPos = faSheQiDiDian;
        this.setPosition(this.origin.x, this.origin.y, this.origin.z);
        this.setRotation(0.0f, 90.0f);
    }

    public EMissile(
        final World par1World,
        final Vector3 diDian,
        final int haoMa,
        final float yaw,
        final float pitch
    ) {
        this(par1World);
        this.missileId = haoMa;
        this.origin = diDian;
        this.launcherPos = diDian;
        this.missileType = MissileType.ROCKET;
        this.protectionTicks = 10;
        this.setPosition(this.origin.x, this.origin.y, this.origin.z);
        this.setRotation(yaw, pitch);
    }

    @Override
    public String getCommandSenderName() {
        if (this.missileId >= 100) {
            return TranslationHelper.getLocal(
                "icbm.missile." + MissileBase.list[this.missileId].getUnlocalizedName()
                + ".name"
            );
        }

        return TranslationHelper.getLocal(
            "icbm.missile." + ZhaPin.list[this.missileId].getUnlocalizedName() + ".name"
        );
    }

    @Override
    public void writeSpawnData(final ByteBuf data) {
        try {
            data.writeInt(this.missileId);
            data.writeInt(this.missileType.ordinal());
            data.writeDouble(this.origin.x);
            data.writeDouble(this.origin.y);
            data.writeDouble(this.origin.z);
            data.writeInt(this.launcherPos.intX());
            data.writeInt(this.launcherPos.intY());
            data.writeInt(this.launcherPos.intZ());
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readSpawnData(final ByteBuf data) {
        try {
            this.missileId = data.readInt();
            this.missileType = MissileType.values()[data.readInt()];
            this.origin
                = new Vector3(data.readDouble(), data.readDouble(), data.readDouble());
            this.launcherPos
                = new Vector3(data.readInt(), data.readInt(), data.readInt());
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public void launch(final Vector3 target) {
        this.origin = new Vector3(this);
        this.target = target;
        this.explosionHeight = this.target.intY();
        MissileBase.list[this.missileId].launch(this);
        this.flyingTicks = 0;
        this.jiSuan();
        super.worldObj.playSoundAtEntity(
            (Entity) this,
            "icbm:missilelaunch",
            4.0f,
            (1.0f
             + (super.worldObj.rand.nextFloat() - super.worldObj.rand.nextFloat()) * 0.2f)
                * 0.7f
        );
        RadarRegistry.register(this);
        MainBase.LOGGER.info(
            "Launching " + this.getCommandSenderName() + " from " + this.origin.intX()
            + ", " + this.origin.intY() + ", " + this.origin.intZ() + " to "
            + this.target.intX() + ", " + this.target.intY() + ", " + this.target.intZ()
        );
    }

    public void launch(final Vector3 target, final int height) {
        this.startHeight = height;
        this.launch(target);
    }

    public void jiSuan() {
        if (this.target != null) {
            this.xDiff = this.target.x - this.origin.x;
            this.yDiff = this.target.y - this.origin.y;
            this.zDiff = this.target.z - this.origin.z;
            this.targetDistance
                = Vector2.distance(this.origin.toVector2(), this.target.toVector2());
            this.skyHeight = 160 + (int) (this.targetDistance * 3.0);
            this.flightDuration
                = (float) Math.max(100.0, 2.0 * this.targetDistance) - this.flyingTicks;
            this.acceleration
                = this.skyHeight * 2.0f / (this.flightDuration * this.flightDuration);
        }
    }

    @Override
    public void entityInit() {
        super.dataWatcher.addObject(16, (Object) (-1));
        this.daoDanInit(ForgeChunkManager.requestTicket(
            (Object) ICBMExplosion.instance, super.worldObj, ForgeChunkManager.Type.ENTITY
        ));
    }

    public void daoDanInit(final ForgeChunkManager.Ticket ticket) {
        if (ticket != null) {
            if (this.chunkTicket == null) {
                (this.chunkTicket = ticket).bindEntity((Entity) this);
                this.chunkTicket.getModData();
            }

            ForgeChunkManager.forceChunk(
                this.chunkTicket,
                new ChunkCoordIntPair(super.chunkCoordX, super.chunkCoordZ)
            );
        }
    }

    public void updateLoadChunk(final int newChunkX, final int newChunkZ) {
        if (!super.worldObj.isRemote && MainBase.ZAI_KUAI && this.chunkTicket != null) {
            for (int x = -2; x <= 2; ++x) {
                for (int z = -2; z <= 2; ++z) {
                    super.worldObj.getChunkFromChunkCoords(newChunkX + x, newChunkZ + z);
                }
            }

            for (int x = -1; x <= 1; ++x) {
                for (int z = -1; z <= 1; ++z) {
                    ForgeChunkManager.forceChunk(
                        this.chunkTicket,
                        new ChunkCoordIntPair(newChunkX + x, newChunkZ + z)
                    );
                }
            }
        }
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public void onUpdate() {
        if (this.sound != null) {
            this.sound.update();
        }

        if (!super.worldObj.isRemote
            && ICBMExplosion.shiBaoHu(
                super.worldObj,
                new Vector3(this),
                ZhaPin.ZhaPinType.DAO_DAN,
                this.missileId
            )) {
            if (this.flyingTicks >= 0) {
                this.dropMissileAsItem();
            }

            this.setDead();
            return;
        }

        if (this.setNormalExplode) {
            this.normalExplode();
            return;
        }

        if (this.setExplode) {
            this.explode();
            return;
        }

        try {
            if (super.worldObj.isRemote) {
                this.flyingTicks = super.dataWatcher.getWatchableObjectInt(16);
            } else {
                super.dataWatcher.updateObject(16, (Object) this.flyingTicks);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

        this.updateLoadChunk(super.chunkCoordX, super.chunkCoordZ);

        if (this.flyingTicks >= 0) {
            RadarRegistry.register(this);

            if (!super.worldObj.isRemote) {
                if (this.missileType == MissileType.SMALL_MISSILE
                    || this.missileType == MissileType.ROCKET) {
                    if (this.flyingTicks == 0 && this.smallMissileMotion != null) {
                        this.smallMissileMotion = new Vector3(
                            this.xDiff / (this.flightDuration * 0.3),
                            this.yDiff / (this.flightDuration * 0.3),
                            this.zDiff / (this.flightDuration * 0.3)
                        );
                    }

                    super.motionX = this.smallMissileMotion.x;
                    super.motionY = this.smallMissileMotion.y;
                    super.motionZ = this.smallMissileMotion.z;
                    super.rotationPitch = (float
                    ) (Math.atan(
                           super.motionY
                           / Math.sqrt(
                               super.motionX * super.motionX
                               + super.motionZ * super.motionZ
                           )
                       )
                       * 180.0 / 3.141592653589793);
                    super.rotationYaw = (float
                    ) (Math.atan2(super.motionX, super.motionZ) * 180.0
                       / 3.141592653589793);
                    MissileBase.list[this.missileId].update(this);
                    final Block block = super.worldObj.getBlock(
                        (int) super.posX, (int) super.posY, (int) super.posZ
                    );

                    if (this.protectionTicks <= 0
                        && ((block != Blocks.air && !(block instanceof BlockLiquid))
                            || super.posY > 1000.0 || super.isCollided
                            || this.flyingTicks > 20000
                            || (super.motionX == 0.0 && super.motionY == 0.0
                                && super.motionZ == 0.0))) {
                        this.explode();
                        return;
                    }

                    this.moveEntity(super.motionX, super.motionY, super.motionZ);
                } else if (this.startHeight > 0.0) {
                    super.motionY = 0.012f * this.flyingTicks * (this.flyingTicks / 2);
                    super.motionX = 0.0;
                    super.motionZ = 0.0;
                    this.startHeight -= super.motionY;
                    this.moveEntity(super.motionX, super.motionY, super.motionZ);

                    if (this.startHeight <= 0.0) {
                        super.motionY = this.acceleration * (this.flightDuration / 2.0f);
                        super.motionX = this.xDiff / this.flightDuration;
                        super.motionZ = this.zDiff / this.flightDuration;
                    }
                } else {
                    super.motionY -= this.acceleration;
                    super.rotationPitch = (float
                    ) (Math.atan(
                           super.motionY
                           / Math.sqrt(
                               super.motionX * super.motionX
                               + super.motionZ * super.motionZ
                           )
                       )
                       * 180.0 / 3.141592653589793);
                    super.rotationYaw = (float
                    ) (Math.atan2(super.motionX, super.motionZ) * 180.0
                       / 3.141592653589793);
                    MissileBase.list[this.missileId].update(this);
                    this.moveEntity(super.motionX, super.motionY, super.motionZ);

                    if (super.isCollided) {
                        this.explode();
                    }

                    if (this.explosionHeight > 0 && super.motionY < 0.0) {
                        final Block blockBelow = super.worldObj.getBlock(
                            (int) super.posX,
                            (int) super.posY - this.explosionHeight,
                            (int) super.posZ
                        );

                        if (blockBelow != Blocks.air) {
                            this.explosionHeight = 0;
                            this.explode();
                        }
                    }
                }
            } else {
                super.rotationPitch = (float
                ) (Math.atan(
                       super.motionY
                       / Math.sqrt(
                           super.motionX * super.motionX + super.motionZ * super.motionZ
                       )
                   )
                   * 180.0 / 3.141592653589793);
                super.rotationYaw = (float
                ) (Math.atan2(super.motionX, super.motionZ) * 180.0 / 3.141592653589793);
            }

            super.lastTickPosX = super.posX;
            super.lastTickPosY = super.posY;
            super.lastTickPosZ = super.posZ;
            this.spawnMissileSmoke();
            --this.protectionTicks;
            ++this.flyingTicks;
        } else if (this.missileType != MissileType.ROCKET) {
            final ILauncherContainer launcher = this.getLauncher();

            if (launcher != null) {
                launcher.setContainingMissile(this);

                if (launcher instanceof TCruiseLauncher) {
                    this.missileType = MissileType.SMALL_MISSILE;
                    super.noClip = true;

                    if (super.worldObj.isRemote) {
                        super.rotationYaw
                            = -((TCruiseLauncher) launcher).rotationYaw + 90.0f;
                        super.rotationPitch = ((TCruiseLauncher) launcher).rotationPitch;
                    }

                    super.posY = ((TCruiseLauncher) launcher).yCoord + 1;
                }
            } else {
                this.setDead();
            }
        }

        super.onUpdate();
    }

    public ILauncherContainer getLauncher() {
        if (this.launcherPos != null) {
            final TileEntity tileEntity
                = this.launcherPos.getTileEntity((IBlockAccess) super.worldObj);

            if (tileEntity != null && tileEntity instanceof ILauncherContainer
                && !tileEntity.isInvalid()) {
                return (ILauncherContainer) tileEntity;
            }
        }

        return null;
    }

    @Override
    public boolean interactFirst(final EntityPlayer entityPlayer) {
        if (MissileBase.list[this.missileId] != null
            && MissileBase.list[this.missileId].onInteract(this, entityPlayer)) {
            return true;
        }

        if (!super.worldObj.isRemote
            && (super.riddenByEntity == null || super.riddenByEntity == entityPlayer)) {
            entityPlayer.mountEntity((Entity) this);
            return true;
        }

        return false;
    }

    @Override
    public double getMountedYOffset() {
        if (this.flightDuration <= 0.0f && this.missileType == MissileType.MISSILE) {
            return super.height;
        }

        if (this.missileType == MissileType.SMALL_MISSILE) {
            return super.height * 0.1;
        }

        return super.height / 2.0f + super.motionY;
    }

    private void spawnMissileSmoke() {
        if (super.worldObj.isRemote) {
            final Vector3 position = new Vector3(this);
            final double distance = -this.missileHeight - 0.20000000298023224;
            final Vector3 delta = new Vector3();
            delta.y = Math.sin(Math.toRadians(super.rotationPitch)) * distance;
            final double dH = Math.cos(Math.toRadians(super.rotationPitch)) * distance;
            delta.x = Math.sin(Math.toRadians(super.rotationYaw)) * dH;
            delta.z = Math.cos(Math.toRadians(super.rotationYaw)) * dH;
            position.add(delta);
            super.worldObj.spawnParticle(
                "flame", position.x, position.y, position.z, 0.0, 0.0, 0.0
            );
            ICBMExplosion.proxy.spawnParticle(
                "missile_smoke", super.worldObj, position, 4.0f, 2.0
            );
            position.multiply(1.0 - 0.001 * Math.random());
            ICBMExplosion.proxy.spawnParticle(
                "missile_smoke", super.worldObj, position, 4.0f, 2.0
            );
            position.multiply(1.0 - 0.001 * Math.random());
            ICBMExplosion.proxy.spawnParticle(
                "missile_smoke", super.worldObj, position, 4.0f, 2.0
            );
            position.multiply(1.0 - 0.001 * Math.random());
            ICBMExplosion.proxy.spawnParticle(
                "missile_smoke", super.worldObj, position, 4.0f, 2.0
            );
        }
    }

    @Override
    public AxisAlignedBB getCollisionBox(final Entity entity) {
        if (!(entity instanceof EntityItem) && entity != super.riddenByEntity
            && this.protectionTicks <= 0) {
            if (entity instanceof EMissile) {
                ((EMissile) entity).setNormalExplode();
            }

            this.setExplode();
        }

        return null;
    }

    public Vector3 getPredictedPosition(final int t) {
        final Vector3 guJiDiDian = new Vector3(this);
        double tempMotionY = super.motionY;

        if (this.flyingTicks > 20) {
            for (int i = 0; i < t; ++i) {
                if (this.missileType == MissileType.SMALL_MISSILE
                    || this.missileType == MissileType.ROCKET) {
                    final Vector3 vector3 = guJiDiDian;
                    vector3.x += this.smallMissileMotion.x;
                    final Vector3 vector4 = guJiDiDian;
                    vector4.y += this.smallMissileMotion.y;
                    final Vector3 vector5 = guJiDiDian;
                    vector5.z += this.smallMissileMotion.z;
                } else {
                    final Vector3 vector6 = guJiDiDian;
                    vector6.x += super.motionX;
                    final Vector3 vector7 = guJiDiDian;
                    vector7.y += tempMotionY;
                    final Vector3 vector8 = guJiDiDian;
                    vector8.z += super.motionZ;
                    tempMotionY -= this.acceleration;
                }
            }
        }

        return guJiDiDian;
    }

    public void setNormalExplode() {
        this.setNormalExplode = true;
    }

    public void setExplode() {
        this.setExplode = true;
    }

    @Override
    public void setDead() {
        RadarRegistry.unregister(this);

        if (this.chunkTicket != null) {
            ForgeChunkManager.releaseTicket(this.chunkTicket);
        }

        super.setDead();

        if (this.sound != null) {
            this.sound.update();
        }
    }

    public void explode() {
        try {
            if (!this.exploded && !this.isDead) {
                if (this.missileId == 0) {
                    if (!super.worldObj.isRemote) {
                        super.worldObj.createExplosion(
                            (Entity) this, super.posX, super.posY, super.posZ, 5.0f, true
                        );
                    }
                } else {
                    MissileBase.list[this.missileId].onExplode(this);
                }

                this.exploded = true;
                MainBase.LOGGER.info(
                    this.getCommandSenderName() + " exploded in " + (int) super.posX
                    + ", " + (int) super.posY + ", " + (int) super.posZ
                );
            }

            this.setDead();
        } catch (final Exception e) {
            MainBase.LOGGER.severe(
                "Missile failed to explode properly. Report this to the developers."
            );
            e.printStackTrace();
        }
    }

    public void normalExplode() {
        if (!this.exploded) {
            this.exploded = true;

            if (!super.worldObj.isRemote) {
                super.worldObj.createExplosion(
                    (Entity) this, super.posX, super.posY, super.posZ, 5.0f, true
                );
            }

            this.setDead();
        }
    }

    public void dropMissileAsItem() {
        if (!this.exploded && !super.worldObj.isRemote) {
            EntityItem entityItem;

            if (this.missileId >= 100) {
                entityItem = new EntityItem(
                    super.worldObj,
                    super.posX,
                    super.posY,
                    super.posZ,
                    new ItemStack(ICBMExplosion.itTeBieDaoDan, 1, this.missileId - 100)
                );
            } else {
                entityItem = new EntityItem(
                    super.worldObj,
                    super.posX,
                    super.posY,
                    super.posZ,
                    new ItemStack(ICBMExplosion.itDaoDan, 1, this.missileId)
                );
            }

            final float var13 = 0.05f;
            final Random random = new Random();
            ((Entity) entityItem).motionX = (float) random.nextGaussian() * var13;
            ((Entity) entityItem).motionY = (float) random.nextGaussian() * var13 + 0.2f;
            ((Entity) entityItem).motionZ = (float) random.nextGaussian() * var13;
            super.worldObj.spawnEntityInWorld((Entity) entityItem);
        }

        this.setDead();
    }

    @Override
    protected void readEntityFromNBT(final NBTTagCompound nbt) {
        this.origin = Vector3.readFromNBT(nbt.getCompoundTag("kaiShi"));
        this.target = Vector3.readFromNBT(nbt.getCompoundTag("muBiao"));
        this.launcherPos = Vector3.readFromNBT(nbt.getCompoundTag("faSheQi"));
        this.acceleration = nbt.getFloat("jiaSu");
        this.explosionHeight = nbt.getInteger("baoZhaGaoDu");
        this.missileId = nbt.getInteger("haoMa");
        this.flyingTicks = nbt.getInteger("feiXingTick");
        this.startHeight = nbt.getDouble("qiFeiGaoDu");
        this.missileType = MissileType.values()[nbt.getInteger("xingShi")];
    }

    @Override
    protected void writeEntityToNBT(final NBTTagCompound nbt) {
        nbt.setTag("kaiShi", this.origin.writeToNBT(new NBTTagCompound()));

        if (this.target != null) {
            nbt.setTag("muBiao", this.target.writeToNBT(new NBTTagCompound()));
        }

        if (this.launcherPos != null) {
            nbt.setTag("faSheQi", this.launcherPos.writeToNBT(new NBTTagCompound()));
        }

        nbt.setFloat("jiaSu", this.acceleration);
        nbt.setInteger("haoMa", this.missileId);
        nbt.setInteger("baoZhaGaoDu", this.explosionHeight);
        nbt.setInteger("feiXingTick", this.flyingTicks);
        nbt.setDouble("qiFeiGaoDu", this.startHeight);
        nbt.setInteger("xingShi", this.missileType.ordinal());
    }

    @Override
    public float getShadowSize() {
        return 1.0f;
    }

    @Override
    public int getTicksInAir() {
        return this.flyingTicks;
    }

    public IExplosive getExplosiveType() {
        if (this.missileId > ZhaPin.list.length) {
            return MissileBase.list[this.missileId];
        }

        return ZhaPin.list[this.missileId];
    }

    public boolean canLock(final IMissile missile) {
        return this.flyingTicks > 0;
    }

    public void destroyCraft() {
        this.normalExplode();
    }

    public int doDamage(final int damage) {
        return -1;
    }

    @Override
    public boolean canBeTargeted(final Object turret) {
        return this.getTicksInAir() > 0;
    }

    public enum MissileType {
        MISSILE,
        SMALL_MISSILE,
        ROCKET;
    }
}
