package com.ag.core.data.jpa.specification;

import lombok.var;
import org.hibernate.query.criteria.internal.OrderImpl;

import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

/**
 * @Author: zhengaiguo
 * @CreateDate: 2020-08-27 14:43
 */
class OrderUtils {

    static Order toJpaOrder(Root<?> root, query.Order order) {
        var expression = PathUtils.getPath(root, order.getField());
        return new OrderImpl(expression, !order.isDesc());
    }
}
