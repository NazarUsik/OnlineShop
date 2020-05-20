package co.proarena.usik.filter;

import co.proarena.usik.entity.User;
import co.proarena.usik.exeption.ResourceNotFoundException;
import co.proarena.usik.repository.UserRepository;
import co.proarena.usik.security.UserPrincipal;
import co.proarena.usik.utils.StoredAttributeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@Order(1)
public class CookieFilter implements Filter {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();

        User userInSession = StoredAttributeUtils.getLoginedUser(session);

        if (userInSession != null) {
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
            chain.doFilter(req, servletResponse);
            return;
        }

        String checked = (String) session.getAttribute("COOKIE_CHECKED");
        if (checked == null) {
            String userEmail = StoredAttributeUtils.getUserInCookie(req);

            if (userEmail != null) {
                try {
                    User user = userRepository.findByEmail(userEmail).orElseThrow(() ->
                            new ResourceNotFoundException("User", "email", userEmail));

                    UserPrincipal userPrincipal = UserPrincipal.create(user);
                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(
                                    new UsernamePasswordAuthenticationToken(
                                            userPrincipal.getUsername(),
                                            userPrincipal.getPassword(),
                                            userPrincipal.getAuthorities()));
                    StoredAttributeUtils.storeLoginedUser(session, user);
                } catch (ResourceNotFoundException ex) {
                    System.out.println(ex.getMessage());
                    StoredAttributeUtils.storeLoginedUser(session, null);
                    StoredAttributeUtils.deleteUserCookie((HttpServletResponse) servletResponse);
                }
            }

            session.setAttribute("COOKIE_CHECKED", "CHECKED");
        }

        chain.doFilter(req, servletResponse);
    }
}
