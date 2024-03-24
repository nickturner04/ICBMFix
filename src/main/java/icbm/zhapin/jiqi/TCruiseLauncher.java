package icbm.zhapin.jiqi;

import dan200.computercraft.api.peripheral.IPeripheral;
import icbm.api.ILauncherContainer;
import icbm.api.ILauncherController;
import icbm.api.IMissile;
import icbm.api.LauncherType;
import icbm.zhapin.ICBMExplosion;
import icbm.zhapin.daodan.EMissile;
import icbm.zhapin.daodan.ItMissile;
import icbm.zhapin.daodan.ItModuleMissile;
import icbm.zhapin.daodan.MissileBase;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import universalelectricity.core.electricity.ElectricityPack;
import universalelectricity.core.item.ElectricItemHelper;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.multiblock.IBlockActivate;

public class TCruiseLauncher extends TLauncherController
    implements IBlockActivate, IInventory, ILauncherContainer {
    public IMissile missile;
    public float rotationYaw;
    public float rotationPitch;
    private ItemStack[] containingItems;
    private boolean isPowered;

    public TCruiseLauncher() {
        this.missile = null;
        this.rotationYaw = 0.0f;
        this.rotationPitch = 0.0f;
        this.containingItems = new ItemStack[2];
        this.isPowered = false;
        super.target = new Vector3();
    }

    @Override
    public int getSizeInventory() {
        return this.containingItems.length;
    }

    @Override
    public ItemStack getStackInSlot(final int par1) {
        return this.containingItems[par1];
    }

    @Override
    public ItemStack decrStackSize(final int par1, final int par2) {
        if (this.containingItems[par1] == null) {
            return null;
        }

        if (this.containingItems[par1].stackSize <= par2) {
            final ItemStack var3 = this.containingItems[par1];
            this.containingItems[par1] = null;
            return var3;
        }

        final ItemStack var3 = this.containingItems[par1].splitStack(par2);

        if (this.containingItems[par1].stackSize == 0) {
            this.containingItems[par1] = null;
        }

        return var3;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(final int par1) {
        if (this.containingItems[par1] != null) {
            final ItemStack var2 = this.containingItems[par1];
            this.containingItems[par1] = null;
            return var2;
        }

        return null;
    }

    @Override
    public void setInventorySlotContents(final int par1, final ItemStack par2ItemStack) {
        this.containingItems[par1] = par2ItemStack;

        if (par2ItemStack != null
            && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getStatus() {
        String color = "§4";
        String status = "Idle";

        if (this.isDisabled()) {
            status = "Disabled";
        } else if (this.getJoules() < this.getMaxJoules()) {
            status = "No Power!";
        } else if (this.missile == null) {
            status = "Silo Empty!";
        } else if (super.target == null) {
            status = "Invalid Target!";
        } else {
            color = "§2";
            status = "Ready!";
        }

        return color + status;
    }

    @Override
    public String getInventoryName() {
        return "Cruise Launcher";
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!this.isDisabled()) {
            this.onReceive(ElectricityPack.getFromWatts(
                ElectricItemHelper.dechargeItem(
                    this.containingItems[1],
                    this.getRequest().getWatts(),
                    this.getVoltage()
                ),
                this.getVoltage()
            ));

            if (this.getYawFromTarget() - this.rotationYaw != 0.0f) {
                this.rotationYaw
                    += (float) ((this.getYawFromTarget() - this.rotationYaw) * 0.1);
            }

            if (this.getPitchFromTarget() - this.rotationPitch != 0.0f) {
                this.rotationPitch
                    += (float) ((this.getPitchFromTarget() - this.rotationPitch) * 0.1);
            }

            if (!this.worldObj.isRemote) {
                this.setMissile();

                if (this.isPowered) {
                    this.launch();
                    this.isPowered = false;
                }
            }

            if (!this.worldObj.isRemote && super.ticks % 3L == 0L) {
                if (super.target == null) {
                    super.target = new Vector3(this.xCoord, this.yCoord, this.zCoord);
                }

                this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
            }
        }
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();

        nbt.setDouble("joules", this.getJoules());
        nbt.setInteger("frequency", super.frequency);
        nbt.setInteger("disabledTicks", super.disabledTicks);
        nbt.setTag("target", super.target.writeToNBT(new NBTTagCompound()));

        return new S35PacketUpdateTileEntity(
            this.xCoord, this.yCoord, this.zCoord, this.getBlockMetadata(), nbt
        );
    }

    @Override
    public void placeMissile(final ItemStack itemStack) {
        this.containingItems[0] = itemStack;
    }

    public void setMissile() {
        if (this.containingItems[0] != null
            && this.containingItems[0].getItem() instanceof ItMissile) {
            int haoMa = this.containingItems[0].getItemDamage();

            if (this.containingItems[0].getItem() instanceof ItModuleMissile) {
                haoMa += 100;
            }

            if (!ICBMExplosion.shiBaoHu(
                    this.worldObj, new Vector3(this), ZhaPin.ZhaPinType.DAO_DAN, haoMa
                )) {
                if (this.missile == null && MissileBase.list[haoMa].isCruise()
                    && MissileBase.list[haoMa].getTier() <= 3) {
                    final Vector3 startingPosition = new Vector3(
                        this.xCoord + 0.5f, this.yCoord + 0.2f, this.zCoord + 0.5f
                    );
                    this.missile = new EMissile(
                        this.worldObj, startingPosition, new Vector3(this), haoMa
                    );
                    this.worldObj.spawnEntityInWorld((Entity) this.missile);
                    return;
                }

                if (this.missile != null
                    && this.missile.getExplosiveType().getID() == haoMa) {
                    return;
                }
            }
        }

        if (this.missile != null) {
            ((Entity) this.missile).setDead();
        }

        this.missile = null;
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound nbt = pkt.func_148857_g();

        this.setJoules(nbt.getDouble("joules"));
        super.frequency = nbt.getInteger("frequency");
        super.disabledTicks = nbt.getInteger("disabledTicks");
        super.target = Vector3.readFromNBT(nbt.getCompoundTag("target"));
    }

    private float getPitchFromTarget() {
        final double distance = Math.sqrt(
            (super.target.x - this.xCoord) * (super.target.x - this.xCoord)
            + (super.target.z - this.zCoord) * (super.target.z - this.zCoord)
        );
        return (float
        ) Math.toDegrees(Math.atan((super.target.y - (this.yCoord + 0.5f)) / distance));
    }

    private float getYawFromTarget() {
        final double xDifference = super.target.x - (this.xCoord + 0.5f);
        final double yDifference = super.target.z - (this.zCoord + 0.5f);
        return (float) Math.toDegrees(Math.atan2(yDifference, xDifference));
    }

    @Override
    public boolean canLaunch() {
        if (this.missile != null && this.containingItems[0] != null) {
            final MissileBase missile
                = MissileBase.list[this.containingItems[0].getItemDamage()];
            int haoMa = this.missile.getExplosiveType().getID();

            if (this.missile.getExplosiveType().getID() >= 100) {
                haoMa -= 100;
            }

            if (missile != null && missile.getID() == haoMa && missile.isCruise()
                && missile.getTier() <= 3 && this.getJoules() >= this.getMaxJoules()
                && !this.isTooClose(super.target)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void launch() {
        if (this.canLaunch()) {
            this.decrStackSize(0, 1);
            this.setJoules(0.0);
            this.missile.launch(super.target);
            this.missile = null;
        }
    }

    public boolean isTooClose(final Vector3 target) {
        return Vector3.distance(
                   new Vector3(this.xCoord, 0.0, this.zCoord),
                   new Vector3(target.x, 0.0, target.z)
               )
            < 8.0;
    }

    @Override
    public void readFromNBT(final NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        final NBTTagList var2 = nbt.getTagList("Items", 10);
        this.containingItems = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
            final NBTTagCompound var4 = var2.getCompoundTagAt(var3);
            final byte var5 = var4.getByte("Slot");

            if (var5 >= 0 && var5 < this.containingItems.length) {
                this.containingItems[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }
    }

    @Override
    public void writeToNBT(final NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        final NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < this.containingItems.length; ++var3) {
            if (this.containingItems[var3] != null) {
                final NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte) var3);
                this.containingItems[var3].writeToNBT(var4);
                var2.appendTag((NBTBase) var4);
            }
        }

        par1NBTTagCompound.setTag("Items", (NBTBase) var2);
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(final EntityPlayer par1EntityPlayer) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this
            && par1EntityPlayer.getDistanceSq(
                   this.xCoord + 0.5, this.yCoord + 0.5, this.zCoord + 0.5
               )
            <= 64.0;
    }

    @Override
    public void onPowerOn() {
        this.isPowered = true;
    }

    @Override
    public void onPowerOff() {
        this.isPowered = false;
    }

    @Override
    public double getMaxJoules() {
        return 800000.0;
    }

    @Override
    public boolean onActivated(final EntityPlayer entityPlayer) {
        if (this.isItemValidForSlot(0, entityPlayer.inventory.getCurrentItem())) {
            this.setInventorySlotContents(0, entityPlayer.inventory.getCurrentItem());
            entityPlayer.inventory.setInventorySlotContents(
                entityPlayer.inventory.currentItem, (ItemStack) null
            );
            return true;
        }

        entityPlayer.openGui(
            (Object) ICBMExplosion.instance,
            1,
            this.worldObj,
            this.xCoord,
            this.yCoord,
            this.zCoord
        );
        return true;
    }

    @Override
    public LauncherType getLauncherType() {
        return LauncherType.CRUISE;
    }

    @Override
    public boolean canConnect(final ForgeDirection direction) {
        return true;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return true;
    }

    @Override
    public boolean isItemValidForSlot(final int slotID, final ItemStack itemStack) {
        return itemStack != null && itemStack.getItem() instanceof ItMissile
            && this.getStackInSlot(slotID) == null
            && MissileBase.list[itemStack.getItemDamage()] != null
            && MissileBase.list[itemStack.getItemDamage()].isCruise()
            && MissileBase.list[itemStack.getItemDamage()].getTier() <= 3;
    }

    @Override
    public void setContainingMissile(final IMissile missile) {
        this.missile = missile;
    }

    @Override
    public ILauncherController getController() {
        return this;
    }

    @Override
    public IMissile getMissile() {
        return this.missile;
    }

    @Override
    public IMissile getContainingMissile() {
        return this.missile;
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return TileEntity.INFINITE_EXTENT_AABB;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean equals(IPeripheral other) {
        return this == other;
    }
}
