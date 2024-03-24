package icbm.zhapin.jiqi;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import universalelectricity.core.vector.Vector3;

public class LauncherControlPanelGuiPacket implements IMessage {
    Vector3 pos;
    int frequency;
    Vector3 target;
    short height;

    public LauncherControlPanelGuiPacket() {}

    public LauncherControlPanelGuiPacket(TLauncherControlPanel te) {
        this.pos = new Vector3(te);
        this.frequency = te.getFrequency();
        this.target = te.getTarget();
        this.height = te.height;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.pos = new Vector3(buf.readInt(), buf.readInt(), buf.readInt());
        this.frequency = buf.readInt();
        this.target = new Vector3(buf.readInt(), buf.readInt(), buf.readInt());
        this.height = buf.readShort();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.pos.intX());
        buf.writeInt(this.pos.intY());
        buf.writeInt(this.pos.intZ());

        buf.writeInt(this.frequency);

        buf.writeInt(this.target.intX());
        buf.writeInt(this.target.intY());
        buf.writeInt(this.target.intZ());

        buf.writeShort(this.height);
    }
}
