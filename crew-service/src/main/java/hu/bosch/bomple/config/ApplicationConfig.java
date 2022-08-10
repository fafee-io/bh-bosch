package hu.bosch.bomple.config;

import hu.bosch.bomple.security.EnableBompleSecurity;
import org.springframework.boot.autoconfigure.domain.EntityScan;
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
public class ApplicationConfig {

    @Bean
    public Clock clock() {
        return Clock.system(ZoneId.of("Europe/Budapest"));
    }

}
