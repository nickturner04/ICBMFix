package icbm.wanyi;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import icbm.wanyi.b.TProximityDetector;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ProximityDetectorModePacketHandler
    implements IMessageHandler<ProximityDetectorModePacket, IMessage> {
    @Override
    public IMessage onMessage(ProximityDetectorModePacket message, MessageContext ctx) {
        World world = ctx.getServerHandler().playerEntity.worldObj;

        TileEntity te = message.pos.getTileEntity(world);

        if (te instanceof TProximityDetector) {
            ((TProximityDetector) te).onModePacket(message);
        }

        return null;
    }
}
