package hu.bosch.bomple.security;

import org.springframework.context.annotation.Bean;

public class AuthorizerClientConfig {

    @Bean
    public AuthorizationInterceptor authorizationInterceptor(JwtFacade jwtFacade) {
        return new AuthorizationInterceptor(jwtFacade);
    }

}
