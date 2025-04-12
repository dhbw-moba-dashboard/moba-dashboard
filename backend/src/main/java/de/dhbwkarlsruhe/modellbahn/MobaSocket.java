package de.dhbwkarlsruhe.modellbahn;

import de.dhbwkarlsruhe.modellbahn.models.CANMessage;
import de.dhbwkarlsruhe.modellbahn.models.SimpleLocFactory;
import de.dhbwkarlsruhe.modellbahn.models.SimpleLocValue;
import de.dhbwkarlsruhe.modellbahn.models.UnknownModel;
import de.dhbwkarlsruhe.modellbahn.schemes.CommandScheme;
import de.dhbwkarlsruhe.modellbahn.schemes.LocValueScheme;
import de.dhbwkarlsruhe.modellbahn.schemes.Priority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;

@Component
public class MobaSocket
{
	private static final Logger logger = LoggerFactory.getLogger(MobaSocket.class);
	private static final int PORT = 15731;
	private static final int PACKAGE_LENGTH = 13;

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

	public SimpleLocValue handleCAN(int locID, LocValueScheme scheme) throws SocketTimeoutException
	{
		CANMessage request = SimpleLocFactory.createRequest(locID, scheme);
		try
		{
			CANMessage response = handleCANInteraction(request);
			if (response.getPayload() instanceof SimpleLocValue value)
			{
				return value;
			}
		} catch (SocketTimeoutException connectException)
		{
			throw connectException;
		} catch (IOException ioException)
		{
			logger.error("Error when trying to send CAN request.", ioException);
		}

		return new UnknownModel();
	}

	public void send(CANMessage message) throws IOException
	{
		try (Socket socket = createSocket())
		{
			socket.getOutputStream().write(message.toByteArray());
		}
	}

	public CANMessage receive(CommandScheme scheme) throws IOException
	{
		try (Socket socket = createSocket())
		{
			for (int i = 0; i < 10; i++)
			{
				byte[] buffer = new byte[PACKAGE_LENGTH];

				int length = socket.getInputStream().read(buffer);
				if (length != PACKAGE_LENGTH)
				{
					logger.error("The CAN response isn't in the appropriate size range! The program might fail.");
				}

				CANMessage canMessage = new CANMessage(buffer);

				if (canMessage.getCommand() == scheme && canMessage.isResponse())
				{
					return canMessage;
				}
			}
		}
		return new CANMessage(Priority.BEFEHLE, CommandScheme.UNKNOWN_COMMAND, new UnknownModel(), true);
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

}

