package de.dhbwkarlsruhe.modellbahn.Models;

public class SystemModel {
    /**
     * id = 0 for all system commands because central station has id 0
     */
    private static final int id =0x0;
    public record SystemGo() implements Model
    {
        private static final int cmd = 0x01;
        private static final int id =0x0;

        @Override
        public byte[] toByteArray() {
            /*
            0x0 : Prio 0
            0x0, 0x0 : Kommando 0 da system-command
            0x0 : Antwortbit


             */
            byte[] startCommand= new byte[]{0x00,0x00,0x47,0x11,    0x05,0x00,0x00,0x00,0x00,0x01,0x00,0x00,0x00};
            //byte[] startCommand= new byte[]{0x00,0x00,0x57,0x38,    0x05,0x00,0x00,0x00,0x00,0x01,0x00,0x00,0x00};
            /*List<byte[]> src = new ArrayList<>();
            src.add(BitUtilities.intToByteArray(id,4));
            src.add(BitUtilities.intToByteArray(cmd,1));
            return BitUtilities.mergeByteArrays(src);*/
            return startCommand;
        }
    }
    public record SystemStop(int id) implements Model
    {

        @Override
        public byte[] toByteArray() {
            byte[] stopCommand = new byte[]{0x00,0x00,0x47, 0x11,0x05,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
            return stopCommand;
        }
    }
}
