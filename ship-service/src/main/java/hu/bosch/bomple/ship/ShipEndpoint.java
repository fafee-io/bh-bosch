package hu.bosch.bomple.ship;

import hu.bosch.bomple.api.model.Ship;
import hu.bosch.bomple.ship.service.ShipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ship")
public class ShipEndpoint {

    private final ShipService shipService;

    @GetMapping("/{id}")
    public ResponseEntity<Ship> fetchShip(@PathVariable Long id) {
        return ResponseEntity.ok(shipService.fetch(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Ship>> listShips() {
        return ResponseEntity.ok(shipService.list());
    }


//
//    private final FeatureService featureService;
//
//    @GetMapping
//    public ResponseEntity<List<FeatureDto>> listShips(FeatureSearchParameters searchParameters) {
//        return ResponseEntity.ok(featureService.list(searchParameters));
//    }
//
//    @PostMapping
//    public ResponseEntity<URI> createShip() throws URISyntaxException {
//        featureService.generate();
//        return ResponseEntity.created(new URI("bla")).build();
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Void> updateShip(@PathVariable Long id) {
//
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteFeature(@PathVariable Long id) {
//        return ResponseEntity.ok().build();
//    }
}
