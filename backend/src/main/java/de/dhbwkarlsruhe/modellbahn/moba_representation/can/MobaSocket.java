package de.dhbwkarlsruhe.modellbahn.moba_representation.can;

import de.dhbwkarlsruhe.modellbahn.moba_representation.BitUtilities;
import de.dhbwkarlsruhe.modellbahn.schemes.CommandScheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

@Component
public class MobaSocket {
    private static final int PORT = 15731;
    private static final int PACKAGE_LENGTH = 13;
    private static final Logger logger = LoggerFactory.getLogger(MobaSocket.class);

    private final String ipAddressMoba;

    public MobaSocket(@Value("${moba.ip}") String ipAddressMoba) {
        this.ipAddressMoba = ipAddressMoba;
    }

    public CANMessage handleCANInteraction(CANMessage request) throws IOException {
        CommandScheme command = request.getCommand();

        send(request);
        return receive(command);
    }


    /**
     * sends a CANMessage to the MoBa
     *
     * @param message CANMessage to be sent
     * @throws IOException if the socket connection fails
     */
    public void send(CANMessage message) throws IOException {
        try (Socket socket = createSocket()) {
            byte[] messageBytes = message.toByteArray();
            String messageAsString = BitUtilities.byteArrayToHexString(messageBytes);
            logger.debug("Sending message : {}", messageAsString);
            socket.getOutputStream().write(messageBytes);
        }
    }

    /**
     * receives a CANMessage from the MoBa
     *
     * @param scheme the command scheme which is expected in the response
     * @return object representation of the response
     * @throws IOException if the socket connection fails or the Moba sends an invalid package
     */
    public CANMessage receive(CommandScheme scheme) throws IOException {
        try (Socket socket = createSocket()) {
            for (int i = 0; i < 4; i++) {
                byte[] buffer = new byte[PACKAGE_LENGTH];

                int length = socket.getInputStream().read(buffer);
                String messageAsString = BitUtilities.byteArrayToHexString(buffer);

                logger.debug("Received message : {}", messageAsString);
                if (length != PACKAGE_LENGTH) {
                    throw new InvalidPackageException("Invalid package length: " + length);
                }

                CANMessage canMessage = new CANMessage(buffer);

                if (canMessage.getCommand() == scheme && canMessage.isResponse()) {
                    return canMessage;
                }
            }
        }
        throw new InvalidPackageException("No valid package received");
    }

    protected Socket createSocket() throws IOException {
        try (Socket socket = new Socket()) {
            SocketAddress address = new InetSocketAddress(ipAddressMoba, PORT);

            socket.connect(address, 1000);
            return new Socket(ipAddressMoba, PORT);
        }
    }

    public static class InvalidPackageException extends IOException {
        public InvalidPackageException(String message) {
            super(message);
        }
    }

}

