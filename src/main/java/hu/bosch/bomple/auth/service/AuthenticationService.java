package hu.bosch.bomple.auth.service;

import hu.bosch.bomple.auth.LoginCredentials;
import hu.bosch.bomple.auth.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

//    private final UserRepository userRepository;
    private final JwtGenerator jwtGenerator;
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
            return jwtGenerator.generateJwtToken(user);
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
