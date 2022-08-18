package hu.bosch.bomple;

import hu.bosch.bomple.api.model.Ship;
import hu.bosch.bomple.ship.ShipMapper;
import hu.bosch.bomple.ship.ShipMapperImpl;
import hu.bosch.bomple.ship.model.ShipEntity;
import hu.bosch.bomple.ship.model.ShipRepository;
import hu.bosch.bomple.ship.service.ShipService;
import hu.bosch.bomple.ship.service.SimpleShipService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(SpringExtension.class)
public class MockTests {

    @Mock
    private ShipRepository shipRepository;
    private ShipMapper shipMapper = new ShipMapperImpl();

    @Test
    public void mock() {
        ShipEntity ship = ShipEntity.builder().name("Orville").designation("NCC-1701").weight(500d).build();

        Mockito.when(shipRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(ship));

        SimpleShipService sut = new SimpleShipService(shipRepository, shipMapper);
        Ship dto = sut.fetch(24L);

        Assertions.assertEquals("Orville", dto.getName());
        Assertions.assertEquals("NCC-1701", dto.getDesignation());
    }
}
