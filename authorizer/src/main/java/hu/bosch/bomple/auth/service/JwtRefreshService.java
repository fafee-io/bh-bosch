package hu.bosch.bomple.auth.service;

import hu.bosch.bomple.auth.model.RefreshEntity;
import hu.bosch.bomple.auth.model.RefreshRepository;
import hu.bosch.bomple.auth.model.UserEntity;
import hu.bosch.bomple.security.AutoLoginService;
import hu.bosch.bomple.security.ExpiredJwtException;
import hu.bosch.bomple.security.JwtVerifier;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.Clock;
import java.time.Instant;
import java.util.Random;

@RequiredArgsConstructor
public class JwtRefreshService implements AutoLoginService {

    private final RefreshRepository refreshRepository;
    private final JwtVerifier jwtVerifier;
    private final JwtGenerator generator;
    private final Clock clock;
//    private final MongoTemplate mongoTemplate;
    private final Random random = new SecureRandom();

    @Override
//    @Transactional
    public String autoLogin(String currentJwt) {
        Claims claims;
        try {
            claims = jwtVerifier.verifyAndUnpackJwt(currentJwt);
        } catch (ExpiredJwtException ex) {
            claims = ex.getClaims();
        }
        RefreshEntity refresh = refreshRepository.findByUserIdAndGeneration(Long.valueOf((String) claims.get("sub")),(String) claims.get("rfshg"));
//        Query query = new Query();
//        query.addCriteria(Criteria.where("userId").is(Long.valueOf((String) claims.get("sub"))).andOperator(Criteria.where("generation").is((String) claims.get("rfshg"))));
//        RefreshEntity refresh = mongoTemplate.findOne(query, RefreshEntity.class);
        // todo: türelmi idő bevezetése
        // refresh + néhány másodpercen belül a következő ellenőrzés ne történjen meg, hanem a DB beli tokent küldjük újra
        if (refresh.getToken().equals(claims.get("rfsht"))) {
            String freshToken = generateToken(12);
            refresh.setToken(freshToken);
            refreshRepository.save(refresh);
            return generator.copyJwt(claims, freshToken);
        } else { // itt akkor tartunk ha elcsúszott a generation és a token az az jwt lopás gyanúja áll fenn
            // todo: orbitális exception, minden a userIdhoz tartozó refresh törlése
            return currentJwt;
        }
    }

    public RefreshEntity onFullLogin(UserEntity user) {
        RefreshEntity refresh = new RefreshEntity();
        refresh.setUserId(user.getId());
        refresh.setGeneration(generateToken(12));
        refresh.setToken(generateToken(12));
        refresh.setCreated(Instant.now(clock));

        refreshRepository.save(refresh);
        return refresh;
    }

    private String generateToken(int length) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        String generatedString =
                random
                        .ints(leftLimit, rightLimit + 1)
                        .limit(length)
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString();

        return generatedString;
    }
}
