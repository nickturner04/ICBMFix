package icbm.gangshao.terminal;

import java.util.List;

import icbm.gangshao.ISpecialAccess;
import net.minecraft.entity.player.EntityPlayer;

public abstract class TerminalCommand {
    public abstract String getCommandPrefix();

    public abstract boolean
    processCommand(final EntityPlayer p0, final ITerminal p1, final String[] p2);

    public abstract boolean canPlayerUse(final EntityPlayer p0, final ISpecialAccess p1);

    public abstract boolean showOnHelp(final EntityPlayer p0, final ISpecialAccess p1);

    public abstract List<String>
    getCmdUses(final EntityPlayer p0, final ISpecialAccess p1);

    public abstract boolean canMachineUse(final ISpecialAccess p0);
}
