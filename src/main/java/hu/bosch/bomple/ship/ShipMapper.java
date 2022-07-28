//package hu.bosch.bomple.ship;
//
//import hu.bosch.bomple.ship.dto.FeatureDto;
//import hu.bosch.bomple.ship.model.ShipEntity;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.ReportingPolicy;
//
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//
//@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
//public interface ShipMapper {
//
//    @Mapping(source = "masikStr", target = "valami")
//    FeatureDto entityToDto(ShipEntity entity);
//
//    default LocalDateTime map(Instant value) {
//        if (value == null) return null;
//        return LocalDateTime.ofInstant(value, ZoneId.of("Europe/Budapest"));
//    }
//
//}
