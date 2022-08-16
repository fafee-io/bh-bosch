package hu.bosch.bomple.security;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@EnableConfigurationProperties(JwtSecurityProperties.class)
public class JwtSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationProvider authProvider, JwtSecurityProperties properties, JwtFacade jwtFacade) throws Exception {
        http
                .csrf().disable()
                .formLogin().disable()
                .logout().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterAfter(jwtRequestFilter(authProvider, properties), UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(jwtResponseFilter(jwtFacade), JwtRequestFilter.class)
                .authorizeRequests()
                .antMatchers(properties.getUnprotectedUrls()).permitAll()
                .antMatchers(properties.getSecuredUrls()).authenticated();
        return http.build();
    }

    @Bean
    public StatelessSessionService statelessSessionService(){
        return new StatelessSessionService();
    }

    @Bean
    public JwtVerifier jwtVerifier(JwtSecurityProperties properties) throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec formatPk = new X509EncodedKeySpec(Base64.getDecoder().decode(properties.getPublicKey()));
        KeyFactory kf = KeyFactory.getInstance("EC"); // todo ezt ne ide
        PublicKey PK = kf.generatePublic(formatPk);

        return new JwtVerifier(PK, properties.getJwtLifeTimeInSeconds());
    }

    @Bean
    public AuthenticationProvider authProvider(JwtVerifier jwtVerifier, StatelessSessionService statelessSessionService, AutoLoginService autoLoginService){
        return new JwtAuthenticationProvider(jwtVerifier, statelessSessionService, autoLoginService);
    }

    public JwtRequestFilter jwtRequestFilter(AuthenticationProvider authProvider, JwtSecurityProperties properties){
        return new JwtRequestFilter(authProvider, properties.getSecuredUrls(), properties.getUnprotectedUrls());
    }

    public JwtResponseFilter jwtResponseFilter(JwtFacade jwtFacade) {
        return new JwtResponseFilter(jwtFacade);
    }

    @Bean
    public AutoLoginService autoLoginService(AuthorizerClient client) {
        return new RemoteAutoLoginService(client);
    }

    // !!!!!!!!!!!
    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }

    @Bean
    public SecurityAuditorAware securityAuditorAware(JwtFacade jwtFacade){
        return new SecurityAuditorAware(jwtFacade);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        return builder.build();
    }
}
