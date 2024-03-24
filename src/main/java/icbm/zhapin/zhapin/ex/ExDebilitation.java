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
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.RecipeHelper;
import universalelectricity.prefab.potion.CustomPotionEffect;

public class ExDebilitation extends ZhaPin {
    public ExDebilitation(final String name, final int ID, final int tier) {
        super(name, ID, tier);
    }

    @Override
    public void baoZhaQian(
        final World worldObj, final Vector3 position, final Entity explosionSource
    ) {
        worldObj.playSoundEffect(
            position.x,
            position.y,
            position.z,
            "icbm:debilitation",
            4.0f,
            (1.0f + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2f) * 0.7f
        );
    }

    @Override
    public boolean doBaoZha(
        final World worldObj,
        final Vector3 position,
        final Entity explosionSource,
        final int explosionMetadata,
        final int callCount
    ) {
        float radius = this.getRadius();

        if (explosionSource instanceof EGrenade) {
            radius /= 2.0f;
        }

        final int duration = 600 / this.proceduralInterval();

        if (worldObj.isRemote) {
            for (int i = 0; i < 200; ++i) {
                final Vector3 diDian = new Vector3();
                diDian.x = Math.random() * radius / 2.0 - radius / 4.0f;
                diDian.y = Math.random() * radius / 2.0 - radius / 4.0f;
                diDian.z = Math.random() * radius / 2.0 - radius / 4.0f;
                diDian.multiply(Math.min(radius, (float) callCount) / 10.0f);

                if (diDian.getMagnitude() <= radius) {
                    diDian.add(new Vector3(explosionSource));
                    ICBMExplosion.proxy.spawnParticle(
                        "smoke",
                        explosionSource.worldObj,
                        diDian,
                        (Math.random() - 0.5) / 2.0,
                        (Math.random() - 0.5) / 2.0,
                        (Math.random() - 0.5) / 2.0,
                        1.0f,
                        1.0f,
                        1.0f,
                        7.0f,
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
        final List<EntityLivingBase> allEntities
            = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, bounds);

        for (final EntityLivingBase entity : allEntities) {
            entity.addPotionEffect((PotionEffect
            ) new CustomPotionEffect(Potion.confusion.id, 360, 0));
            entity.addPotionEffect((PotionEffect
            ) new CustomPotionEffect(Potion.digSlowdown.id, 1200, 0));
            entity.addPotionEffect((PotionEffect
            ) new CustomPotionEffect(Potion.moveSlowdown.id, 1200, 2));
        }

        return callCount <= duration;
    }

    public int proceduralInterval() {
        return 5;
    }

    @Override
    public void init() {
        RecipeHelper.addRecipe(
            (IRecipe) new ShapedOreRecipe(
                this.getItemStack(3),
                new Object[] { "SSS",
                               "WRW",
                               "SSS",
                               'R',
                               ZhaPin.repulsive.getItemStack(),
                               'W',
                               Items.water_bucket,
                               'S',
                               "dustSulfur" }
            ),
            this.getUnlocalizedName(),
            MainBase.CONFIGURATION,
            true
        );
    }

    @Override
    public float getRadius() {
        return 20.0f;
    }

    @Override
    public double getEnergy() {
        return 0.0;
    }
}
