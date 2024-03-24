package icbm.zhapin.zhapin.ex;

import cpw.mods.fml.common.FMLLog;
import icbm.zhapin.zhapin.EExplosion;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import universalelectricity.core.vector.Vector3;

public class ExEndothermic2 extends ZhaPin {
    public ExEndothermic2(final String name, final int ID, final int tier) {
        super(name, ID, tier);
        this.setFuse(1);
    }

    @Override
    public void baoZhaQian(
        final World worldObj, final Vector3 position, final Entity explosionSource
    ) {
        super.baoZhaQian(worldObj, position, explosionSource);

        if (!worldObj.isRemote) {
            final EExplosion source = (EExplosion) explosionSource;

            for (int x = 0; x < this.getRadius(); ++x) {
                for (int y = 0; y < this.getRadius(); ++y) {
                    for (int z = 0; z < this.getRadius(); ++z) {
                        if (x == 0 || x == this.getRadius() - 1.0f || y == 0
                            || y == this.getRadius() - 1.0f || z == 0
                            || z == this.getRadius() - 1.0f) {
                            double xStep = x / (this.getRadius() - 1.0f) * 2.0f - 1.0f;
                            double yStep = y / (this.getRadius() - 1.0f) * 2.0f - 1.0f;
                            double zStep = z / (this.getRadius() - 1.0f) * 2.0f - 1.0f;
                            final double diagonalDistance = Math.sqrt(
                                xStep * xStep + yStep * yStep + zStep * zStep
                            );
                            xStep /= diagonalDistance;
                            yStep /= diagonalDistance;
                            zStep /= diagonalDistance;
                            float power = this.getRadius()
                                * (0.7f + worldObj.rand.nextFloat() * 0.6f);
                            double var15 = position.x;
                            double var16 = position.y;
                            double var17 = position.z;

                            for (float var18 = 0.3f; power > 0.0f;
                                 power -= var18 * 0.75f) {
                                final Vector3 targetPosition
                                    = new Vector3(var15, var16, var17);
                                final Block block = worldObj.getBlock(
                                    targetPosition.intX(),
                                    targetPosition.intY(),
                                    targetPosition.intZ()
                                );

                                if (block != Blocks.air) {
                                    float resistance = 0.0f;

                                    if (block == Blocks.bedrock) {
                                        break;
                                    }

                                    if (block instanceof BlockLiquid) {
                                        resistance = 1.0f;
                                    } else {
                                        resistance = (block.getExplosionResistance(
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

                                    power -= resistance;
                                }

                                if (power > 0.0f) {
                                    source.dataList1.add(targetPosition.clone());
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
    }

    @Override
    public boolean doBaoZha(
        final World worldObj,
        final Vector3 position,
        final Entity explosionSource,
        final int metadata,
        final int callCount
    ) {
        try {
            if (!worldObj.isRemote) {
                final EExplosion source = (EExplosion) explosionSource;
                final int radius = callCount;

                for (final Object obj : source.dataList1) {
                    final Vector3 targetPosition = (Vector3) obj;
                    final double distance = Vector3.distance(targetPosition, position);
                    final double distanceFromCenter = position.distanceTo(targetPosition);

                    if (distanceFromCenter <= radius) {
                        if (distanceFromCenter < radius - 2) {
                            continue;
                        }

                        final double chance = radius - Math.random() * distanceFromCenter;

                        if (chance <= distanceFromCenter * 0.55) {
                            continue;
                        }

                        final Block blockID = worldObj.getBlock(
                            (int) targetPosition.x,
                            (int) targetPosition.y,
                            (int) targetPosition.z
                        );

                        if (blockID == Blocks.fire || blockID == Blocks.lava
                            || blockID == Blocks.flowing_lava) {
                            worldObj.setBlock(
                                (int) targetPosition.x,
                                (int) targetPosition.y,
                                (int) targetPosition.z,
                                Blocks.snow_layer,
                                0,
                                2
                            );
                        } else {
                            if (blockID != Blocks.air
                                || worldObj.getBlock(
                                       (int) targetPosition.x,
                                       (int) targetPosition.y - 1,
                                       (int) targetPosition.z
                                   ) == Blocks.ice
                                || worldObj.getBlock(
                                       (int) targetPosition.x,
                                       (int) targetPosition.y - 1,
                                       (int) targetPosition.z
                                   ) == Blocks.air) {
                                continue;
                            }

                            worldObj.setBlock(
                                (int) targetPosition.x,
                                (int) targetPosition.y,
                                (int) targetPosition.z,
                                Blocks.ice,
                                0,
                                2
                            );
                        }
                    }
                }
            }

            worldObj.playSoundEffect(
                position.x + 0.5,
                position.y + 0.5,
                position.z + 0.5,
                "icbm:redmatter",
                6.0f,
                (1.0f + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2f)
                    * 1.0f
            );
        } catch (final Exception e) {
            FMLLog.severe("Endothermic Explosives Failure!", new Object[0]);
            e.printStackTrace();
        }

        return callCount <= this.getRadius();
    }

    @Override
    public int proceduralInterval() {
        return 3;
    }

    @Override
    public float getRadius() {
        return 40.0f;
    }

    @Override
    public double getEnergy() {
        return 0.0;
    }
}
