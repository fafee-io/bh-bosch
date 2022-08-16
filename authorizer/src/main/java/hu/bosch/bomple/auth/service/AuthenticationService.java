package hu.bosch.bomple.auth.service;

import hu.bosch.bomple.auth.LoginCredentials;
import hu.bosch.bomple.auth.model.RefreshEntity;
import hu.bosch.bomple.auth.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

//    private final UserRepository userRepository;
    private final JwtGenerator jwtGenerator;
    private final JwtRefreshService jwtRefreshService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String login(LoginCredentials credentials) {
//        Optional<UserEntity> user;
//        if (credentials.getUser().contains("@")) {
//            user = userRepository.findUserEntityByEmail(credentials.getUser());
//        } else {
//            user = userRepository.findUserEntityByUsername(credentials.getUser());
//        }

        // TODO: repo helyett
        UserEntity user = new UserEntity();
        user.setId(1337L);
        user.setUsername("user");
        user.setPassword("$2a$14$p.NlOiwF2DpEe92ABUSwFOZsPBYlj/H7kCQC2qfvFQGqpazTy.24a");
        if (passwordEncoder.matches(credentials.getPassword(), user.getPassword())) {
            RefreshEntity refresh = jwtRefreshService.onFullLogin(user);
            return jwtGenerator.generateJwtToken(user, refresh);
        } else {
            throw new IllegalArgumentException("jaj");
        }
//
//        //TODO: CustomUserDetailService ha kell extra adat a jwt-be
//        if (user.isEmpty() || !) {
//            throw new UnauthorizedException(ErrorCode.BAD_CREDENTIALS);
//        }
//
//        if (!user.get().getActive()) {
//            throw new UnauthorizedException(ErrorCode.USER_NOT_ACTIVE);
//        }


    }

}
