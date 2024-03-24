package icbm.gangshao.terminal.command;

import java.util.ArrayList;
import java.util.List;

import icbm.gangshao.ISpecialAccess;
import icbm.gangshao.access.AccessLevel;
import icbm.gangshao.access.UserAccess;
import icbm.gangshao.platform.TTurretPlatform;
import icbm.gangshao.terminal.ITerminal;
import icbm.gangshao.terminal.TerminalCommand;
import net.minecraft.entity.player.EntityPlayer;

public class CommandGet extends TerminalCommand {
    @Override
    public String getCommandPrefix() {
        return "get";
    }

    @Override
    public boolean
    processCommand(final EntityPlayer player, final ITerminal TE, final String[] args) {
        if (args[0].equalsIgnoreCase("get") && args.length > 1 && args[1] != null
            && TE instanceof TTurretPlatform) {
            final TTurretPlatform turret = (TTurretPlatform) TE;

            if (args[1].equalsIgnoreCase("owner")) {
                final List<UserAccess> userList
                    = turret.getUsersWithAcess(AccessLevel.OWNER);

                if (userList.size() > 0) {
                    for (final UserAccess access : userList) {
                        TE.addToConsole("" + access.username);
                    }
                } else {
                    TE.addToConsole("No owners");
                }

                return true;
            }

            if (args[1].equalsIgnoreCase("position")) {
                TE.addToConsole(
                    "position: " + turret.xCoord + "x " + turret.yCoord + "y "
                    + turret.zCoord + "z "
                );
                return true;
            }

            if (args[1].equalsIgnoreCase("kills")) {
                TE.addToConsole("Not yet useable");
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean canPlayerUse(final EntityPlayer var1, final ISpecialAccess mm) {
        return true;
    }

    @Override
    public boolean showOnHelp(final EntityPlayer player, final ISpecialAccess mm) {
        return true;
    }

    @Override
    public List<String> getCmdUses(final EntityPlayer player, final ISpecialAccess mm) {
        final List<String> cmds = new ArrayList<>();
        cmds.add("get owner");
        cmds.add("get position");
        return cmds;
    }

    @Override
    public boolean canMachineUse(final ISpecialAccess mm) {
        return mm instanceof TTurretPlatform;
    }
}
