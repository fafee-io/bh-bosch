package hu.bosch.bomple.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class JwtFacade {

    private JwtAuthenticationToken getToken(){
        //TODO beautify
        return (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    }

    public Long getCurrentUserId(){
        return (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
