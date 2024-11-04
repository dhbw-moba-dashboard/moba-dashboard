package de.dhbwkarlsruhe.modellbahn;

import java.util.ArrayList;
import java.util.List;

public class BitUtilities {
    /**
     * This method returns a byte array containing the bits from startByte:startBit to lastByte:lastBit
     * @param data the byte array to extract the bits from
     * @param startByte the byte where the sequence starts
     * @param startBit the bit in the start byte where the sequence starts
     * @param lastByte the byte where the sequence ends
     * @param lastBit the bit in the last byte where the sequence ends
     * @return a byte array containing the bits from startByte:startBit to lastByte:lastBit
     */
    public static byte []getBitSequence(byte [] data ,int startByte, int startBit, int lastByte,int lastBit){
        byte []result = new byte[lastByte-startByte+1];
        for (int i = startByte; i <= lastByte; i++) {
           if (i == startByte) {
               result[i - startByte] = (byte) (data[i] & (0xFF >> startBit));
           } else if (i==lastByte){
               result[i - startByte] = (byte) (data[i] & (0xFF << (8-lastBit)));
           } else {
               result[i - startByte] = data[i];

           }
        }
        return result;
    }
    /**
     * This method converts a byte array to an integer
     * @param data the byte array to convert
     * @return the integer representation of the byte array
     */
    public static int byteArrayToInt(byte []data){
        int result = 0;
        for (byte datum : data) {
            result = result << 8;
            result = result | (datum & 0xFF);
        }
        return result;
    }
    /**
     * This method returns an integer representation of the bits from startByte:startBit to lastByte:lastBit
     * @param data the byte array to extract the bits from
     * @param startByte the byte where the sequence starts
     * @param startBit the bit in the start byte where the sequence starts
     * @param lastByte the byte where the sequence ends
     * @param lastBit the bit in the last byte where the sequence ends
     * @return an integer representation of the bits from startByte:startBit to lastByte:lastBit
     */
    public static int transformBitSequenceToInt(byte [] data ,int startByte, int startBit, int lastByte,int lastBit){
        byte []result = getBitSequence(data,startByte,startBit,lastByte,lastBit);
        return byteArrayToInt(result);
    }
    /**
     * This method converts an integer to a byte array
     * @param value the integer to convert
     * @return the byte array representation of the integer
     */
    public static byte[] intToByteArray(int value) {
        int length = getByteArrayLength(value);
        byte[] result = new byte[length];
        for (int i = 0; i < result.length; i++) {
            //retrieves the least significant byte
            // and writes it to the result array starting from the end
            result[(result.length-1) - i] = (byte) (value & 0xFF);
            //shifts the value 8 bits to the right
            value = value >> 8;
        }
        return result;
    }
    /**
     * This method returns the number of bytes needed to represent the integer
     * @param value the integer to check
     * @return the number of bytes needed to represent the integer
     */
    private static int getByteArrayLength(int value){
        int length = 0;
        while (value > 0){
            value = value >> 8;
            length++;
        }
        return length;
    }
    public static byte [] mergeByteArrays(List<byte []> list, int length) {
        byte[] result = new byte[length];
        int destinationPosition = 0;
        for(byte [] element: list){
            System.arraycopy(element,0,result,destinationPosition,element.length);
            destinationPosition+=element.length;
        }
        return result;
    }
}
