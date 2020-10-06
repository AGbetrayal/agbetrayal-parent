package com.ag.core.data.jpa.specification;

import com.ag.core.commons.util.ArrayUtils;
import com.ag.core.commons.util.CollectionUtils;
import org.springframework.data.jpa.domain.Specification;
import query.Order;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings({"rawtypes", "unchecked", "serial"})
public class Criteria<T> implements Specification<T> {

    private List<Criterion> criterions = new ArrayList<>();

    private List<Criterion> havings = new ArrayList<>();

    private List<Order> orders = new ArrayList<>();

    private List<String> groupByPropertyNames;

    public List<Criterion> getCriterions() {
        return criterions;
    }

    public List<Criterion> getHavings() {
        return havings;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<String> getGroupByPropertyNames() {
        return groupByPropertyNames;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List predicates;
        Iterator iterator;
        Criterion criterion;
        if (!this.criterions.isEmpty()) {
            predicates = new ArrayList();
            iterator = this.criterions.iterator();
            while (iterator.hasNext()) {
                criterion = (Criterion) iterator.next();
                if (criterion != null) {
                    Predicate predicate = criterion.toPredicate(root, query, cb);
                    if (predicate != null) {
                        predicates.add(predicate);
                    }
                }
            }
            if (predicates.size() > 0) {
                query.where(cb.and((Predicate[]) predicates.toArray(new Predicate[0])));
            }
        }
        if (this.orders != null) {
            predicates = new ArrayList();
            iterator = this.orders.iterator();
            while (iterator.hasNext()) {
                Order order = (Order) iterator.next();
                predicates.add(OrderUtils.toJpaOrder(root, order));
            }
            query.orderBy(predicates);
        }
        if (this.groupByPropertyNames != null) {
            predicates = new ArrayList();
            iterator = this.groupByPropertyNames.iterator();

            while (iterator.hasNext()) {
                String propertyName = (String) iterator.next();
                predicates.add(PathUtils.getPath(root, propertyName));
            }

            query.groupBy(predicates);
        }
        if (!this.havings.isEmpty()) {
            predicates = new ArrayList();
            iterator = this.havings.iterator();
            while (iterator.hasNext()) {
                criterion = (Criterion) iterator.next();
                predicates.add(criterion.toPredicate(root, query, cb));
            }
            if (predicates.size() > 0) {
                query.having((Predicate[]) predicates.toArray(new Predicate[0]));
            }
        }
        return null;
    }

    public Criteria<T> add(Criterion criterion) {
        if (criterion != null) {
            this.criterions.add(criterion);
        }
        return this;
    }

    public Criteria<T> having(Criterion criterion) {
        if (criterion != null) {
            this.havings.add(criterion);
        }
        return this;
    }

    public void addOrder(Order... orders) {
        CollectionUtils.addAllNotNull(this.orders, orders);
    }

    public void addGroupBy(List<String> propertyNames) {
        this.groupByPropertyNames = propertyNames;
    }

    public void addGroupBy(String... propertyNames) {
        if (ArrayUtils.isNotEmpty(propertyNames)) {
            addGroupBy(Arrays.asList(propertyNames));
        }
    }
}
