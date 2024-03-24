package icbm.gangshao.access;

public enum AccessLevel {
    NONE("None"),
    USER("User"),
    ADMIN("Admin"),
    OWNER("Owner");

    public String displayName;

    private AccessLevel(final String name) {
        this.displayName = name;
    }

    public static AccessLevel get(final Object ob) {
        if (ob instanceof String) {
            for (final AccessLevel access : values()) {
                if (access.displayName.equalsIgnoreCase((String) ob)
                    || access.name().equalsIgnoreCase((String) ob)) {
                    return access;
                }
            }
        }

        if (ob instanceof Integer) {
            final int i = (int) ob % values().length;

            if (i >= 0 && i < values().length) {
                return values()[i];
            }
        }

        return AccessLevel.NONE;
    }
}
