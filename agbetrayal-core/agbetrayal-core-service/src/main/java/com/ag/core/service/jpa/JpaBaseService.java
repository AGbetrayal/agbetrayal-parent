package com.ag.core.service.jpa;

import com.ag.core.service.BaseService;
import org.springframework.data.domain.Persistable;
import query.Order;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhengaiguo
 * @date 2018-10-10 12:58
 */
public interface JpaBaseService<T extends Persistable<ID>, ID extends Serializable> extends BaseService<T, ID>, JpaSelectService<T, ID> {

    List<T> findAll(T t, Order... orders);

}
