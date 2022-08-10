package hu.bosch.bomple.ship.model;

import hu.bosch.bomple.api.model.Ship;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;

@Component
public class ShipCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Ship getShipDtoData(Long id) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Ship> query = cb.createQuery(Ship.class);
//        Root<ShipEntity> ship = query.from(ShipEntity.class);
//        Join<ShipEntity, CrewEntity> captain = ship.join("captain", JoinType.INNER);
////        Join<ShipEntity, AssignmentEntity> compliment = ship.join("compliment", JoinType.INNER);
////        Join<ShipEntity, ArmamentEmbeddable> armament = ship.join("armament", JoinType.INNER);
////
//        Predicate predicate = cb.equal(ship.get("id"), id);
//
//        query.select(cb.construct(
//                ShipDto.class,
//                ship.get(ShipEntity_.NAME),
//                ship.get(ShipEntity_.DESIGNATION),
//                ship.get(ShipEntity_.CLASSIFICATION),
//                ship.get(ShipEntity_.DISTANCE_FROM_SOL),
//                captain.get(CrewEntity_.FIRST_NAME),
//                captain.get(CrewEntity_.LAST_NAME),
//                captain.get(CrewEntity_.RANK)
//        )).where(predicate);

        return entityManager.createQuery(query).getSingleResult();
    }
}
