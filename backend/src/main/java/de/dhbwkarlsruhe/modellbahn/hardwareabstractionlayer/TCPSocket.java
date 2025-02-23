package de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer;

import de.dhbwkarlsruhe.modellbahn.CommandlineArguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.Socket;
@Component
public class TCPSocket {
    private static final int SEND_PORT = 15731;
    private static final int RECEIVE_PORT = 15730;
    private final String IP_ADDRESS_MOBA;


    public TCPSocket(CommandlineArguments commandlineArguments) {
        this.IP_ADDRESS_MOBA = commandlineArguments.getIpAddress();
    }
    public void send(CANMessage message) {
        System.out.println("IP-Adresse: " + IP_ADDRESS_MOBA);
        try {
            Socket socket = new Socket(IP_ADDRESS_MOBA, SEND_PORT);
            System.out.println("IP-Adresse: " + IP_ADDRESS_MOBA);
            socket.getOutputStream().write(message.toByteArray());
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
