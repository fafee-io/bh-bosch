package hu.bosch.bomple.config;

import hu.bosch.bomple.security.AuthorizerClient;
import hu.bosch.bomple.security.EnableBompleSecurity;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.Clock;
import java.time.ZoneId;

@Configuration
@EnableJpaRepositories(basePackages = {"hu.bosch.bomple"})
@EntityScan(basePackages = {"hu.bosch.bomple"})
@EnableJpaAuditing
@EnableBompleSecurity
@EnableFeignClients(clients = {AuthorizerClient.class})
public class ApplicationConfig {

    @Bean
    public Clock clock() {
        return Clock.system(ZoneId.of("Europe/Budapest"));
    }

    private void interjuKerdes() {
        double niceValue = 32.5d;
        double measuredValue = bonyolultKalkulacio();
        if (niceValue == measuredValue) {
            System.out.println("Happiness");
        } else {
            System.out.println("Sadness");
        }
    }

    private double bonyolultKalkulacio() {
        return 55d;
    }
}
