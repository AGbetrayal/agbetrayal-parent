package com.ag.core.data.jpa.specification;

import javax.persistence.criteria.*;

@Deprecated
public class AggregateProjection implements Projection {

    private String propertyName;

    private Aggregate aggregate;

    public String getPropertyName() {
        return propertyName;
    }

    public Aggregate getAggregate() {
        return aggregate;
    }

    public AggregateProjection(String propertyName, Aggregate aggregate) {
        this.propertyName = propertyName;
        this.aggregate = aggregate;
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Expression<?> toExpression(Root<?> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        Path path = PathUtils.getPath(root, propertyName);
        switch (this.aggregate) {
            case MAX:
                return cb.max(path);
            case MIN:
                return cb.min(path);
            case AVG:
                return cb.avg(path);
            case SUM:
                return cb.sum(path);
            default:
                return null;
        }
    }
}
