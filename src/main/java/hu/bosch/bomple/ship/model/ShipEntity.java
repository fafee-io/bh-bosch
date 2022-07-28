package hu.bosch.bomple.ship.model;

import hu.bosch.bomple.common.BaseEntity;
import hu.bosch.bomple.crew.model.AssignmentEntity;
import hu.bosch.bomple.crew.model.CrewEntity;
import hu.bosch.bomple.ship.enums.ShipClass;
import lombok.*;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bosch_bomple_ship")
public class ShipEntity extends BaseEntity {

    @ToString.Include
    @EqualsAndHashCode.Include
    private String name;
    @ToString.Include
    @EqualsAndHashCode.Include
    private String designation;
    @ToString.Include
    @EqualsAndHashCode.Include
    private Double weight;
    @Column(columnDefinition = "TEXT")
    private String serviceRecord;

    @Enumerated(EnumType.STRING)
    private ShipClass classification;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "x", column = @Column(name = "position_x")),
            @AttributeOverride(name = "y", column = @Column(name = "position_y")),
            @AttributeOverride(name = "z", column = @Column(name = "position_z"))
    })
    private PositionEmbeddable position;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "captain_id")
    private CrewEntity captain;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "bosch_bomple_armament", joinColumns = @JoinColumn(name = "ship_id"))
    private List<ArmamentEmbeddable> armament;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ship")
    @Where(clause = "end is null")
    private Set<AssignmentEntity> compliment;

    private Boolean active;
    private Boolean retired;

    @Formula("sqrt(position_x * position_x + position_y * position_y + position_z * position_z)")
    private Double distanceFromSol;

}
