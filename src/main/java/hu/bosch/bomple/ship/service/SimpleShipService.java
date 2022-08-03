package hu.bosch.bomple.ship.service;

import hu.bosch.bomple.common.ResourceNotFoundException;
import hu.bosch.bomple.ship.ShipMapper;
import hu.bosch.bomple.ship.dto.ShipDto;
import hu.bosch.bomple.ship.model.ShipEntity;
import hu.bosch.bomple.ship.model.ShipRepository;
import liquibase.pro.packaged.O;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Profile("simple")
public class SimpleShipService implements ShipService {

    private final ShipRepository shipRepository;
    private final ShipMapper shipMapper;

    @Override
    public ShipDto fetch(Long id) {
        return shipMapper.entityToDto(loadShip(id));
    }

    @Override
    public List<ShipDto> list() {
        return null;
    }

    @Override
    public ShipEntity loadShip(Long id) {
        return shipRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(ShipEntity.class, String.valueOf(id)));
    }

}
