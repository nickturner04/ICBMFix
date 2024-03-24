package icbm.zhapin.dianqi;

import java.util.Random;

import icbm.core.di.ItElectricICBM;
import icbm.zhapin.ICBMExplosion;
import icbm.zhapin.cart.ECart;
import icbm.zhapin.zhapin.EExplosive;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import universalelectricity.core.electricity.ElectricityPack;

public class ItDefuser extends ItElectricICBM {
    public ItDefuser() {
        super("defuser");
        this.setTextureName("icbm:defuser");
    }

    public boolean onLeftClickEntity(
        final ItemStack itemStack, final EntityPlayer player, final Entity entity
    ) {
        if (this.getJoules(itemStack) > 2000.0) {
            if (entity instanceof EExplosive) {
                if (!entity.worldObj.isRemote) {
                    final EExplosive entityTNT = (EExplosive) entity;
                    final EntityItem entityItem = new EntityItem(
                        entity.worldObj,
                        entity.posX,
                        entity.posY,
                        entity.posZ,
                        new ItemStack(ICBMExplosion.bExplosives, 1, entityTNT.haoMa)
                    );
                    final float var13 = 0.05f;
                    final Random random = new Random();
                    ((Entity) entityItem).motionX = (float) random.nextGaussian() * var13;
                    ((Entity) entityItem).motionY
                        = (float) random.nextGaussian() * var13 + 0.2f;
                    ((Entity) entityItem).motionZ = (float) random.nextGaussian() * var13;
                    entity.worldObj.spawnEntityInWorld((Entity) entityItem);
                }

                entity.setDead();
            } else if (entity instanceof EntityTNTPrimed) {
                if (!entity.worldObj.isRemote) {
                    final EntityItem entityItem2 = new EntityItem(
                        entity.worldObj,
                        entity.posX,
                        entity.posY,
                        entity.posZ,
                        new ItemStack(Blocks.tnt)
                    );
                    final float var14 = 0.05f;
                    final Random random2 = new Random();
                    ((Entity) entityItem2).motionX
                        = (float) random2.nextGaussian() * var14;
                    ((Entity) entityItem2).motionY
                        = (float) random2.nextGaussian() * var14 + 0.2f;
                    ((Entity) entityItem2).motionZ
                        = (float) random2.nextGaussian() * var14;
                    entity.worldObj.spawnEntityInWorld((Entity) entityItem2);
                }

                entity.setDead();
            } else if (entity instanceof ECart) {
                ((ECart) entity).killMinecart(DamageSource.generic);
            }

            this.onProvide(
                ElectricityPack.getFromWatts(2000.0, this.getJoules(itemStack)), itemStack
            );
            return true;
        }

        player.addChatMessage(new ChatComponentText("Defuser out of electricity!"));
        return false;
    }

    public double getVoltage(final ItemStack itemStack) {
        return 20.0;
    }

    public double getMaxJoules(final ItemStack itemStack) {
        return 80000.0;
    }
}
