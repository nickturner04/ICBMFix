package icbm.zhapin.zhapin.ex;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.registry.LanguageRegistry;
import icbm.core.MainBase;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTNT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.RecipeHelper;

public class ExCondensed extends ZhaPin {
    public ExCondensed(final String name, final int ID, final int tier) {
        super(name, ID, tier);
        this.setFuse(1);
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
                                final Block block
                                    = worldObj.getBlock(var19, var20, var21);

                                if (block != Blocks.air) {
                                    var14 -= (block.getExplosionResistance(
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
            "random.explode",
            4.0f,
            (1.0f + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2f) * 0.7f
        );
        this.doDamageEntities(worldObj, position, 2.0f, 10.0f, false);

        if (!worldObj.isRemote) {
            for (int var23 = blownBlocks.size() - 1; var23 >= 0; --var23) {
                final ChunkPosition var24 = blownBlocks.get(var23);
                final int var25 = var24.chunkPosX;
                final int var26 = var24.chunkPosY;
                final int var27 = var24.chunkPosZ;
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

                if (block != Blocks.air) {
                    try {
                        if (block.canDropFromExplosion((Explosion) null)) {
                            block.dropBlockAsItemWithChance(
                                worldObj,
                                var25,
                                var26,
                                var27,
                                worldObj.getBlockMetadata(var25, var26, var27),
                                1.0f,
                                0
                            );
                        }

                        if (block instanceof BlockTNT) {
                            worldObj.setBlockToAir(var25, var26, var27);

                            if (!worldObj.isRemote) {
                                final EntityTNTPrimed entitytntprimed
                                    = new EntityTNTPrimed(
                                        worldObj,
                                        var25 + 0.5,
                                        var26 + 0.5,
                                        var27 + 0.5,
                                        (EntityLiving) null
                                    );
                                entitytntprimed.fuse
                                    = worldObj.rand.nextInt(entitytntprimed.fuse / 4)
                                    + entitytntprimed.fuse / 8;
                                worldObj.spawnEntityInWorld((Entity) entitytntprimed);
                            }
                        } else {
                            block.onBlockExploded(
                                worldObj, var25, var26, var27, (Explosion) null
                            );
                        }
                    } catch (final Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public String getMissileName() {
        return LanguageRegistry.instance().getStringLocalization(
                   "icbm.explosive.conventional"
               )
            + " " + LanguageRegistry.instance().getStringLocalization("icbm.missile");
    }

    @Override
    public String getMinecartName() {
        return LanguageRegistry.instance().getStringLocalization("icbm.explosive") + " "
            + LanguageRegistry.instance().getStringLocalization("item.minecart.name");
    }

    @Override
    public void init() {
        RecipeHelper.addRecipe(
            (IRecipe) new ShapedOreRecipe(
                this.getItemStack(3),
                new Object[] { "@?@", '@', Blocks.tnt, '?', Items.redstone }
            ),
            this.getUnlocalizedName(),
            MainBase.CONFIGURATION,
            true
        );
    }

    @Override
    public float getRadius() {
        return 2.0f;
    }

    @Override
    public double getEnergy() {
        return 4000.0;
    }
}
