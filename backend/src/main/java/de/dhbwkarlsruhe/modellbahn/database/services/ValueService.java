package de.dhbwkarlsruhe.modellbahn.database.services;

import de.dhbwkarlsruhe.modellbahn.MobaSocket;
import de.dhbwkarlsruhe.modellbahn.database.entities.Value;
import de.dhbwkarlsruhe.modellbahn.database.repositories.ValueRepository;
import de.dhbwkarlsruhe.modellbahn.models.LocName;
import de.dhbwkarlsruhe.modellbahn.models.SimpleLocValue;
import de.dhbwkarlsruhe.modellbahn.models.UnknownModel;
import de.dhbwkarlsruhe.modellbahn.schemes.LocValueScheme;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.SocketTimeoutException;
import java.util.List;

@Service
@AllArgsConstructor
public class ValueService
{
	private static final Logger logger = LoggerFactory.getLogger(ValueService.class);
	private final ValueRepository valueRepository;
	private final LocService locService;
	private final MobaSocket socket;

	public void addValue(SimpleLocValue model)
	{
		Value value = Value.createValue(model);
		valueRepository.save(value);
	}

	@Scheduled(cron = "0 * * * * *")
	public void checkAndSaveValues()
	{
		List<Integer> locIDs = locService.getLocs().stream().map(LocName::locID).toList();
		try
		{
			for (int locID : locIDs)
			{
				iterateOverScheme(locID);
			}
		} catch (SocketTimeoutException connectException)
		{
			logger.error("Could not connect to MOBA");
		}

	}

	private void iterateOverScheme(int locID) throws SocketTimeoutException
	{
		for (LocValueScheme scheme : LocValueScheme.values())
		{
			SimpleLocValue value = socket.handleCAN(locID, scheme);
			if (value instanceof UnknownModel)
			{
				continue;
			}
			addValue(value);
		}
	}


	public List<Value> getLocValuesByScheme(LocValueScheme scheme, long start, long end, int locID)
	{
		return valueRepository.findValueInRange(start, end, scheme.ordinal(), locID);
	}

	public List<Value> getLocValuesByScheme(LocValueScheme scheme, int locID, int number)
	{
		return valueRepository.findNumberOfValues(scheme.ordinal(), locID, number);
	}
}
