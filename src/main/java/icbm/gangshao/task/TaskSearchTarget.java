package icbm.gangshao.task;

import java.util.List;

import icbm.gangshao.IAutoSentry;
import icbm.gangshao.access.AccessLevel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import universalelectricity.core.vector.Vector3;

public class TaskSearchTarget extends Task {
    @Override
    protected boolean onUpdateTask() {
        super.onUpdateTask();

        if (super.tileEntity instanceof IAutoSentry) {
            final IAutoSentry sentry = super.tileEntity;

            if (sentry.getTarget() == null || !sentry.isValidTarget(sentry.getTarget())) {
                final AxisAlignedBB bounds = sentry.getTargetingBox();
                final List<Entity> entities
                    = super.tileEntity.getWorldObj().getEntitiesWithinAABB(
                        Entity.class, bounds
                    );
                Entity currentTarget = null;

                for (final Entity entity : entities) {
                    if (entity instanceof EntityPlayer) {
                        final EntityPlayer player = (EntityPlayer) entity;
                        final AccessLevel level
                            = super.tileEntity.getPlatform().getUserAccess(
                                player.getDisplayName()
                            );

                        if (level.ordinal() >= AccessLevel.USER.ordinal()
                            && player.getLastAttacker() != null
                            && sentry.isValidTarget((Entity) player.getLastAttacker())
                            && !((Entity) player.getLastAttacker()).isDead) {
                            currentTarget = (Entity) player.getLastAttacker();
                            break;
                        }

                        continue;
                    }
                }

                if (currentTarget == null) {
                    double smallestDis = sentry.getDetectRange();

                    for (final Entity entity2 : entities) {
                        final double distance
                            = super.tileEntity.getCenter().distanceTo(new Vector3(entity2)
                            );

                        if (sentry.isValidTarget(entity2) && distance <= smallestDis) {
                            currentTarget = entity2;
                            smallestDis = distance;
                        }
                    }
                }

                if (currentTarget != null) {
                    super.tileEntity.cancelRotation();
                    super.taskManager.addTask(new TaskKillTarget());
                    sentry.setTarget(currentTarget);
                    return false;
                }

                if (super.tileEntity.lastRotateTick > super.world.rand.nextInt(30) + 10) {
                    super.tileEntity.rotateTo(
                        (float) (super.world.rand.nextInt(360) - 180),
                        (float) (super.world.rand.nextInt(30) - 10)
                    );
                }

                return true;
            } else {
                super.taskManager.addTask(new TaskKillTarget());
            }
        }

        return false;
    }
}
