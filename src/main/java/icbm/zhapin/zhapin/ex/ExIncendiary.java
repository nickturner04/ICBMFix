package icbm.zhapin.zhapin.ex;

import icbm.core.MainBase;
import icbm.zhapin.zhapin.EGrenade;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.RecipeHelper;

public class ExIncendiary extends ZhaPin {
    public ExIncendiary(final String name, final int ID, final int tier) {
        super(name, ID, tier);
    }

    @Override
    public void
    onYinZha(final World worldObj, final Vector3 position, final int fuseTicks) {
        super.onYinZha(worldObj, position, fuseTicks);
        worldObj.spawnParticle(
            "lava", position.x, position.y + 0.5, position.z, 0.0, 0.0, 0.0
        );
    }

    @Override
    public void
    doBaoZha(final World worldObj, final Vector3 position, final Entity explosionSource) {
        int radius = (int) this.getRadius();

        if (explosionSource instanceof EGrenade) {
            radius /= 2;
        }

        for (int x = 0; x < radius; ++x) {
            for (int y = 0; y < radius; ++y) {
                for (int z = 0; z < radius; ++z) {
                    if (x == 0 || x == radius - 1 || y == 0 || y == radius - 1 || z == 0
                        || z == radius - 1) {
                        double xStep = x / (radius - 1.0f) * 2.0f - 1.0f;
                        double yStep = y / (radius - 1.0f) * 2.0f - 1.0f;
                        double zStep = z / (radius - 1.0f) * 2.0f - 1.0f;
                        final double diagonalDistance
                            = Math.sqrt(xStep * xStep + yStep * yStep + zStep * zStep);
                        xStep /= diagonalDistance;
                        yStep /= diagonalDistance;
                        zStep /= diagonalDistance;
                        float var14 = radius * (0.7f + worldObj.rand.nextFloat() * 0.6f);
                        double var15 = position.x;
                        double var16 = position.y;
                        double var17 = position.z;

                        for (float var18 = 0.3f; var14 > 0.0f; var14 -= var18 * 0.75f) {
                            final Vector3 targetPosition
                                = new Vector3(var15, var16, var17);
                            final double distanceFromCenter
                                = position.distanceTo(targetPosition);
                            final Block var19 = worldObj.getBlock(
                                targetPosition.intX(),
                                targetPosition.intY(),
                                targetPosition.intZ()
                            );

                            if (var19 != Blocks.air) {
                                var14 -= (var19.getExplosionResistance(
                                              explosionSource,
                                              worldObj,
                                              targetPosition.intX(),
                                              targetPosition.intY(),
                                              targetPosition.intZ(),
                                              (double) position.intX(),
                                              (double) position.intY(),
                                              (double) position.intZ()
                                          )
                                          + 0.3f)
                                    * var18;
                            }

                            if (var14 > 0.0f) {
                                final double chance
                                    = radius - Math.random() * distanceFromCenter;

                                if (chance > distanceFromCenter * 0.55) {
                                    final Block block = worldObj.getBlock(
                                        (int) targetPosition.x,
                                        (int) targetPosition.y,
                                        (int) targetPosition.z
                                    );

                                    if ((block == Blocks.air || block == Blocks.snow_layer
                                        )
                                        && worldObj
                                               .getBlock(
                                                   (int) targetPosition.x,
                                                   (int) targetPosition.y - 1,
                                                   (int) targetPosition.z
                                               )
                                               .getMaterial()
                                               .isSolid()) {
                                        worldObj.setBlock(
                                            (int) targetPosition.x,
                                            (int) targetPosition.y,
                                            (int) targetPosition.z,
                                            Blocks.fire,
                                            0,
                                            2
                                        );
                                    } else if (block == Blocks.ice) {
                                        worldObj.setBlock(
                                            (int) targetPosition.x,
                                            (int) targetPosition.y,
                                            (int) targetPosition.z,
                                            Blocks.air,
                                            0,
                                            2
                                        );
                                    }
                                }
                            }

                            var15 += xStep * var18;
                            var16 += yStep * var18;
                            var17 += zStep * var18;
                        }
                    }
                }
            }
        }

        worldObj.playSoundEffect(
            position.x + 0.5,
            position.y + 0.5,
            position.z + 0.5,
            "icbm:explosionfire",
            4.0f,
            (1.0f + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2f) * 1.0f
        );
    }

    @Override
    public void init() {
        RecipeHelper.addRecipe(
            (IRecipe) new ShapedOreRecipe(
                this.getItemStack(),
                new Object[] { "@@@",
                               "@?@",
                               "@!@",
                               '@',
                               "dustSulfur",
                               '?',
                               ZhaPin.repulsive.getItemStack(),
                               '!',
                               Items.lava_bucket }
            ),
            this.getUnlocalizedName(),
            MainBase.CONFIGURATION,
            true
        );
    }

    @Override
    public float getRadius() {
        return 14.0f;
    }

    @Override
    public double getEnergy() {
        return 1000.0;
    }
}
