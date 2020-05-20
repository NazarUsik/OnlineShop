package co.proarena.usik.utils;

import co.proarena.usik.entity.User;

import javax.servlet.http.HttpServletResponse;

public class RememberUtils {
    public static void remember(HttpServletResponse response, boolean remember, User user) {
        if (remember) {
            StoredAttributeUtils.storeUserCookie(response, user);
        } else {
            StoredAttributeUtils.deleteUserCookie(response);
        }
    }
}
