package co.proarena.usik.utils;

import co.proarena.usik.entity.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StoredAttributeUtils {

    private static final String ATT_NAME_USER_NAME = "user_email";
    private static final String ATT_NAME_USER = "loginedUser";

    public static void storeLoginedUser(HttpSession session, User loginedUser) {
        session.setAttribute(ATT_NAME_USER, loginedUser);
    }

    public static User getLoginedUser(HttpSession session) {
        return (User) session.getAttribute(ATT_NAME_USER);
    }

    public static void storeUserCookie(HttpServletResponse response, User user) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getEmail());
        cookieUserName.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookieUserName);
    }

    public static String getUserInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
    }
}
