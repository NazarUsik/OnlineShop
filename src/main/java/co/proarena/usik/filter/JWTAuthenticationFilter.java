package co.proarena.usik.filter;

import co.proarena.usik.entity.User;
import co.proarena.usik.security.CustomUserDetailsService;
import co.proarena.usik.service.TokenAuthenticationService;
import co.proarena.usik.service.UserService;
import co.proarena.usik.utils.RememberUtils;
import co.proarena.usik.utils.StoredAttributeUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JWTAuthenticationFilter extends GenericFilterBean {

    private CustomUserDetailsService userDetailsService;
    private UserService userService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        if (userDetailsService == null) {
            ServletContext servletContext = servletRequest.getServletContext();
            WebApplicationContext webAppContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            userDetailsService = webAppContext.getBean(CustomUserDetailsService.class);
        }

        if (userService == null) {
            ServletContext servletContext = servletRequest.getServletContext();
            WebApplicationContext webAppContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            userService = webAppContext.getBean(UserService.class);
        }
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        Authentication authentication = TokenAuthenticationService
                .getAuthentication((request.getSession()));

        if (authentication != null) {
            boolean remember = (boolean) (request.getSession().getAttribute("remember"));

            try {
                UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
                User user = userService.findByEmail(authentication.getName());


                if (user != null &&
                        userService.matchesPassword((String) authentication.getCredentials(), user.getPassword())) {
                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                            userDetails.getUsername(),
                            userDetails.getPassword(),
                            userDetails.getAuthorities());

                    StoredAttributeUtils.storeLoginedUser(request.getSession(), user);
                    RememberUtils.remember(response, remember, user);

                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(token);
                } else {
                    request.setAttribute("error", "Email or Password incorrect!");
                    request
                            .getServletContext()
                            .getRequestDispatcher("/WEB-INF/views/login.jsp")
                            .forward(request, response);
                }
            } catch (UsernameNotFoundException ex) {
                request.setAttribute("error", "Email or Password incorrect!");
                request
                        .getServletContext()
                        .getRequestDispatcher("/WEB-INF/views/login.jsp")
                        .forward(request, response);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
