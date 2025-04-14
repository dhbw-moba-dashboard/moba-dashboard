package de.dhbwkarlsruhe.modellbahn.database.repositories;


import de.dhbwkarlsruhe.modellbahn.database.entities.Type;
import de.dhbwkarlsruhe.modellbahn.database.entities.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValueRepository extends JpaRepository<Value, Integer>
{

    /**
     * @param start  older than starttime
     * @param end    younger than endtime
     * @param typeID kind of values e.g. speed, direction
     * @param locID  specific locomotive
     * @return all values in specific timespan
     */
    @Query("SELECT v  from Value v where v.timeStamp between ?1 and ?2 and v.type = ?3 and v.loc = ?4")
    List<Value> findValueInRange(long start, long end, Type typeID, int locID);

    /**
     * @param number number of entries
     * @param typeID kind of values e.g. speed, direction
     * @param locID  specific locomotive
     * @return the last n values
     */
    @Query("SELECT v  from Value v where v.type = ?1 and v.loc = ?2 order by v.timeStamp asc limit ?3")
    List<Value> findNumberOfValues(Type typeID, int locID, int number);
}
