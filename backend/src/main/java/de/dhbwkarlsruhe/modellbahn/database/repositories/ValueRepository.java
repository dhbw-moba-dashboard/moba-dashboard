package de.dhbwkarlsruhe.modellbahn.database.repositories;


import de.dhbwkarlsruhe.modellbahn.database.entities.Value;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ValueRepository extends JpaRepository<Value, Integer>
{

}
