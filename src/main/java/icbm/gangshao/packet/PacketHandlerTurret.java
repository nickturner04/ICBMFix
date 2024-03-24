package icbm.gangshao.packet;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.gangshao.turret.TTurretBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PacketHandlerTurret implements IMessageHandler<PacketTurret, IMessage> {
    @Override
    @SideOnly(Side.CLIENT)
    public IMessage onMessage(PacketTurret message, MessageContext ctx) {
        World world = FMLClientHandler.instance().getWorldClient();

        TileEntity te = message.turretPos.getTileEntity(world);

        if (te instanceof TTurretBase) {
            ((TTurretBase) te).onTurretPacket(message.type, message.data);
        }

        return null;
    }
}
