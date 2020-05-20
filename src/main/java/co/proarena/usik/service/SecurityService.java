package co.proarena.usik.service;

import co.proarena.usik.entity.User;
import co.proarena.usik.security.UserPrincipal;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {



    public void autoLogin(User user) {
        UserPrincipal principal = UserPrincipal.create(user);
        SecurityContextHolder
                .getContext()
                .setAuthentication(
                        new UsernamePasswordAuthenticationToken(
                                principal.getUsername(),
                                principal.getPassword(),
                                principal.getAuthorities()));
    }
}
