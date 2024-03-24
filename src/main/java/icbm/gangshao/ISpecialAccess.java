package icbm.gangshao;

import java.util.List;

import icbm.gangshao.access.AccessLevel;
import icbm.gangshao.access.UserAccess;

public interface ISpecialAccess {
    AccessLevel getUserAccess(final String p0);

    List<UserAccess> getUsers();

    boolean addUserAccess(final String p0, final AccessLevel p1, final boolean p2);

    boolean removeUserAccess(final String p0);

    List<UserAccess> getUsersWithAcess(final AccessLevel p0);
}
