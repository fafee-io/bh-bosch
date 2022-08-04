package hu.bosch.bomple.security;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@RequiredArgsConstructor
public class SecurityAuditorAware implements AuditorAware<Long> {

    private final JwtFacade jwtFacade;

    @Override
    public Optional getCurrentAuditor() {
        return Optional.ofNullable(jwtFacade.getCurrentUserId());
    }
}
