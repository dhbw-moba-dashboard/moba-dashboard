package de.dhbwkarlsruhe.modellbahn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BitTest {
    @Test
    void sequencing() {
        byte[] data = {0x55, 0x6F, 0x1C, 0x3E, 0x04};
        byte[] actualResult = BitUtilities.getBitSequence(data, 0, 2, 3, 6);
        byte[] expectedResult = {0b00010101, 0x6F, 0x1C, 0b00111100};
        Assertions.assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    void transforming() {
        byte[] data = {0x55, 0x6F, 0x1C, 0x3E};
        int actualResult = BitUtilities.byteArrayToInt(data);
        int expectedResult = 0x556F1C3E;
        Assertions.assertEquals(expectedResult, actualResult);
    }
}
