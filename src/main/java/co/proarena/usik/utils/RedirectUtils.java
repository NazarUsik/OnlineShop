package co.proarena.usik.utils;

import co.proarena.usik.constants.ViewsConstants;
import co.proarena.usik.entity.User;

import javax.servlet.http.HttpServletRequest;

public class RedirectUtils {

    public static String invalidRedirect(HttpServletRequest request){
        User user = StoredAttributeUtils.getLoginedUser(request.getSession());
        if (user == null) {
            return ViewsConstants.REDIRECT + ViewsConstants.JSP_LOGIN + "?error=Login first!";
        }
        return ViewsConstants.REDIRECT + ViewsConstants.JSP_USER;
    }

}
