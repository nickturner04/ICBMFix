package icbm.zhapin.daodan;

import icbm.api.ITracker;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.WorldServer;
import universalelectricity.core.vector.Vector2;
import universalelectricity.core.vector.Vector3;

public class DHoming extends MissileBase {
    protected DHoming(final String name, final int ID, final int tier) {
        super(name, ID, tier);
    }

    @Override
    public void launch(final EMissile missileObj) {
        if (!missileObj.worldObj.isRemote) {
            final WorldServer worldServer = (WorldServer) missileObj.worldObj;
            final Entity trackingEntity
                = worldServer.getEntityByID(missileObj.targetEntityId);

            if (trackingEntity != null) {
                if (trackingEntity == missileObj) {
                    missileObj.setExplode();
                }

                missileObj.target = new Vector3(trackingEntity);
            }
        }
    }

    @Override
    public void update(final EMissile missileObj) {
        if (missileObj.flyingTicks > missileObj.flightDuration / 2.0f
            && missileObj.missileType == EMissile.MissileType.MISSILE) {
            final WorldServer worldServer = (WorldServer) missileObj.worldObj;
            final Entity trackingEntity
                = worldServer.getEntityByID(missileObj.targetEntityId);

            if (trackingEntity != null) {
                if (trackingEntity == missileObj) {
                    missileObj.setExplode();
                }

                missileObj.target = new Vector3(trackingEntity);
                missileObj.missileType = EMissile.MissileType.SMALL_MISSILE;
                missileObj.xDiff = missileObj.target.x - missileObj.posX;
                missileObj.yDiff = missileObj.target.y - missileObj.posY;
                missileObj.zDiff = missileObj.target.z - missileObj.posZ;
                missileObj.targetDistance = Vector2.distance(
                    missileObj.origin.toVector2(), missileObj.target.toVector2()
                );
                missileObj.skyHeight = 150 + (int) (missileObj.targetDistance * 1.8);
                missileObj.flightDuration
                    = (float) Math.max(100.0, 2.4 * missileObj.targetDistance);
                missileObj.acceleration = missileObj.skyHeight * 2.0f
                    / (missileObj.flightDuration * missileObj.flightDuration);

                if (missileObj.smallMissileMotion.equals(new Vector3())
                    || missileObj.smallMissileMotion == null) {
                    final float suDu = 0.3f;
                    missileObj.smallMissileMotion = new Vector3();
                    missileObj.smallMissileMotion.x
                        = missileObj.xDiff / (missileObj.flightDuration * suDu);
                    missileObj.smallMissileMotion.y
                        = missileObj.yDiff / (missileObj.flightDuration * suDu);
                    missileObj.smallMissileMotion.z
                        = missileObj.zDiff / (missileObj.flightDuration * suDu);
                }
            }
        }
    }

    @Override
    public boolean
    onInteract(final EMissile missileObj, final EntityPlayer entityPlayer) {
        if (!missileObj.worldObj.isRemote && missileObj.flyingTicks <= 0
            && entityPlayer.getCurrentEquippedItem() != null
            && entityPlayer.getCurrentEquippedItem().getItem() instanceof ITracker) {
            final Entity trackingEntity
                = ((ITracker) entityPlayer.getCurrentEquippedItem().getItem())
                      .getTrackingEntity(
                          missileObj.worldObj, entityPlayer.getCurrentEquippedItem()
                      );

            if (trackingEntity != null
                && missileObj.targetEntityId != trackingEntity.getEntityId()) {
                missileObj.targetEntityId = trackingEntity.getEntityId();
                entityPlayer.addChatMessage(new ChatComponentText(
                    "Missile target locked to: " + trackingEntity.getCommandSenderName()
                ));

                if (missileObj.getLauncher() != null
                    && missileObj.getLauncher().getController() != null) {
                    final Vector3 newTarget = new Vector3(trackingEntity);
                    newTarget.y = 0.0;
                    missileObj.getLauncher().getController().setTarget(newTarget);
                }

                return true;
            }
        }

        return false;
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
