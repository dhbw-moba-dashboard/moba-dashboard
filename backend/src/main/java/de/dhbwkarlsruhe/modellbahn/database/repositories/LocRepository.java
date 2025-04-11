package de.dhbwkarlsruhe.modellbahn.database.repositories;

import de.dhbwkarlsruhe.modellbahn.database.entities.Loc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocRepository extends JpaRepository<Loc,Integer >
{
}
