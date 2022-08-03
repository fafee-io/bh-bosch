package hu.bosch.bomple.ship.model;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<ShipEntity, Long>, JpaSpecificationExecutor<ShipEntity> {

    @Query("SELECT s FROM ShipEntity s JOIN FETCH s.captain JOIN FETCH s.compliment c JOIN FETCH c.crew WHERE s.id = ?1")
    ShipEntity findOneFetch(Long id);

    @EntityGraph(attributePaths = {"captain", "compliment", "compliment.crew", "armament"})
    Optional<ShipEntity> findById(Long id);

//    @Query("SELECT f FROM FeatureEntity f JOIN FETCH f.collection")
//    List<FeatureEntity> findAllFetch();
}
