package hu.bosch.bomple.auth;

import hu.bosch.bomple.auth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/auth/login")
    public ResponseEntity<Void> login(@RequestBody LoginCredentials credentials) {

        String jwt = authenticationService.login(credentials);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", jwt);
        return ResponseEntity.ok().headers(headers).build();
    }

}
