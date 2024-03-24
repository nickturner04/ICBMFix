package icbm.zhapin.jiqi;

import java.util.ArrayList;
import java.util.List;

import universalelectricity.core.vector.Vector2;

public class LauncherManager {
    private static List<TLauncherController> launchers;

    public static void addLauncher(final TLauncherController launcher) {
        findLaunchers();

        if (!LauncherManager.launchers.contains(launcher)) {
            LauncherManager.launchers.add(launcher);
        }
    }

    public static List<TLauncherController>
    launchersInArea(final Vector2 minVector, final Vector2 maxVector) {
        findLaunchers();
        final List<TLauncherController> returnArray = new ArrayList<>();

        for (final TLauncherController launcher : LauncherManager.launchers) {
            if (launcher.xCoord > minVector.x && launcher.xCoord < maxVector.x
                && launcher.zCoord > minVector.y && launcher.zCoord < maxVector.y) {
                returnArray.add(launcher);
            }
        }

        return returnArray;
    }

    public static List<TLauncherController> getLaunchers() {
        findLaunchers();
        return LauncherManager.launchers;
    }

    public static void findLaunchers() {
        for (int i = 0; i < LauncherManager.launchers.size(); ++i) {
            if (LauncherManager.launchers.get(i) == null) {
                LauncherManager.launchers.remove(i);
            } else if (LauncherManager.launchers.get(i).isInvalid()) {
                LauncherManager.launchers.remove(i);
            }
        }
    }

    static {
        LauncherManager.launchers = new ArrayList<>();
    }
}
