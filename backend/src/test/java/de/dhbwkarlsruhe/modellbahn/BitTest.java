package de.dhbwkarlsruhe.modellbahn;

import de.dhbwkarlsruhe.modellbahn.moba_representation.BitUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class BitTest {
    @Test
    void sequencing() {
        byte[] data = {0x55, 0x6F, 0x1C, 0x3E, 0x04};
        byte[] actualResult = BitUtilities.getBitSequence(data, 0, 2, 3, 5);
        byte[] expectedResult = {0b00010101, 0x6F, 0x1C, 0b00111100};
        Assertions.assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    void testArrayMerging() {
        byte[] data1 = {0x55, 0x6F, 0x1C, 0x3E, 0x04};
        byte[] data2 = {0x55, 0x6F, 0x04, 0x7F};
        byte[] data3 = {0x55, 0x55, 0x4A, 0x7A, 0x9, 0xA, 0xE};

        Assertions.assertArrayEquals(new byte[]{0x55, 0x6F, 0x1C, 0x3E, 0x04, 0x55, 0x6F, 0x04, 0x7F}, BitUtilities.mergeByteArrays(List.of(data1, data2)));
        Assertions.assertArrayEquals(new byte[]{0x55, 0x6F, 0x1C, 0x3E, 0x04, 0x55, 0x55, 0x4A, 0x7A, 0x9, 0xA, 0xE}, BitUtilities.mergeByteArrays(List.of(data1, data3)));
        Assertions.assertArrayEquals(new byte[]{0x55, 0x6F, 0x04, 0x7F, 0x55, 0x55, 0x4A, 0x7A, 0x9, 0xA, 0xE}, BitUtilities.mergeByteArrays(List.of(data2, data3)));
    }

    @Test
    void transforming() {
        byte[] data = {0x55, 0x6F, 0x1C, 0x3E};
        int actualResult = BitUtilities.byteArrayToInt(data);
        int expectedResult = 0x556F1C3E;
        Assertions.assertEquals(expectedResult, actualResult);
    }
}
