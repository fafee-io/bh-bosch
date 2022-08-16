package hu.bosch.bomple.config;

import hu.bosch.bomple.common.ShipMessage;
import hu.bosch.bomple.ship.service.ShipService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class RabbitStreamListener {

    /** Resulting binding: valami-in-0 **/
    @Bean
    public Consumer<ShipMessage> valami(ShipService shipService) {
        return shipService::handleStreamMessage;
    }
}
