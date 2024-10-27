package de.dhbwkarlsruhe.modellbahn;

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
}
