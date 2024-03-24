package icbm.gangshao.task;

import icbm.gangshao.turret.sentries.TAutomaticTurret;
import net.minecraft.world.World;

public abstract class Task {
    protected int ticks;
    public World world;
    public TAutomaticTurret tileEntity;
    public TaskManager taskManager;

    public Task() {
        this.ticks = 0;
    }

    protected boolean onUpdateTask() {
        ++this.ticks;
        return false;
    }

    public void onTaskStart() {}

    public void onTaskEnd() {}

    public int getTickInterval() {
        return 1;
    }
}
