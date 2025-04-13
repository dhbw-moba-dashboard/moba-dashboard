package de.dhbwkarlsruhe.modellbahn.database.services;

import de.dhbwkarlsruhe.modellbahn.database.entities.Loc;
import de.dhbwkarlsruhe.modellbahn.database.repositories.LocRepository;
import de.dhbwkarlsruhe.modellbahn.models.LocName;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

@AllArgsConstructor
public class LocService
{
    private final LocRepository locRepository;

    public List<LocName> getLocs()
    {
        return locRepository.findAll().stream().map(Loc::toModel).toList();
    }

    public LocName getLocByID(int locID)
    {
        return locRepository.findById(locID).orElseThrow().toModel();
    }

}
