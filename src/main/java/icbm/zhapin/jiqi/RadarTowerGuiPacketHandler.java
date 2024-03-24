package icbm.zhapin.jiqi;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class RadarTowerGuiPacketHandler
    implements IMessageHandler<RadarTowerGuiPacket, IMessage> {
    @Override
    public IMessage onMessage(RadarTowerGuiPacket message, MessageContext ctx) {
        World world = ctx.getServerHandler().playerEntity.worldObj;

        TileEntity te = message.pos.getTileEntity(world);

        if (te instanceof TRadarTower) {
            TRadarTower rt = (TRadarTower) te;

            rt.alarmRadius = message.alarmRadius;
            rt.safetyRadius = message.safetyRadius;
        }

        return null;
    }
}
