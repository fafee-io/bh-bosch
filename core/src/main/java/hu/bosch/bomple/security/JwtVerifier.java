package hu.bosch.bomple.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

import java.security.PublicKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@RequiredArgsConstructor
public class JwtVerifier {

    private final PublicKey publicKey;
    private final Integer lifeTimeInSeconds;

    public Claims verifyAndUnpackJwt(String jwt){
        Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(publicKey).build().parseClaimsJws(jwt);
        Date issuedAt = claims.getBody().getIssuedAt();
        Instant instant = Instant.ofEpochMilli(issuedAt.getTime());
        Instant expiration = instant.plus(lifeTimeInSeconds, ChronoUnit.SECONDS);
        Instant now = Instant.now();

        if (expiration.isBefore(now)) {
            throw new ExpiredJwtException("Lejárt", claims.getBody());
        }

        return claims.getBody();
    }

}
