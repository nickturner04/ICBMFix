package icbm.wanyi;

import java.util.Optional;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import universalelectricity.core.vector.Vector3;

public class ProximityDetectorModePacket implements IMessage {
    Vector3 pos;

    public Optional<Byte> mode;
    public Optional<Short> frequency;
    public Optional<Vector3> minCoord;
    public Optional<Vector3> maxCoord;

    public ProximityDetectorModePacket() {
        this(null);
    }

    public ProximityDetectorModePacket(Vector3 pos) {
        this.pos = pos;

        this.mode = Optional.empty();
        this.frequency = Optional.empty();
        this.minCoord = Optional.empty();
        this.maxCoord = Optional.empty();
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.pos = new Vector3(buf.readInt(), buf.readInt(), buf.readInt());

        int ordinal = buf.readInt();

        if (ordinal < 0 || ordinal >= Type.values().length)
            return;

        switch (Type.values()[ordinal]) {
            case MODE:
                this.mode = Optional.of(buf.readByte());
                break;

            case FREQUENCY:
                this.frequency = Optional.of(buf.readShort());
                break;

            case MIN_COORD:
                this.minCoord = Optional.of(new Vector3(
                    clampCoord(buf.readInt()),
                    clampCoord(buf.readInt()),
                    clampCoord(buf.readInt())
                ));
                break;

            case MAX_COORD:
                this.maxCoord = Optional.of(new Vector3(
                    clampCoord(buf.readInt()),
                    clampCoord(buf.readInt()),
                    clampCoord(buf.readInt())
                ));
                break;
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.pos.intX());
        buf.writeInt(this.pos.intY());
        buf.writeInt(this.pos.intZ());

        Type type = this.mode.isPresent() ? Type.MODE
            : this.frequency.isPresent()  ? Type.FREQUENCY
            : this.minCoord.isPresent()   ? Type.MIN_COORD
            : this.maxCoord.isPresent()   ? Type.MAX_COORD
                                          : null;

        if (type == null)
            throw new IllegalArgumentException("No field set!");

        buf.writeInt(type.ordinal());

        switch (type) {
            case MODE:
                buf.writeByte(this.mode.get());
                break;

            case FREQUENCY:
                buf.writeShort(this.frequency.get());
                break;

            case MIN_COORD: {
                Vector3 v = this.minCoord.get();
                buf.writeInt(v.intX());
                buf.writeInt(v.intY());
                buf.writeInt(v.intZ());
                break;
            }

            case MAX_COORD: {
                Vector3 v = this.maxCoord.get();
                buf.writeInt(v.intX());
                buf.writeInt(v.intY());
                buf.writeInt(v.intZ());
                break;
            }
        }
    }

    static int clampCoord(int coord) {
        return Math.min(30, Math.max(0, coord));
    }

    public static enum Type {
        MODE,
        FREQUENCY,
        MIN_COORD,
        MAX_COORD,
    }
}
