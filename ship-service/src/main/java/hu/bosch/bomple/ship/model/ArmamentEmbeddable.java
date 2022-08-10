package hu.bosch.bomple.ship.model;

import hu.bosch.bomple.ship.enums.ArmamentType;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Embeddable
public class ArmamentEmbeddable {

    @Enumerated(EnumType.STRING)
    private ArmamentType type;
    private String name;
    private Integer quantity;
    private Double yield;

}
