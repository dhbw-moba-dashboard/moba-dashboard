package de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer;

import java.net.Socket;

public class TCPSocket {
    private static final int SEND_PORT = 15731;
    private static final int RECEIVE_PORT = 15730;
    private static final String IP_ADDRESS_MOBA = "";
    public static void send(CANMessage message) {
        try {
            Socket socket = new Socket(IP_ADDRESS_MOBA, SEND_PORT);
            socket.getOutputStream().write(message.toByteArray());
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static CANMessage receive() {
        try {
            Socket socket = new Socket(IP_ADDRESS_MOBA, RECEIVE_PORT);
            byte[] buffer = new byte[1024];
            int length = socket.getInputStream().read(buffer);
            byte[] message = new byte[length];
            System.arraycopy(buffer, 0, message, 0, length);
            socket.close();
            return new CANMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
