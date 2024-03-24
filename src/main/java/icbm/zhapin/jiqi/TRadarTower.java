package icbm.zhapin.jiqi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import calclavia.lib.TileEntityUniversalRunnable;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import icbm.api.IItemFrequency;
import icbm.api.RadarRegistry;
import icbm.core.IICBMPeripheral;
import icbm.core.MainBase;
import icbm.zhapin.ICBMExplosion;
import icbm.zhapin.daodan.EMissile;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.util.ForgeDirection;
import universalelectricity.core.electricity.ElectricityPack;
import universalelectricity.core.vector.Vector2;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.block.BlockAdvanced;
import universalelectricity.prefab.implement.IRedstoneProvider;
import universalelectricity.prefab.multiblock.IMultiBlock;

public class TRadarTower extends TileEntityUniversalRunnable
    implements IRedstoneProvider, IMultiBlock, IICBMPeripheral {
    public static final int MAX_BIAN_JING = 500;
    public float rotation;
    public int alarmRadius;
    public int safetyRadius;
    private List<EMissile> missilesInRange;
    public List<Entity> entitiesInRange;
    public List<TileEntity> tileEntitiesInRange;
    public boolean emitAll;
    private ForgeChunkManager.Ticket ticket;
    public double wattsForDisplay;

    public TRadarTower() {
        this.rotation = 0.0f;
        this.alarmRadius = 100;
        this.safetyRadius = 50;
        this.missilesInRange = new ArrayList<>();
        this.entitiesInRange = new ArrayList<>();
        this.tileEntitiesInRange = new ArrayList<>();
        this.emitAll = true;
        RadarRegistry.register(this);
    }

    @Override
    public void initiate() {
        if (this.worldObj != null) {
            this.worldObj.notifyBlocksOfNeighborChange(
                this.xCoord,
                this.yCoord,
                this.zCoord,
                this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord)
            );
        }

        if (this.ticket == null) {
            this.ticket = ForgeChunkManager.requestTicket(
                (Object) ICBMExplosion.instance,
                this.worldObj,
                ForgeChunkManager.Type.NORMAL
            );

            if (this.ticket != null) {
                this.ticket.getModData();
            }
        }

        ForgeChunkManager.forceChunk(
            this.ticket, new ChunkCoordIntPair(this.xCoord >> 4, this.zCoord >> 4)
        );
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        try {
            if (!this.worldObj.isRemote) {
                this.wattsForDisplay = super.wattsReceived;
                if (super.ticks % 40L == 0L) {
                    this.worldObj.markBlockForUpdate(
                        this.xCoord, this.yCoord, this.zCoord
                    );
                }
            }

            if (!this.isDisabled()) {
                if (this.canRun()) {
                    this.rotation += 0.05f;

                    if (this.rotation > 360.0f) {
                        this.rotation = 0.0f;
                    }

                    if (!this.worldObj.isRemote) {
                        super.wattsReceived = Math.max(
                            super.wattsReceived - this.getRequest().getWatts(), 0.0
                        );
                    }

                    final int prevShuMu = this.entitiesInRange.size();
                    this.doScan();

                    if (prevShuMu != this.entitiesInRange.size()) {
                        this.worldObj.notifyBlocksOfNeighborChange(
                            this.xCoord, this.yCoord, this.zCoord, this.getBlockType()
                        );
                    }
                } else {
                    if (this.entitiesInRange.size() > 0) {
                        this.worldObj.notifyBlocksOfNeighborChange(
                            this.xCoord, this.yCoord, this.zCoord, this.getBlockType()
                        );
                    }

                    this.entitiesInRange.clear();
                    this.tileEntitiesInRange.clear();
                }
            }

            if (super.ticks % 40L == 0L) {
                this.worldObj.notifyBlocksOfNeighborChange(
                    this.xCoord, this.yCoord, this.zCoord, this.getBlockType()
                );
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private void doScan() {
        this.missilesInRange.clear();
        this.entitiesInRange.clear();
        this.tileEntitiesInRange.clear();
        final List<Entity> entities
            = RadarRegistry.getEntitiesWithinRadius(new Vector3(this).toVector2(), 500);

        for (final Entity entity : entities) {
            if (entity instanceof EMissile) {
                if (((EMissile) entity).flyingTicks <= -1) {
                    continue;
                }

                if (!this.entitiesInRange.contains(entity)) {
                    this.entitiesInRange.add(entity);
                }

                if (!this.isWeiXianDaoDan((EMissile) entity)) {
                    continue;
                }

                this.missilesInRange.add((EMissile) entity);
            } else {
                this.entitiesInRange.add(entity);
            }
        }

        final List<EntityPlayer> players = this.worldObj.getEntitiesWithinAABB(
            (Class) EntityPlayer.class,
            AxisAlignedBB.getBoundingBox(
                (double) (this.xCoord - 500),
                (double) (this.yCoord - 500),
                (double) (this.zCoord - 500),
                (double) (this.xCoord + 500),
                (double) (this.yCoord + 500),
                (double) (this.zCoord + 500)
            )
        );

        for (final EntityPlayer player : players) {
            if (player != null) {
                boolean youHuoLuan = false;

                for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
                    final ItemStack itemStack = player.inventory.getStackInSlot(i);

                    if (itemStack != null
                        && itemStack.getItem() instanceof IItemFrequency) {
                        youHuoLuan = true;
                        break;
                    }
                }

                if (youHuoLuan) {
                    continue;
                }

                this.entitiesInRange.add(player);
            }
        }

        for (final TileEntity jiQi : RadarRegistry.getTileEntitiesInArea(
                 new Vector2(this.xCoord - 500, this.zCoord - 500),
                 new Vector2(this.xCoord + 500, this.zCoord + 500)
             )) {
            if (jiQi instanceof TRadarTower) {
                if (((TRadarTower) jiQi).isDisabled()
                    || ((TRadarTower) jiQi).prevWatts <= 0.0) {
                    continue;
                }

                this.tileEntitiesInRange.add(jiQi);
            } else {
                this.tileEntitiesInRange.add(jiQi);
            }
        }
    }

    public boolean isWeiXianDaoDan(final EMissile daoDan) {
        return daoDan != null && daoDan.target != null
            && Vector2.distance(
                   new Vector3(daoDan).toVector2(), new Vector2(this.xCoord, this.zCoord)
               )
            < this.alarmRadius
            && Vector2.distance(
                   daoDan.target.toVector2(), new Vector2(this.xCoord, this.zCoord)
               )
            < this.safetyRadius;
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();

        nbt.setDouble("wattsReceived", super.wattsReceived);
        nbt.setDouble("wattsForDisplay", this.wattsForDisplay);
        nbt.setInteger("disabledTicks", this.disabledTicks);
        nbt.setInteger("safetyRadius", this.safetyRadius);
        nbt.setInteger("alarmRadius", this.alarmRadius);

        return new S35PacketUpdateTileEntity(
            this.xCoord, this.yCoord, this.zCoord, this.getBlockMetadata(), nbt
        );
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound nbt = pkt.func_148857_g();

        super.wattsReceived = nbt.getDouble("wattsReceived");
        this.wattsForDisplay = nbt.getDouble("wattsForDisplay");
        this.disabledTicks = nbt.getInteger("disabledTicks");
        this.safetyRadius = nbt.getInteger("safetyRadius");
        this.alarmRadius = nbt.getInteger("alarmRadius");
    }

    @Override
    public boolean isPoweringTo(final ForgeDirection side) {
        if ((super.prevWatts > 0.0 || super.wattsReceived > 0.0)
            && this.missilesInRange.size() > 0) {
            if (this.emitAll) {
                return true;
            }

            // This detects which direction a missile is coming from, and emits
            // redstone on the other side.
            for (final EMissile missile : this.missilesInRange) {
                final Vector2 position = new Vector3(missile).toVector2();
                ForgeDirection missileSide = ForgeDirection.UNKNOWN;
                double closest = -1.0;

                for (int i = 2; i < 6; ++i) {
                    final double dist = Vector2.distance(
                        position,
                        new Vector2(
                            this.xCoord + ForgeDirection.getOrientation(i).offsetX,
                            this.zCoord + ForgeDirection.getOrientation(i).offsetZ
                        )
                    );

                    if (dist < closest || closest < 0.0) {
                        missileSide = ForgeDirection.getOrientation(i);
                        closest = dist;
                    }
                }

                if (missileSide.getOpposite() == side) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean isIndirectlyPoweringTo(final ForgeDirection side) {
        return this.isPoweringTo(side);
    }

    @Override
    public void readFromNBT(final NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.safetyRadius = nbt.getInteger("safetyRadius");
        this.alarmRadius = nbt.getInteger("alarmRadius");
        this.emitAll = nbt.getBoolean("emitAll");
    }

    @Override
    public void writeToNBT(final NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("safetyRadius", this.safetyRadius);
        par1NBTTagCompound.setInteger("alarmRadius", this.alarmRadius);
        par1NBTTagCompound.setBoolean("emitAll", this.emitAll);
    }

    @Override
    public void onDestroy(final TileEntity callingBlock) {
        this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, Blocks.air, 0, 3);
        this.worldObj.setBlock(
            this.xCoord, this.yCoord + 1, this.zCoord, Blocks.air, 0, 3
        );
        this.worldObj.setBlock(
            this.xCoord + 1, this.yCoord + 1, this.zCoord, Blocks.air, 0, 3
        );
        this.worldObj.setBlock(
            this.xCoord - 1, this.yCoord + 1, this.zCoord, Blocks.air, 0, 3
        );
        this.worldObj.setBlock(
            this.xCoord, this.yCoord + 1, this.zCoord + 1, Blocks.air, 0, 3
        );
        this.worldObj.setBlock(
            this.xCoord, this.yCoord + 1, this.zCoord - 1, Blocks.air, 0, 3
        );
        this.worldObj.setBlock(
            this.xCoord + 1, this.yCoord + 1, this.zCoord + 1, Blocks.air, 0, 3
        );
        this.worldObj.setBlock(
            this.xCoord - 1, this.yCoord + 1, this.zCoord - 1, Blocks.air, 0, 3
        );
        this.worldObj.setBlock(
            this.xCoord + 1, this.yCoord + 1, this.zCoord - 1, Blocks.air, 0, 3
        );
        this.worldObj.setBlock(
            this.xCoord - 1, this.yCoord + 1, this.zCoord + 1, Blocks.air, 0, 3
        );
    }

    @Override
    public boolean onActivated(final EntityPlayer entityPlayer) {
        if (entityPlayer.inventory.getCurrentItem() != null
            && ((BlockAdvanced) this.getBlockType())
                   .isUsableWrench(
                       entityPlayer,
                       entityPlayer.inventory.getCurrentItem(),
                       this.xCoord,
                       this.yCoord,
                       this.zCoord
                   )) {
            if (!this.worldObj.isRemote) {
                this.emitAll = !this.emitAll;
                entityPlayer.addChatMessage(new ChatComponentText(
                    "Radar redstone all side emission: " + this.emitAll
                ));
            }

            return true;
        }

        entityPlayer.openGui(
            (Object) ICBMExplosion.instance,
            3,
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
            Vector3.add(new Vector3(0.0, 1.0, 0.0), position),
            new Vector3(this)
        );
        MainBase.bJia.makeFakeBlock(
            this.worldObj,
            Vector3.add(new Vector3(1.0, 1.0, 0.0), position),
            new Vector3(this)
        );
        MainBase.bJia.makeFakeBlock(
            this.worldObj,
            Vector3.add(new Vector3(-1.0, 1.0, 0.0), position),
            new Vector3(this)
        );
        MainBase.bJia.makeFakeBlock(
            this.worldObj,
            Vector3.add(new Vector3(0.0, 1.0, 1.0), position),
            new Vector3(this)
        );
        MainBase.bJia.makeFakeBlock(
            this.worldObj,
            Vector3.add(new Vector3(0.0, 1.0, -1.0), position),
            new Vector3(this)
        );
        MainBase.bJia.makeFakeBlock(
            this.worldObj,
            Vector3.add(new Vector3(1.0, 1.0, -1.0), position),
            new Vector3(this)
        );
        MainBase.bJia.makeFakeBlock(
            this.worldObj,
            Vector3.add(new Vector3(-1.0, 1.0, 1.0), position),
            new Vector3(this)
        );
        MainBase.bJia.makeFakeBlock(
            this.worldObj,
            Vector3.add(new Vector3(1.0, 1.0, 1.0), position),
            new Vector3(this)
        );
        MainBase.bJia.makeFakeBlock(
            this.worldObj,
            Vector3.add(new Vector3(-1.0, 1.0, -1.0), position),
            new Vector3(this)
        );
    }

    public boolean canRun() {
        if (this.worldObj.isRemote) {
            return this.wattsForDisplay >= this.getRequest().getWatts();
        } else {
            return super.wattsReceived >= this.getRequest().getWatts();
        }
    }

    @Override
    public String getType() {
        return "ICBMRadar";
    }

    @Override
    public String[] getMethodNames() {
        return new String[] { "getEntities", "getBlocks" };
    }

    @Override
    public Object[] callMethod(
        final IComputerAccess computer,
        ILuaContext ctx,
        final int method,
        final Object[] arguments
    ) throws LuaException {
        if (super.wattsReceived < this.getRequest().getWatts()) {
            throw new LuaException("Radar has insufficient electricity!");
        }

        final HashMap<String, Double> returnArray = new HashMap<>();
        int count = 0;

        switch (method) {
            case 0: {
                final List<Entity> entities = RadarRegistry.getEntitiesWithinRadius(
                    new Vector3(this).toVector2(), this.alarmRadius
                );

                for (final Entity entity : entities) {
                    // TODO: completely retarded lua API
                    returnArray.put("x_" + count, entity.posX);
                    returnArray.put("y_" + count, entity.posY);
                    returnArray.put("z_" + count, entity.posZ);
                    ++count;
                }

                return new Object[] { returnArray };
            }

            case 1: {
                for (final TileEntity jiQi : RadarRegistry.getTileEntitiesInArea(
                         new Vector2(this.xCoord - 500, this.zCoord - 500),
                         new Vector2(this.xCoord + 500, this.zCoord + 500)
                     )) {
                    returnArray.put("x_" + count, (double) jiQi.xCoord);
                    returnArray.put("y_" + count, (double) jiQi.yCoord);
                    returnArray.put("z_" + count, (double) jiQi.zCoord);
                    ++count;
                }

                return new Object[] { returnArray };
            }

            default: {
                throw new LuaException("Invalid ICBM Radar Function.");
            }
        }
    }

    @Override
    public void invalidate() {
        ForgeChunkManager.releaseTicket(this.ticket);
        RadarRegistry.unregister(this);
        super.invalidate();
    }

    @Override
    public void attach(final IComputerAccess computer) {}

    @Override
    public void detach(final IComputerAccess computer) {}

    @Override
    public ElectricityPack getRequest() {
        return new ElectricityPack(15.0 / this.getVoltage(), this.getVoltage());
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return TileEntity.INFINITE_EXTENT_AABB;
    }

    @Override
    public boolean equals(IPeripheral other) {
        return this == other;
    }
}
