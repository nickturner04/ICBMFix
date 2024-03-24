package icbm.zhapin;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import icbm.core.MainBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import universalelectricity.core.vector.Vector3;

public class EGravityBlock extends Entity implements IEntityAdditionalSpawnData {
    public Block block;
    public int metadata;
    public float yawChange;
    public float pitchChange;
    public float gravity;

    public EGravityBlock(final World world) {
        super(world);
        this.block = Blocks.air;
        this.metadata = 0;
        this.yawChange = 0.0f;
        this.pitchChange = 0.0f;
        this.gravity = 0.045f;
        super.ticksExisted = 0;
        super.preventEntitySpawning = true;
        super.isImmuneToFire = true;
        this.setSize(1.0f, 1.0f);
    }

    public EGravityBlock(
        final World world, final Vector3 position, final Block block, final int metadata
    ) {
        super(world);
        this.yawChange = 0.0f;
        this.pitchChange = 0.0f;
        this.gravity = 0.045f;
        super.isImmuneToFire = true;
        super.ticksExisted = 0;
        this.setSize(0.98f, 0.98f);
        super.yOffset = super.height / 2.0f;
        this.setPosition(position.x + 0.5, position.y, position.z + 0.5);
        super.motionX = 0.0;
        super.motionY = 0.0;
        super.motionZ = 0.0;
        this.block = block;
        this.metadata = metadata;
    }

    public EGravityBlock(
        final World world,
        final Vector3 position,
        final Block block,
        final int metadata,
        final float gravity
    ) {
        this(world, position, block, metadata);
        this.gravity = gravity;
    }

    @Override
    public String getCommandSenderName() {
        return "Flying Block";
    }

    @Override
    public void writeSpawnData(final ByteBuf data) {
        data.writeInt(Block.getIdFromBlock(this.block));
        data.writeInt(this.metadata);
        data.writeFloat(this.gravity);
    }

    @Override
    public void readSpawnData(final ByteBuf data) {
        this.block = Block.getBlockById(data.readInt());
        this.metadata = data.readInt();
        this.gravity = data.readFloat();
    }

    @Override
    protected void entityInit() {}

    @Override
    public void onUpdate() {
        if (super.posY > 400.0 || this.block == null || this.block == MainBase.bJia
            || this.block == Blocks.piston_head || this.block == Blocks.flowing_water
            || this.block == Blocks.flowing_lava) {
            this.setDead();
            return;
        }

        super.motionY -= this.gravity;

        if (super.isCollided) {
            this.func_145771_j(
                super.posX,
                (super.boundingBox.minY + super.boundingBox.maxY) / 2.0,
                super.posZ
            );
        }

        this.moveEntity(super.motionX, super.motionY, super.motionZ);

        if (this.yawChange > 0.0f) {
            super.rotationYaw += this.yawChange;
            this.yawChange -= 2.0f;
        }

        if (this.pitchChange > 0.0f) {
            super.rotationPitch += this.pitchChange;
            this.pitchChange -= 2.0f;
        }

        if ((super.onGround && super.ticksExisted > 20) || super.ticksExisted > 2400) {
            this.setBlock();
            return;
        }

        ++super.ticksExisted;
    }

    public void setBlock() {
        if (!super.worldObj.isRemote) {
            final int i = MathHelper.floor_double(super.posX);
            final int j = MathHelper.floor_double(super.posY);
            final int k = MathHelper.floor_double(super.posZ);
            super.worldObj.setBlock(i, j, k, this.block, this.metadata, 2);
        }

        this.setDead();
    }

    @Override
    public AxisAlignedBB getCollisionBox(final Entity par1Entity) {
        if (par1Entity instanceof EntityLiving && this.block != null
            && !(this.block instanceof BlockLiquid)
            && (super.motionX > 2.0 || super.motionY > 2.0 || super.motionZ > 2.0)) {
            final int damage = (int
            ) (1.2
               * (Math.abs(super.motionX) + Math.abs(super.motionY)
                  + Math.abs(super.motionZ)));
            ((EntityLiving) par1Entity)
                .attackEntityFrom(DamageSource.fallingBlock, damage);
        }

        return null;
    }

    @Override
    protected void writeEntityToNBT(final NBTTagCompound nbttagcompound) {
        nbttagcompound.setInteger("metadata", this.metadata);
        nbttagcompound.setInteger("blockID", Block.getIdFromBlock(this.block));
        nbttagcompound.setFloat("gravity", this.gravity);
    }

    @Override
    protected void readEntityFromNBT(final NBTTagCompound nbttagcompound) {
        this.metadata = nbttagcompound.getInteger("metadata");
        this.block = Block.getBlockById(nbttagcompound.getInteger("blockID"));
        this.gravity = nbttagcompound.getFloat("gravity");
    }

    @Override
    public float getShadowSize() {
        return 0.5f;
    }

    @Override
    public boolean canBePushed() {
        return true;
    }

    @Override
    protected boolean canTriggerWalking() {
        return true;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }
}
