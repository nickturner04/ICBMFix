package icbm.gangshao.task;

import icbm.gangshao.IAutoSentry;

public class TaskKillTarget extends TaskSearchTarget {
    @Override
    protected boolean onUpdateTask() {
        super.onUpdateTask();

        if (super.tileEntity instanceof IAutoSentry) {
            if (!super.tileEntity.isValidTarget(super.tileEntity.getTarget())) {
                super.tileEntity.setTarget(null);
                super.tileEntity.cancelRotation();
                return false;
            }

            if (super.tileEntity.canActivateWeapon()) {
                super.tileEntity.onWeaponActivated();
            } else {
                final float[] rotations = super.tileEntity.lookHelper.getDeltaRotations(
                    super.tileEntity.getTargetPosition()
                );
                super.tileEntity.rotateTo(rotations[0], rotations[1]);
            }
        }

        return true;
    }
}
