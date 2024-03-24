package icbm.gangshao.terminal.command;

import java.util.ArrayList;
import java.util.List;

import icbm.gangshao.ISpecialAccess;
import icbm.gangshao.access.AccessLevel;
import icbm.gangshao.platform.TTurretPlatform;
import icbm.gangshao.terminal.ITerminal;
import icbm.gangshao.terminal.TerminalCommand;
import net.minecraft.entity.player.EntityPlayer;

public class CommandAccess extends TerminalCommand {
    @Override
    public String getCommandPrefix() {
        return "access";
    }

    @Override
    public boolean processCommand(
        final EntityPlayer player, final ITerminal terminal, final String[] args
    ) {
        if (args[0].equalsIgnoreCase("access") && args.length > 1 && args[1] != null
            && terminal instanceof TTurretPlatform) {
            final TTurretPlatform platform = (TTurretPlatform) terminal;
            final AccessLevel userAccess
                = terminal.getUserAccess(player.getDisplayName());

            if (args[1].equalsIgnoreCase("?")) {
                terminal.addToConsole(
                    "Access Level: "
                    + platform.getUserAccess(player.getDisplayName()).displayName
                );
                return true;
            }

            if (args[1].equalsIgnoreCase("set") && args.length > 3
                && userAccess.ordinal() >= AccessLevel.ADMIN.ordinal()) {
                final String username = args[2];
                final AccessLevel currentAccess = terminal.getUserAccess(username);
                final AccessLevel playerAccess
                    = terminal.getUserAccess(player.getDisplayName());

                if (playerAccess.ordinal() < AccessLevel.ADMIN.ordinal()
                    || playerAccess.ordinal() < currentAccess.ordinal()
                    || (player.getDisplayName().equalsIgnoreCase(username)
                        && playerAccess != AccessLevel.OWNER)) {
                    terminal.addToConsole("Access denied!");
                    return true;
                }

                if (currentAccess != AccessLevel.NONE) {
                    final AccessLevel newAccess = AccessLevel.get(args[3]);

                    if ((currentAccess != AccessLevel.OWNER
                         || platform.getUsersWithAcess(AccessLevel.OWNER).size() > 1)
                        && newAccess != AccessLevel.NONE
                        && terminal.addUserAccess(username, newAccess, true)) {
                        terminal.addToConsole(
                            username + " set to " + newAccess.displayName
                        );
                        platform.getWorldObj().markBlockForUpdate(
                            platform.xCoord, platform.yCoord, platform.zCoord
                        );
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public boolean canPlayerUse(final EntityPlayer var1, final ISpecialAccess mm) {
        return mm.getUserAccess(var1.getDisplayName()).ordinal()
            >= AccessLevel.USER.ordinal()
            || var1.capabilities.isCreativeMode;
    }

    @Override
    public boolean showOnHelp(final EntityPlayer player, final ISpecialAccess mm) {
        return this.canPlayerUse(player, mm);
    }

    @Override
    public List<String> getCmdUses(final EntityPlayer player, final ISpecialAccess mm) {
        final List<String> cmds = new ArrayList<>();
        cmds.add("access set username level");
        cmds.add("access ?");
        return cmds;
    }

    @Override
    public boolean canMachineUse(final ISpecialAccess mm) {
        return mm instanceof TTurretPlatform;
    }
}
