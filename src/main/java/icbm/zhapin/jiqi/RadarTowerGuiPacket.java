package icbm.zhapin.jiqi;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import universalelectricity.core.vector.Vector3;

public class RadarTowerGuiPacket implements IMessage {
    Vector3 pos;
    int safetyRadius;
    int alarmRadius;

    public RadarTowerGuiPacket(TRadarTower te) {
        this.pos = new Vector3(te);
        this.safetyRadius = te.safetyRadius;
        this.alarmRadius = te.alarmRadius;
    }

    public RadarTowerGuiPacket() {
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.pos = new Vector3(buf.readInt(), buf.readInt(), buf.readInt());
        this.safetyRadius = buf.readInt();
        this.alarmRadius = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.pos.intX());
        buf.writeInt(this.pos.intY());
        buf.writeInt(this.pos.intZ());

        buf.writeInt(this.safetyRadius);
        buf.writeInt(this.alarmRadius);
    }
}
