package icbm.zhapin.jiqi;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import universalelectricity.core.vector.Vector3;

public class EmpTowerGuiPacket implements IMessage {
    Vector3 pos;
    int radius;
    byte holzOhJa;

    public EmpTowerGuiPacket(Vector3 pos, int radius, byte holzOhJa) {
        this.holzOhJa = holzOhJa;
        this.pos = pos;
        this.radius = radius;
    }

    public EmpTowerGuiPacket() {}

    @Override
    public void fromBytes(ByteBuf buf) {
        this.pos = new Vector3(buf.readInt(), buf.readInt(), buf.readInt());
        this.radius = buf.readInt();
        this.holzOhJa = buf.readByte();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.pos.intX());
        buf.writeInt(this.pos.intY());
        buf.writeInt(this.pos.intZ());

        buf.writeInt(this.radius);
        buf.writeByte(this.holzOhJa);
    }
}
