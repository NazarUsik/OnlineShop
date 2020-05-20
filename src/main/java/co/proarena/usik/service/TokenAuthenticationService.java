package co.proarena.usik.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Date;

public class TokenAuthenticationService {

    private static final long EXPIRATION_TIME = 864_000_000; // 10 days

    private static final String SECRET = "ThisIsASecret";

    private static final String TOKEN_PREFIX = "Bearer";

    private static final String HEADER_STRING = "Authorization";

    public static void addAuthentication(HttpSession session, Authentication authentication) {
        String JWT = Jwts.builder().setSubject(authentication.getName() + "/" + authentication.getCredentials())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();

        session.setAttribute(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
        session.setAttribute("remember", authentication.getDetails());
    }

    public static Authentication getAuthentication(HttpSession session) {
        String token = (String) session.getAttribute(HEADER_STRING);
        if (token != null) {

            String user =
                    Jwts
                            .parser()
                            .setSigningKey(SECRET)
                            .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                            .getBody()
                            .getSubject();
            return user != null ?
                    new UsernamePasswordAuthenticationToken(user.split("/")[0], user.split("/")[1],
                            Collections.emptyList()) : null;
        }
        return null;
    }

}
