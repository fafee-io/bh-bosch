package hu.bosch.bomple;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UtilityTests {

    @Test
    public void passwordGeneration() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(14);

        String password = "Almafa123";
        String hash = encoder.encode(password);

        System.out.println(password + " : " + hash);
    }

}
