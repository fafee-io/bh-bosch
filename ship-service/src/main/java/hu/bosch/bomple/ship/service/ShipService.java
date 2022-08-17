package hu.bosch.bomple.ship.service;

import hu.bosch.bomple.api.model.Ship;
import hu.bosch.bomple.common.ShipMessage;
import hu.bosch.bomple.ship.model.ShipEntity;

import java.util.List;

//@Transactional
public interface ShipService {

//    @Transactional
    Ship fetch(Long id);
    List<Ship> list();
    ShipEntity loadShip(Long id);
    void handleStreamMessage(ShipMessage message);

}
