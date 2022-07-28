package hu.bosch.bomple.ship.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface ShipRepository extends JpaRepository<ShipEntity, Long>, JpaSpecificationExecutor<ShipEntity> {

    List<ShipEntity> findAllByCreatedAfter(Instant after);
//
//    @Query("SELECT f FROM FeatureEntity f JOIN FETCH f.collection")
//    List<FeatureEntity> findAllFetch();
}
