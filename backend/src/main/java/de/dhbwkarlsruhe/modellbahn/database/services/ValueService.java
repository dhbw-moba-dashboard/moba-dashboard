package de.dhbwkarlsruhe.modellbahn.database.services;

import com.google.gson.Gson;
import de.dhbwkarlsruhe.modellbahn.MobaSocket;
import de.dhbwkarlsruhe.modellbahn.Models.*;
import de.dhbwkarlsruhe.modellbahn.database.entities.Loc;
import de.dhbwkarlsruhe.modellbahn.database.entities.Value;
import de.dhbwkarlsruhe.modellbahn.database.repositories.ValueRepository;
import de.dhbwkarlsruhe.modellbahn.schemes.LocValueScheme;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class ValueService
{
    private final ValueRepository valueRepository;
    private final LocService locService;
    private final MobaSocket socket;
    public void addValue(SimpleLocValue model){
        Value value = Value.createValue(model);
        valueRepository.save(value);
    }

    @Scheduled(cron = "0 * * * * *")
    public void checkAndSaveValues() {
        List<Integer> locIDs = locService.getLocs().stream().map(LocName::locID).toList();
        System.out.println("Checking Locs");
        for (int locID : locIDs) {

            for (LocValueScheme scheme : LocValueScheme.values()){
                SimpleLocValue value = handleCAN(locID, scheme);
                if (value instanceof UnknownModel){
                    continue;
                }
                addValue(value);
            }
        }
    }
    public SimpleLocValue handleCAN(int locID, LocValueScheme scheme){

        CANMessage request = SimpleLocFactory.createRequest(locID, scheme);
        try{
            CANMessage response = socket.handleCANInteraction(request);
            if (response.getPayload() instanceof SimpleLocValue value){
                return value;
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }

        return new UnknownModel();
    }
   public List<Value> getLocValuesByScheme(LocValueScheme scheme, long start, long end, int locID){
        return valueRepository.findValueInRange(start, end, scheme.ordinal(), locID);
   }
   public List<Value> getLocValuesByScheme(LocValueScheme scheme,int locID, int number){
        return valueRepository.findNumberOfValues(scheme.ordinal(),locID, number);
   }

}
