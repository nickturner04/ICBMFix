package icbm.zhapin.daodan;

import net.minecraft.entity.Entity;
import universalelectricity.core.vector.Vector3;

public class DCluster extends MissileBase {
    public static final int MAX_CLUSTER = 12;

    protected DCluster(final String name, final int ID, final int tier) {
        super(name, ID, tier);
    }

    @Override
    public void update(final EMissile missileObj) {
        if (missileObj.motionY < -0.5) {
            if (missileObj.missileCount < 12) {
                if (!missileObj.worldObj.isRemote) {
                    final Vector3 position = new Vector3(missileObj);
                    final EMissile clusterMissile = new EMissile(
                        missileObj.worldObj, position, new Vector3(missileObj), 0
                    );
                    clusterMissile.missileType = EMissile.MissileType.SMALL_MISSILE;
                    clusterMissile.protectionTicks = 20;
                    clusterMissile.launch(Vector3.add(
                        missileObj.target,
                        new Vector3(
                            (missileObj.missileCount - 6) * Math.random() * 6.0,
                            (missileObj.missileCount - 6) * Math.random() * 6.0,
                            (missileObj.missileCount - 6) * Math.random() * 6.0
                        )
                    ));
                    missileObj.worldObj.spawnEntityInWorld((Entity) clusterMissile);
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
        missileObj.worldObj.createExplosion(
            (Entity) missileObj,
            missileObj.posX,
            missileObj.posY,
            missileObj.posZ,
            6.0f,
            true
        );
    }

    @Override
    public boolean isCruise() {
        return false;
    }
}
