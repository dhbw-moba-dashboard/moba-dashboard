package de.dhbwkarlsruhe.modellbahn;

import de.dhbwkarlsruhe.modellbahn.Models.CANMessage;
import de.dhbwkarlsruhe.modellbahn.schemes.CommandScheme;
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
    public void send(CANMessage message) throws IOException {
        System.out.println("IP-Adresse: " + IP_ADDRESS_MOBA);
        /*
        0x0 :
         */
        try {
            java.net.Socket socket = new java.net.Socket(IP_ADDRESS_MOBA, PORT);

            BitUtilities.printBytes(message.toByteArray());
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
            while(true){

                byte[] buffer = new byte[13];
                CANMessage canMessage = new CANMessage(buffer);
                System.out.println("Empfangene Nachricht:");
                BitUtilities.printBytes(buffer);
                System.out.println("Empfangenes command scheme:" + canMessage.getCommand().name());
                if (canMessage.getCommand() == scheme && canMessage.isResponse()) {
                    socket.close();
                    return canMessage;
                }

            }

    }

}
