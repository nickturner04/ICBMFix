package icbm.gangshao.terminal;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TerminalOutputPacketHandler
    implements IMessageHandler<TerminalOutputPacket, IMessage> {
    @Override
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(TerminalOutputPacket message, MessageContext ctx) {
        World world = FMLClientHandler.instance().getWorldClient();

        TileEntity te = message.pos.getTileEntity(world);
        if (te instanceof TileEntityTerminal) {
            TileEntityTerminal t = (TileEntityTerminal) te;

            boolean shouldSetScroll = !t.terminalOutput.equals(message.output);
            t.terminalOutput = message.output;
            if (shouldSetScroll) {
                t.setScroll(t.terminalOutput.size() - 15);
            }
        }

        return null;
    }
}
