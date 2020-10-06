package com.ag.core.data.jpa.specification;

import com.ag.core.commons.query.AndOr;
import com.ag.core.commons.util.ArrayUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class LogicalExpression implements Criterion {

    @Getter
    private AndOr andOr;

    @Getter
    private Criterion[] criterions;

    @Override
    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        if (ArrayUtils.isEmpty(this.criterions)) {
            return null;
        }
        List<Predicate> predicates = new ArrayList<>();
        Criterion[] crits = criterions;
        for (Criterion criterion : crits) {
            if (criterion != null) {
                predicates.add(criterion.toPredicate(root, cq, cb));
            }
        }
        if (predicates.isEmpty()) {
            return null;
        } else {
            if (andOr == AndOr.OR) {
                return cb.or(predicates.toArray(new Predicate[0]));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        }
    }
}
