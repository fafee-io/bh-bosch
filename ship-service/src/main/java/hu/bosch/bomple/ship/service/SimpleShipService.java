package hu.bosch.bomple.ship.service;

import hu.bosch.bomple.api.model.Ship;
import hu.bosch.bomple.aspect.Timed;
import hu.bosch.bomple.common.NotYetImplementedException;
import hu.bosch.bomple.common.ResourceNotFoundException;
import hu.bosch.bomple.common.ShipMessage;
import hu.bosch.bomple.ship.ShipMapper;
import hu.bosch.bomple.ship.model.ShipEntity;
import hu.bosch.bomple.ship.model.ShipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Profile("simple")
public class SimpleShipService implements ShipService {

    private final ShipRepository shipRepository;
    private final ShipMapper shipMapper;

    @Override
    @Secured("fetch_ship")
    @Timed
    public Ship fetch(Long id) {
        // TODO: hibernate session unwrapping
        return shipMapper.entityToDto(loadShip(id));
    }

    @Override
//    @Secured("list_ships")
    public List<Ship> list() {
        throw new NotYetImplementedException("listShips");
    }

    @Override
    public ShipEntity loadShip(Long id) {
        return shipRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(ShipEntity.class, String.valueOf(id)));
    }

    @Override
    public void handleStreamMessage(ShipMessage message) {
        ShipEntity newShip = new ShipEntity();
        newShip.setName(message.getName());
        newShip.setDesignation(message.getDesignation());
        newShip.setWeight(message.getWeight());

        shipRepository.save(newShip);
    }

}
