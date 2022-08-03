package hu.bosch.bomple.crew.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.bosch.bomple.common.BaseEntity;
import hu.bosch.bomple.crew.enums.Division;
import hu.bosch.bomple.crew.enums.Rank;
import lombok.*;
import org.hibernate.annotations.SortComparator;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bosch_bomple_crew")
public class CrewEntity extends BaseEntity {

    @ToString.Include
    @EqualsAndHashCode.Include
    private String firstName;
    @ToString.Include
    @EqualsAndHashCode.Include
    private String lastName;
    @ToString.Include
    @EqualsAndHashCode.Include
    private String callSign;

    @Column(columnDefinition = "TEXT")
    private String serviceRecord;

    @Column(name = "`rank`")
    @Enumerated(EnumType.STRING)
    private Rank rank;
    @Enumerated(EnumType.STRING)
    private Division division;

    @OneToMany(mappedBy = "crew", fetch = FetchType.LAZY)
    @OrderBy("start")
    @SortNatural
    private SortedSet<AssignmentEntity> assignments;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "current_assignment_id")
//    private AssignmentEntity currentAssignment;

    @Transient
    public String getName() {
        return firstName.concat(" ").concat(lastName);
    }
}
