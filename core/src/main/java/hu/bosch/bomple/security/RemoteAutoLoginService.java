package hu.bosch.bomple.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
public class RemoteAutoLoginService implements AutoLoginService {

    private final AuthorizerClient authorizerClient;

    public static ThreadLocal<String> threadLocalJwt = new ThreadLocal<>();

    @Override
    public String autoLogin(String currentJwt) {
        threadLocalJwt.set(currentJwt);
        ResponseEntity<Void> response = authorizerClient.autoLogin();
        return response.getHeaders().get("Authorization").iterator().next();
    }

}
