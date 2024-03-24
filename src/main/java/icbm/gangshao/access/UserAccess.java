package icbm.gangshao.access;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class UserAccess {
    public String username;
    public AccessLevel level;
    public boolean shouldSave;

    public UserAccess(final String user, final AccessLevel level, final boolean save) {
        this.username = user;
        this.level = level;
        this.shouldSave = save;
    }

    public NBTTagCompound writeToNBT(final NBTTagCompound nbt) {
        nbt.setString("username", this.username);
        nbt.setInteger("ID", this.level.ordinal());
        return nbt;
    }

    public void readFromNBT(final NBTTagCompound nbt) {
        this.username = nbt.getString("username");
        this.level = AccessLevel.get(nbt.getInteger("ID"));
    }

    public static UserAccess loadFromNBT(final NBTTagCompound nbt) {
        final UserAccess access = new UserAccess("", AccessLevel.NONE, true);
        access.readFromNBT(nbt);
        return access;
    }

    public static List<UserAccess>
    readListFromNBT(final NBTTagCompound nbt, final String tagName) {
        final NBTTagList userList = nbt.getTagList(tagName, 10);
        final List<UserAccess> users = new ArrayList<>();

        for (int i = 0; i < userList.tagCount(); ++i) {
            final NBTTagCompound var4 = (NBTTagCompound) userList.getCompoundTagAt(i);
            users.add(loadFromNBT(var4));
        }

        return users;
    }

    public static void
    writeListToNBT(final NBTTagCompound save, final List<UserAccess> users) {
        final NBTTagList usersTag = new NBTTagList();

        for (int player = 0; player < users.size(); ++player) {
            final UserAccess access = users.get(player);

            if (access != null && access.shouldSave) {
                final NBTTagCompound accessData = new NBTTagCompound();
                access.writeToNBT(accessData);
                usersTag.appendTag((NBTBase) accessData);
            }
        }

        save.setTag("Users", (NBTBase) usersTag);
    }

    public static List<UserAccess>
    removeUserAccess(final String player, final List<UserAccess> users) {
        final List<UserAccess> removeList = new ArrayList<UserAccess>();
        final List<UserAccess> returnList = users;

        for (int i = 0; i < users.size(); ++i) {
            final UserAccess ref = (UserAccess) users.get(i);

            if (ref.username.equalsIgnoreCase(player)) {
                removeList.add(ref);
            }
        }

        if (removeList != null && removeList.size() > 0) {
            returnList.removeAll(removeList);
        }

        return returnList;
    }
}
