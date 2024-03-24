package icbm.gangshao;

import icbm.gangshao.access.AccessLevel;
import icbm.gangshao.terminal.ITerminal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import universalelectricity.prefab.SlotSpecific;

public class SlotTurret extends SlotSpecific {
    public SlotTurret(
        final IInventory par2iInventory,
        final int par3,
        final int par4,
        final int par5,
        final Class... validClasses
    ) {
        super(par2iInventory, par3, par4, par5, validClasses);
    }

    public boolean canTakeStack(final EntityPlayer entityPlayer) {
        return super.inventory instanceof ITerminal
            && ((ITerminal) super.inventory)
                   .getUserAccess(entityPlayer.getDisplayName())
                   .ordinal()
            > AccessLevel.NONE.ordinal();
    }
}
