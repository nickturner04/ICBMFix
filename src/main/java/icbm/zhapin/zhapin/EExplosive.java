package icbm.zhapin.zhapin;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import icbm.zhapin.ICBMExplosion;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.implement.IRotatable;

public class EExplosive extends Entity implements IRotatable, IEntityAdditionalSpawnData {
    public int fuse;
    public int haoMa;
    private int metadata;
    private byte orientation;

    public EExplosive(final World par1World) {
        super(par1World);
        this.fuse = 90;
        this.haoMa = 0;
        this.metadata = -1;
        this.orientation = 3;
        this.fuse = 0;
        super.preventEntitySpawning = true;
        this.setSize(0.98f, 0.98f);
        super.yOffset = super.height / 2.0f;
    }

    public EExplosive(
        final World par1World,
        final Vector3 position,
        final byte orientation,
        final int explosiveID
    ) {
        this(par1World);
        this.setPosition(position.x, position.y, position.z);
        final float var8 = (float) (Math.random() * 3.141592653589793 * 2.0);
        super.motionX = -(float) Math.sin(var8) * 0.02f;
        super.motionY = 0.20000000298023224;
        super.motionZ = -(float) Math.cos(var8) * 0.02f;
        super.prevPosX = position.x;
        super.prevPosY = position.y;
        super.prevPosZ = position.z;
        this.haoMa = explosiveID;
        this.fuse = ZhaPin.list[explosiveID].getYinXin();
        this.orientation = orientation;
        ZhaPin.list[explosiveID].yinZhaQian(par1World, this);
    }

    public EExplosive(
        final World par1World,
        final Vector3 position,
        final int explosiveID,
        final byte orientation,
        final int metadata
    ) {
        this(par1World, position, orientation, explosiveID);
        this.metadata = metadata;
    }

    @Override
    public String getCommandSenderName() {
        return "Explosives";
    }

    @Override
    public void onUpdate() {
        if (!super.worldObj.isRemote
            && ICBMExplosion.shiBaoHu(
                super.worldObj, new Vector3(this), ZhaPin.ZhaPinType.ZHA_DAN, this.haoMa
            )) {
            ICBMExplosion.bExplosives.dropBlockAsItem(
                super.worldObj,
                (int) super.posX,
                (int) super.posY,
                (int) super.posZ,
                this.haoMa,
                0
            );
            this.setDead();
            return;
        }

        super.prevPosX = super.posX;
        super.prevPosY = super.posY;
        super.prevPosZ = super.posZ;
        super.motionX *= 0.95;
        super.motionY -= 0.045;
        super.motionZ *= 0.95;
        this.moveEntity(super.motionX, super.motionY, super.motionZ);

        if (this.fuse < 1) {
            this.explode();
        } else {
            ZhaPin.list[this.haoMa].onYinZha(
                super.worldObj, new Vector3(super.posX, super.posY, super.posZ), this.fuse
            );
        }

        --this.fuse;
        super.onUpdate();
    }

    public void explode() {
        super.worldObj.spawnParticle(
            "hugeexplosion", super.posX, super.posY, super.posZ, 0.0, 0.0, 0.0
        );
        ZhaPin.createExplosion(super.worldObj, new Vector3(this), this, this.haoMa);
        this.setDead();
    }

    public void destroyedByExplosion() {
        this.fuse = ZhaPin.list[this.haoMa].onBeiZha();
    }

    @Override
    protected void readEntityFromNBT(final NBTTagCompound par1NBTTagCompound) {
        this.fuse = par1NBTTagCompound.getByte("Fuse");
        this.metadata = par1NBTTagCompound.getInteger("metadata");
        this.haoMa = par1NBTTagCompound.getInteger("explosiveID");
    }

    @Override
    protected void writeEntityToNBT(final NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.setByte("Fuse", (byte) this.fuse);
        par1NBTTagCompound.setInteger("metadata", this.metadata);
        par1NBTTagCompound.setInteger("explosiveID", this.haoMa);
    }

    @Override
    public float getShadowSize() {
        return 0.5f;
    }

    @Override
    protected void entityInit() {}

    @Override
    protected boolean canTriggerWalking() {
        return true;
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
    public ForgeDirection
    getDirection(final IBlockAccess world, final int x, final int y, final int z) {
        return ForgeDirection.getOrientation((int) this.orientation);
    }

    @Override
    public void setDirection(
        final World world,
        final int x,
        final int y,
        final int z,
        final ForgeDirection facingDirection
    ) {
        this.orientation = (byte) facingDirection.ordinal();
    }

    @Override
    public void writeSpawnData(final ByteBuf data) {
        data.writeInt(this.haoMa);
        data.writeInt(this.fuse);
        data.writeByte((int) this.orientation);
        data.writeInt(this.metadata);
    }

    @Override
    public void readSpawnData(final ByteBuf data) {
        this.haoMa = data.readInt();
        this.fuse = data.readInt();
        this.orientation = data.readByte();
        this.metadata = data.readInt();
    }
}
