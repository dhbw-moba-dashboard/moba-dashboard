package de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer;

import de.dhbwkarlsruhe.modellbahn.CommandlineArguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.codec.binary.Hex;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
@Component
public class TCPSocket {
    private static final int SEND_PORT = 15731;
    private static final int RECEIVE_PORT = 8888;
    private final String IP_ADDRESS_MOBA;


    public TCPSocket(CommandlineArguments commandlineArguments)  {
        this.IP_ADDRESS_MOBA = commandlineArguments.getIpAddress();
    }
    public void send(CANMessage message) throws IOException {
        System.out.println("IP-Adresse: " + IP_ADDRESS_MOBA);
        /*
        0x0 :
         */
        try {
            Socket socket = new Socket(IP_ADDRESS_MOBA, SEND_PORT);
            System.out.println("IP-Adresse: " + IP_ADDRESS_MOBA);
            printBytes(message.payload.toByteArray());
            socket.getOutputStream().write(message.payload.toByteArray());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;

        }
    }
    private void printBytes(byte[] bytes) {
        char[] hexArray = Hex.encodeHex(bytes);
        int i = 0;
        for (char c : hexArray) {
            System.out.println("0x"+c);
            i++;
        }
        System.out.println(i);
    }
    public CANMessage receive() {
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
