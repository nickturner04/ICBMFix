package icbm.zhapin.zhapin.ex;

import java.util.List;

import icbm.core.MainBase;
import icbm.zhapin.EGravityBlock;
import icbm.zhapin.ICBMExplosion;
import icbm.zhapin.daodan.EMissile;
import icbm.zhapin.zhapin.BExplosives;
import icbm.zhapin.zhapin.EExplosion;
import icbm.zhapin.zhapin.TExplosive;
import icbm.zhapin.zhapin.ZhaPin;
import mffs.api.IForceFieldBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.RecipeHelper;

public class ExSonic extends ZhaPin {
    public ExSonic(final String name, final int ID, final int tier) {
        super(name, ID, tier);
    }

    @Override
    public void baoZhaQian(
        final World worldObj, final Vector3 position, final Entity explosionSource
    ) {
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
                            float power
                                = 40.0f * (0.7f + worldObj.rand.nextFloat() * 0.6f);
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
                                        resistance = 2.0f;
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

        worldObj.playSoundEffect(
            position.x,
            position.y,
            position.z,
            "icbm:sonicwave",
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
        final EExplosion source = (EExplosion) explosionSource;
        int r = callCount;

        if (!worldObj.isRemote) {
            for (final Object obj : source.dataList1) {
                final Vector3 targetPosition = (Vector3) obj;
                final double distance = Vector3.distance(targetPosition, position);

                if (distance <= r) {
                    if (distance < r - 3) {
                        continue;
                    }

                    final Block block = worldObj.getBlock(
                        targetPosition.intX(),
                        targetPosition.intY(),
                        targetPosition.intZ()
                    );

                    if (block == Blocks.air || block == Blocks.bedrock) {
                        continue;
                    }

                    if (block == Blocks.obsidian) {
                        continue;
                    }

                    if (block instanceof IForceFieldBlock) {
                        continue;
                    }

                    final int metadata = worldObj.getBlockMetadata(
                        targetPosition.intX(),
                        targetPosition.intY(),
                        targetPosition.intZ()
                    );

                    if (distance >= r - 1 && worldObj.rand.nextInt(3) <= 0) {
                        continue;
                    }

                    if (block == ICBMExplosion.bExplosives) {
                        BExplosives.yinZha(
                            worldObj,
                            targetPosition.intX(),
                            targetPosition.intY(),
                            targetPosition.intZ(),
                            ((TExplosive) worldObj.getTileEntity(
                                 targetPosition.intX(),
                                 targetPosition.intY(),
                                 targetPosition.intZ()
                             ))
                                .explosiveId,
                            1
                        );
                    } else {
                        worldObj.setBlock(
                            targetPosition.intX(),
                            targetPosition.intY(),
                            targetPosition.intZ(),
                            Blocks.air,
                            0,
                            2
                        );
                    }

                    targetPosition.add(0.5);

                    if (worldObj.rand.nextFloat() >= 0.3 * (this.getRadius() - r)) {
                        continue;
                    }

                    final EGravityBlock entity
                        = new EGravityBlock(worldObj, targetPosition, block, metadata);
                    worldObj.spawnEntityInWorld((Entity) entity);
                    entity.yawChange = 50.0f * worldObj.rand.nextFloat();
                    entity.pitchChange = 100.0f * worldObj.rand.nextFloat();
                }
            }
        }

        final int radius = 2 * callCount;
        final AxisAlignedBB bounds = AxisAlignedBB.getBoundingBox(
            position.x - radius,
            position.y - radius,
            position.z - radius,
            position.x + radius,
            position.y + radius,
            position.z + radius
        );
        final List<Entity> allEntities
            = worldObj.getEntitiesWithinAABB(Entity.class, bounds);

        synchronized (allEntities) {
            for (final Entity entity2 : allEntities) {
                if (entity2 instanceof EMissile) {
                    ((EMissile) entity2).setExplode();
                    break;
                }

                final double xDifference = entity2.posX - position.x;
                final double zDifference = entity2.posZ - position.z;
                r = (int) this.getRadius();

                if (xDifference < 0.0) {
                    r = (int) (-this.getRadius());
                }

                final Entity entity3 = entity2;
                entity3.motionX += (r - xDifference) * 0.02 * worldObj.rand.nextFloat();
                final Entity entity4 = entity2;
                entity4.motionY += 3.0f * worldObj.rand.nextFloat();
                r = (int) this.getRadius();

                if (zDifference < 0.0) {
                    r = (int) (-this.getRadius());
                }

                final Entity entity5 = entity2;
                entity5.motionZ += (r - zDifference) * 0.02 * worldObj.rand.nextFloat();
            }
        }

        return callCount <= this.getRadius();
    }

    public int proceduralInterval() {
        return 4;
    }

    @Override
    public void init() {
        RecipeHelper.addRecipe(
            (IRecipe) new ShapedOreRecipe(
                this.getItemStack(),
                new Object[] { "@?@",
                               "?R?",
                               "@?@",
                               'R',
                               ZhaPin.repulsive.getItemStack(),
                               '?',
                               Blocks.noteblock,
                               '@',
                               "ingotBronze" }
            ),
            this.getUnlocalizedName(),
            MainBase.CONFIGURATION,
            true
        );
    }

    @Override
    public float getRadius() {
        return 8.0f;
    }

    @Override
    public double getEnergy() {
        return 0.0;
    }
}
