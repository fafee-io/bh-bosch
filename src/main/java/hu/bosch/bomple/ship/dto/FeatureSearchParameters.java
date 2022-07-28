package hu.bosch.bomple.ship.dto;

import lombok.Data;

@Data
public class FeatureSearchParameters {

    private Long id;
    private String strLike;
    private String valamiLike;
    private Integer lessThan;
    private Integer moreThan;

}
