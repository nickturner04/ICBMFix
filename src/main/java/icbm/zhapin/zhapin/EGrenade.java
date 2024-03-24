package icbm.zhapin.zhapin;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import icbm.api.explosion.IExplosive;
import icbm.api.explosion.IExplosiveContainer;
import icbm.zhapin.ICBMExplosion;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import universalelectricity.core.vector.Vector3;

public class EGrenade
    extends Entity implements IExplosiveContainer, IEntityAdditionalSpawnData {
    protected EntityLivingBase thrower;
    public int haoMa;

    public EGrenade(final World par1World) {
        super(par1World);
        this.setSize(0.3f, 0.3f);
        super.renderDistanceWeight = 10.0;
    }

    public EGrenade(
        final World par1World, final Vector3 position, final int explosiveID
    ) {
        this(par1World);
        this.setPosition(position.x, position.y, position.z);
        this.haoMa = explosiveID;
    }

    public EGrenade(
        final World par1World,
        final EntityLivingBase par2EntityLiving,
        final int explosiveID,
        final float nengLiang
    ) {
        this(par1World);
        this.thrower = par2EntityLiving;
        this.setSize(0.25f, 0.25f);
        this.setLocationAndAngles(
            ((Entity) par2EntityLiving).posX,
            ((Entity) par2EntityLiving).posY + par2EntityLiving.getEyeHeight(),
            ((Entity) par2EntityLiving).posZ,
            ((Entity) par2EntityLiving).rotationYaw,
            ((Entity) par2EntityLiving).rotationPitch
        );
        super.posX -= MathHelper.cos(super.rotationYaw / 180.0f * 3.1415927f) * 0.16f;
        super.posY -= 0.10000000149011612;
        super.posZ -= MathHelper.sin(super.rotationYaw / 180.0f * 3.1415927f) * 0.16f;
        this.setPosition(super.posX, super.posY, super.posZ);
        super.yOffset = 0.0f;
        final float var3 = 0.4f;
        super.motionX = -MathHelper.sin(super.rotationYaw / 180.0f * 3.1415927f)
            * MathHelper.cos(super.rotationPitch / 180.0f * 3.1415927f) * var3;
        super.motionZ = MathHelper.cos(super.rotationYaw / 180.0f * 3.1415927f)
            * MathHelper.cos(super.rotationPitch / 180.0f * 3.1415927f) * var3;
        super.motionY = -MathHelper.sin(super.rotationPitch / 180.0f * 3.1415927f) * var3;
        this.setThrowableHeading(
            super.motionX, super.motionY, super.motionZ, 1.8f * nengLiang, 1.0f
        );
        this.haoMa = explosiveID;
    }

    @Override
    public String getCommandSenderName() {
        return "Grenade";
    }

    @Override
    public void writeSpawnData(final ByteBuf data) {
        data.writeInt(this.haoMa);
    }

    @Override
    public void readSpawnData(final ByteBuf data) {
        this.haoMa = data.readInt();
    }

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
        }
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    protected void entityInit() {}

    @Override
    public void onUpdate() {
        if (!super.worldObj.isRemote
            && ICBMExplosion.shiBaoHu(
                super.worldObj,
                new Vector3(this),
                ZhaPin.ZhaPinType.SHOU_LIU_DAN,
                this.haoMa
            )) {
            final float var6 = 0.7f;
            final double var7
                = super.worldObj.rand.nextFloat() * var6 + (1.0f - var6) * 0.5;
            final double var8
                = super.worldObj.rand.nextFloat() * var6 + (1.0f - var6) * 0.5;
            final double var9
                = super.worldObj.rand.nextFloat() * var6 + (1.0f - var6) * 0.5;
            final EntityItem var10 = new EntityItem(
                super.worldObj,
                super.posX + var7,
                super.posY + var8,
                super.posZ + var9,
                new ItemStack(ICBMExplosion.itShouLiuDan, this.haoMa, 1)
            );
            var10.delayBeforeCanPickup = 10;
            super.worldObj.spawnEntityInWorld((Entity) var10);
            this.setDead();
            return;
        }

        super.lastTickPosX = super.posX;
        super.lastTickPosY = super.posY;
        super.lastTickPosZ = super.posZ;
        super.onUpdate();
        this.moveEntity(super.motionX, super.motionY, super.motionZ);
        final float var11 = MathHelper.sqrt_double(
            super.motionX * super.motionX + super.motionZ * super.motionZ
        );
        super.rotationYaw = (float
        ) (Math.atan2(super.motionX, super.motionZ) * 180.0 / 3.141592653589793);
        super.rotationPitch
            = (float) (Math.atan2(super.motionY, var11) * 180.0 / 3.141592653589793);

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
        super.rotationYaw
            = super.prevRotationYaw + (super.rotationYaw - super.prevRotationYaw) * 0.2f;
        float var12 = 0.98f;
        final float gravity = 0.03f;

        if (this.isInWater()) {
            for (int var13 = 0; var13 < 4; ++var13) {
                final float var14 = 0.25f;
                super.worldObj.spawnParticle(
                    "bubble",
                    super.posX - super.motionX * var14,
                    super.posY - super.motionY * var14,
                    super.posZ - super.motionZ * var14,
                    super.motionX,
                    super.motionY,
                    super.motionZ
                );
            }

            var12 = 0.8f;
        }

        super.motionX *= var12;
        super.motionY *= var12;
        super.motionZ *= var12;

        if (super.onGround) {
            super.motionX *= 0.5;
            super.motionZ *= 0.5;
            super.motionY *= 0.5;
        } else {
            super.motionY -= gravity;
            this.func_145771_j(
                super.posX,
                (super.boundingBox.minY + super.boundingBox.maxY) / 2.0,
                super.posZ
            );
        }

        if (super.ticksExisted > Math.max(60, ZhaPin.list[this.haoMa].getYinXin())) {
            super.worldObj.spawnParticle(
                "hugeexplosion", super.posX, super.posY, super.posZ, 0.0, 0.0, 0.0
            );
            ZhaPin.createExplosion(
                super.worldObj,
                new Vector3(super.posX, super.posY, super.posZ),
                this,
                this.haoMa
            );
            this.setDead();
        } else {
            ZhaPin.list[this.haoMa].onYinZha(
                super.worldObj,
                new Vector3(super.posX, super.posY + 0.5, super.posZ),
                super.ticksExisted
            );
        }
    }

    @Override
    public boolean handleWaterMovement() {
        return super.worldObj.handleMaterialAcceleration(
            super.boundingBox, Material.water, (Entity) this
        );
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public boolean canBePushed() {
        return true;
    }

    @Override
    protected void readEntityFromNBT(final NBTTagCompound par1NBTTagCompound) {
        this.haoMa = par1NBTTagCompound.getInteger("haoMa");
    }

    @Override
    protected void writeEntityToNBT(final NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.setInteger("haoMa", this.haoMa);
    }

    @Override
    public IExplosive getExplosiveType() {
        return ZhaPin.list[this.haoMa];
    }
}
