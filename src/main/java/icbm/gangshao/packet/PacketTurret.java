package icbm.gangshao.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import universalelectricity.core.vector.Vector3;

public class PacketTurret implements IMessage {
    Type type;
    Vector3 turretPos;
    NBTTagCompound data;

    public PacketTurret() {
        this(null, null, null);
    }

    public PacketTurret(Vector3 turretPos, Type type, NBTTagCompound data) {
        this.type = type;
        this.data = data;
        this.turretPos = turretPos;
    }

    public static PacketTurret rotation(Vector3 turretPos, float yaw, float pitch) {
        NBTTagCompound data = new NBTTagCompound();

        data.setFloat("yaw", yaw);
        data.setFloat("pitch", pitch);

        return new PacketTurret(turretPos, Type.ROTATION, data);
    }

    public static PacketTurret
    shot(Vector3 turretPos, Vector3 target, float yaw, float pitch) {
        NBTTagCompound data = new NBTTagCompound();

        data.setTag("target", target.writeToNBT(new NBTTagCompound()));
        data.setFloat("yaw", yaw);
        data.setFloat("pitch", pitch);

        return new PacketTurret(turretPos, Type.SHOT, data);
    }

    public static PacketTurret stats(Vector3 turretPos, int health) {
        NBTTagCompound data = new NBTTagCompound();

        data.setInteger("health", health);

        return new PacketTurret(turretPos, Type.STATS, data);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        try {
            this.data = CompressedStreamTools.readCompressed(
                new DataInputStream(new ByteBufInputStream(buf))
            );

            this.turretPos = Vector3.readFromNBT(this.data);

            if (this.data.getInteger("type") >= Type.values().length)
                throw new IllegalArgumentException("Type out of bounds");

            this.type = Type.values()[this.data.getInteger("type")];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        this.data.setInteger("type", this.type.ordinal());
        this.turretPos.writeToNBT(this.data);

        try {
            CompressedStreamTools.writeCompressed(
                this.data, new DataOutputStream(new ByteBufOutputStream(buf))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static enum Type {
        ROTATION,
        SHOT,
        STATS;
    }
}
