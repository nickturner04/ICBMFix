package icbm.zhapin.jiqi;

import icbm.api.ILauncherContainer;
import icbm.api.ILauncherController;
import icbm.api.IMissile;
import icbm.core.MainBase;
import icbm.zhapin.ICBMExplosion;
import icbm.zhapin.daodan.EMissile;
import icbm.zhapin.daodan.ItMissile;
import icbm.zhapin.daodan.ItModuleMissile;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import universalelectricity.core.vector.Vector3;
import universalelectricity.core.vector.VectorHelper;
import universalelectricity.prefab.implement.IRotatable;
import universalelectricity.prefab.implement.ITier;
import universalelectricity.prefab.multiblock.IMultiBlock;
import universalelectricity.prefab.tile.TileEntityAdvanced;

public class TLauncherPlatform extends TileEntityAdvanced
    implements ILauncherContainer, IRotatable, ITier, IMultiBlock, IInventory {
    public IMissile daoDan;
    public TLauncher jiaZi;
    private ItemStack[] containingItems;
    private int tier;
    private byte orientation;
    private boolean packetGengXin;

    public TLauncherPlatform() {
        this.daoDan = null;
        this.jiaZi = null;
        this.containingItems = new ItemStack[1];
        this.tier = 0;
        this.orientation = 3;
        this.packetGengXin = true;
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
    public String getInventoryName() {
        return "Launcher Platform";
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (this.jiaZi == null) {
            for (byte i = 2; i < 6; ++i) {
                final Vector3 position
                    = new Vector3(this.xCoord, this.yCoord, this.zCoord);
                position.modifyPositionFromSide(ForgeDirection.getOrientation((int) i));
                final TileEntity tileEntity = this.worldObj.getTileEntity(
                    position.intX(), position.intY(), position.intZ()
                );

                if (tileEntity instanceof TLauncher) {
                    (this.jiaZi = (TLauncher) tileEntity)
                        .setDirection(
                            this.worldObj,
                            this.xCoord,
                            this.yCoord,
                            this.zCoord,
                            VectorHelper.getOrientationFromSide(
                                ForgeDirection.getOrientation((int) i),
                                ForgeDirection.NORTH
                            )
                        );
                }
            }
        } else if (this.jiaZi.isInvalid()) {
            this.jiaZi = null;
        } else if (this.packetGengXin ||
            (super.ticks % 600L == 0L && this.jiaZi != null &&
                !this.worldObj.isRemote)) {
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        }

        if (!this.worldObj.isRemote) {
            this.setMissile();

            if (this.packetGengXin || super.ticks % 600L == 0L) {
                this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                this.packetGengXin = false;
            }
        }
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();

        nbt.setByte("orientation", this.orientation);
        nbt.setInteger("tier", this.tier);

        return new S35PacketUpdateTileEntity(
            this.xCoord, this.yCoord, this.zCoord, this.getBlockMetadata(), nbt
        );
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound nbt = pkt.func_148857_g();

        this.orientation = nbt.getByte("orientation");
        this.tier = nbt.getInteger("tier");
    }

    private void setMissile() {
        if (!this.worldObj.isRemote) {
            if (this.containingItems[0] != null
                && this.containingItems[0].getItem() instanceof ItMissile) {
                int haoMa = this.containingItems[0].getItemDamage();

                if (!ICBMExplosion.shiBaoHu(
                        this.worldObj, new Vector3(this), ZhaPin.ZhaPinType.DAO_DAN, haoMa
                    )) {
                    if (this.containingItems[0].getItem() instanceof ItModuleMissile) {
                        haoMa += 100;
                    }

                    if (this.daoDan == null) {
                        final Vector3 position = new Vector3(
                            this.xCoord + 0.5f, this.yCoord + 2, this.zCoord + 0.5f
                        );
                        this.daoDan = new EMissile(
                            this.worldObj, position, new Vector3(this), haoMa
                        );
                        this.worldObj.spawnEntityInWorld((Entity) this.daoDan);
                        return;
                    }

                    if (this.daoDan.getExplosiveType() != null
                        && this.daoDan.getExplosiveType().getID() == haoMa) {
                        return;
                    }
                }
            }

            if (this.daoDan != null) {
                ((Entity) this.daoDan).setDead();
            }

            this.daoDan = null;
        }
    }

    public void launchMissile(final Vector3 target, final int gaoDu) {
        float inaccuracy;

        if (this.jiaZi != null) {
            inaccuracy = (float) this.jiaZi.getInaccuracy();
        } else {
            inaccuracy = 30.0f;
        }

        inaccuracy *= (float) Math.random() * 2.0f - 1.0f;
        target.x += inaccuracy;
        target.z += inaccuracy;
        this.decrStackSize(0, 1);
        this.daoDan.launch(target, gaoDu);
        this.daoDan = null;
    }

    public boolean isInRange(final Vector3 target) {
        return target != null && !this.shiTaiYuan(target) && !this.shiTaiJin(target);
    }

    public boolean shiTaiJin(final Vector3 target) {
        return Vector3.distance(
                   new Vector3(this.xCoord, 0.0, this.zCoord),
                   new Vector3(target.x, 0.0, target.z)
               )
            < 10.0;
    }

    public boolean shiTaiYuan(final Vector3 target) {
        if (this.tier == 0) {
            if (Vector3.distance(
                    new Vector3(this.xCoord, 0.0, this.zCoord),
                    new Vector3(target.x, 0.0, target.z)
                )
                < MainBase.DAO_DAN_ZUI_YUAN / 10) {
                return false;
            }
        } else if (this.tier == 1) {
            if (Vector3.distance(
                    new Vector3(this.xCoord, 0.0, this.zCoord),
                    new Vector3(target.x, 0.0, target.z)
                )
                < MainBase.DAO_DAN_ZUI_YUAN / 5) {
                return false;
            }
        } else if (this.tier == 2 &&
            Vector3.distance(new Vector3(this.xCoord, 0.0, this.zCoord),
                new Vector3(target.x, 0.0, target.z)) < MainBase.DAO_DAN_ZUI_YUAN) {
            return false;
        }

        return true;
    }

    @Override
    public void readFromNBT(final NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        final NBTTagList var2 = nbt.getTagList("Items", 10);
        this.tier = nbt.getInteger("tier");
        this.orientation = nbt.getByte("facingDirection");
        this.containingItems = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3) {
            final NBTTagCompound var4 = (NBTTagCompound) var2.getCompoundTagAt(var3);
            final byte var5 = var4.getByte("Slot");

            if (var5 >= 0 && var5 < this.containingItems.length) {
                this.containingItems[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }
    }

    @Override
    public void writeToNBT(final NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("tier", this.tier);
        nbt.setByte("facingDirection", this.orientation);
        final NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < this.containingItems.length; ++var3) {
            if (this.containingItems[var3] != null) {
                final NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte) var3);
                this.containingItems[var3].writeToNBT(var4);
                var2.appendTag((NBTBase) var4);
            }
        }

        nbt.setTag("Items", (NBTBase) var2);
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
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public int getTier() {
        return this.tier;
    }

    @Override
    public void setTier(final int tier) {
        this.tier = tier;
    }

    @Override
    public boolean onActivated(final EntityPlayer entityPlayer) {
        if (entityPlayer.inventory.getCurrentItem() != null
            && this.getStackInSlot(0) == null
            && entityPlayer.inventory.getCurrentItem().getItem() instanceof ItMissile) {
            this.setInventorySlotContents(0, entityPlayer.inventory.getCurrentItem());
            entityPlayer.inventory.setInventorySlotContents(
                entityPlayer.inventory.currentItem, (ItemStack) null
            );
            return true;
        }

        entityPlayer.openGui(
            (Object) ICBMExplosion.instance,
            7,
            this.worldObj,
            this.xCoord,
            this.yCoord,
            this.zCoord
        );
        return true;
    }

    @Override
    public void onCreate(final Vector3 position) {
        if (this.orientation == 3 || this.orientation == 2) {
            MainBase.bJia.makeFakeBlock(
                this.worldObj,
                Vector3.add(position, new Vector3(1.0, 0.0, 0.0)),
                new Vector3(this)
            );
            MainBase.bJia.makeFakeBlock(
                this.worldObj,
                Vector3.add(position, new Vector3(1.0, 1.0, 0.0)),
                new Vector3(this)
            );
            MainBase.bJia.makeFakeBlock(
                this.worldObj,
                Vector3.add(position, new Vector3(1.0, 2.0, 0.0)),
                new Vector3(this)
            );
            MainBase.bJia.makeFakeBlock(
                this.worldObj,
                Vector3.add(position, new Vector3(-1.0, 0.0, 0.0)),
                new Vector3(this)
            );
            MainBase.bJia.makeFakeBlock(
                this.worldObj,
                Vector3.add(position, new Vector3(-1.0, 1.0, 0.0)),
                new Vector3(this)
            );
            MainBase.bJia.makeFakeBlock(
                this.worldObj,
                Vector3.add(position, new Vector3(-1.0, 2.0, 0.0)),
                new Vector3(this)
            );
        } else {
            MainBase.bJia.makeFakeBlock(
                this.worldObj,
                Vector3.add(position, new Vector3(0.0, 0.0, 1.0)),
                new Vector3(this)
            );
            MainBase.bJia.makeFakeBlock(
                this.worldObj,
                Vector3.add(position, new Vector3(0.0, 1.0, 1.0)),
                new Vector3(this)
            );
            MainBase.bJia.makeFakeBlock(
                this.worldObj,
                Vector3.add(position, new Vector3(0.0, 2.0, 1.0)),
                new Vector3(this)
            );
            MainBase.bJia.makeFakeBlock(
                this.worldObj,
                Vector3.add(position, new Vector3(0.0, 0.0, -1.0)),
                new Vector3(this)
            );
            MainBase.bJia.makeFakeBlock(
                this.worldObj,
                Vector3.add(position, new Vector3(0.0, 1.0, -1.0)),
                new Vector3(this)
            );
            MainBase.bJia.makeFakeBlock(
                this.worldObj,
                Vector3.add(position, new Vector3(0.0, 2.0, -1.0)),
                new Vector3(this)
            );
        }
    }

    @Override
    public void onDestroy(final TileEntity callingBlock) {
        final Vector3 position = new Vector3(this.xCoord, this.yCoord, this.zCoord);

        if (this.orientation == 3 || this.orientation == 3) {
            this.worldObj.setBlock(
                (int) position.x, (int) position.y, (int) position.z, Blocks.air, 0, 3
            );
            this.worldObj.setBlock(
                (int) position.x + 1, (int) position.y, (int) position.z, Blocks.air, 0, 3
            );
            this.worldObj.setBlock(
                (int) position.x + 1,
                (int) position.y + 1,
                (int) position.z,
                Blocks.air,
                0,
                3
            );
            this.worldObj.setBlock(
                (int) position.x + 1,
                (int) position.y + 2,
                (int) position.z,
                Blocks.air,
                0,
                3
            );
            this.worldObj.setBlock(
                (int) position.x - 1, (int) position.y, (int) position.z, Blocks.air, 0, 3
            );
            this.worldObj.setBlock(
                (int) position.x - 1,
                (int) position.y + 1,
                (int) position.z,
                Blocks.air,
                0,
                3
            );
            this.worldObj.setBlock(
                (int) position.x - 1,
                (int) position.y + 2,
                (int) position.z,
                Blocks.air,
                0,
                3
            );
        } else {
            this.worldObj.setBlock(
                (int) position.x, (int) position.y, (int) position.z, Blocks.air, 0, 3
            );
            this.worldObj.setBlock(
                (int) position.x, (int) position.y, (int) position.z + 1, Blocks.air, 0, 3
            );
            this.worldObj.setBlock(
                (int) position.x,
                (int) position.y + 1,
                (int) position.z + 1,
                Blocks.air,
                0,
                3
            );
            this.worldObj.setBlock(
                (int) position.x,
                (int) position.y + 2,
                (int) position.z + 1,
                Blocks.air,
                0,
                3
            );
            this.worldObj.setBlock(
                (int) position.x, (int) position.y, (int) position.z - 1, Blocks.air, 0, 3
            );
            this.worldObj.setBlock(
                (int) position.x,
                (int) position.y + 1,
                (int) position.z - 1,
                Blocks.air,
                0,
                3
            );
            this.worldObj.setBlock(
                (int) position.x,
                (int) position.y + 2,
                (int) position.z - 1,
                Blocks.air,
                0,
                3
            );
        }

        if (this.daoDan != null) {
            ((Entity) this.daoDan).setDead();
        }
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
    public AxisAlignedBB getRenderBoundingBox() {
        return TileEntity.INFINITE_EXTENT_AABB;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return true;
    }

    @Override
    public boolean isItemValidForSlot(final int slotID, final ItemStack itemStack) {
        return itemStack.getItem() instanceof ItMissile;
    }

    @Override
    public IMissile getContainingMissile() {
        return this.daoDan;
    }

    @Override
    public void setContainingMissile(final IMissile missile) {
        this.daoDan = missile;
    }

    @Override
    public ILauncherController getController() {
        for (byte i = 2; i < 6; ++i) {
            final Vector3 position = new Vector3(this).modifyPositionFromSide(
                ForgeDirection.getOrientation((int) i)
            );
            final TileEntity tileEntity
                = position.getTileEntity((IBlockAccess) this.worldObj);

            if (tileEntity instanceof ILauncherController) {
                return (ILauncherController) tileEntity;
            }
        }

        return null;
    }
}
