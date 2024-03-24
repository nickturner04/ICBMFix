package icbm.zhapin.zhapin.ex;

import java.util.ArrayList;
import java.util.List;

import icbm.core.MainBase;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.RecipeHelper;
import universalelectricity.prefab.vector.Region3;

public class ExPushPull extends ZhaPin {
    public ExPushPull(final String name, final int ID, final int tier) {
        super(name, ID, tier);
        this.setFuse(120);
    }

    @Override
    public void
    doBaoZha(final World worldObj, final Vector3 position, final Entity explosionSource) {
        final List<ChunkPosition> blownBlocks = new ArrayList<>();

        if (!worldObj.isRemote) {
            for (int x = 0; x < 16; ++x) {
                for (int y = 0; y < 16; ++y) {
                    for (int z = 0; z < 16; ++z) {
                        if (x == 0 || x == 15 || y == 0 || y == 15 || z == 0 || z == 15) {
                            double xStep = x / 15.0f * 2.0f - 1.0f;
                            double yStep = y / 15.0f * 2.0f - 1.0f;
                            double zStep = z / 15.0f * 2.0f - 1.0f;
                            final double diagonalDistance = Math.sqrt(
                                xStep * xStep + yStep * yStep + zStep * zStep
                            );
                            xStep /= diagonalDistance;
                            yStep /= diagonalDistance;
                            zStep /= diagonalDistance;
                            float var14
                                = 2.0f * (0.7f + worldObj.rand.nextFloat() * 0.6f);
                            double var15 = position.x;
                            double var16 = position.y;
                            double var17 = position.z;

                            for (float var18 = 0.3f; var14 > 0.0f;
                                 var14 -= var18 * 0.75f) {
                                final int var19 = MathHelper.floor_double(var15);
                                final int var20 = MathHelper.floor_double(var16);
                                final int var21 = MathHelper.floor_double(var17);
                                final Block var22
                                    = worldObj.getBlock(var19, var20, var21);

                                if (var22 != Blocks.air) {
                                    var14 -= (var22.getExplosionResistance(
                                                  explosionSource,
                                                  worldObj,
                                                  var19,
                                                  var20,
                                                  var21,
                                                  (double) position.intX(),
                                                  (double) position.intY(),
                                                  (double) position.intZ()
                                              )
                                              + 0.3f)
                                        * var18;
                                }

                                if (var14 > 0.0f) {
                                    blownBlocks.add(new ChunkPosition(var19, var20, var21)
                                    );
                                }

                                var15 += xStep * var18;
                                var16 += yStep * var18;
                                var17 += zStep * var18;
                            }
                        }
                    }
                }
            }
        }

        worldObj.playSoundEffect(
            position.x,
            position.y,
            position.z,
            "random:explode",
            4.0f,
            (1.0f + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2f) * 0.7f
        );
        this.pushEntities(worldObj, position, 20.0f);

        if (!worldObj.isRemote) {
            for (int var23 = blownBlocks.size() - 1; var23 >= 0; --var23) {
                final ChunkPosition var24 = blownBlocks.get(var23);
                final int var25 = var24.chunkPosX;
                final int var26 = var24.chunkPosY;
                final int var27 = var24.chunkPosY;
                final Block block = worldObj.getBlock(var25, var26, var27);
                final double var28 = var25 + worldObj.rand.nextFloat();
                final double var29 = var26 + worldObj.rand.nextFloat();
                final double var30 = var27 + worldObj.rand.nextFloat();
                double var31 = var28 - position.y;
                double var32 = var29 - position.y;
                double var33 = var30 - position.z;
                final double var34 = MathHelper.sqrt_double(
                    var31 * var31 + var32 * var32 + var33 * var33
                );
                var31 /= var34;
                var32 /= var34;
                var33 /= var34;
                double var35 = 0.5 / (var34 / 2.0 + 0.1);
                var35 *= worldObj.rand.nextFloat() * worldObj.rand.nextFloat() + 0.3f;
                var31 *= var35;
                var32 *= var35;
                var33 *= var35;
                worldObj.spawnParticle(
                    "explode",
                    (var28 + position.x * 1.0) / 2.0,
                    (var29 + position.y * 1.0) / 2.0,
                    (var30 + position.z * 1.0) / 2.0,
                    var31,
                    var32,
                    var33
                );
                worldObj.spawnParticle("smoke", var28, var29, var30, var31, var32, var33);

                if (block == Blocks.air) {
                    block.onBlockDestroyedByExplosion(
                        worldObj, var25, var26, var27, (Explosion) null
                    );
                    block.dropBlockAsItemWithChance(
                        worldObj,
                        var25,
                        var26,
                        var27,
                        worldObj.getBlockMetadata(var25, var26, var27),
                        1.0f,
                        0
                    );
                    worldObj.setBlock(var25, var26, var27, Blocks.air, 0, 2);
                }
            }
        }
    }

    public void
    pushEntities(final World worldObj, final Vector3 position, final float radius) {
        final Vector3 minCoord = position.clone();
        minCoord.add(-radius - 1.0f);
        final Vector3 maxCoord = position.clone();
        maxCoord.add(radius + 1.0f);
        final Region3 region = new Region3(minCoord, maxCoord);
        final List<Entity> entities = region.getEntities(worldObj, Entity.class);

        for (final Entity entity : entities) {
            final double var13
                = entity.getDistance(position.x, position.y, position.z) / radius;

            if (var13 <= 1.0) {
                double xDifference = entity.posX - position.x;
                double yDifference = entity.posY - position.y;
                double zDifference = entity.posZ - position.z;
                final double var14 = MathHelper.sqrt_double(
                    xDifference * xDifference + yDifference * yDifference
                    + zDifference * zDifference
                );
                xDifference /= var14;
                yDifference /= var14;
                zDifference /= var14;

                if (this.getID() == ZhaPin.attractive.getID()) {
                    final double modifier = var13 * 4.0;
                    final Entity entity2 = entity;
                    entity2.motionX -= xDifference * modifier;
                    final Entity entity3 = entity;
                    entity3.motionY -= yDifference * modifier;
                    final Entity entity4 = entity;
                    entity4.motionZ -= zDifference * modifier;
                    entity.isAirBorne = true;
                } else {
                    final double modifier = (1.0 - var13) * 3.0;
                    final Entity entity5 = entity;
                    entity5.motionX += xDifference * modifier;
                    final Entity entity6 = entity;
                    entity6.motionY += yDifference * modifier;
                    final Entity entity7 = entity;
                    entity7.motionZ += zDifference * modifier;
                    entity.isAirBorne = true;
                }
            }
        }
    }

    @Override
    public void init() {
        if (this.getID() == ZhaPin.attractive.getID()) {
            RecipeHelper.addRecipe(
                (IRecipe) new ShapedOreRecipe(
                    this.getItemStack(),
                    new Object[] { "YY", 'Y', ZhaPin.condensed.getItemStack() }
                ),
                this.getUnlocalizedName(),
                MainBase.CONFIGURATION,
                true
            );
        } else {
            RecipeHelper.addRecipe(
                (IRecipe) new ShapedOreRecipe(
                    this.getItemStack(),
                    new Object[] { "Y", "Y", 'Y', ZhaPin.condensed.getItemStack() }
                ),
                this.getUnlocalizedName(),
                MainBase.CONFIGURATION,
                true
            );
        }
    }

    @Override
    public float getRadius() {
        return 16.0f;
    }

    @Override
    public double getEnergy() {
        return 2000.0;
    }
}
