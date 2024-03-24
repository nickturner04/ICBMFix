package icbm.zhapin.cart;

import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import icbm.api.explosion.IExplosive;
import icbm.api.explosion.IExplosiveContainer;
import icbm.zhapin.ICBMExplosion;
import icbm.zhapin.zhapin.ZhaPin;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityMinecartTNT;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import universalelectricity.core.vector.Vector3;

public class ECart
    extends EntityMinecartTNT implements IExplosiveContainer, IEntityAdditionalSpawnData {
    public int haoMa;

    public ECart(final World par1World) {
        super(par1World);
        this.haoMa = 0;
    }

    public ECart(
        final World par1World,
        final double x,
        final double y,
        final double z,
        final int explosiveID
    ) {
        super(par1World, x, y, z);
        this.haoMa = 0;
        this.haoMa = explosiveID;
    }

    @Override
    public void writeSpawnData(final ByteBuf data) {
        data.writeInt(this.haoMa);
    }

    @Override
    public void readSpawnData(final ByteBuf data) {
        this.haoMa = data.readInt();
    }

    @Override
    protected void explodeCart(final double par1) {
        ((Entity) this)
            .worldObj.spawnParticle(
                "hugeexplosion",
                ((Entity) this).posX,
                ((Entity) this).posY,
                ((Entity) this).posZ,
                0.0,
                0.0,
                0.0
            );
        ZhaPin.createExplosion(
            ((Entity) this).worldObj,
            new Vector3((Entity) this),
            (Entity) this,
            this.haoMa
        );
        this.setDead();
    }

    @Override
    public void killMinecart(final DamageSource par1DamageSource) {
        this.setDead();
        final ItemStack itemstack = new ItemStack(Items.minecart, 1);

        if (((EntityMinecart) this).getCommandSenderName() != null) {
            itemstack.setStackDisplayName(((EntityMinecart) this).getCommandSenderName());
        }

        this.entityDropItem(itemstack, 0.0f);
        final double d0 = ((Entity) this).motionX * ((Entity) this).motionX
            + ((Entity) this).motionZ * ((Entity) this).motionZ;

        if (!par1DamageSource.isExplosion()) {
            this.entityDropItem(
                new ItemStack(ICBMExplosion.bExplosives, 1, this.haoMa), 0.0f
            );
        }

        if (par1DamageSource.isFireDamage() || par1DamageSource.isExplosion()
            || d0 >= 0.009999999776482582) {
            this.explodeCart(d0);
        }
    }

    @Override
    public ItemStack getCartItem() {
        return new ItemStack(ICBMExplosion.itChe, 1, this.haoMa);
    }

    @Override
    protected void writeEntityToNBT(final NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("haoMa", this.haoMa);
    }

    @Override
    protected void readEntityFromNBT(final NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.haoMa = par1NBTTagCompound.getInteger("haoMa");
    }

    @Override
    public IExplosive getExplosiveType() {
        return ZhaPin.list[this.haoMa];
    }

    @Override
    public Block func_145817_o() {
        return ICBMExplosion.bExplosives;
    }

    @Override
    public int getDefaultDisplayTileData() {
        return this.haoMa;
    }
}
