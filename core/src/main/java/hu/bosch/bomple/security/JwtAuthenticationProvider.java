package hu.bosch.bomple.security;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtVerifier jwtVerifier;
    private final StatelessSessionService statelessSessionService;
//    private final AuthorizerClient authorizerClient;
//    private final RestTemplate restTemplate;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String jwt = (String) authentication.getCredentials();

        Claims claims = null;
        try {
            claims = jwtVerifier.verifyAndUnpackJwt(jwt);
        } catch (RuntimeException ex) {
//            authorizerClient.autoLogin();
//            throw new BadCredentialsException("Lejárt volt a JWT!");
        }
        return statelessSessionService.jwtAuthenticationToken(claims);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
