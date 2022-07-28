package hu.bosch.bomple.generator;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

/**
 * Responsible for any and all secret generation If you want to customize the SecureRandom(), you
 * can do so in this bean
 *
 * <p>(They're French Cyclists, Bough, and they're obstructing her Majesty's Secret Service!)
 */
@Component
public final class SecretService {

    private final SecureRandom random = new SecureRandom();

    /** [0-9][a-z][A-Z] */
    public String generateSecret(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'

        String generatedString =
                this.random
                        .ints(leftLimit, rightLimit + 1)
                        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                        .limit(length)
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString();

        return generatedString;
    }

    public int nextInt(int bound) {
        return random.nextInt(bound);
    }

    public double nextDouble(double min, double max) {
        return random.nextDouble(min, max);
    }

    public String generateLetters(int length) {
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

    public String generateSecretNumber(int length) {
        String num = "";
        for (int i = 0; i < length / 2; ++i) {
            num = num.concat(padWithZeros(this.random.nextInt(100)));
        }
        return num;
    }

    private String padWithZeros(int num) {
        if (num < 10) {
            return "0".concat(String.valueOf(num));
        } else {
            return String.valueOf(num);
        }
    }
}
