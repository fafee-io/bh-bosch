package hu.bosch.bomple.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "bomple.security")
@Data
public class JwtSecurityProperties {

    private String publicKey; // JWT ellenőrzéshez használt publikus kulcs, kötelező
    // fun TODO: az ilyen propertykbe lehet validációt rakni, pl erre, hogy a publikus kulcs hiánya esetén el se próbáljon indulni az alkalmazás
    // ez szebb megoldás mint az NPE, amit egyébként okozna

    private String[] securedUrls; // Védett URLek listája (csak belépve, azaz valid JWTvel)
    private String[] unprotectedUrls; // JWT nélkül is elérhető URLek listája

    private String issuer; // az elvárt issuer a JWTben
    private String audience; // az elvárt audience a JWTben
    private int jwtLifeTimeInSeconds; // a JWT lejárati ideje

    private String privateKey; // csak az Auth modulban szükséges kitölteni
    private boolean generateKeys; // csak az Auth modulban szükséges kitölteni

}
