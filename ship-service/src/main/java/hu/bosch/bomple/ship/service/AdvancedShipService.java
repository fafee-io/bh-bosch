package hu.bosch.bomple.ship.service;

import hu.bosch.bomple.api.model.Ship;
import hu.bosch.bomple.common.ShipMessage;
import hu.bosch.bomple.ship.model.ShipCustomRepository;
import hu.bosch.bomple.ship.model.ShipEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Profile("!simple")
public class AdvancedShipService implements ShipService {

    private final ShipCustomRepository customRepository;

    @Override
    public Ship fetch(Long id) {
        return customRepository.getShipDtoData(id);
    }

    @Override
    public List<Ship> list() {
        return null;
    }

    @Override
    public ShipEntity loadShip(Long id) {
        return null;
    }

    @Override
    public void handleStreamMessage(ShipMessage message) {
    }
}
