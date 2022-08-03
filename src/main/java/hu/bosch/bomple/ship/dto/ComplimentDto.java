package hu.bosch.bomple.ship.dto;

import hu.bosch.bomple.crew.enums.Division;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ComplimentDto {

    private LocalDateTime start;
    private LocalDateTime end;
    private String name;
    private Division division;

}
