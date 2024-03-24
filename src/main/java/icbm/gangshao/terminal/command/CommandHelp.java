package icbm.gangshao.terminal.command;

import java.util.ArrayList;
import java.util.List;

import icbm.gangshao.ISpecialAccess;
import icbm.gangshao.terminal.ITerminal;
import icbm.gangshao.terminal.TerminalCommand;
import net.minecraft.entity.player.EntityPlayer;

public class CommandHelp extends TerminalCommand {
    @Override
    public String getCommandPrefix() {
        return "help";
    }

    @Override
    public boolean
    processCommand(final EntityPlayer player, final ITerminal te, final String[] args) {
        if (args.length > 1) {
            final List<String> displayed = new ArrayList<>();

            for (final TerminalCommand cc : CommandRegistry.COMMANDS) {
                if (cc.getCommandPrefix().equalsIgnoreCase(args[1])
                    && cc.showOnHelp(player, te) && cc.canMachineUse(te)) {
                    te.addToConsole("----------------------");
                    te.addToConsole(args[1] + " commands");
                    te.addToConsole("----------------------");
                    final List<String> ccList = cc.getCmdUses(player, te);

                    for (final String cm : ccList) {
                        if (!displayed.contains(cm.toLowerCase())) {
                            te.addToConsole(cm);
                            displayed.add(cm.toLowerCase());
                        }
                    }

                    te.addToConsole("----------------------");
                }
            }

            return true;
        }

        te.addToConsole("----------------------");
        te.addToConsole("Listing commands");
        te.addToConsole("----------------------");
        te.addToConsole("Help command");

        for (final TerminalCommand cc2 : CommandRegistry.COMMANDS) {
            if (cc2.showOnHelp(player, te) && cc2.canMachineUse(te)) {
                final List<String> ccList2 = cc2.getCmdUses(player, te);

                for (final String cm2 : ccList2) {
                    te.addToConsole(cm2);
                }
            }
        }

        te.addToConsole("-----------------------");
        return true;
    }

    @Override
    public boolean canPlayerUse(final EntityPlayer var1, final ISpecialAccess mm) {
        return true;
    }

    @Override
    public boolean showOnHelp(final EntityPlayer player, final ISpecialAccess mm) {
        return false;
    }

    @Override
    public List<String> getCmdUses(final EntityPlayer player, final ISpecialAccess mm) {
        return null;
    }

    @Override
    public boolean canMachineUse(final ISpecialAccess mm) {
        return true;
    }
}
