package hu.bosch.bomple.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class JwtFacade {

    private Authentication getToken() {
        //TODO beautify
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public boolean isLoggedIn() {
        Authentication auth = getToken();
        if (auth != null && JwtAuthenticationToken.class.isAssignableFrom(auth.getClass())) {
            return auth.isAuthenticated();
        }
        return false;
    }

    public String getOriginalJwt() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
    }

    public Long getCurrentUserId() {
        if (isLoggedIn()) {
            return (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } else {
            return null;
        }
    }
}
