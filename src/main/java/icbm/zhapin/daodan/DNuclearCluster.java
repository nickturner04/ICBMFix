package icbm.zhapin.daodan;

import icbm.zhapin.zhapin.ZhaPin;
import net.minecraft.entity.Entity;
import universalelectricity.core.vector.Vector3;

public class DNuclearCluster extends DCluster {
    public static final int MAX_CLUSTER = 4;

    protected DNuclearCluster(final String name, final int ID, final int tier) {
        super(name, ID, tier);
    }

    @Override
    public void update(final EMissile missileObj) {
        if (missileObj.motionY < -0.5) {
            if (missileObj.missileCount < 4) {
                if (!missileObj.worldObj.isRemote) {
                    final Vector3 position = new Vector3(missileObj);
                    final EMissile clusterMissile = new EMissile(
                        missileObj.worldObj,
                        position,
                        new Vector3(missileObj),
                        ZhaPin.nuclear.getID()
                    );
                    missileObj.worldObj.spawnEntityInWorld((Entity) clusterMissile);
                    clusterMissile.missileType = EMissile.MissileType.SMALL_MISSILE;
                    clusterMissile.protectionTicks = 20;
                    clusterMissile.launch(Vector3.add(
                        missileObj.target,
                        new Vector3(
                            (missileObj.missileCount - 2) * Math.random() * 30.0,
                            (missileObj.missileCount - 2) * Math.random() * 30.0,
                            (missileObj.missileCount - 2) * Math.random() * 30.0
                        )
                    ));
                }

                missileObj.protectionTicks = 20;
                ++missileObj.missileCount;
            } else {
                missileObj.setDead();
            }
        }
    }

    @Override
    public void onExplode(final EMissile missileObj) {
        ZhaPin.createExplosion(
            missileObj.worldObj,
            new Vector3(missileObj),
            missileObj,
            ZhaPin.nuclear.getID()
        );
    }

    @Override
    public boolean isCruise() {
        return false;
    }
}
