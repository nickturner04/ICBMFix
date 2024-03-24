package icbm.wanyi;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.item.ItemStack;

public class SetSignalDisrupterFrequencyPacketHandler
    implements IMessageHandler<SetSignalDisrupterFrequencyPacket, IMessage> {
    @Override
    public IMessage
    onMessage(SetSignalDisrupterFrequencyPacket message, MessageContext ctx) {
        ItemStack handStack
            = ctx.getServerHandler().playerEntity.getCurrentEquippedItem();

        if (handStack.getItem() instanceof ItSignalDisrupter) {
            ((ItSignalDisrupter) handStack.getItem())
                .setFrequency(message.freq, handStack);
        }

        return null;
    }
}
