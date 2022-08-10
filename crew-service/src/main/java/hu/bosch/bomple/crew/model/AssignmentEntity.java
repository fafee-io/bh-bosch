package hu.bosch.bomple.crew.model;

import hu.bosch.bomple.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bosch_bomple_assignment")
public class AssignmentEntity extends BaseEntity implements Comparable<AssignmentEntity> {

    @ToString.Include
    @EqualsAndHashCode.Include
    private Instant start;
    @ToString.Include
    @EqualsAndHashCode.Include
    private Instant end;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ship_id")
//    private ShipEntity ship;

    private Long shipId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crew_id")
    private CrewEntity crew;

    @Override
    public int compareTo(AssignmentEntity o) {
        return start.compareTo(o.getStart());
    }
}
