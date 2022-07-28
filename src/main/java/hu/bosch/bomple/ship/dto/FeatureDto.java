package hu.bosch.bomple.ship.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FeatureDto {

    private Long id;
    private LocalDateTime created;
    private String str;
    private String valami;
    private Integer number;

}
