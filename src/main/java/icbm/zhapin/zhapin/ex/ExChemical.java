package icbm.zhapin.zhapin.ex;

import java.util.List;

import icbm.core.MainBase;
import icbm.zhapin.ICBMExplosion;
import icbm.zhapin.zhapin.EGrenade;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.RecipeHelper;

public class ExChemical extends ZhaPin {
    public ExChemical(final String name, final int ID, final int tier) {
        super(name, ID, tier);
    }

    @Override
    public boolean doBaoZha(
        final World worldObj,
        final Vector3 position,
        final Entity explosionSource,
        final int explosionMetadata,
        final int callCount
    ) {
        final int duration = 60 / this.proceduralInterval();
        final boolean isContagious = this.getTier() == 2;
        float radius = this.getRadius();

        if (explosionSource instanceof EGrenade) {
            radius /= 2.0f;
        }

        if (worldObj.isRemote) {
            float red = 0.8f;
            float green = 0.8f;
            float blue = 0.0f;

            if (isContagious) {
                red = 0.3f;
                green = 0.8f;
                blue = 0.0f;
            }

            for (int i = 0; i < 200; ++i) {
                final Vector3 diDian = new Vector3();
                diDian.x
                    = Math.random() * this.getRadius() / 2.0 - this.getRadius() / 4.0f;
                diDian.y
                    = Math.random() * this.getRadius() / 2.0 - this.getRadius() / 4.0f;
                diDian.z
                    = Math.random() * this.getRadius() / 2.0 - this.getRadius() / 4.0f;
                diDian.multiply(Math.min(this.getRadius(), (float) callCount) / 10.0f);

                if (diDian.getMagnitude() <= this.getRadius()) {
                    diDian.add(new Vector3(explosionSource));
                    ICBMExplosion.proxy.spawnParticle(
                        "smoke",
                        explosionSource.worldObj,
                        diDian,
                        (Math.random() - 0.5) / 2.0,
                        (Math.random() - 0.5) / 2.0,
                        (Math.random() - 0.5) / 2.0,
                        red,
                        green,
                        blue,
                        4.0f,
                        8.0
                    );
                }
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
        final List<EntityLivingBase> entitiesNearby
            = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, bounds);

        for (final EntityLivingBase entity : entitiesNearby) {
            if (isContagious) {
                ICBMExplosion.DU_CHUAN_RAN.poisonEntity(position, entity);
            } else {
                ICBMExplosion.DU_DU.poisonEntity(position, entity);
            }
        }

        worldObj.playSoundEffect(
            position.x + 0.5,
            position.y + 0.5,
            position.z + 0.5,
            "icbm:gasleak",
            4.0f,
            (1.0f + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2f) * 1.0f
        );

        if (isContagious) {
            ZhaPin.mutateLiving.doBaoZha(worldObj, position, null, (int) radius, -1);
        }

        return callCount <= duration;
    }

    public int proceduralInterval() {
        return 5;
    }

    @Override
    public void init() {
        if (this.getTier() == 1) {
            RecipeHelper.addRecipe(
                (IRecipe) new ShapedOreRecipe(
                    this.getItemStack(),
                    new Object[] { "@@@",
                                   "@?@",
                                   "@@@",
                                   '@',
                                   MainBase.itDu,
                                   '?',
                                   ZhaPin.debilitation.getItemStack() }
                ),
                "Chemical",
                MainBase.CONFIGURATION,
                true
            );
        } else if (this.getTier() == 2) {
            RecipeHelper.addRecipe(
                (IRecipe) new ShapedOreRecipe(
                    this.getItemStack(2),
                    new Object[] { " @ ",
                                   "@?@",
                                   " @ ",
                                   '?',
                                   Items.rotten_flesh,
                                   '@',
                                   ZhaPin.chemical.getItemStack() }
                ),
                "Contagious",
                MainBase.CONFIGURATION,
                true
            );
        }
    }

    @Override
    public float getRadius() {
        return (this.getTier() == 2) ? 22.0f : 15.0f;
    }

    @Override
    public double getEnergy() {
        return 0.0;
    }
}
