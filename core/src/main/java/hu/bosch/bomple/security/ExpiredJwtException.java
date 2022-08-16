package hu.bosch.bomple.security;

import io.jsonwebtoken.Claims;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class ExpiredJwtException extends AuthenticationException {

    private final Claims claims;

    public ExpiredJwtException(String message, Claims claims) {
        super(message);
        this.claims = claims;
    }

}
