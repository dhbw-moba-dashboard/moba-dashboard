package de.dhbwkarlsruhe.modellbahn;

import de.dhbwkarlsruhe.modellbahn.models.CANMessage;
import de.dhbwkarlsruhe.modellbahn.models.UnknownModel;
import de.dhbwkarlsruhe.modellbahn.schemes.CommandScheme;
import de.dhbwkarlsruhe.modellbahn.schemes.Priority;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MobaSocket
{
    private static final int PORT = 15731;
    private static final int PACKAGE_LENGHT = 13;

    private final String IP_ADDRESS_MOBA;


    public MobaSocket(CommandlineArguments args)
    {
        this.IP_ADDRESS_MOBA = args.getIpAddress();
    }

    public CANMessage handleCANInteraction(CANMessage request) throws IOException
    {
        CommandScheme command = request.getCommand();

        send(request);
        return receive(command);
    }

    public void send(CANMessage message) throws IOException
    {
        System.out.println("IP-Adresse: " + IP_ADDRESS_MOBA);
        /*
        0x0 :
         */
        try
        {
            java.net.Socket socket = new java.net.Socket(IP_ADDRESS_MOBA, PORT);
            System.out.println("Nachricht: ");
            BitUtilities.printBytes(message.toByteArray());
            System.out.println("Typ : " + message.getCommand().toString());
            socket.getOutputStream().write(message.toByteArray());
            socket.close();
        } catch (IOException e)
        {
            e.printStackTrace();
            throw e;

        }
    }

    public CANMessage receive(CommandScheme scheme) throws IOException
    {

        java.net.Socket socket = new java.net.Socket(IP_ADDRESS_MOBA, PORT);
        for (int i = 0; i < 10; i++)
        {

            byte[] buffer = new byte[PACKAGE_LENGHT];
            socket.getInputStream().read(buffer);

            CANMessage canMessage = new CANMessage(buffer);

            if (canMessage.getCommand() == scheme && canMessage.isResponse())
            {
                socket.close();
                return canMessage;
            }


        }
        return new CANMessage(Priority.BEFEHLE, CommandScheme.UNKNOWN_COMMAND, new UnknownModel(), true);

    }

}
