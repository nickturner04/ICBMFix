package icbm.wanyi;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class SetSignalDisrupterFrequencyPacket implements IMessage {
    int freq;

    public SetSignalDisrupterFrequencyPacket() {
        this(0);
    }

    public SetSignalDisrupterFrequencyPacket(int freq) {
        this.freq = freq;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.freq = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(freq);
    }
}
