package icbm.zhapin.zhapin.ex;

import atomicscience.AtomicScience;
import icbm.zhapin.zhapin.EExplosion;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import universalelectricity.core.vector.Vector3;

public class ExDecayLand extends ExThr {
    public ExDecayLand(final String name, final int ID, final int tier) {
        super(name, ID, tier);
    }

    @Override
    public void baoZhaQian(
        final World worldObj, final Vector3 position, final Entity explosionSource
    ) {
        super.baoZhaQian(worldObj, position, explosionSource);

        if (!worldObj.isRemote) {
            final ThrSheXian thread = new ThrSheXian(
                worldObj, position, (int) ZhaPin.nuclear.getRadius(), 200, explosionSource
            );
            thread.run();
            ((EExplosion) explosionSource).dataList1.add(thread);
        }
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

            for (final Vector3 targetPosition : thread.destroyed) {
                final Block block = targetPosition.getBlock((IBlockAccess) worldObj);

                if (block != Blocks.air) {
                    if ((block == Blocks.grass || block == Blocks.sand)
                        && worldObj.rand.nextFloat() > 0.96) {
                        targetPosition.setBlock(worldObj, AtomicScience.blockRadioactive);
                    }

                    if (block == Blocks.stone)
                        if (worldObj.rand.nextFloat() <= 0.99) {
                            continue;
                        }

                    targetPosition.setBlock(worldObj, AtomicScience.blockRadioactive);
                } else if (block == Blocks.leaves) {
                    targetPosition.setBlock(worldObj, Blocks.air);
                } else if (block == Blocks.tallgrass) {
                    if (Math.random() * 100.0 > 50.0) {
                        targetPosition.setBlock(worldObj, Blocks.cobblestone);
                    } else {
                        targetPosition.setBlock(worldObj, Blocks.air);
                    }
                } else {
                    if (block != Blocks.farmland) {
                        continue;
                    }

                    targetPosition.setBlock(worldObj, AtomicScience.blockRadioactive);
                }
            }
        }
    }

    @Override
    public float getRadius() {
        return 50.0f;
    }

    @Override
    public double getEnergy() {
        return 0.0;
    }
}
