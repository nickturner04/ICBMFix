package icbm.zhapin.zhapin.ex;

import icbm.core.MainBase;
import icbm.zhapin.ICBMExplosion;
import icbm.zhapin.zhapin.EExplosion;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.RecipeHelper;

public class ExNuclear extends ExThr {
    public static final int BAN_JING = 45;
    public static final int NENG_LIANG = 200;

    public ExNuclear(final String name, final int ID, final int tier) {
        super(name, ID, tier);
        this.setFuse(200);
    }

    @Override
    public void baoZhaQian(
        final World worldObj, final Vector3 position, final Entity explosionSource
    ) {
        super.baoZhaQian(worldObj, position, explosionSource);

        if (!worldObj.isRemote) {
            final ThrSheXian thread
                = new ThrSheXian(worldObj, position, 45, 200, explosionSource);
            thread.run();
            ((EExplosion) explosionSource).dataList1.add(thread);
        } else if (ICBMExplosion.proxy.isGaoQing()) {
            for (int y = 0; y < 26; ++y) {
                int r = 4;

                if (y < 8) {
                    r = Math.max(Math.min((8 - y) * 2, 10), 4);
                } else if (y > 15) {
                    r = Math.max(Math.min((y - 15) * 2, 15), 5);
                }

                for (int x = -r; x < r; ++x) {
                    for (int z = -r; z < r; ++z) {
                        final double distance
                            = MathHelper.sqrt_double((double) (x * x + z * z));

                        if (r > distance && r - 3 < distance) {
                            final Vector3 spawnPosition = Vector3.add(
                                position, new Vector3(x * 2, (y - 2) * 2, z * 2)
                            );
                            final float xDiff = (float) (spawnPosition.x - position.x);
                            final float zDiff = (float) (spawnPosition.z - position.z);
                            ICBMExplosion.proxy.spawnParticle(
                                "smoke",
                                worldObj,
                                spawnPosition,
                                xDiff * 0.3 * worldObj.rand.nextFloat(),
                                -worldObj.rand.nextFloat(),
                                zDiff * 0.3 * worldObj.rand.nextFloat(),
                                (float) (distance / 45.0) * worldObj.rand.nextFloat(),
                                0.0f,
                                0.0f,
                                8.0f,
                                1.2000000476837158
                            );
                        }
                    }
                }
            }
        }

        this.doDamageEntities(worldObj, position, 45.0f, 200000.0f);
        worldObj.playSoundEffect(
            position.x,
            position.y,
            position.z,
            "icbm:explosion",
            7.0f,
            (1.0f + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2f) * 0.7f
        );
    }

    @Override
    public void baoZhaHou(
        final World worldObj, final Vector3 position, final Entity explosionSource
    ) {
        super.baoZhaHou(worldObj, position, explosionSource);
        final EExplosion source = (EExplosion) explosionSource;

        try {
            if (!worldObj.isRemote && source.dataList1.size() > 0
                && source.dataList1.get(0) instanceof ThrSheXian) {
                final ThrSheXian thread = (ThrSheXian) source.dataList1.get(0);

                for (final Vector3 targetPosition : thread.destroyed) {
                    final Block block = worldObj.getBlock(
                        targetPosition.intX(),
                        targetPosition.intY(),
                        targetPosition.intZ()
                    );

                    if (block != Blocks.air) {
                        targetPosition.setBlock(worldObj, Blocks.air);
                        block.onBlockDestroyedByExplosion(
                            thread.world,
                            targetPosition.intX(),
                            targetPosition.intY(),
                            targetPosition.intZ(),
                            (Explosion) null
                        );
                    }
                }
            }
        } catch (final Exception e) {
            MainBase.LOGGER.severe("Detonation Failed!");
            e.printStackTrace();
        }

        this.doDamageEntities(worldObj, position, 45.0f, 200000.0f);
        worldObj.playSoundEffect(
            position.x,
            position.y,
            position.z,
            "icbm:explosion",
            10.0f,
            (1.0f + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2f) * 0.7f
        );
        ZhaPin.createExplosion(
            worldObj, position, explosionSource, ZhaPin.decayLand.getID()
        );
        ZhaPin.mutateLiving.doBaoZha(worldObj, position, null, 65, -1);

        if (worldObj.rand.nextInt(3) == 0) {
            worldObj.getWorldInfo().setRaining(!worldObj.getWorldInfo().isRaining());
        }
    }

    @Override
    public void
    onYinZha(final World worldObj, final Vector3 position, final int fuseTicks) {
        super.onYinZha(worldObj, position, fuseTicks);

        if (fuseTicks % 25 == 0) {
            worldObj.playSoundEffect(
                (double) (int) position.x,
                (double) (int) position.y,
                (double) (int) position.z,
                "icbm:alarm",
                4.0f,
                1.0f
            );
        }
    }

    @Override
    public void init() {
        if (OreDictionary.getOres("ingotUranium").size() > 0) {
            RecipeHelper.addRecipe(
                (IRecipe) new ShapedOreRecipe(
                    this.getItemStack(),
                    new Object[] { "UUU",
                                   "UEU",
                                   "UUU",
                                   'E',
                                   ZhaPin.thermobaric.getItemStack(),
                                   'U',
                                   "ingotUranium" }
                ),
                this.getUnlocalizedName(),
                MainBase.CONFIGURATION,
                true
            );
        } else {
            RecipeHelper.addRecipe(
                (IRecipe) new ShapedOreRecipe(
                    this.getItemStack(),
                    new Object[] {
                        "EEE", "EEE", "EEE", 'E', ZhaPin.thermobaric.getItemStack() }
                ),
                this.getUnlocalizedName(),
                MainBase.CONFIGURATION,
                true
            );
        }
    }

    @Override
    public float getRadius() {
        return 45.0f;
    }

    @Override
    public double getEnergy() {
        return 100000.0;
    }
}
