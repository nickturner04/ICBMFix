package icbm.zhapin;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ESuiPian extends EntityArrow implements IEntityAdditionalSpawnData {
    private int xTile;
    private int yTile;
    private int zTile;
    private Block inTile;
    private int inData;
    private boolean inGround;
    public boolean doesArrowBelongToPlayer;
    public boolean isExplosive;
    public boolean isAnvil;
    private boolean isExploding;
    public int arrowShake;
    private int ticksInAir;
    private int knowBackStrength;
    public boolean arrowCritical;
    public float explosionSize;

    public ESuiPian(final World par1World) {
        super(par1World);
        this.xTile = -1;
        this.yTile = -1;
        this.zTile = -1;
        this.inTile = Blocks.air;
        this.inData = 0;
        this.inGround = false;
        this.doesArrowBelongToPlayer = false;
        this.isExploding = false;
        this.arrowShake = 0;
        this.ticksInAir = 0;
        this.arrowCritical = false;
        this.explosionSize = 1.5f;
        this.setSize(0.5f, 0.5f);
    }

    public ESuiPian(
        final World par1World,
        final double x,
        final double y,
        final double z,
        final boolean isExplosive,
        final boolean isAnvil
    ) {
        super(par1World);
        this.xTile = -1;
        this.yTile = -1;
        this.zTile = -1;
        this.inTile = Blocks.air;
        this.inData = 0;
        this.inGround = false;
        this.doesArrowBelongToPlayer = false;
        this.isExploding = false;
        this.arrowShake = 0;
        this.ticksInAir = 0;
        this.arrowCritical = false;
        this.explosionSize = 1.5f;
        this.setPosition(x, y, z);
        super.yOffset = 0.0f;
        this.isExplosive = isExplosive;
        this.isAnvil = isAnvil;

        if (this.isAnvil) {
            this.setSize(1.0f, 1.0f);
        } else {
            this.setSize(0.5f, 0.5f);
        }
    }

    @Override
    public void writeSpawnData(final ByteBuf data) {
        data.writeBoolean(this.isExplosive);
        data.writeBoolean(this.isAnvil);
    }

    @Override
    public void readSpawnData(final ByteBuf data) {
        this.isExplosive = data.readBoolean();
        this.isAnvil = data.readBoolean();
    }

    @Override
    protected void entityInit() {}

    @Override
    public String getCommandSenderName() {
        return "Fragments";
    }

    @Override
    public void setThrowableHeading(
        double par1, double par3, double par5, final float par7, final float par8
    ) {
        final float var9
            = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
        par1 /= var9;
        par3 /= var9;
        par5 /= var9;
        par1 += super.rand.nextGaussian() * 0.007499999832361937 * par8;
        par3 += super.rand.nextGaussian() * 0.007499999832361937 * par8;
        par5 += super.rand.nextGaussian() * 0.007499999832361937 * par8;
        par1 *= par7;
        par3 *= par7;
        par5 *= par7;
        super.motionX = par1;
        super.motionY = par3;
        super.motionZ = par5;
        final float var10 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
        final float n = (float) (Math.atan2(par1, par5) * 180.0 / 3.141592653589793);
        super.rotationYaw = n;
        super.prevRotationYaw = n;
        final float n2 = (float) (Math.atan2(par3, var10) * 180.0 / 3.141592653589793);
        super.rotationPitch = n2;
        super.prevRotationPitch = n2;
    }

    @Override
    public void setVelocity(final double par1, final double par3, final double par5) {
        super.motionX = par1;
        super.motionY = par3;
        super.motionZ = par5;

        if (super.prevRotationPitch == 0.0f && super.prevRotationYaw == 0.0f) {
            final float var7 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
            final float n = (float) (Math.atan2(par1, par5) * 180.0 / 3.141592653589793);
            super.rotationYaw = n;
            super.prevRotationYaw = n;
            final float n2 = (float) (Math.atan2(par3, var7) * 180.0 / 3.141592653589793);
            super.rotationPitch = n2;
            super.prevRotationPitch = n2;
            super.prevRotationPitch = super.rotationPitch;
            super.prevRotationYaw = super.rotationYaw;
            this.setLocationAndAngles(
                super.posX, super.posY, super.posZ, super.rotationYaw, super.rotationPitch
            );
        }
    }

    private void explode() {
        if (!this.isExploding && !super.worldObj.isRemote) {
            this.isExploding = true;
            super.worldObj.createExplosion(
                (Entity) this,
                (double) this.xTile,
                (double) this.yTile,
                (double) this.zTile,
                this.explosionSize,
                true
            );
            this.setDead();
        }
    }

    @Override
    public void onUpdate() {
        //super.onUpdate();
        if (this.isAnvil) {
            final ArrayList<Entity> entities
                = new ArrayList<>(super.worldObj.getEntitiesWithinAABBExcludingEntity(
                    this, super.boundingBox
                ));

            for (final Entity entity : entities) {
                entity.attackEntityFrom(DamageSource.anvil, 15);
            }
        }

        if (super.prevRotationPitch == 0.0f && super.prevRotationYaw == 0.0f) {
            final float var6 = MathHelper.sqrt_double(
                super.motionX * super.motionX + super.motionZ * super.motionZ
            );
            final float n = (float
            ) (Math.atan2(super.motionX, super.motionZ) * 180.0 / 3.141592653589793);
            super.rotationYaw = n;
            super.prevRotationYaw = n;
            final float n2
                = (float) (Math.atan2(super.motionY, var6) * 180.0 / 3.141592653589793);
            super.rotationPitch = n2;
            super.prevRotationPitch = n2;
        }

        Block var7 = super.worldObj.getBlock(this.xTile, this.yTile, this.zTile);

        if (var7 != Blocks.air) {
            var7.setBlockBoundsBasedOnState(
                (IBlockAccess) super.worldObj, this.xTile, this.yTile, this.zTile
            );
            final AxisAlignedBB var8 = var7.getCollisionBoundingBoxFromPool(
                super.worldObj, this.xTile, this.yTile, this.zTile
            );

            if (var8 != null
                && var8.isVecInside(
                    Vec3.createVectorHelper(super.posX, super.posY, super.posZ)
                )) {
                this.inGround = true;
            }
        }

        if (this.arrowShake > 0) {
            --this.arrowShake;
        }

        if (this.inGround) {
            var7 = super.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
            final int var9
                = super.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);

            if (var7 == this.inTile && var9 == this.inData) {
                if (this.isExplosive) {
                    this.explode();
                } else {
                    if (this.isAnvil && super.worldObj.rand.nextFloat() > 0.5f) {
                        super.worldObj.playAuxSFX(
                            1022, (int) super.posX, (int) super.posY, (int) super.posZ, 0
                        );
                    }

                    this.setDead();
                }
            } else {
                this.inGround = false;
                super.motionX *= super.rand.nextFloat() * 0.2f;
                super.motionY *= super.rand.nextFloat() * 0.2f;
                super.motionZ *= super.rand.nextFloat() * 0.2f;
                this.ticksInAir = 0;
            }
        } else {
            ++this.ticksInAir;
            Vec3 var10 = Vec3.createVectorHelper(super.posX, super.posY, super.posZ);
            Vec3 var11 = Vec3.createVectorHelper(
                super.posX + super.motionX,
                super.posY + super.motionY,
                super.posZ + super.motionZ
            );
            MovingObjectPosition movingObjPos
                = super.worldObj.rayTraceBlocks(var10, var11, false);
            var10 = Vec3.createVectorHelper(super.posX, super.posY, super.posZ);
            var11 = Vec3.createVectorHelper(
                super.posX + super.motionX,
                super.posY + super.motionY,
                super.posZ + super.motionZ
            );

            if (movingObjPos != null) {
                var11 = Vec3.createVectorHelper(
                    movingObjPos.hitVec.xCoord,
                    movingObjPos.hitVec.yCoord,
                    movingObjPos.hitVec.zCoord
                );
            }

            Entity var12 = null;
            final List<Entity> var13
                = super.worldObj.getEntitiesWithinAABBExcludingEntity(
                    (Entity) this,
                    super.boundingBox
                        .addCoord(super.motionX, super.motionY, super.motionZ)
                        .expand(1.0, 1.0, 1.0)
                );
            double var14 = 0.0;

            for (int var15 = 0; var15 < var13.size(); ++var15) {
                final Entity var16 = var13.get(var15);

                if (var16.canBeCollidedWith() && this.ticksInAir >= 5) {
                    final float var17 = 0.3f;
                    final AxisAlignedBB var18 = var16.boundingBox.expand(
                        (double) var17, (double) var17, (double) var17
                    );
                    final MovingObjectPosition var19
                        = var18.calculateIntercept(var10, var11);

                    if (var19 != null) {
                        final double var20 = var10.distanceTo(var19.hitVec);

                        if (var20 < var14 || var14 == 0.0) {
                            var12 = var16;
                            var14 = var20;
                        }
                    }
                }
            }

            if (var12 != null) {
                movingObjPos = new MovingObjectPosition(var12);
            }

            if (movingObjPos != null) {
                if (movingObjPos.entityHit != null) {
                    final float sqrt_double;
                    final float speed = sqrt_double = MathHelper.sqrt_double(
                        super.motionX * super.motionX + super.motionY * super.motionY
                        + super.motionZ * super.motionZ
                    );
                    this.getClass();
                    int damage = (int) Math.ceil(sqrt_double * 11.0f);

                    if (this.arrowCritical) {
                        damage += super.rand.nextInt(damage / 2 + 2);
                    }

                    final DamageSource damageSource
                        = new EntityDamageSourceIndirect(
                              "arrow", (Entity) this, (Entity) this
                        )
                              .setProjectile();

                    if (this.isBurning()) {
                        movingObjPos.entityHit.setFire(5);
                    }

                    if (movingObjPos.entityHit.attackEntityFrom(damageSource, damage)) {
                        if (movingObjPos.entityHit instanceof EntityLiving) {
                            final EntityLiving var21
                                = (EntityLiving) movingObjPos.entityHit;

                            if (!super.worldObj.isRemote) {
                                var21.setArrowCountInEntity(
                                    var21.getArrowCountInEntity() + 1
                                );
                            }

                            if (this.knowBackStrength > 0) {
                                final float var22 = MathHelper.sqrt_double(
                                    super.motionX * super.motionX
                                    + super.motionZ * super.motionZ
                                );

                                if (var22 > 0.0f) {
                                    movingObjPos.entityHit.addVelocity(
                                        super.motionX * this.knowBackStrength
                                            * 0.6000000238418579 / var22,
                                        0.1,
                                        super.motionZ * this.knowBackStrength
                                            * 0.6000000238418579 / var22
                                    );
                                }
                            }
                        }

                        super.worldObj.playSoundAtEntity(
                            (Entity) this,
                            "random.bowhit",
                            1.0f,
                            1.2f / (super.rand.nextFloat() * 0.2f + 0.9f)
                        );
                        this.setDead();
                    } else {
                        super.motionX *= -0.10000000149011612;
                        super.motionY *= -0.10000000149011612;
                        super.motionZ *= -0.10000000149011612;
                        super.rotationYaw += 180.0f;
                        super.prevRotationYaw += 180.0f;
                        this.ticksInAir = 0;
                    }
                } else {
                    this.xTile = movingObjPos.blockX;
                    this.yTile = movingObjPos.blockY;
                    this.zTile = movingObjPos.blockZ;
                    this.inTile
                        = super.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
                    this.inData = super.worldObj.getBlockMetadata(
                        this.xTile, this.yTile, this.zTile
                    );
                    super.motionX = (float) (movingObjPos.hitVec.xCoord - super.posX);
                    super.motionY = (float) (movingObjPos.hitVec.yCoord - super.posY);
                    super.motionZ = (float) (movingObjPos.hitVec.zCoord - super.posZ);
                    final float speed = MathHelper.sqrt_double(
                        super.motionX * super.motionX + super.motionY * super.motionY
                        + super.motionZ * super.motionZ
                    );
                    super.posX -= super.motionX / speed * 0.05000000074505806;
                    super.posY -= super.motionY / speed * 0.05000000074505806;
                    super.posZ -= super.motionZ / speed * 0.05000000074505806;
                    super.worldObj.playSoundAtEntity(
                        (Entity) this,
                        "random.bowhit",
                        1.0f,
                        1.2f / (super.rand.nextFloat() * 0.2f + 0.9f)
                    );
                    this.inGround = true;
                    this.arrowShake = 7;
                    this.arrowCritical = false;
                }
            }

            if (this.arrowCritical) {
                for (int var15 = 0; var15 < 4; ++var15) {
                    super.worldObj.spawnParticle(
                        "crit",
                        super.posX + super.motionX * var15 / 4.0,
                        super.posY + super.motionY * var15 / 4.0,
                        super.posZ + super.motionZ * var15 / 4.0,
                        -super.motionX,
                        -super.motionY + 0.2,
                        -super.motionZ
                    );
                }
            }

            super.posX += super.motionX;
            super.posY += super.motionY;
            super.posZ += super.motionZ;
            final float speed = MathHelper.sqrt_double(
                super.motionX * super.motionX + super.motionZ * super.motionZ
            );
            super.rotationYaw = (float
            ) (Math.atan2(super.motionX, super.motionZ) * 180.0 / 3.141592653589793);
            super.rotationPitch
                = (float) (Math.atan2(super.motionY, speed) * 180.0 / 3.141592653589793);

            while (super.rotationPitch - super.prevRotationPitch < -180.0f) {
                super.prevRotationPitch -= 360.0f;
            }

            while (super.rotationPitch - super.prevRotationPitch >= 180.0f) {
                super.prevRotationPitch += 360.0f;
            }

            while (super.rotationYaw - super.prevRotationYaw < -180.0f) {
                super.prevRotationYaw -= 360.0f;
            }

            while (super.rotationYaw - super.prevRotationYaw >= 180.0f) {
                super.prevRotationYaw += 360.0f;
            }

            super.rotationPitch = super.prevRotationPitch
                + (super.rotationPitch - super.prevRotationPitch) * 0.2f;
            super.rotationYaw = super.prevRotationYaw
                + (super.rotationYaw - super.prevRotationYaw) * 0.2f;
            float var23 = 0.99f;
            final float var17 = 0.05f;

            if (this.isInWater()) {
                for (int var24 = 0; var24 < 4; ++var24) {
                    final float var25 = 0.25f;
                    super.worldObj.spawnParticle(
                        "bubble",
                        super.posX - super.motionX * var25,
                        super.posY - super.motionY * var25,
                        super.posZ - super.motionZ * var25,
                        super.motionX,
                        super.motionY,
                        super.motionZ
                    );
                }

                var23 = 0.8f;
            }

            super.motionX *= var23;
            super.motionY *= var23;
            super.motionZ *= var23;
            super.motionY -= var17;
            this.setPosition(super.posX, super.posY, super.posZ);
        }
    }

    @Override
    public void writeEntityToNBT(final NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.setShort("xTile", (short) this.xTile);
        par1NBTTagCompound.setShort("yTile", (short) this.yTile);
        par1NBTTagCompound.setShort("zTile", (short) this.zTile);
        par1NBTTagCompound.setInteger("inTile", Block.getIdFromBlock(this.inTile));
        par1NBTTagCompound.setByte("inData", (byte) this.inData);
        par1NBTTagCompound.setByte("shake", (byte) this.arrowShake);
        par1NBTTagCompound.setByte("inGround", (byte) (byte) (this.inGround ? 1 : 0));
        par1NBTTagCompound.setBoolean("isExplosive", this.isExplosive);
    }

    @Override
    public void readEntityFromNBT(final NBTTagCompound par1NBTTagCompound) {
        this.xTile = par1NBTTagCompound.getShort("xTile");
        this.yTile = par1NBTTagCompound.getShort("yTile");
        this.zTile = par1NBTTagCompound.getShort("zTile");
        this.inTile = Block.getBlockById(par1NBTTagCompound.getInteger("inTile"));
        this.inData = (par1NBTTagCompound.getByte("inData") & 0xFF);
        this.arrowShake = (par1NBTTagCompound.getByte("shake") & 0xFF);
        this.inGround = (par1NBTTagCompound.getByte("inGround") == 1);
        this.isExplosive = par1NBTTagCompound.getBoolean("isExplosive");
    }

    @Override
    public void applyEntityCollision(final Entity par1Entity) {
        super.applyEntityCollision(par1Entity);

        if (this.isExplosive && super.ticksExisted < 40) {
            this.explode();
        }
    }

    @Override
    public float getShadowSize() {
        return 0.0f;
    }

    @Override
    public boolean canAttackWithItem() {
        return false;
    }
}
