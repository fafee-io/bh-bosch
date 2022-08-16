package hu.bosch.bomple.security;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthorizationInterceptor implements RequestInterceptor {

    private final JwtFacade jwtFacade;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String jwt = null;
        try {
            jwt = jwtFacade.getOriginalJwt();
        } catch (Exception ex) {
            // logolni hogy nem volt
        }
        if (null == jwt) {
            jwt = RemoteAutoLoginService.threadLocalJwt.get();
        }
        requestTemplate.header("Authorization", "Bearer " + jwt);
    }
}
