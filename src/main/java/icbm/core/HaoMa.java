package icbm.core;

import java.util.HashMap;

public class HaoMa {
    private static final HashMap<String, Integer> IDS;

    public static int getID(final String name, final int defaultID) {
        int id = defaultID;

        if (HaoMa.IDS.containsKey(name)) {
            id = HaoMa.IDS.get(name);
            ++id;
        }

        HaoMa.IDS.put(name, id);
        return id;
    }

    public static int getID(final String name) {
        return getID(name, 0);
    }

    static {
        IDS = new HashMap<>();
    }
}
