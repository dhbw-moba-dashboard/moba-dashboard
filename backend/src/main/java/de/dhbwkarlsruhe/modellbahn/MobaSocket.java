package de.dhbwkarlsruhe.modellbahn;

import de.dhbwkarlsruhe.modellbahn.models.CANMessage;
import de.dhbwkarlsruhe.modellbahn.models.SimpleLocFactory;
import de.dhbwkarlsruhe.modellbahn.models.SimpleLocValue;
import de.dhbwkarlsruhe.modellbahn.schemes.CommandScheme;
import de.dhbwkarlsruhe.modellbahn.schemes.LocValueScheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

@Component
public class MobaSocket
{
    private static final int PORT = 15731;
    private static final int PACKAGE_LENGTH = 13;
    private static final Logger logger = LoggerFactory.getLogger(MobaSocket.class);

    private final String ipAddressMoba;

    public MobaSocket(@Value("${moba.ip}") String ipAddressMoba)
    {
        this.ipAddressMoba = ipAddressMoba;
    }

    public CANMessage handleCANInteraction(CANMessage request) throws IOException
    {
        CommandScheme command = request.getCommand();

        send(request);
        return receive(command);
    }

    public SimpleLocValue handleSimpleCANRequest(int locID, LocValueScheme scheme) throws IOException
    {
        CANMessage request = SimpleLocFactory.createRequest(locID, scheme);

        CANMessage response = handleCANInteraction(request);
        return (SimpleLocValue) response.getPayload();
    }

    public void send(CANMessage message) throws IOException
    {
        try (Socket socket = createSocket())
        {
            byte[] messageBytes = message.toByteArray();
            String messageAsString = BitUtilities.byteArrayToHexString(messageBytes);
            logger.debug("Sending message : {}", messageAsString);
            socket.getOutputStream().write(messageBytes);
        }
    }

    public CANMessage receive(CommandScheme scheme) throws IOException
    {
        try (Socket socket = createSocket())
        {
            for (int i = 0; i < 4; i++)
            {
                byte[] buffer = new byte[PACKAGE_LENGTH];

                int length = socket.getInputStream().read(buffer);
                String messageAsString = BitUtilities.byteArrayToHexString(buffer);

                logger.debug("Received message : {}", messageAsString);
                if (length != PACKAGE_LENGTH)
                {
                    throw new InvalidPackageException("Invalid package length: " + length);
                }

                CANMessage canMessage = new CANMessage(buffer);

                if (canMessage.getCommand() == scheme && canMessage.isResponse())
                {
                    return canMessage;
                }
            }
        }
        throw new InvalidPackageException("No valid package received");
    }

    protected Socket createSocket() throws IOException
    {
        try (Socket socket = new Socket())
        {
            SocketAddress address = new InetSocketAddress(ipAddressMoba, PORT);

            socket.connect(address, 1000);
            return new Socket(ipAddressMoba, PORT);
        }
    }

    public static class InvalidPackageException extends IOException
    {
        public InvalidPackageException(String message)
        {
            super(message);
        }
    }

}

