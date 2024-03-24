package icbm.zhapin;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import universalelectricity.core.vector.Vector3;

public class ELightBeam extends Entity implements IEntityAdditionalSpawnData {
    private int life;
    public float red;
    public float green;
    public float blue;

    public ELightBeam(final World world) {
        super(world);
        this.setSize(1.0f, 1.0f);
        super.preventEntitySpawning = true;
        super.ignoreFrustumCheck = true;
        super.renderDistanceWeight = 3.0;
    }

    public ELightBeam(
        final World world,
        final Vector3 position,
        final int life,
        final float red,
        final float green,
        final float blue
    ) {
        super(world);
        this.setPosition(position.x, position.y, position.z);
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.life = life;
    }

    @Override
    public String getCommandSenderName() {
        return "Light Beam";
    }

    @Override
    public void writeSpawnData(final ByteBuf data) {
        data.writeInt(this.life);
        data.writeFloat(this.red);
        data.writeFloat(this.green);
        data.writeFloat(this.blue);
    }

    @Override
    public void readSpawnData(final ByteBuf data) {
        this.life = data.readInt();
        this.red = data.readFloat();
        this.green = data.readFloat();
        this.blue = data.readFloat();
    }

    @Override
    protected void entityInit() {}

    @Override
    public void onUpdate() {
        if (this.life > 0) {
            --this.life;
        } else {
            this.setDead();
        }
    }

    @Override
    public float getShadowSize() {
        return 0.0f;
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    protected void readEntityFromNBT(final NBTTagCompound var1) {}

    @Override
    protected void writeEntityToNBT(final NBTTagCompound var1) {}
}
