package hu.bosch.bomple;

import hu.bosch.bomple.generator.SecretService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UnitTests {

    private final SecretService sut = new SecretService();

    @Test
    public void letterGenerationLength() {
        String str = sut.generateLetters(12);

        Assertions.assertEquals(12, str.length());
    }

    @Test
    public void letterGenerationLetters() {
        String str = sut.generateLetters(12);

        Assertions.assertTrue(str.chars().allMatch(Character::isLetter));
    }
}
