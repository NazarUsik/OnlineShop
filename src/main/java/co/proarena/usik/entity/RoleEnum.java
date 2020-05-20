package co.proarena.usik.entity;

public enum RoleEnum {
    ROLE_ADMIN, ROLE_USER, BLOCKED, ROLE_ANONYMOUS;

    public static RoleEnum getRole(User user) {
        long roleId = user.getRoleId();
        return RoleEnum.values()[(int) (roleId - 1)];
    }

    public static RoleEnum getRole(int roleId) {
        return RoleEnum.values()[roleId - 1];
    }

    public String getName() {
        return name().toUpperCase();
    }
}
