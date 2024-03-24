package icbm.zhapin;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import universalelectricity.core.vector.Vector3;

public class ItemUsePacket implements IMessage {
    Type type;
    Vector3 pos;

    public ItemUsePacket(Type type, Vector3 pos) {
        this.type = type;
        this.pos = pos;
    }

    public ItemUsePacket() {}

    @Override
    public void fromBytes(ByteBuf buf) {
        this.type = Type.get(buf.readInt());
        this.pos = new Vector3(buf.readInt(), buf.readInt(), buf.readInt());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.type.ordinal());
        buf.writeInt(this.pos.intX());
        buf.writeInt(this.pos.intY());
        buf.writeInt(this.pos.intZ());
    }

    public enum Type {
        UNSPECIFIED,
        RADAR_GUN,
        LASER_DESIGNATOR,
        REMOTE;

        public static Type get(final int id) {
            if (id >= 0 && id < values().length) {
                return values()[id];
            }

            return Type.UNSPECIFIED;
        }
    }
}
