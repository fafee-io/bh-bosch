package hu.bosch.bomple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
public class ShipServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShipServiceApplication.class, args);
    }

}
