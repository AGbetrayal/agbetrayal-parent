package com.ag.core.data.jpa.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 函數查詢
 *
 * @Author: zhengaiguo
 * @CreateDate: 2020-08-27 11:40
 */
@Deprecated
public class AggregateExpression implements Criterion {

    private Projection projection;

    public Projection getProjection() {
        return projection;
    }

    public AggregateExpression(Projection projection) {
        this.projection = projection;
    }

    @Override
    @SuppressWarnings({"rawtypes"})
    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        projection.toExpression(root, cq, cb);
        return null;
    }

}
