package RP.W8.A4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class normDensitiyClassTest {
    @Test
    void testNormDensitiy() {
        assertEquals(0.3989, normDensitiyClass.normDensitiy(0, 0, 1));
    }
}
