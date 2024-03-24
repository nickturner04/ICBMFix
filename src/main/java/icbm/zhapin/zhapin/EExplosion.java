package icbm.zhapin.zhapin;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import icbm.zhapin.ICBMExplosion;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import universalelectricity.core.vector.Vector3;

public class EExplosion extends Entity implements IEntityAdditionalSpawnData {
    public int haoMa;
    public int jiaoShuMu;
    public int tickCallCounter;
    private int metadata;
    private boolean endExplosion;
    public List<Entity> entityList;
    public List<Object> dataList1;
    public List<Object> dataList2;
    private boolean isMobile;

    public EExplosion(final World par1World) {
        super(par1World);
        this.metadata = -1;
        this.endExplosion = false;
        this.entityList = new ArrayList<>();
        this.dataList1 = new ArrayList<>();
        this.dataList2 = new ArrayList<>();
        this.isMobile = false;
        super.preventEntitySpawning = true;
        this.setSize(0.98f, 0.98f);
        super.yOffset = super.height / 2.0f;
        super.renderDistanceWeight = 2.0;
        super.ignoreFrustumCheck = true;
    }

    public EExplosion(
        final World par1World,
        final Vector3 position,
        final int explosionID,
        final boolean isMobile
    ) {
        this(par1World);
        this.jiaoShuMu = 0;
        this.haoMa = explosionID;
        this.isMobile = isMobile;
        this.setPosition(position.x, position.y, position.z);
    }

    public EExplosion(
        final World par1World,
        final Vector3 position,
        final int explosionID,
        final boolean isMobile,
        final int metadata
    ) {
        this(par1World, position, explosionID, isMobile);
        this.metadata = metadata;
    }

    @Override
    public String getCommandSenderName() {
        return "Explosion";
    }

    @Override
    public void writeSpawnData(final ByteBuf data) {
        data.writeBoolean(this.isMobile);
        data.writeInt(this.haoMa);
        data.writeInt(this.metadata);
    }

    @Override
    public void readSpawnData(final ByteBuf data) {
        this.isMobile = data.readBoolean();
        this.haoMa = data.readInt();
        this.metadata = data.readInt();
    }

    @Override
    protected void entityInit() {}

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public void onUpdate() {
        if (!super.worldObj.isRemote
            && ICBMExplosion.shiBaoHu(
                super.worldObj, new Vector3(this), ZhaPin.ZhaPinType.ZHA_DAN, this.haoMa
            )) {
            this.setDead();
            return;
        }

        if (this.isMobile
            && (super.motionX != 0.0 || super.motionY != 0.0 || super.motionZ != 0.0)) {
            this.moveEntity(super.motionX, super.motionY, super.motionZ);
        }

        if (super.ticksExisted == 1) {
            ZhaPin.list[this.haoMa].baoZhaQian(super.worldObj, new Vector3(this), this);
        }

        if (this.tickCallCounter >= ZhaPin.list[this.haoMa].proceduralInterval(
                super.worldObj, this.jiaoShuMu
            )) {
            if (!this.endExplosion
                && ZhaPin.list[this.haoMa].doBaoZha(
                    super.worldObj,
                    new Vector3(super.posX, super.posY, super.posZ),
                    this,
                    this.metadata,
                    this.jiaoShuMu
                )) {
                this.jiaoShuMu += ZhaPin.list[this.haoMa].countIncrement();
                this.tickCallCounter = 0;
            } else {
                ZhaPin.list[this.haoMa].baoZhaHou(
                    super.worldObj, new Vector3(super.posX, super.posY, super.posZ), this
                );
                this.setDead();
            }
        }

        ++this.tickCallCounter;
        ZhaPin.list[this.haoMa].gengXin(
            super.worldObj,
            new Vector3(super.posX, super.posY, super.posZ),
            super.ticksExisted
        );
        ++super.ticksExisted;
    }

    public void endExplosion() {
        this.endExplosion = true;
    }

    @Override
    protected void readEntityFromNBT(final NBTTagCompound par1NBTTagCompound) {
        this.isMobile = par1NBTTagCompound.getBoolean("isMobile");
        this.haoMa = par1NBTTagCompound.getInteger("explosionID");
        this.jiaoShuMu = par1NBTTagCompound.getInteger("callCounter");
        super.ticksExisted = par1NBTTagCompound.getInteger("ticksExisted");
        this.metadata = par1NBTTagCompound.getInteger("metadata");
        this.tickCallCounter = par1NBTTagCompound.getInteger("tickCallCounter");
    }

    @Override
    protected void writeEntityToNBT(final NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.setBoolean("isMobile", this.isMobile);
        par1NBTTagCompound.setInteger("explosionID", this.haoMa);
        par1NBTTagCompound.setInteger("callCounter", this.jiaoShuMu);
        par1NBTTagCompound.setInteger("ticksExisted", super.ticksExisted);
        par1NBTTagCompound.setInteger("metadata", this.metadata);
        par1NBTTagCompound.setInteger("tickCallCounter", this.tickCallCounter);
    }

    @Override
    public float getShadowSize() {
        return 0.0f;
    }
}
