package hu.bosch.bomple.config;

import hu.bosch.bomple.auth.model.RefreshRepository;
import hu.bosch.bomple.auth.service.AsymmetricKeyService;
import hu.bosch.bomple.auth.service.JwtGenerator;
import hu.bosch.bomple.auth.service.JwtRefreshService;
import hu.bosch.bomple.security.AutoLoginService;
import hu.bosch.bomple.security.EnableBompleSecurity;
import hu.bosch.bomple.security.JwtSecurityProperties;
import hu.bosch.bomple.security.JwtVerifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.Clock;
import java.time.ZoneId;

@Configuration
@EnableJpaRepositories(basePackages = {"hu.bosch.bomple"})
@EnableMongoRepositories(basePackages = {"hu.bosch.bomple.auth.model"})
@EntityScan(basePackages = {"hu.bosch.bomple"})
@EnableJpaAuditing
@EnableBompleSecurity
public class ApplicationConfig {

    @Bean
    public Clock clock() {
        return Clock.system(ZoneId.of("Europe/Budapest"));
    }

    @Bean
    @Primary
    public AutoLoginService autoLoginService(RefreshRepository refreshRepository, JwtVerifier jwtVerifier,
                                             JwtGenerator jwtGenerator, Clock clock) {
        return new JwtRefreshService(refreshRepository, jwtVerifier, jwtGenerator, clock);
    }

    @Bean
    public JwtRefreshService jwtRefreshService(RefreshRepository refreshRepository, JwtVerifier jwtVerifier,
                                             JwtGenerator jwtGenerator, Clock clock) {
        return new JwtRefreshService(refreshRepository, jwtVerifier, jwtGenerator, clock);
    }

    @Bean
    public JwtGenerator jwtGenerator(JwtSecurityProperties properties, AsymmetricKeyService keyService) {
        if (properties.isGenerateKeys()) {
            return new JwtGenerator(properties, keyService.generate().getPrivate());
        } else {
            return new JwtGenerator(properties, keyService.readKeys(properties.getPrivateKey()));
        }
    }

//    @Bean
//    public TransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }
}
