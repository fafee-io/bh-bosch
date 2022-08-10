package hu.bosch.bomple.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Service
@Slf4j
public class AsymmetricKeyService {

    public KeyPair generate() {
        Security.addProvider(new BouncyCastleProvider());
        try {
            ECGenParameterSpec spec = new ECGenParameterSpec("secp256r1");
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDSA", BouncyCastleProvider.PROVIDER_NAME);
            keyPairGenerator.initialize(spec);
            KeyPair kp = keyPairGenerator.generateKeyPair();

            byte[] encPrK = kp.getPrivate().getEncoded();
            byte[] encPK = kp.getPublic().getEncoded();
            log.info("Generated keys:"); // todo: comment these lines out
            log.info("PRK:: ".concat(new String(Base64.getEncoder().encode(encPrK))));
            log.info("PK:: ".concat(new String(Base64.getEncoder().encode(encPK))));
            System.out.println("PRK:: ".concat(new String(Base64.getEncoder().encode(encPrK))));
            System.out.println("PK:: ".concat(new String(Base64.getEncoder().encode(encPK))));
            return kp;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public PrivateKey readKeys(String sprk) {
        Security.addProvider(new BouncyCastleProvider());
        try {
            PKCS8EncodedKeySpec formatPrk = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(sprk));
//            X509EncodedKeySpec formatPk = new X509EncodedKeySpec(Base64.getDecoder().decode(spk));

            KeyFactory kf = KeyFactory.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);

//            PublicKey PK = kf.generatePublic(formatPk);
            PrivateKey PRK = kf.generatePrivate(formatPrk);

            return PRK;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

}
