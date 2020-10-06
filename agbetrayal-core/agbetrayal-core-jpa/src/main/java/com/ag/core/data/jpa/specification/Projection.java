package com.ag.core.data.jpa.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

@Deprecated
public interface Projection {

    @SuppressWarnings("rawtypes")
    Expression toExpression(Root<?> root, CriteriaQuery<?> cq, CriteriaBuilder cb);

}
