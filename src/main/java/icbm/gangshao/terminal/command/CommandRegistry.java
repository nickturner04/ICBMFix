package icbm.gangshao.terminal.command;

import java.util.ArrayList;
import java.util.List;

import icbm.gangshao.terminal.ITerminal;
import icbm.gangshao.terminal.TerminalCommand;
import net.minecraft.entity.player.EntityPlayer;

public class CommandRegistry {
    public static final List<TerminalCommand> COMMANDS;

    public static void register(final TerminalCommand cmd) {
        if (!CommandRegistry.COMMANDS.contains(cmd)) {
            CommandRegistry.COMMANDS.add(cmd);
        }
    }

    public static void
    onCommand(final EntityPlayer player, final ITerminal terminal, final String cmd) {
        if (cmd != null && cmd != "") {
            final String[] args = cmd.split(" ");
            terminal.addToConsole("§A" + player.getDisplayName() + ": " + cmd);

            if (args[0] != null) {
                for (final TerminalCommand command : CommandRegistry.COMMANDS) {
                    if (command.getCommandPrefix().equalsIgnoreCase(args[0])) {
                        if (!command.canMachineUse(terminal)) {
                            terminal.addToConsole("N/A");
                            return;
                        }

                        if (!command.canPlayerUse(player, terminal)) {
                            terminal.addToConsole("Access Denied.");
                            return;
                        }

                        if (command.processCommand(player, terminal, args)) {
                            return;
                        }

                        continue;
                    }
                }
            }

            terminal.addToConsole("Unknown Command.");
        }
    }

    static {
        COMMANDS = new ArrayList<>();
    }
}
