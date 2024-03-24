package icbm.gangshao;

import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;

public interface IAutoSentry extends ISentry {
    AxisAlignedBB getTargetingBox();

    Entity getTarget();

    void setTarget(final Entity p0);

    boolean isValidTarget(final Entity p0);

    double getDetectRange();
}
