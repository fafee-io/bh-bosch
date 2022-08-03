package hu.bosch.bomple.ship.dto;

import hu.bosch.bomple.crew.enums.Rank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaptainDto {

    private String name;
    private Rank rank;

}
