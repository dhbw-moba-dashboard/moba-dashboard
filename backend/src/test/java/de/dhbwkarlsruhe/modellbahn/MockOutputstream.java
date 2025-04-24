package de.dhbwkarlsruhe.modellbahn;

import de.dhbwkarlsruhe.modellbahn.moba_representation.can.CANMessage;
import org.jetbrains.annotations.NotNull;

import java.io.OutputStream;

public class MockOutputstream extends OutputStream {

    private final CANMessage expectedMessageObject;
    private CANMessage actualMessageObject;

    public MockOutputstream(CANMessage expectedMessageObject) {
        this.expectedMessageObject = expectedMessageObject;
    }

    @Override
    public void write(int b) {
        //only the write(byte[] b) method is used
    }

    @Override
    public void write(byte @NotNull [] message) {
        actualMessageObject = new CANMessage(message);
    }

    public boolean isMessageCorrect() {
        return expectedMessageObject.equals(actualMessageObject);
    }
}
