package icbm.zhapin.zhapin.ex;

import java.util.ArrayList;
import java.util.List;

import icbm.core.MainBase;
import icbm.zhapin.EGravityBlock;
import icbm.zhapin.ELightBeam;
import icbm.zhapin.po.PDongShang;
import icbm.zhapin.zhapin.EExplosion;
import icbm.zhapin.zhapin.EGrenade;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.RecipeHelper;
import universalelectricity.prefab.potion.CustomPotionEffect;

public class ExEndothermic extends ZhaPin {
    public ExEndothermic(final String name, final int ID, final int tier) {
        super(name, ID, tier);
    }

    @Override
    public void baoZhaQian(
        final World worldObj, final Vector3 position, final Entity explosionSource
    ) {
        if (!worldObj.isRemote) {
            final ELightBeam lightBeam
                = new ELightBeam(worldObj, position, 400, 0.0f, 0.3f, 0.7f);
            worldObj.spawnEntityInWorld((Entity) lightBeam);
            ((EExplosion) explosionSource).entityList.add(0, lightBeam);
            worldObj.createExplosion(
                (Entity) null, position.x, position.y, position.z, 4.0f, true
            );
        }
    }

    @Override
    public boolean doBaoZha(
        final World worldObj,
        final Vector3 position,
        final Entity explosionSource,
        final int callCount
    ) {
        List<Entity> gravityBlocks = new ArrayList<>();
        this.getClass();
        int radius = 5;

        if (explosionSource instanceof EGrenade) {
            radius /= 2;
        }

        if (this.canFocusBeam(worldObj, position)) {
            if (!worldObj.isRemote) {
                for (int r = radius, x = -r; x < r; ++x) {
                    for (int y = -r; y < r; ++y) {
                        for (int z = -r; z < r; ++z) {
                            final double dist = MathHelper.sqrt_double((double
                            ) (x * x + y * y + z * z));

                            if (dist <= r) {
                                if (dist >= r - 3) {
                                    final Vector3 currentPos = new Vector3(
                                        position.x + x, position.y + y, position.z + z
                                    );
                                    final Block blockID = worldObj.getBlock(
                                        currentPos.intX(),
                                        currentPos.intY(),
                                        currentPos.intZ()
                                    );

                                    if (blockID != Blocks.air
                                        && blockID != Blocks.bedrock) {
                                        if (blockID != Blocks.obsidian) {
                                            final int metadata
                                                = worldObj.getBlockMetadata(
                                                    currentPos.intX(),
                                                    currentPos.intY(),
                                                    currentPos.intZ()
                                                );

                                            if (worldObj.rand.nextInt(3) > 0) {
                                                worldObj.setBlock(
                                                    currentPos.intX(),
                                                    currentPos.intY(),
                                                    currentPos.intZ(),
                                                    Blocks.air,
                                                    0,
                                                    2
                                                );
                                                currentPos.add(0.5);
                                                final EGravityBlock entity
                                                    = new EGravityBlock(
                                                        worldObj,
                                                        currentPos,
                                                        blockID,
                                                        metadata
                                                    );
                                                worldObj.spawnEntityInWorld((Entity
                                                ) entity);
                                                gravityBlocks.add(entity);
                                                entity.pitchChange
                                                    = 50.0f * worldObj.rand.nextFloat();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                ((EExplosion) explosionSource).entityList.addAll(gravityBlocks);
            }

            gravityBlocks = ((EExplosion) explosionSource).entityList;

            for (final Entity unspecifiedEntity : gravityBlocks) {
                if (unspecifiedEntity instanceof EGravityBlock) {
                    final EGravityBlock entity2 = (EGravityBlock) unspecifiedEntity;
                    final double xDifference = entity2.posX - position.x;
                    final double zDifference = entity2.posZ - position.z;
                    int r2 = radius;

                    if (xDifference < 0.0) {
                        r2 = -radius;
                    }

                    if (xDifference > 4.0) {
                        final EGravityBlock eFeiBlock = entity2;
                        eFeiBlock.motionX
                            += (r2 - xDifference) * -0.02 * worldObj.rand.nextFloat();
                    }

                    if (entity2.posY < position.y + 15.0) {
                        final EGravityBlock eFeiBlock2 = entity2;
                        eFeiBlock2.motionY += 0.5 + 0.6 * worldObj.rand.nextFloat();

                        if (entity2.posY < position.y + 3.0) {
                            final EGravityBlock eFeiBlock3 = entity2;
                            eFeiBlock3.motionY += 1.5;
                        }
                    }

                    r2 = radius;

                    if (zDifference < 0.0) {
                        r2 = -radius;
                    }

                    if (zDifference > 4.0) {
                        final EGravityBlock eFeiBlock4 = entity2;
                        eFeiBlock4.motionZ
                            += (r2 - zDifference) * -0.02 * worldObj.rand.nextFloat();
                    }

                    final EGravityBlock eFeiBlock5 = entity2;
                    eFeiBlock5.yawChange += 3.0f * worldObj.rand.nextFloat();
                }
            }

            worldObj.playSoundEffect(
                position.x, position.y, position.z, "icbm:redmatter", 4.0f, 0.8f
            );
            return callCount <= 35;
        }

        return false;
    }

    @Override
    public void baoZhaHou(
        final World worldObj, final Vector3 position, final Entity explosionSource
    ) {
        super.baoZhaHou(worldObj, position, explosionSource);

        if (!worldObj.isRemote) {
            ((EExplosion) explosionSource).entityList.get(0).setDead();

            if (this.canFocusBeam(worldObj, position)) {
                for (final Entity entity : ((EExplosion) explosionSource).entityList) {
                    if (!(entity instanceof ELightBeam)) {
                        final double xDifference = entity.posX - position.x;
                        final double zDifference = entity.posZ - position.z;
                        int m = 1;

                        if (xDifference < 0.0) {
                            m = -1;
                        }

                        final Entity entity3 = entity;
                        entity3.motionX += m * 5 * worldObj.rand.nextFloat();
                        m = 1;

                        if (zDifference < 0.0) {
                            m = -1;
                        }

                        final Entity entity4 = entity;
                        entity4.motionZ += m * 5 * worldObj.rand.nextFloat();
                    }
                }

                ((EExplosion) explosionSource).entityList.clear();
                final List<EntityLivingBase> livingEntities
                    = worldObj.getEntitiesWithinAABB(
                        EntityLivingBase.class,
                        AxisAlignedBB.getBoundingBox(
                            position.x - this.getRadius(),
                            position.y - this.getRadius(),
                            position.z - this.getRadius(),
                            position.x + this.getRadius(),
                            position.y + this.getRadius(),
                            position.z + this.getRadius()
                        )
                    );

                for (final EntityLivingBase entity2 : livingEntities) {
                    entity2.addPotionEffect((PotionEffect
                    ) new CustomPotionEffect(PDongShang.INSTANCE.getId(), 1200, 1, null));
                    entity2.addPotionEffect(new PotionEffect(Potion.confusion.id, 200, 2)
                    );
                    entity2.addPotionEffect(
                        new PotionEffect(Potion.digSlowdown.id, 2400, 2)
                    );
                    entity2.addPotionEffect(
                        new PotionEffect(Potion.moveSlowdown.id, 2400, 4)
                    );
                }

                ZhaPin.createExplosion(
                    worldObj, position, explosionSource, ZhaPin.endothermic2.getID()
                );
            }
        }
    }

    @Override
    public void init() {
        RecipeHelper.addRecipe(
            (IRecipe) new ShapedOreRecipe(
                this.getItemStack(),
                new Object[] { "?!?",
                               "!@!",
                               "?!?",
                               '@',
                               ZhaPin.attractive.getItemStack(),
                               '?',
                               Blocks.ice,
                               '!',
                               Blocks.snow }
            ),
            this.getUnlocalizedName(),
            MainBase.CONFIGURATION,
            true
        );
    }

    public boolean canFocusBeam(final World worldObj, final Vector3 position) {
        long worldTime;

        for (worldTime = worldObj.getWorldTime(); worldTime > 23999L;
             worldTime -= 23999L) {}

        return worldTime > 12000L
            && worldObj.canBlockSeeTheSky(
                position.intX(), position.intY() + 1, position.intZ()
            );
    }

    public int proceduralInterval() {
        return 5;
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
