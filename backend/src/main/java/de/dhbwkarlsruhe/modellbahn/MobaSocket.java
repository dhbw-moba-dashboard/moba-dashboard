package de.dhbwkarlsruhe.modellbahn;

import de.dhbwkarlsruhe.modellbahn.Models.CANMessage;
import de.dhbwkarlsruhe.modellbahn.schemes.CommandScheme;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class MobaSocket
{
    private static final int SEND_PORT = 15731;
    private static final int RECEIVE_PORT = 15730;

    private final String IP_ADDRESS_MOBA;


    public MobaSocket(CommandlineArguments commandlineArguments)  {
        this.IP_ADDRESS_MOBA = commandlineArguments.getIpAddress();
    }
    public void send(CANMessage message) throws IOException {
        System.out.println("IP-Adresse: " + IP_ADDRESS_MOBA);
        /*
        0x0 :
         */
        try {
            java.net.Socket socket = new java.net.Socket(IP_ADDRESS_MOBA, SEND_PORT);
            System.out.println("IP-Adresse: " + IP_ADDRESS_MOBA);
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

            java.net.Socket socket = new java.net.Socket(IP_ADDRESS_MOBA, SEND_PORT);
            while(true){

                byte[] buffer = new byte[13];
                int length = socket.getInputStream().read(buffer);
                CANMessage canMessage = new CANMessage(buffer);
                System.out.println("Empfangene Nachricht:");
                BitUtilities.printBytes(buffer);
                System.out.println("Empfangenes command scheme:" + canMessage.getCommand().name());
                if (canMessage.getCommand() == scheme && canMessage.isResponse()) {
                    socket.close();
                    return canMessage;
                }

            }
            //return new CANMessage(new byte[]{0x00});

    }
    public void receiveStream(){
        try (Socket socket = new Socket(IP_ADDRESS_MOBA, SEND_PORT)) {

            System.out.println("Warte auf Verbindungen auf Port " + RECEIVE_PORT);

            while (true) {
                // Warten auf Verbindungen

                System.out.println("Verbindung akzeptiert: " + socket.getInetAddress());

                // Mit dem Client kommunizieren (z.B. Daten empfangen)
                InputStream in = socket.getInputStream();

                byte [] message = in.readAllBytes();
                BitUtilities.printBytes(message);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
