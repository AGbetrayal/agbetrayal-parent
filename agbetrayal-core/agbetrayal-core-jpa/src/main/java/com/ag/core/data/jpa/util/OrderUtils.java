package com.ag.core.data.jpa.util;

import com.ag.core.commons.util.ArrayUtils;
import com.ag.core.commons.util.CollectionUtils;
import com.ag.core.commons.util.StringUtils;
import lombok.var;
import org.springframework.data.domain.Sort;
import query.Order;

import java.util.*;
import java.util.stream.Collectors;

/**
 * SQL排序工具类
 *
 * @Author: zhengaiguo
 * @CreateDate: 2020-08-27 14:43
 */
public abstract class OrderUtils {

    /**
     * 转换为Spring Data Sort
     *
     * @param orders orders
     * @return {@link Sort}
     */
    public static Sort toSort(List<Order> orders) {
        var sort = Sort.unsorted();
        if (CollectionUtils.isNotEmpty(orders)) {
            var orderList = orders.stream()
                    .filter(order -> Objects.nonNull(SqlEscapeUtils.escape(order.getField())))
                    .map(order -> new Sort.Order(order.isDesc() ? Sort.Direction.DESC : Sort.Direction.ASC, order.getField()))
                    .collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(orderList)) {
                sort = Sort.by(orderList);
            }
        }
        return sort;
    }

    /**
     * 转换为Spring Data Sort
     *
     * @param orders orders
     * @return {@link Sort}
     */
    public static Sort toSort(Order... orders) {
        return toSort(ArrayUtils.asArrayList(orders));
    }

    /**
     * Sort 转换为  Order
     *
     * @param sort {@link Sort}
     * @return 排序字段
     */
    public static List<Order> toOrderList(Sort sort) {
        List<Order> orders = new ArrayList<>();
        Iterator<Sort.Order> iterator = sort.iterator();
        iterator.forEachRemaining(item -> orders.add(new Order(item.getProperty(), item.isDescending())));
        return orders;
    }

    public static String toOrderSql(Collection<Order> orders) {
        StringBuilder orderSql = new StringBuilder();
        if (CollectionUtils.isNotEmpty(orders)) {
            int index = 0;
            for (var order : orders) {
                if (StringUtils.isNotEmpty(SqlEscapeUtils.escape(order.getField()))) {
                    if (index++ > 0) {
                        orderSql.append(StringUtils.COMMA_SEPARATE);
                    }
                    orderSql.append(order.toString());
                }
            }
        }
        return StringUtils.isEmpty(orderSql.toString()) ? StringUtils.EMPTY : orderSql.insert(0, " ORDER BY ").toString();
    }
}
