package icbm.gangshao.turret.mount;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import icbm.core.MainBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import universalelectricity.core.vector.Vector3;

public class ESeat extends Entity implements IEntityAdditionalSpawnData {
    private TileEntity controller;
    private boolean shouldSit;

    public ESeat(final World par1World) {
        super(par1World);
        this.shouldSit = false;
        this.setSize(1.0f, 1.0f);
        super.noClip = true;
    }

    public ESeat(
        final World par1World,
        final Vector3 position,
        final TileEntity controller,
        final boolean sit
    ) {
        this(par1World);
        super.isImmuneToFire = true;
        this.setPosition(position.x, position.y, position.z);
        this.controller = controller;
        this.shouldSit = sit;
    }

    @Override
    public String getCommandSenderName() {
        return "Seat";
    }

    @Override
    public void writeSpawnData(final ByteBuf data) {
        if (this.controller != null) {
            data.writeInt(this.controller.xCoord);
            data.writeInt(this.controller.yCoord);
            data.writeInt(this.controller.zCoord);
        } else {
            MainBase.LOGGER.severe("Failed to send ridable turret packet!");
        }

        data.writeBoolean(this.shouldSit);
    }

    @Override
    public void readSpawnData(final ByteBuf data) {
        try {
            this.controller = super.worldObj.getTileEntity(
                data.readInt(), data.readInt(), data.readInt()
            );
            this.shouldSit = data.readBoolean();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdate() {
        if (this.controller == null) {
            this.setDead();
            return;
        }

        if (this.controller.isInvalid()) {
            this.setDead();
            return;
        }

        if (this.controller instanceof TTurretSeat) {
            ((TTurretSeat) this.controller).entityFake = this;
        }

        if (super.worldObj.isRemote && super.riddenByEntity != null) {
            super.riddenByEntity.updateRiderPosition();
        }

        super.posY = this.controller.yCoord + 1.2;
    }

    @Override
    public double getMountedYOffset() {
        return -0.5;
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    public boolean shouldRiderSit() {
        return this.shouldSit;
    }

    @Override
    protected void entityInit() {}

    @Override
    protected void readEntityFromNBT(final NBTTagCompound nbt) {
        this.shouldSit = nbt.getBoolean("shouldSit");
    }

    @Override
    protected void writeEntityToNBT(final NBTTagCompound nbt) {
        nbt.setBoolean("shouldSit", this.shouldSit);
    }
}
