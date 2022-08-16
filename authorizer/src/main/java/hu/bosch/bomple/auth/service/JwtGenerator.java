package hu.bosch.bomple.auth.service;

import hu.bosch.bomple.auth.model.RefreshEntity;
import hu.bosch.bomple.auth.model.UserEntity;
import hu.bosch.bomple.security.JwtSecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

import java.security.PrivateKey;
import java.time.Instant;
import java.util.Date;

@RequiredArgsConstructor
public class JwtGenerator {

    private final JwtSecurityProperties properties;
    private final PrivateKey privateKey;

    public String generateJwtToken(UserEntity user, RefreshEntity refresh){
        JwtBuilder jwtBuilder = Jwts.builder();

        jwtBuilder.setAudience(properties.getAudience());
        jwtBuilder.setIssuer(properties.getIssuer());
        jwtBuilder.setIssuedAt(Date.from(Instant.now()));
//        jwtBuilder.setExpiration(Date.from(Instant.now()));
        jwtBuilder.setSubject(user.getId().toString());
        jwtBuilder.claim("username", user.getUsername());
        jwtBuilder.claim("rfshg", refresh.getGeneration());
        jwtBuilder.claim("rfsht", refresh.getToken());
//        jwtBuilder.claim("email", user.getEmail());
//        jwtBuilder.claim("role", user.getIsAdmin() ? "admin" : "user");
        jwtBuilder.claim("role", "fetch_ship"); // TODO értelmes jogsi összeszedés

        jwtBuilder.signWith(privateKey, SignatureAlgorithm.ES256);

        return jwtBuilder.compact();
    }

    public String copyJwt(Claims claims, String token) {
        JwtBuilder jwtBuilder = Jwts.builder();

        jwtBuilder.setAudience(claims.getAudience());
        jwtBuilder.setIssuer(claims.getIssuer());
        jwtBuilder.setIssuedAt(Date.from(Instant.now()));
//        jwtBuilder.setExpiration(Date.from(Instant.now()));
        jwtBuilder.setSubject(claims.getSubject());
        jwtBuilder.claim("username", claims.get("username"));
        jwtBuilder.claim("rfshg", claims.get("rfshg"));
        jwtBuilder.claim("rfsht", token);
//        jwtBuilder.claim("email", user.getEmail());
//        jwtBuilder.claim("role", user.getIsAdmin() ? "admin" : "user");
        jwtBuilder.claim("role", "fetch_ship"); // TODO adott esetben jogsi frissítés

        jwtBuilder.signWith(privateKey, SignatureAlgorithm.ES256);

        return jwtBuilder.compact();
    }
}
