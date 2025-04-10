package de.dhbwkarlsruhe.modellbahn;

import de.dhbwkarlsruhe.modellbahn.Models.CANMessage;
import de.dhbwkarlsruhe.modellbahn.Models.ModelFactory;
import de.dhbwkarlsruhe.modellbahn.Models.UnknownModel;
import de.dhbwkarlsruhe.modellbahn.schemes.CommandScheme;
import de.dhbwkarlsruhe.modellbahn.schemes.Priority;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class MobaSocket
{
    private static final int PORT = 15731;

    private final String IP_ADDRESS_MOBA;


    public MobaSocket(CommandlineArguments args)  {
        this.IP_ADDRESS_MOBA = args.getIpAddress();
    }
    public CANMessage handleCANInteraction(CANMessage request) throws IOException
    {
        CommandScheme command = request.getCommand();

        send(request);
        return receive(command);
    }
    public void send(CANMessage message) throws IOException {
        System.out.println("IP-Adresse: " + IP_ADDRESS_MOBA);
        /*
        0x0 :
         */
        try {
            java.net.Socket socket = new java.net.Socket(IP_ADDRESS_MOBA, PORT);
            System.out.println("Nachricht: ");
            BitUtilities.printBytes(message.toByteArray());
            System.out.println("Typ : "+message.getCommand().toString());
            socket.getOutputStream().write(message.toByteArray());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;

        }
    }
    public CANMessage receive(CommandScheme scheme) throws IOException
    {

            java.net.Socket socket = new java.net.Socket(IP_ADDRESS_MOBA, PORT);
            int counter = 0;
            while(true){

                byte[] buffer = new byte[13];
                int length = socket.getInputStream().read(buffer);
                System.out.println("Empfangene Nachricht:");
                BitUtilities.printBytes(buffer);
                CANMessage canMessage = new CANMessage(buffer);

                System.out.println("Empfangenes command scheme:" + canMessage.getCommand().name());
                if (canMessage.getCommand() == scheme && canMessage.isResponse()) {
                    socket.close();
                    return canMessage;
                }
                counter++;
                if (counter == 10) break;

            }
            return new CANMessage(Priority.BEFEHLE, CommandScheme.UNKNOWN_COMMAND, new UnknownModel(), true);

    }

}
