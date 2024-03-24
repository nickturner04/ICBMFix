package icbm.gangshao.damage;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import icbm.gangshao.turret.TTurretBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityTileDamagable
    extends EntityLiving implements IEntityAdditionalSpawnData {
    private TTurretBase host;

    public EntityTileDamagable(final World par1World) {
        super(par1World);
        this.isImmuneToFire = true;
        this.setSize(1.1f, 1.1f);
    }

    public EntityTileDamagable(final TTurretBase host) {
        this(host.getWorldObj());
        this.setPosition(host.xCoord + 0.5, (double) host.yCoord, host.zCoord + 0.5);
        this.host = host;
    }

    @Override
    public boolean attackEntityFrom(final DamageSource source, final float amount) {
        return !(this.host instanceof IHealthTile)
            || this.host.onDamageTaken(source, (int) amount);
    }

    @Override
    public boolean isPotionApplicable(final PotionEffect par1PotionEffect) {
        return par1PotionEffect != null && this.host instanceof IHealthTile
            && this.host.canApplyPotion(par1PotionEffect);
    }

    public void addPotionEffect(final PotionEffect par1PotionEffect) {
        if (this.isPotionApplicable(par1PotionEffect)) {
            if (this.isPotionActive(par1PotionEffect.getPotionID())) {
                ((PotionEffect) this.activePotionsMap.get(par1PotionEffect.getPotionID()))
                    .combine(par1PotionEffect);
                this.onChangedPotionEffect(
                    (PotionEffect
                    ) this.activePotionsMap.get(par1PotionEffect.getPotionID()),
                    true
                );
            } else {
                this.activePotionsMap.put(
                    par1PotionEffect.getPotionID(), par1PotionEffect
                );
                this.onNewPotionEffect(par1PotionEffect);
            }
        }
    }

    @Override
    public String getCustomNameTag() {
        if (this.host == null) {
            return "EntityTileTarget";
        }

        if (this.host.getPlatform() != null) {
            return this.host.getPlatform().getInventoryName();
        }

        return this.host.getName();
    }

    @Override
    public void writeSpawnData(final ByteBuf data) {
        if (this.host != null) {
            data.writeInt(this.host.xCoord);
            data.writeInt(this.host.yCoord);
            data.writeInt(this.host.zCoord);
        }
    }

    @Override
    public void readSpawnData(final ByteBuf data) {
        try {
            final TileEntity tileEntity
                = ((Entity) this)
                      .worldObj.getTileEntity(
                          data.readInt(), data.readInt(), data.readInt()
                      );

            if (tileEntity instanceof TTurretBase) {
                this.host = (TTurretBase) tileEntity;
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdate() {
        if (!((Entity) this).worldObj.isRemote) {
            if (this.host == null || this.host.isInvalid()) {
                this.setDead();
            } else if (this.host instanceof IHealthTile && !this.host.isAlive()) {
                this.setDead();
            } else {
                this.updatePotionEffects();
                this.setPosition(
                    this.host.xCoord + 0.5,
                    (double) this.host.yCoord,
                    this.host.zCoord + 0.5
                );
            }
        }
    }

    @Override
    public void readEntityFromNBT(final NBTTagCompound nbttagcompound) {}

    @Override
    public void moveEntity(final double par1, final double par3, final double par5) {}

    @Override
    public void writeEntityToNBT(final NBTTagCompound nbttagcompound) {}

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    public AxisAlignedBB getCollisionBox(final Entity par1Entity) {
        return AxisAlignedBB.getBoundingBox(
            ((Entity) this).posX - 0.6,
            ((Entity) this).posY - 0.6,
            ((Entity) this).posZ - 0.6,
            ((Entity) this).posX + 0.6,
            ((Entity) this).posY + 0.6,
            ((Entity) this).posZ + 0.6
        );
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRender3d(double x, double y, double z) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(final double par1) {
        return false;
    }

    @Override

    public void setVelocity(final double par1, final double par3, final double par5) {}

    @Override
    public boolean isInsideOfMaterial(final Material par1Material) {
        return false;
    }

    @Override
    public boolean interact(final EntityPlayer player) {
        if (this.host != null && player != null) {
            final Block block
                = ((Entity) this)
                      .worldObj.getBlock(
                          this.host.xCoord, this.host.yCoord, this.host.zCoord
                      );

            if (block != null) {
                return block.onBlockActivated(
                    ((Entity) this).worldObj,
                    this.host.xCoord,
                    this.host.yCoord,
                    this.host.zCoord,
                    player,
                    0,
                    0.0f,
                    0.0f,
                    0.0f
                );
            }
        }

        return false;
    }

    @Override
    public float getMaxHealth() {
        return (this.host != null && this.host instanceof IHealthTile)
            ? this.host.getMaxHealth()
            : 100;
    }
}
