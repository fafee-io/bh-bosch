package hu.bosch.bomple.ship.dto;

import hu.bosch.bomple.crew.enums.Rank;
import hu.bosch.bomple.ship.enums.ShipClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipDto {

    private String name;
    private String designation;
    private String classification;

    private Double distanceFromSol;

    private List<ArmamentDto> armament;
    private CaptainDto captain;

    private List<ComplimentDto> compliment;
    private Integer personnelCount;

    public ShipDto(String name, String designation, ShipClass classification, Double distanceFromSol, String firstName, String lastName, Rank rank) {
        this.name = name;
        this.designation = designation;
        this.classification = classification.name();
        this.distanceFromSol = distanceFromSol;
        this.captain = new CaptainDto(firstName.concat(" ").concat(lastName), rank);
    }
}
