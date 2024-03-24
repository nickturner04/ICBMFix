package icbm.gangshao.task;

import java.util.ArrayList;
import java.util.List;

import icbm.core.MainBase;
import icbm.gangshao.turret.sentries.TAutomaticTurret;

public class TaskManager {
    private final List<Task> tasks;
    public TAutomaticTurret tileEntity;

    public TaskManager(final TAutomaticTurret tileEntity) {
        this.tasks = new ArrayList<>();
        this.tileEntity = tileEntity;
    }

    public void onUpdate() {
        try {
            if (this.tasks.size() > 0) {
                final int taskIndex = 0;
                final Task currentTask = this.tasks.get(taskIndex);

                if (currentTask != null && !currentTask.onUpdateTask()) {
                    currentTask.onTaskEnd();
                    this.tasks.remove(taskIndex);
                }
            }
        } catch (final Exception e) {
            MainBase.LOGGER.severe("Failed to execute AI tasks!");
            e.printStackTrace();
        }
    }

    public void addTask(final Task task) {
        task.taskManager = this;
        task.world = this.tileEntity.getWorldObj();
        task.tileEntity = this.tileEntity;
        this.tasks.add(task);
    }

    public boolean hasTasks() {
        return this.tasks.size() > 0;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void clear() {
        this.tasks.clear();
    }

    public int getTaskCount() {
        return this.tasks.size();
    }
}
