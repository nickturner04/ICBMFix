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
import net.minecraftforge.oredict.ShapedOreRecipe;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.RecipeHelper;

public class ExThermobaric extends ExThr {
    public static final int BAN_JING = 20;
    public static final int NENG_LIANG = 150;
    public static final int CALC_SPEED = 800;

    public ExThermobaric(final String name, final int ID, final int tier) {
        super(name, ID, tier);
        this.setFuse(120);
    }

    @Override
    public void baoZhaQian(
        final World worldObj, final Vector3 position, final Entity explosionSource
    ) {
        super.baoZhaQian(worldObj, position, explosionSource);

        if (!worldObj.isRemote) {
            final ThrSheXian thread
                = new ThrSheXian(worldObj, position, 20, 150, explosionSource);
            thread.run();
            ((EExplosion) explosionSource).dataList1.add(thread);
        }

        this.doDamageEntities(worldObj, position, 20.0f, 150000.0f);
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
    public boolean doBaoZha(
        final World worldObj,
        final Vector3 position,
        final Entity explosionSource,
        final int callCount
    ) {
        final int r = callCount;

        if (worldObj.isRemote && ICBMExplosion.proxy.isGaoQing()) {
            for (int x = -r; x < r; ++x) {
                for (int z = -r; z < r; ++z) {
                    final double distance
                        = MathHelper.sqrt_double((double) (x * x + z * z));

                    if (distance < r && distance > r - 1) {
                        final Vector3 targetPosition
                            = Vector3.add(position, new Vector3(x, 0.0, z));

                        if (worldObj.rand.nextFloat() < Math.max(0.001 * r, 0.05)) {
                            ICBMExplosion.proxy.spawnParticle(
                                "smoke", worldObj, targetPosition, 5.0f, 1.0
                            );
                        }
                    }
                }
            }
        }

        return super.doBaoZha(worldObj, position, explosionSource, callCount);
    }

    @Override
    public void baoZhaHou(
        final World worldObj, final Vector3 position, final Entity explosionSource
    ) {
        super.baoZhaHou(worldObj, position, explosionSource);
        final EExplosion source = (EExplosion) explosionSource;

        if (!worldObj.isRemote && source.dataList1.size() > 0
            && source.dataList1.get(0) instanceof ThrSheXian) {
            final ThrSheXian thread = (ThrSheXian) source.dataList1.get(0);

            for (final Object obj : thread.destroyed) {
                final Vector3 targetPosition = (Vector3) obj;
                final Block block = worldObj.getBlock(
                    targetPosition.intX(), targetPosition.intY(), targetPosition.intZ()
                );

                if (block != Blocks.air) {
                    try {
                        worldObj.setBlock(
                            targetPosition.intX(),
                            targetPosition.intY(),
                            targetPosition.intZ(),
                            Blocks.air,
                            0,
                            3
                        );
                        block.onBlockDestroyedByExplosion(
                            worldObj,
                            targetPosition.intX(),
                            targetPosition.intY(),
                            targetPosition.intZ(),
                            (Explosion) null
                        );
                    } catch (final Exception e) {
                        MainBase.LOGGER.severe("Detonation Failed!");
                        e.printStackTrace();
                    }
                }
            }
        }

        this.doDamageEntities(worldObj, position, 20.0f, 150000.0f);
        worldObj.playSoundEffect(
            position.x,
            position.y,
            position.z,
            "icbm:explosion",
            10.0f,
            (1.0f + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2f) * 0.7f
        );
    }

    public int proceduralInterval() {
        return 1;
    }

    @Override
    public void init() {
        RecipeHelper.addRecipe(
            (IRecipe) new ShapedOreRecipe(
                this.getItemStack(),
                new Object[] { "CIC",
                               "IRI",
                               "CIC",
                               'R',
                               ZhaPin.repulsive.getItemStack(),
                               'C',
                               ZhaPin.chemical.getItemStack(),
                               'I',
                               ZhaPin.indenciary.getItemStack() }
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
        return 80000.0;
    }
}
