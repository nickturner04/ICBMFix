package icbm.zhapin.zhapin.ex;

import icbm.zhapin.zhapin.EExplosion;
import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import universalelectricity.core.vector.Vector3;

public abstract class ExThr extends ZhaPin {
    protected ExThr(final String mingZi, final int ID, final int tier) {
        super(mingZi, ID, tier);
    }

    @Override
    public boolean doBaoZha(
        final World worldObj,
        final Vector3 position,
        final Entity explosionSource,
        final int callCount
    ) {
        final EExplosion source = (EExplosion) explosionSource;

        if (!worldObj.isRemote && source.dataList1.size() > 0
            && source.dataList1.get(0) instanceof ThrSheXian) {
            final ThrSheXian thread = (ThrSheXian) source.dataList1.get(0);

            if (thread.isComplete) {
                return false;
            }
        }

        return true;
    }

    @Override
    protected int proceduralInterval() {
        return 1;
    }
}
