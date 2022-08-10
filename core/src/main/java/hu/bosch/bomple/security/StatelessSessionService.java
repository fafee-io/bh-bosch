package hu.bosch.bomple.security;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

public class StatelessSessionService {

    // TODO: név
    public JwtAuthenticationToken jwtAuthenticationToken(Claims claims){
        Long userId = Long.valueOf(claims.getSubject());
        String role = (String) claims.get("role");

        return new JwtAuthenticationToken(null, userId,
                Collections.singletonList(new SimpleGrantedAuthority(role)));
    }

}
