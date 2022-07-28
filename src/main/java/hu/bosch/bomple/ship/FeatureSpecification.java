package hu.bosch.bomple.ship;

import hu.bosch.bomple.ship.dto.FeatureSearchParameters;
import hu.bosch.bomple.ship.model.ShipEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class FeatureSpecification implements Specification<ShipEntity> {

    private final FeatureSearchParameters searchParameters;

    @Override
    public Predicate toPredicate(Root<ShipEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        Long id = searchParameters.getId();
        Integer lessThan = searchParameters.getLessThan();
        Integer moreThan = searchParameters.getMoreThan();
        String strLike = searchParameters.getStrLike();
        String valamiLike = searchParameters.getValamiLike();

        if (id != null) {
            predicates.add(criteriaBuilder.equal(root.get("id"), id));
        }

        if (lessThan != null) {
            predicates.add(criteriaBuilder.lessThan(root.get("number"), lessThan));
        }

        if (moreThan != null) {
            predicates.add(criteriaBuilder.greaterThan(root.get("number"), moreThan));
        }

        if (strLike != null) {
            predicates.add(criteriaBuilder.like(root.get("str"), getLike(strLike)));
        }

        if (valamiLike != null) {
            predicates.add(criteriaBuilder.like(root.get("masikStr"), getLike(valamiLike)));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    public static String getLike(String string) {
        string = string.trim()
                .replaceAll("_", "\\\\_")
                .replaceAll("%", "\\\\%")
                .replaceAll("[ ]+", "%");

        return "%".concat(string).concat("%");
    }
}
