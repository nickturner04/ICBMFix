package icbm.zhapin.zhapin.ex;

import java.util.List;

import cpw.mods.fml.common.FMLLog;
import icbm.core.MainBase;
import icbm.zhapin.ICBMExplosion;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.RecipeHelper;

public class ExEnder extends ZhaPin {
    public static final int SHI_JIAN = 160;

    public ExEnder(final String name, final int ID, final int tier) {
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
        if (worldObj.isRemote) {
            for (int r = (int) (this.getRadius() - callCount / 160.0 * this.getRadius()),
                     x = -r;
                 x < r;
                 ++x) {
                for (int z = -r; z < r; ++z) {
                    for (int y = -r; y < r; ++y) {
                        final Vector3 targetPosition
                            = Vector3.add(position, new Vector3(x, y, z));
                        final double distance = targetPosition.distanceTo(position);

                        if (distance < r && distance > r - 1) {
                            if (targetPosition.getBlock((IBlockAccess) worldObj)
                                == Blocks.air) {
                                if (worldObj.rand.nextFloat()
                                    < Math.max(0.001 * r, 0.01)) {
                                    final float velX
                                        = (float) ((targetPosition.x - position.x) * 0.5);
                                    final float velY
                                        = (float) ((targetPosition.y - position.y) * 0.5);
                                    final float velZ
                                        = (float) ((targetPosition.z - position.z) * 0.5);
                                    ICBMExplosion.proxy.spawnParticle(
                                        "portal",
                                        worldObj,
                                        targetPosition,
                                        velX,
                                        velY,
                                        velZ,
                                        5.0f,
                                        1.0
                                    );
                                }
                            }
                        }
                    }
                }
            }
        }

        final int radius = (int) this.getRadius();
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

        for (final Entity entity : allEntities) {
            if (entity == explosionSource) {
                continue;
            }

            final double xDifference = entity.posX - position.x;
            final double yDifference = entity.posY - position.y;
            final double zDifference = entity.posZ - position.z;
            int r2 = (int) this.getRadius();

            if (xDifference < 0.0) {
                r2 = (int) (-this.getRadius());
            }

            final Entity entity2 = entity;
            entity2.motionX -= (r2 - xDifference) * Math.abs(xDifference) * 6.0E-4;
            r2 = (int) this.getRadius();

            if (entity.posY > position.y) {
                r2 = (int) (-this.getRadius());
            }

            final Entity entity3 = entity;
            entity3.motionY += (r2 - yDifference) * Math.abs(yDifference) * 0.0011;
            r2 = (int) this.getRadius();

            if (zDifference < 0.0) {
                r2 = (int) (-this.getRadius());
            }

            final Entity entity4 = entity;
            entity4.motionZ -= (r2 - zDifference) * Math.abs(zDifference) * 6.0E-4;

            if (Vector3.distance(
                    new Vector3(entity.posX, entity.posY, entity.posZ), position
                )
                >= 4.0) {
                continue;
            }

            if (!explosionCreated && callCount % 5 == 0) {
                worldObj.spawnParticle(
                    "hugeexplosion", entity.posX, entity.posY, entity.posZ, 0.0, 0.0, 0.0
                );
                explosionCreated = true;
            }

            try {
                if (entity.worldObj.provider.dimensionId == 1) {
                    entity.travelToDimension(0);
                } else {
                    entity.travelToDimension(1);
                }
            } catch (final Exception e) {
                FMLLog.severe("Failed to teleport entity to the End.", new Object[0]);
            }
        }

        worldObj.playSound(
            position.x,
            position.y,
            position.z,
            "portal.portal",
            2.0f,
            worldObj.rand.nextFloat() * 0.4f + 0.8f,
            false
        );
        return callCount <= 160;
    }

    @Override
    public void baoZhaHou(
        final World worldObj, final Vector3 position, final Entity explosionSource
    ) {
        super.baoZhaHou(worldObj, position, explosionSource);

        if (!explosionSource.worldObj.isRemote) {
            for (int i = 0; i < 20; ++i) {
                final EntityEnderman enderman = new EntityEnderman(worldObj);
                enderman.setPosition(position.x, position.y, position.z);
                explosionSource.worldObj.spawnEntityInWorld((Entity) enderman);
            }
        }
    }

    public int proceduralInterval() {
        return 1;
    }

    @Override
    public void init() {
        RecipeHelper.addRecipe(
            (IRecipe) new ShapedOreRecipe(
                this.getItemStack(),
                new Object[] { "EPE",
                               "ETE",
                               "EPE",
                               'P',
                               Items.ender_pearl,
                               'E',
                               Blocks.end_stone,
                               'T',
                               ZhaPin.attractive.getItemStack() }
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
