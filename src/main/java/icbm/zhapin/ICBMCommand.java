package icbm.zhapin;

import java.util.Iterator;
import java.util.List;

import icbm.zhapin.zhapin.EExplosion;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;

public class ICBMCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "icbm";
    }

    @Override
    public String getCommandUsage(final ICommandSender par1ICommandSender) {
        return "/icbm lag <radius>";
    }

    @Override
    public void processCommand(final ICommandSender sender, final String[] args) {
        try {
            final EntityPlayer entityPlayer = (EntityPlayer) sender;
            final int dimension
                = ((Entity) entityPlayer).worldObj.getWorldInfo().getVanillaDimension();

            if (args[0].equalsIgnoreCase("lag")) {
                final int radius = parseInt(sender, args[1]);

                if (radius > 0 && radius < Integer.MAX_VALUE) {
                    final EntityPlayer player = (EntityPlayer) sender;
                    final AxisAlignedBB bounds = AxisAlignedBB.getBoundingBox(
                        ((Entity) player).posX - radius,
                        ((Entity) player).posY - radius,
                        ((Entity) player).posZ - radius,
                        ((Entity) player).posX + radius,
                        ((Entity) player).posY + radius,
                        ((Entity) player).posZ + radius
                    );
                    final List<Entity> entitiesNearby
                        = player.worldObj.getEntitiesWithinAABB(Entity.class, bounds);

                    for (final Entity entity : entitiesNearby) {
                        if (entity instanceof EGravityBlock) {
                            ((EGravityBlock) entity).setBlock();
                        } else {
                            if (!(entity instanceof EExplosion)) {
                                continue;
                            }

                            entity.setDead();
                        }
                    }

                    sender.addChatMessage(new ChatComponentText(
                        "Removed all ICBM lag sources within " + radius + " radius."
                    ));
                    return;
                }

                throw new WrongUsageException("Radius not within range.", new Object[0]);
            }
        } catch (final Exception ex) {}

        throw new WrongUsageException(this.getCommandUsage(sender), new Object[0]);
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public List
    addTabCompletionOptions(final ICommandSender sender, final String[] args) {
        return (args.length == 1)
            ? getListOfStringsMatchingLastWord(args, new String[] { "lag" })
            : null;
    }
}
