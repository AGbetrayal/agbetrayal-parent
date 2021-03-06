package com.ag.core.cache.service;


import com.ag.core.service.jpa.JpaBaseService;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;

/**
 * @author zhengaiguo
 * @date 2019-01-22 11:02
 */
public interface JpaCacheService<T extends Persistable<ID>, ID extends Serializable> extends JpaBaseService<T, ID>, CacheService {

}
