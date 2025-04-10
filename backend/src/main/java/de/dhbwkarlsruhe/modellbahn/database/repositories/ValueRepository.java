package de.dhbwkarlsruhe.modellbahn.database.repositories;


import de.dhbwkarlsruhe.modellbahn.database.entities.Value;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValueRepository extends JpaRepository<Value, Integer>
{

    List<Value> findAllByTypeID(int typeID);
}
