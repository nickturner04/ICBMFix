package icbm.zhapin.zhapin.ex;

import java.util.List;

import icbm.api.IMissile;
import icbm.api.RadarRegistry;
import icbm.api.explosion.IEMPItem;
import icbm.zhapin.zhapin.EExplosive;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import universalelectricity.core.item.IItemElectric;
import universalelectricity.core.vector.Vector3;

public class ExEmpSignal extends ZhaPin {
    public ExEmpSignal(final String name, final int ID, final int tier) {
        super(name, ID, tier);
    }

    @Override
    public boolean doBaoZha(
        final World worldObj,
        final Vector3 position,
        final Entity explosionSource,
        final int radius,
        final int callCount
    ) {
        final List<Entity> entitiesNearby
            = RadarRegistry.getEntitiesWithinRadius(position.toVector2(), radius);

        for (final Entity entity : entitiesNearby) {
            if (entity instanceof IMissile && !entity.isEntityEqual(explosionSource)
                && ((IMissile) entity).getTicksInAir() > -1) {
                ((IMissile) entity).dropMissileAsItem();
            }
        }

        final AxisAlignedBB bounds = AxisAlignedBB.getBoundingBox(
            position.x - radius,
            position.y - radius,
            position.z - radius,
            position.x + radius,
            position.y + radius,
            position.z + radius
        );
        final List<Entity> entities
            = worldObj.getEntitiesWithinAABB(Entity.class, bounds);

        for (final Entity entity2 : entities) {
            if (entity2 instanceof EntityPlayer) {
                final IInventory inventory
                    = (IInventory) ((EntityPlayer) entity2).inventory;

                for (int i = 0; i < inventory.getSizeInventory(); ++i) {
                    final ItemStack itemStack = inventory.getStackInSlot(i);

                    if (itemStack != null) {
                        if (itemStack.getItem() instanceof IEMPItem) {
                            ((IEMPItem) itemStack.getItem())
                                .onEMP(itemStack, entity2, ZhaPin.emp);
                        } else if (itemStack.getItem() instanceof IItemElectric) {
                            ((IItemElectric) itemStack.getItem())
                                .setJoules(0.0, itemStack);
                        }

                        // TODO: WTF: IC2
                        // else if (itemStack.getItem() instanceof ICustomElectricItem) {
                        // ((ICustomElectricItem) itemStack.getItem())
                        // .discharge(itemStack,
                        // ((ICustomElectricItem) itemStack.getItem())
                        // .getMaxCharge(itemStack),
                        // 0, true, false);
                        // }
                    }
                }
            } else {
                if (!(entity2 instanceof EExplosive)) {
                    continue;
                }

                entity2.setDead();
            }
        }

        worldObj.playSoundEffect(
            position.x,
            position.y,
            position.z,
            "icbm:emp",
            4.0f,
            (1.0f + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2f) * 0.7f
        );
        return false;
    }

    @Override
    public float getRadius() {
        return 50.0f;
    }

    @Override
    public double getEnergy() {
        return 0.0;
    }
}
