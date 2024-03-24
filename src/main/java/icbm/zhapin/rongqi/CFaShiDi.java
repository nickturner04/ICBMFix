package icbm.zhapin.rongqi;

import icbm.zhapin.ICBMExplosion;
import icbm.zhapin.jiqi.TLauncherPlatform;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import universalelectricity.prefab.SlotSpecific;

public class CFaShiDi extends Container {
    private TLauncherPlatform missileLauncher;

    public CFaShiDi(
        final InventoryPlayer par1InventoryPlayer, final TLauncherPlatform tileEntity
    ) {
        this.missileLauncher = tileEntity;
        this.addSlotToContainer((Slot) new SlotSpecific(
            (IInventory) tileEntity,
            0,
            84,
            47,
            new ItemStack[] { new ItemStack(ICBMExplosion.itDaoDan),
                              new ItemStack(ICBMExplosion.itTeBieDaoDan) }
        ));

        for (int var3 = 0; var3 < 3; ++var3) {
            for (int var4 = 0; var4 < 9; ++var4) {
                this.addSlotToContainer(new Slot(
                    (IInventory) par1InventoryPlayer,
                    var4 + var3 * 9 + 9,
                    8 + var4 * 18,
                    84 + var3 * 18
                ));
            }
        }

        for (int var3 = 0; var3 < 9; ++var3) {
            this.addSlotToContainer(
                new Slot((IInventory) par1InventoryPlayer, var3, 8 + var3 * 18, 142)
            );
        }
    }

    public boolean canInteractWith(final EntityPlayer par1EntityPlayer) {
        return this.missileLauncher.isUseableByPlayer(par1EntityPlayer);
    }

    public ItemStack
    transferStackInSlot(final EntityPlayer par1EntityPlayer, final int par1) {
        ItemStack var2 = null;
        final Slot var3 = (Slot) super.inventorySlots.get(par1);

        if (var3 != null && var3.getHasStack()) {
            final ItemStack var4 = var3.getStack();
            var2 = var4.copy();

            if (par1 != 0) {
                if (this.getSlot(0).isItemValid(var4)
                    && !this.mergeItemStack(var4, 0, 1, false)) {
                    return null;
                }
            } else if (!this.mergeItemStack(var4, 3, 37, false)) {
                return null;
            }

            if (var4.stackSize == 0) {
                var3.putStack((ItemStack) null);
            } else {
                var3.onSlotChanged();
            }

            if (var4.stackSize == var2.stackSize) {
                return null;
            }

            var3.onPickupFromSlot(par1EntityPlayer, var4);
        }

        return var2;
    }
}
