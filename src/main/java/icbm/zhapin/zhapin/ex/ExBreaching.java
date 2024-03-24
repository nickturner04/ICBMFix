package icbm.zhapin.zhapin.ex;

import java.util.ArrayList;
import java.util.List;

import icbm.core.MainBase;
import icbm.zhapin.zhapin.ZhaPin;
import mffs.api.IForceFieldBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.ShapedOreRecipe;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.RecipeHelper;
import universalelectricity.prefab.implement.IRotatable;

public class ExBreaching extends ZhaPin {
    public ExBreaching(final String name, final int ID, final int tier) {
        super(name, ID, tier);
        this.setFuse(10);
    }

    @Override
    public void
    doBaoZha(final World worldObj, final Vector3 position, final Entity explosionSource) {
        if (!worldObj.isRemote) {
            final Vector3 difference = new Vector3();

            if (explosionSource instanceof IRotatable) {
                difference.modifyPositionFromSide(((IRotatable) explosionSource)
                                                      .getDirection(
                                                          (IBlockAccess) worldObj,
                                                          position.intX(),
                                                          position.intY(),
                                                          position.intZ()
                                                      ));
            } else {
                difference.modifyPositionFromSide(ForgeDirection.DOWN);
            }

            worldObj.playSoundEffect(
                position.x,
                position.y,
                position.z,
                "random.explode",
                5.0f,
                (1.0f + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2f)
                    * 0.7f
            );
            final Vector3 targetPosition = position.clone();

            for (int i = 0; i < 8; ++i) {
                if (targetPosition.getBlock((IBlockAccess) worldObj) != null) {
                    if (targetPosition.getBlock((IBlockAccess) worldObj)
                            .getExplosionResistance(
                                explosionSource,
                                worldObj,
                                targetPosition.intX(),
                                targetPosition.intY(),
                                targetPosition.intZ(),
                                position.x,
                                position.y,
                                position.z
                            )
                        > Blocks.obsidian.getExplosionResistance(explosionSource)) {
                        break;
                    }

                    if (targetPosition.getBlock((IBlockAccess) worldObj)
                            instanceof IForceFieldBlock) {
                        break;
                    }
                }

                final List<ChunkPosition> blownBlocks = new ArrayList<>();

                for (int x = 0; x < 16; ++x) {
                    for (int y = 0; y < 16; ++y) {
                        for (int z = 0; z < 16; ++z) {
                            if (x == 0 || x == 15 || y == 0 || y == 15 || z == 0
                                || z == 15) {
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
                                double var15 = targetPosition.x;
                                double var16 = targetPosition.y;
                                double var17 = targetPosition.z;

                                for (float var18 = 0.3f; var14 > 0.0f;
                                     var14 -= var18 * 0.75f) {
                                    final int var19 = MathHelper.floor_double(var15);
                                    final int var20 = MathHelper.floor_double(var16);
                                    final int var21 = MathHelper.floor_double(var17);
                                    final Block var22
                                        = worldObj.getBlock(var19, var20, var21);

                                    if (var22 == Blocks.air) {
                                        var14 -= (var22.getExplosionResistance(
                                                      explosionSource,
                                                      worldObj,
                                                      var19,
                                                      var20,
                                                      var21,
                                                      (double) targetPosition.intX(),
                                                      (double) targetPosition.intY(),
                                                      (double) targetPosition.intZ()
                                                  )
                                                  + 0.3f)
                                            * var18;
                                    }

                                    if (var14 > 0.0f) {
                                        blownBlocks.add(
                                            new ChunkPosition(var19, var20, var21)
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

                this.doDamageEntities(worldObj, targetPosition, 2.0f, 3.5f, false);

                for (int var23 = blownBlocks.size() - 1; var23 >= 0; --var23) {
                    final ChunkPosition var24 = blownBlocks.get(var23);
                    final int var25 = var24.chunkPosX;
                    final int var26 = var24.chunkPosY;
                    final int var27 = var24.chunkPosZ;
                    final Block block = worldObj.getBlock(var25, var26, var27);
                    final double var28 = var25 + worldObj.rand.nextFloat();
                    final double var29 = var26 + worldObj.rand.nextFloat();
                    final double var30 = var27 + worldObj.rand.nextFloat();
                    double var31 = var28 - targetPosition.y;
                    double var32 = var29 - targetPosition.y;
                    double var33 = var30 - targetPosition.z;
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
                        (var28 + targetPosition.x * 1.0) / 2.0,
                        (var29 + targetPosition.y * 1.0) / 2.0,
                        (var30 + targetPosition.z * 1.0) / 2.0,
                        var31,
                        var32,
                        var33
                    );
                    worldObj.spawnParticle(
                        "smoke", var28, var29, var30, var31, var32, var33
                    );

                    if (block != Blocks.air) {
                        block.dropBlockAsItemWithChance(
                            worldObj,
                            var25,
                            var26,
                            var27,
                            worldObj.getBlockMetadata(var25, var26, var27),
                            1.0f,
                            0
                        );
                        block.onBlockDestroyedByExplosion(
                            worldObj, var25, var26, var27, (Explosion) null
                        );
                        worldObj.setBlock(var25, var26, var27, Blocks.air, 0, 2);
                    }
                }

                targetPosition.add(difference);
            }
        }
    }

    @Override
    public void init() {
        RecipeHelper.addRecipe(
            (IRecipe) new ShapedOreRecipe(
                this.getItemStack(2),
                new Object[] { "GCG",
                               "GCG",
                               "GCG",
                               'C',
                               ZhaPin.condensed.getItemStack(),
                               'G',
                               Items.gunpowder }
            ),
            this.getUnlocalizedName(),
            MainBase.CONFIGURATION,
            true
        );
    }

    @Override
    public float getRadius() {
        return 6.0f;
    }

    @Override
    public double getEnergy() {
        return 6000.0;
    }
}
