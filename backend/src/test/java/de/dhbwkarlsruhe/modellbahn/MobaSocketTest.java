package de.dhbwkarlsruhe.modellbahn;

import de.dhbwkarlsruhe.modellbahn.moba_representation.BitUtilities;
import de.dhbwkarlsruhe.modellbahn.moba_representation.can.CANMessage;
import de.dhbwkarlsruhe.modellbahn.moba_representation.can.MobaSocket;
import de.dhbwkarlsruhe.modellbahn.moba_representation.interfaces.SimpleLocFactory;
import de.dhbwkarlsruhe.modellbahn.moba_representation.interfaces.SimpleLocValue;
import de.dhbwkarlsruhe.modellbahn.moba_representation.loc.LocSpeed;
import de.dhbwkarlsruhe.modellbahn.schemes.CommandScheme;
import de.dhbwkarlsruhe.modellbahn.schemes.LocValueScheme;
import de.dhbwkarlsruhe.modellbahn.schemes.Priority;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import static org.mockito.Mockito.mock;

class MobaSocketTest {

    private final Socket socket = mock(Socket.class);
    private final MobaSocket mobaSocket = new MobaSocket("") {
        @Override
        public Socket createSocket() {
            return socket;
        }
    };

    @BeforeEach
    void setupSocketStream() throws IOException {
        CANMessage toBeIgnored = new CANMessage(Priority.COMMAND, CommandScheme.LOCOMOTIVE_SPEED, new LocSpeed(16289, -1), false);
        CANMessage answer = new CANMessage(Priority.COMMAND, CommandScheme.LOCOMOTIVE_SPEED, new LocSpeed(16289, 200), true);
        byte[] firstMessage = toBeIgnored.toByteArray();
        byte[] secondMesage = answer.toByteArray();
        byte[] expectedMessage = BitUtilities.mergeByteArrays(List.of(firstMessage, secondMesage));
        ByteArrayInputStream stream = new ByteArrayInputStream(expectedMessage);
        Mockito.when(socket.getInputStream()).thenReturn(stream);
    }

    @Test
    void receiveMessagesHappyPath() throws IOException {


        CANMessage answer = new CANMessage(Priority.COMMAND, CommandScheme.LOCOMOTIVE_SPEED, new LocSpeed(16289, 200), true);
        CANMessage response = mobaSocket.receive(CommandScheme.LOCOMOTIVE_SPEED);
        Assertions.assertEquals(response, answer);
    }

    @Test
    void receiveMessageWrongCommand() {
        Assertions.assertThrows(MobaSocket.InvalidPackageException.class, () -> mobaSocket.receive(CommandScheme.LOCOMOTIVE_DIRECTION));
    }

    @Test
    void receiveMessageWrongPackageSize() throws IOException {
        byte[] packages = new byte[]{0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
        ByteArrayInputStream stream = new ByteArrayInputStream(packages);

        Mockito.when(socket.getInputStream()).thenReturn(stream);
        Assertions.assertThrows(MobaSocket.InvalidPackageException.class, () -> mobaSocket.receive(CommandScheme.LOCOMOTIVE_DIRECTION));
    }

    @Test
    void handleSimpleCANRequest() throws IOException {
        SimpleLocValue expectedAnswer = new LocSpeed(16289, 200);
        CANMessage request = new CANMessage(Priority.COMMAND, CommandScheme.LOCOMOTIVE_SPEED, new LocSpeed(16289, -1), false);
        MockOutputstream mockOutputstream = new MockOutputstream(request);
        Mockito.when(socket.getOutputStream()).thenReturn(mockOutputstream);
        CANMessage message = SimpleLocFactory.createRequest(16289, LocValueScheme.SPEED);
        CANMessage response = mobaSocket.handleCANInteraction(message);
        SimpleLocValue value = (SimpleLocValue) response.getPayload();
        Assertions.assertTrue(mockOutputstream.isMessageCorrect());
        Assertions.assertEquals(expectedAnswer, value);
    }
}
