package icbm.gangshao.terminal;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import icbm.gangshao.terminal.command.CommandRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class TerminalCommandPacketHandler
    implements IMessageHandler<TerminalCommandPacket, IMessage> {
    @Override
    public IMessage onMessage(TerminalCommandPacket message, MessageContext ctx) {
        EntityPlayer pl = ctx.getServerHandler().playerEntity;

        TileEntity te = message.pos.getTileEntity(pl.worldObj);
        if (te instanceof TileEntityTerminal) {
            TileEntityTerminal t = (TileEntityTerminal) te;

            CommandRegistry.onCommand(pl, t, message.cmd);
            t.sendTerminalOutputToClients();
        }

        return null;
    }
}
