package icbm.gangshao.access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.nbt.NBTTagCompound;
import universalelectricity.prefab.flag.NBTFileLoader;

public class GlobalAccessManager {
    private static Map<String, List<UserAccess>> globalUserLists;
    private static NBTTagCompound masterSaveNbt;
    public static boolean loading;
    public static boolean hasLoaded;
    public static boolean needsSaving;

    public static List<UserAccess>
    getOrCreateList(final String name, final String owner) {
        if (name.toCharArray().length < 5 || owner.isEmpty()
            || name.startsWith("Default#")) {
            return null;
        }

        List<UserAccess> list = getList(name);

        if (list == null) {
            list = createList(name, owner);
        }

        return list;
    }

    public static List<String> getUsersLists(final String username) {
        final List<String> lists = new ArrayList<>();

        for (final Map.Entry<String, List<UserAccess>> entry :
             GlobalAccessManager.globalUserLists.entrySet()) {
            final List<UserAccess> list = entry.getValue();

            for (final UserAccess access : list) {
                if (access.username.equalsIgnoreCase(username)
                    && access.level.ordinal() >= AccessLevel.ADMIN.ordinal()) {
                    lists.add(entry.getKey());
                    break;
                }
            }
        }

        return lists;
    }

    public static List<UserAccess> createList(final String name, final String owner) {
        final List<UserAccess> list = new ArrayList<>();
        list.add(new UserAccess(owner, AccessLevel.OWNER, true));
        GlobalAccessManager.globalUserLists.put(name, list);
        saveList(name, list);
        GlobalAccessManager.needsSaving = true;
        return list;
    }

    public static List<UserAccess> getList(final String name) {
        if (GlobalAccessManager.globalUserLists.containsKey(name)) {
            return GlobalAccessManager.globalUserLists.get(name);
        }

        final List<UserAccess> list = loadList(name);

        if (list != null) {
            GlobalAccessManager.globalUserLists.put(name, list);
        }

        return list;
    }

    public boolean addUser(final String listName, final UserAccess user) {
        if (user == null) {
            return false;
        }

        List<UserAccess> userList = getList(listName);

        if (userList != null) {
            if (userList.contains(user)) {
                userList = UserAccess.removeUserAccess(user.username, userList);
            }

            if (userList.add(user)) {
                GlobalAccessManager.globalUserLists.put(listName, userList);
                saveList(listName, userList);
                return GlobalAccessManager.needsSaving = true;
            }
        }

        return false;
    }

    public boolean removeUser(final String listName, final UserAccess user) {
        if (user == null) {
            return false;
        }

        List<UserAccess> userList = getList(listName);

        if (userList != null && userList.contains(user)) {
            userList = UserAccess.removeUserAccess(user.username, userList);
            GlobalAccessManager.globalUserLists.put(listName, userList);
            saveList(listName, userList);
            return GlobalAccessManager.needsSaving = true;
        }

        return false;
    }

    private static List<UserAccess> loadList(final String name) {
        final NBTTagCompound masterSave = getMasterSaveFile();

        if (masterSave != null && masterSave.hasKey(name)) {
            final NBTTagCompound accessSave = masterSave.getCompoundTag(name);
            return UserAccess.readListFromNBT(accessSave, "Users");
        }

        return null;
    }

    private static void saveList(final String name, final List<UserAccess> list) {
        final NBTTagCompound masterSave = getMasterSaveFile();

        if (masterSave != null) {
            final NBTTagCompound accessSave = masterSave.getCompoundTag(name);
            UserAccess.writeListToNBT(accessSave, list);
            masterSave.setTag(name, accessSave);
        }
    }

    public static NBTTagCompound getMasterSaveFile() {
        if (GlobalAccessManager.masterSaveNbt.hasNoTags()
            && !GlobalAccessManager.loading) {
            GlobalAccessManager.hasLoaded = true;
            GlobalAccessManager.loading = true;
            NBTFileLoader.loadData("Global_Access_List");
            GlobalAccessManager.loading = false;
        }

        return GlobalAccessManager.masterSaveNbt;
    }

    static {
        GlobalAccessManager.globalUserLists = new HashMap<>();
        GlobalAccessManager.masterSaveNbt = new NBTTagCompound();
        GlobalAccessManager.loading = false;
        GlobalAccessManager.hasLoaded = false;
        GlobalAccessManager.needsSaving = false;
    }
}
