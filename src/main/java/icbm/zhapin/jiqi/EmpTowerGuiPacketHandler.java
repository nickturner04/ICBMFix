package icbm.zhapin.jiqi;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class EmpTowerGuiPacketHandler
    implements IMessageHandler<EmpTowerGuiPacket, IMessage> {
    @Override
    public IMessage onMessage(EmpTowerGuiPacket message, MessageContext ctx) {
        World world = ctx.getServerHandler().playerEntity.worldObj;

        TileEntity te = message.pos.getTileEntity(world);

        if (te instanceof TEmpTower) {
            ((TEmpTower) te).radius = message.radius;
            ((TEmpTower) te).holzOhJa = message.holzOhJa;
        }

        return null;
    }
}
