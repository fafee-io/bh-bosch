package hu.bosch.bomple.crew.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrewRepository extends JpaRepository<CrewEntity, Long> {

    @Query("SELECT c FROM CrewEntity c JOIN FETCH c.assignments")
    List<CrewEntity> findAllFetchAssignments();

}
