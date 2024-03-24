package icbm.zhapin.jiqi;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class LauncherControlPanelGuiPacketHandler
    implements IMessageHandler<LauncherControlPanelGuiPacket, IMessage> {
    @Override
    public IMessage onMessage(LauncherControlPanelGuiPacket message, MessageContext ctx) {
        World world = ctx.getServerHandler().playerEntity.worldObj;

        TileEntity te = message.pos.getTileEntity(world);

        if (te instanceof TLauncherControlPanel) {
            TLauncherControlPanel lcp = ((TLauncherControlPanel) te);

            lcp.setFrequency(message.frequency);
            lcp.target = message.target;
            if (lcp.getTier() < 2)
                lcp.target.y = 0.0;
            lcp.height = (short) Math.max(Math.min(message.height, 99), 3);
        }

        return null;
    }
}
