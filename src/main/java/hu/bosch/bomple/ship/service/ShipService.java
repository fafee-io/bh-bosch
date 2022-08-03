package hu.bosch.bomple.ship.service;

import hu.bosch.bomple.ship.dto.ShipDto;
import hu.bosch.bomple.ship.model.ShipEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Transactional
public interface ShipService {

//    @Transactional
    ShipDto fetch(Long id);
    List<ShipDto> list();
    ShipEntity loadShip(Long id);

}
