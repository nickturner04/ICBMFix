package icbm.zhapin.jiqi;

import calclavia.lib.TileEntityUniversalStorable;
import icbm.api.RadarRegistry;
import icbm.core.MainBase;
import icbm.zhapin.ICBMExplosion;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.implement.IRedstoneReceptor;
import universalelectricity.prefab.multiblock.IMultiBlock;

public class TEmpTower
    extends TileEntityUniversalStorable implements IMultiBlock, IRedstoneReceptor {
    public static final int MAX_RADIUS = 150;
    public float rotation;
    private float xuanZhuanLu;
    public byte holzOhJa;
    public int radius;

    public TEmpTower() {
        this.rotation = 0.0f;
        this.holzOhJa = 0;
        this.radius = 60;
        RadarRegistry.register(this);
    }

    @Override
    public void invalidate() {
        RadarRegistry.unregister(this);
        super.invalidate();
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!this.isDisabled()) {
            if (super.ticks % 20L == 0L && this.getJoules() > 0.0) {
                this.worldObj.playSoundEffect(
                    (double) this.xCoord,
                    (double) this.yCoord,
                    (double) this.zCoord,
                    "icbm:machinehum",
                    0.5f,
                    (float) (0.8500000238418579 * this.getJoules() / this.getMaxJoules())
                );
            }

            this.xuanZhuanLu
                = (float) (Math.pow(this.getJoules() / this.getMaxJoules(), 2.0) * 0.5);
            this.rotation += this.xuanZhuanLu;

            if (this.rotation > 360.0f) {
                this.rotation = 0.0f;
            }
        }

        if (!this.worldObj.isRemote) {
            if (super.ticks % 10L == 0L) {
                this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
            }
        }
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound nbt = pkt.func_148857_g();

        this.setJoules(nbt.getDouble("joules"));
        super.disabledTicks = nbt.getInteger("disabledTicks");
        this.radius = nbt.getInteger("radius");
        this.holzOhJa = nbt.getByte("holzOhJa");
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();

        nbt.setDouble("joules", this.getJoules());
        nbt.setInteger("disabledTicks", super.disabledTicks);
        nbt.setInteger("radius", this.radius);
        nbt.setByte("holzOhJa", this.holzOhJa);

        return new S35PacketUpdateTileEntity(
            this.xCoord, this.yCoord, this.zCoord, this.getBlockMetadata(), nbt
        );
    }

    @Override
    public double getVoltage() {
        return 240.0;
    }

    @Override
    public void readFromNBT(final NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.radius = nbt.getInteger("radius");
        this.holzOhJa = nbt.getByte("holzOhJa");
    }

    @Override
    public void writeToNBT(final NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("radius", this.radius);
        nbt.setByte("holzOhJa", this.holzOhJa);
    }

    @Override
    public void onPowerOn() {
        if (this.getJoules() >= this.getMaxJoules()) {
            if (this.holzOhJa == 0 || this.holzOhJa == 1) {
                ZhaPin.empSignal.doBaoZha(
                    this.worldObj,
                    new Vector3(this.xCoord, this.yCoord, this.zCoord),
                    null,
                    this.radius,
                    -1
                );
            }

            if (this.holzOhJa == 0 || this.holzOhJa == 2) {
                ZhaPin.empWave.doBaoZha(
                    this.worldObj,
                    new Vector3(this.xCoord, this.yCoord, this.zCoord),
                    null,
                    this.radius,
                    -1
                );
            }

            this.setJoules(0.0);
        }
    }

    @Override
    public void onPowerOff() {}

    @Override
    public void onDestroy(final TileEntity callingBlock) {
        this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, Blocks.air, 0, 3);
        this.worldObj.setBlock(
            this.xCoord, this.yCoord + 1, this.zCoord, Blocks.air, 0, 3
        );
    }

    @Override
    public boolean onActivated(final EntityPlayer entityPlayer) {
        entityPlayer.openGui(
            (Object) ICBMExplosion.instance,
            6,
            this.worldObj,
            this.xCoord,
            this.yCoord,
            this.zCoord
        );
        return true;
    }

    @Override
    public void onCreate(final Vector3 position) {
        MainBase.bJia.makeFakeBlock(
            this.worldObj,
            Vector3.add(position, new Vector3(0.0, 1.0, 0.0)),
            new Vector3(this)
        );
    }

    @Override
    public double getMaxJoules() {
        return Math.max(2000000.0f * (this.radius / 150.0f), 1000000.0f);
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return TileEntity.INFINITE_EXTENT_AABB;
    }
}
