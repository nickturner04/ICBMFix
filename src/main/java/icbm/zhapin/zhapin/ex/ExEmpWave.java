package icbm.zhapin.zhapin.ex;

import icbm.api.explosion.IEMPBlock;
import icbm.zhapin.zhapin.ZhaPin;
import mffs.api.IForceFieldBlock;
import mffs.api.fortron.IFortronStorage;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import universalelectricity.core.block.IElectricityStorage;
import universalelectricity.core.vector.Vector3;
import universalelectricity.prefab.implement.IDisableable;
import universalelectricity.prefab.tile.TileEntityElectricityRunnable;

public class ExEmpWave extends ZhaPin {
    public ExEmpWave(final String name, final int ID, final int tier) {
        super(name, ID, tier);
    }

    @Override
    public boolean doBaoZha(
        final World worldObj,
        final Vector3 position,
        final Entity explosionSource,
        final int radius,
        final int callCount
    ) {
        for (int r = radius, x = -r; x < r; ++x) {
            for (int y = -r; y < r; ++y) {
                for (int z = -r; z < r; ++z) {
                    final double dist
                        = MathHelper.sqrt_double((double) (x * x + y * y + z * z));
                    final Vector3 searchPosition
                        = Vector3.add(position, new Vector3(x, y, z));

                    if (dist <= r) {
                        if (Math.round(position.x + y) == position.intY()) {
                            worldObj.spawnParticle(
                                "largesmoke",
                                searchPosition.x,
                                searchPosition.y,
                                searchPosition.z,
                                0.0,
                                0.0,
                                0.0
                            );
                        }

                        final Block block
                            = searchPosition.getBlock((IBlockAccess) worldObj);
                        final TileEntity tileEntity
                            = searchPosition.getTileEntity((IBlockAccess) worldObj);

                        if (block != null) {
                            if (block instanceof IForceFieldBlock) {
                                ((IForceFieldBlock) block)
                                    .weakenForceField(
                                        worldObj,
                                        searchPosition.intX(),
                                        searchPosition.intY(),
                                        searchPosition.intZ(),
                                        1000
                                    );
                            } else if (block instanceof IEMPBlock) {
                                ((IEMPBlock) block)
                                    .onEMP(worldObj, searchPosition, ZhaPin.emp);
                            } else if (tileEntity != null) {
                                if (tileEntity instanceof IElectricityStorage) {
                                    ((IElectricityStorage) tileEntity).setJoules(0.0);
                                }

                                if (tileEntity instanceof IDisableable) {
                                    ((IDisableable) tileEntity).onDisable(400);
                                }

                                if (tileEntity instanceof IFortronStorage) {
                                    ((IFortronStorage) tileEntity)
                                        .provideFortron(
                                            (int) worldObj.rand.nextFloat()
                                                * ((IFortronStorage) tileEntity)
                                                      .getFortronCapacity(),
                                            true
                                        );
                                }

                                // TODO: WTF: IC2
                                // if (tileEntity instanceof IEnergyStorage) {
                                // ((IEnergyStorage) tileEntity).setStored(0);
                                // }
                                if (tileEntity instanceof TileEntityElectricityRunnable) {
                                    ((TileEntityElectricityRunnable) tileEntity)
                                        .wattsReceived
                                        = 0.0;
                                }
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
            "icbm:emp",
            4.0f,
            (1.0f + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2f) * 0.7f
        );
        return false;
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
