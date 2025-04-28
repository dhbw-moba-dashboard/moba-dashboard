package de.dhbwkarlsruhe.modellbahn.database.services;

import de.dhbwkarlsruhe.modellbahn.database.entities.Loc;
import de.dhbwkarlsruhe.modellbahn.database.repositories.LocRepository;
import de.dhbwkarlsruhe.modellbahn.moba_representation.interfaces.SimpleLocValue;
import de.dhbwkarlsruhe.modellbahn.moba_representation.loc.LocHandler;
import de.dhbwkarlsruhe.modellbahn.moba_representation.loc.LocName;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

@AllArgsConstructor
public class LocService {
    private final LocRepository locRepository;
    private final LocHandler locHandler;


    public List<LocName> getLocs() {
        return locRepository.findAll().stream().map(Loc::toModel).toList();
    }

    public LocName getLocByID(int locID) {
        return locRepository.findById(locID).orElseThrow().toModel();
    }

    public boolean setValue(SimpleLocValue value) {
        return locHandler.setValue(value);
    }

}
