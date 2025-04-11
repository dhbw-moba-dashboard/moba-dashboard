package de.dhbwkarlsruhe.modellbahn.database.repositories;


import de.dhbwkarlsruhe.modellbahn.database.entities.Value;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValueRepository extends JpaRepository<Value, Integer>
{

    List<Value> findAllByTypeID(int typeID);

    @Query("SELECT v  from Value v where v.TimeStamp between ?1 and ?2 and v.typeID = ?3 and v.Loc = ?4")
    List<Value> findValueInRange(long start, long end, int typeID, int locID);
}
