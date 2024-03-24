package icbm.zhapin.zhapin.ex;

import java.util.List;

import icbm.api.explosion.ExplosionEvent;
import icbm.api.explosion.IExplosiveIgnore;
import icbm.core.MainBase;
import icbm.zhapin.EGravityBlock;
import icbm.zhapin.ICBMExplosion;
import icbm.zhapin.zhapin.EExplosion;
import icbm.zhapin.zhapin.EExplosive;
import icbm.zhapin.zhapin.ZhaPin;
import mffs.api.IForceFieldBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.RecipeHelper;

public class ExRedMatter extends ZhaPin {
    public ExRedMatter(final String name, final int ID, final int tier) {
        super(name, ID, tier);
        super.isMobile = true;
    }

    @Override
    public void baoZhaQian(
        final World worldObj, final Vector3 position, final Entity explosionSource
    ) {
        if (!worldObj.isRemote) {
            worldObj.createExplosion(
                explosionSource, position.x, position.y, position.z, 5.0f, true
            );
        }
    }

    @Override
    public boolean doBaoZha(
        final World worldObj,
        final Vector3 position,
        final Entity explosionSource,
        final int explosionMetadata,
        final int callCount
    ) {
    Label_0434: {
        if (!worldObj.isRemote) {
            int takenBlocks = 0;

            for (int r = 1; r < this.getRadius(); ++r) {
                for (int x = -r; x < r; ++x) {
                    for (int y = -r; y < r; ++y) {
                        for (int z = -r; z < r; ++z) {
                            final double dist = MathHelper.sqrt_double((double
                            ) (x * x + y * y + z * z));

                            if (dist <= r) {
                                if (dist >= r - 2) {
                                    final Vector3 currentPos = new Vector3(
                                        position.x + x, position.y + y, position.z + z
                                    );
                                    final Block block = worldObj.getBlock(
                                        currentPos.intX(),
                                        currentPos.intY(),
                                        currentPos.intZ()
                                    );

                                    if (block != Blocks.air) {
                                        if (block instanceof IForceFieldBlock) {
                                            ((IForceFieldBlock) block)
                                                .weakenForceField(
                                                    worldObj,
                                                    currentPos.intX(),
                                                    currentPos.intY(),
                                                    currentPos.intZ(),
                                                    50
                                                );
                                        } else if (block.getBlockHardness(
                                                    worldObj, currentPos.intX(),
                                                    currentPos.intY(),
                                                    currentPos.intZ()) > -1.0f) {
                                            final int metadata
                                                = worldObj.getBlockMetadata(
                                                    currentPos.intX(),
                                                    currentPos.intY(),
                                                    currentPos.intZ()
                                                );
                                            int notify = 2;

                                            if (block instanceof BlockLiquid) {
                                                notify = 0;
                                            }

                                            worldObj.setBlock(
                                                currentPos.intX(),
                                                currentPos.intY(),
                                                currentPos.intZ(),
                                                Blocks.air,
                                                0,
                                                notify
                                            );

                                            if (!(block instanceof BlockLiquid)) {
                                                currentPos.add(0.5);

                                                if (worldObj.rand.nextFloat() > 0.8) {
                                                    final EGravityBlock entity
                                                        = new EGravityBlock(
                                                            worldObj,
                                                            currentPos,
                                                            block,
                                                            metadata
                                                        );
                                                    worldObj.spawnEntityInWorld((Entity
                                                    ) entity);
                                                    entity.yawChange = 50.0f
                                                        * worldObj.rand.nextFloat();
                                                    entity.pitchChange = 50.0f
                                                        * worldObj.rand.nextFloat();
                                                }

                                                if (++takenBlocks > 5) {
                                                    break Label_0434;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
        final float radius = this.getRadius() + this.getRadius() / 2.0f;
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
        boolean explosionCreated = false;

        for (final Entity entity2 : allEntities) {
            if (entity2 == explosionSource) {
                continue;
            }

            if (entity2 instanceof IExplosiveIgnore
                && ((IExplosiveIgnore) entity2)
                       .canIgnore(new ExplosionEvent(
                           worldObj, position.x, position.y, position.z, this
                       ))) {
                continue;
            }

            if (entity2 instanceof EntityPlayer
                && ((EntityPlayer) entity2).capabilities.isCreativeMode) {
                continue;
            }

            final double xDifference = entity2.posX - position.x;
            final double yDifference = entity2.posY - position.y;
            final double zDifference = entity2.posZ - position.z;
            float r2 = radius;

            if (xDifference < 0.0) {
                r2 = (float) (int) (-radius);
            }

            final Entity entity3 = entity2;
            entity3.motionX -= (r2 - xDifference) * 0.002;
            r2 = radius;

            if (yDifference < 0.0) {
                r2 = (float) (int) (-radius);
            }

            final Entity entity4 = entity2;
            entity4.motionY -= (r2 - yDifference) * 0.005;
            r2 = radius;

            if (zDifference < 0.0) {
                r2 = -radius;
            }

            final Entity entity5 = entity2;
            entity5.motionZ -= (r2 - zDifference) * 0.002;

            if (entity2 instanceof EGravityBlock && worldObj.isRemote
                && ICBMExplosion.proxy.getParticleSetting() == 0
                && worldObj.rand.nextInt(5) == 0) {
                ICBMExplosion.proxy.spawnParticle(
                    "digging",
                    worldObj,
                    new Vector3(entity2),
                    -xDifference,
                    -yDifference + 10.0,
                    -zDifference,
                    (float) Block.getIdFromBlock(((EGravityBlock) entity2).block),
                    0.0f,
                    (float) ((EGravityBlock) entity2).metadata,
                    2.0f,
                    1.0
                );
            }

            if (Vector3.distance(
                    new Vector3(entity2.posX, entity2.posY, entity2.posZ), position
                )
                >= 4.0) {
                continue;
            }

            if (!explosionCreated && callCount % 5 == 0) {
                worldObj.createExplosion(
                    explosionSource, entity2.posX, entity2.posY, entity2.posZ, 3.0f, true
                );
                explosionCreated = true;
            }

            if (entity2 instanceof EntityLiving) {
                entity2.fallDistance = 0.0f;
            } else if (entity2 instanceof EExplosion) {
                if (((EExplosion) entity2).haoMa != ZhaPin.antimatter.getID()) {
                    continue;
                }

                worldObj.playSoundEffect(
                    position.x,
                    position.y,
                    position.z,
                    "icbm:explosion",
                    7.0f,
                    (1.0f + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2f
                    ) * 0.7f
                );

                if (worldObj.rand.nextFloat() > 0.85 && !worldObj.isRemote) {
                    entity2.setDead();
                    return false;
                }

                continue;
            } else if (entity2 instanceof EExplosive) {
                ((EExplosive) entity2).explode();
            } else {
                entity2.setDead();
            }
        }

        if (worldObj.rand.nextInt(10) == 0) {
            worldObj.playSoundEffect(
                position.x + (Math.random() - 0.5) * radius,
                position.y + (Math.random() - 0.5) * radius,
                position.z + (Math.random() - 0.5) * radius,
                "icbm:collapse",
                6.0f - worldObj.rand.nextFloat(),
                1.0f - worldObj.rand.nextFloat() * 0.4f
            );
        }

        worldObj.playSoundEffect(
            position.x,
            position.y,
            position.z,
            "icbm:redmatter",
            3.0f,
            (1.0f + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2f) * 1.0f
        );
        return true;
    }

    public int proceduralInterval() {
        return 1;
    }

    @Override
    public void init() {
        RecipeHelper.addRecipe(
            (IRecipe) new ShapedOreRecipe(
                this.getItemStack(),
                new Object[] { "AAA",
                               "AEA",
                               "AAA",
                               'E',
                               ZhaPin.antimatter.getItemStack(),
                               'A',
                               "strangeMatter" }
            ),
            this.getUnlocalizedName(),
            MainBase.CONFIGURATION,
            true
        );
    }

    @Override
    public float getRadius() {
        return 35.0f;
    }

    @Override
    public double getEnergy() {
        return 4000.0;
    }
}
