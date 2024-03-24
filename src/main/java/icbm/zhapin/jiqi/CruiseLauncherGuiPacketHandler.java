package icbm.zhapin.jiqi;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CruiseLauncherGuiPacketHandler
    implements IMessageHandler<CruiseLauncherGuiPacket, IMessage> {
    @Override
    public IMessage onMessage(CruiseLauncherGuiPacket message, MessageContext ctx) {
        World world = ctx.getServerHandler().playerEntity.worldObj;

        TileEntity te = message.pos.getTileEntity(world);

        if (te instanceof TCruiseLauncher) {
            TCruiseLauncher cl = (TCruiseLauncher)te;

            cl.setFrequency(message.frequency);
            cl.setTarget(message.target);
        }

        return null;
    }
}
