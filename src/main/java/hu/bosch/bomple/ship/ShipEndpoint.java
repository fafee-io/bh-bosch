package hu.bosch.bomple.ship;

import hu.bosch.bomple.ship.dto.FeatureDto;
import hu.bosch.bomple.ship.dto.FeatureSearchParameters;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ship")
public class ShipEndpoint {
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
