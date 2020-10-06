package com.ag.core.data.jpa.repository;

import com.ag.core.data.jpa.query.ConditionQueryModel;
import com.ag.core.data.jpa.util.OrderUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import page.QueryPage;
import page.SimpleQueryPage;
import query.Order;

import java.io.Serializable;
import java.util.List;


/**
 * @param <T>
 * @param <ID>
 * @Author: zhengaiguo
 * @CreateDate: 2020-08-27 14:43
 */
@NoRepositoryBean
public interface BaseJpaRepository<T extends Persistable<ID>, ID extends Serializable> extends JpaRepository<T, ID>,
        JpaSpecificationExecutor<T> {

    /**
     * 查询排序
     *
     * @param example example
     * @param orders  orders
     * @return List
     */
    default List<T> findAll(Example<T> example, Order... orders) {
        return findAll(example, OrderUtils.toSort(orders));
    }

//    T updateByIdSelective(T t);
    T updateByUserId(T t);

    default QueryPage<T> queryForPage(Specification<T> specification, List<Order> orders, int pageIndex, int pageSize) {
        Page<T> page = findAll(specification, PageRequest.of(pageIndex, pageSize, OrderUtils.toSort(orders)));
        return new SimpleQueryPage<>(page.getContent(), page.getTotalElements(), pageIndex, pageSize);
    }

    /**
     * 分页查询
     *
     * @param example   example
     * @param orders    orders
     * @param pageIndex pageIndex
     * @param pageSize  pageSize
     * @return QueryPage
     */
    default QueryPage<T> findByPage(Example<T> example, List<Order> orders, int pageIndex, int pageSize) {
        Page<T> page = findAll(example, PageRequest.of(pageIndex, pageSize, OrderUtils.toSort(orders)));
        return new SimpleQueryPage<>(page.getContent(), page.getTotalElements(), pageIndex, pageSize);
    }

    QueryPage<T> queryForPage(ConditionQueryModel queryModel);
}
