package de.dhbwkarlsruhe.modellbahn;

import de.dhbwkarlsruhe.modellbahn.models.CANMessage;
import de.dhbwkarlsruhe.modellbahn.models.UnknownModel;
import de.dhbwkarlsruhe.modellbahn.schemes.CommandScheme;
import de.dhbwkarlsruhe.modellbahn.schemes.Priority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MobaSocket {
    private static final Logger logger = LoggerFactory.getLogger(MobaSocket.class);
    private static final int PORT = 15731;
    private static final int PACKAGE_LENGTH = 13;

    private final String ipAddressMoba;

    public MobaSocket(CommandlineArguments args) {
        this.ipAddressMoba = args.getIpAddress();
    }

    public CANMessage handleCANInteraction(CANMessage request) throws IOException {
        CommandScheme command = request.getCommand();

        send(request);
        return receive(command);
    }

    public void send(CANMessage message) throws IOException {
        try (java.net.Socket socket = new java.net.Socket(ipAddressMoba, PORT)) {
            socket.getOutputStream().write(message.toByteArray());
        }
    }

    public CANMessage receive(CommandScheme scheme) throws IOException {
        try (java.net.Socket socket = new java.net.Socket(ipAddressMoba, PORT)) {
            for (int i = 0; i < 10; i++) {
                byte[] buffer = new byte[PACKAGE_LENGTH];

                int length = socket.getInputStream().read(buffer);
                if (length != PACKAGE_LENGTH) {
                    logger.error("The CAN response isn't in the appropriate size range! The program might fail.");
                }

                CANMessage canMessage = new CANMessage(buffer);

                if (canMessage.getCommand() == scheme && canMessage.isResponse()) {
                    return canMessage;
                }
            }
        }
        return new CANMessage(Priority.BEFEHLE, CommandScheme.UNKNOWN_COMMAND, new UnknownModel(), true);
    }
}
