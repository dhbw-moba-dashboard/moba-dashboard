package de.dhbwkarlsruhe.modellbahn.database.services;

import de.dhbwkarlsruhe.modellbahn.Models.LocName;
import de.dhbwkarlsruhe.modellbahn.database.entities.Loc;
import de.dhbwkarlsruhe.modellbahn.database.repositories.LocRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

@AllArgsConstructor
public class LocService
{
    private final LocRepository locRepository;
    public List<LocName> getLocs(){
        return locRepository.findAll().stream().map(Loc::toModel).toList();
    }
    public void addLoc(LocName locName){
        Loc loc = new Loc(locName.locID(), locName.Name());

        locRepository.save(loc);
    }
    public void deleteLoc(int locID){
        locRepository.deleteById(locID);
    }
}
