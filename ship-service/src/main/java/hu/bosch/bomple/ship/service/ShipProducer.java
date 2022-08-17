package hu.bosch.bomple.ship.service;

import hu.bosch.bomple.aspect.Timed;
import hu.bosch.bomple.common.ShipMessage;
import hu.bosch.bomple.generator.SecretService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ShipProducer {

    private final StreamBridge streamBridge;
    private final SecretService secretService;

//    @Timed
    public void createShip() {
        ShipMessage request = new ShipMessage();

        request.setName(secretService.generateLetters(12));
        request.setDesignation(secretService.generateLetters(4));
        request.setWeight(secretService.nextDouble(10d, 100000d));

        streamBridge.send("valami-out-0", request);
    }

}
