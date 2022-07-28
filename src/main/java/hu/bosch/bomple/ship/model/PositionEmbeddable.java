package hu.bosch.bomple.ship.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class PositionEmbeddable {

    private Double x;
    private Double y;
    private Double z;

}
