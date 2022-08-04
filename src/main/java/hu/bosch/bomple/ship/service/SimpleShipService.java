package hu.bosch.bomple.ship.service;

import hu.bosch.bomple.common.ResourceNotFoundException;
import hu.bosch.bomple.security.JwtFacade;
import hu.bosch.bomple.ship.ShipMapper;
import hu.bosch.bomple.ship.dto.ShipDto;
import hu.bosch.bomple.ship.model.ShipEntity;
import hu.bosch.bomple.ship.model.ShipRepository;
import liquibase.pro.packaged.O;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Profile("simple")
public class SimpleShipService implements ShipService {

    private final ShipRepository shipRepository;
    private final ShipMapper shipMapper;

    @Override
    @Secured("fetch_ship")
    public ShipDto fetch(Long id) {
        // TODO: hibernate session unwrapping
        return shipMapper.entityToDto(loadShip(id));
    }

    @Override
    @Secured("list_ships")
    public List<ShipDto> list() {
        return null;
    }

    @Override
    public ShipEntity loadShip(Long id) {
        return shipRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(ShipEntity.class, String.valueOf(id)));
    }

}
