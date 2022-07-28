package hu.bosch.bomple.ship;

import hu.bosch.bomple.ship.dto.FeatureDto;
import hu.bosch.bomple.ship.dto.FeatureSearchParameters;
import hu.bosch.bomple.ship.model.ShipEntity;
import hu.bosch.bomple.ship.model.ShipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeatureService {

//    private final ShipRepository shipRepository;
//    private final ShipMapper shipMapper;
//    private final SecureRandom random = new SecureRandom();
//
//    public List<FeatureDto> list(FeatureSearchParameters searchParameters) {
//        return shipRepository.findAll(new FeatureSpecification(searchParameters)).stream().map(shipMapper::entityToDto).collect(Collectors.toList());
//    }
//
//    public void generate() {
//        List<ShipEntity> entities = new ArrayList<>();
//        for (int i = 0; i < 1000; ++i) {
//            ShipEntity entity = new ShipEntity();
//            entity.setStr(generateSecret(24));
//            entity.setMasikStr(generateSecret(155));
//            entity.setNumber(random.nextInt());
//            entities.add(entity);
//        }
//        shipRepository.saveAll(entities);
//    }
//
//    private String generateSecret(int length) {
//        int leftLimit = 48; // numeral '0'
//        int rightLimit = 122; // letter 'z'
//
//        String generatedString =
//                this.random
//                        .ints(leftLimit, rightLimit + 1)
//                        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
//                        .limit(length)
//                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
//                        .toString();
//
//        return generatedString;
//    }

//    public Long save() {
//        FeatureEntity entity = new FeatureEntity();
//        //setterek
//
//        entity = featureRepository.save(entity);
//
//        return entity.getId();
//    }
//
//    public FeatureEntity update(Long id) {
//        FeatureEntity entity = featureRepository.findById(id).get();
//
//        entity.setStr("valami");
//        for() {
//            entity.getCollection();
//        }
//        entity = featureRepository.save(entity);
//    }
}
