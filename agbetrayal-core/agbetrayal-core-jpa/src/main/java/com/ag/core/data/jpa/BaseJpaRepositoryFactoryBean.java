package com.ag.core.data.jpa;

import com.ag.core.data.jpa.repository.BaseJpaRepository;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @Author: zhengaiguo
 * @CreateDate: 2020-08-27 14:43
 */
public class BaseJpaRepositoryFactoryBean<T extends BaseJpaRepository<S, ID>, S extends Persistable<ID>, ID extends Serializable> extends JpaRepositoryFactoryBean<T, S, ID> {

    /**
     * Creates a new {@link JpaRepositoryFactoryBean} for the given repository interface.
     *
     * @param repositoryInterface must not be {@literal null}.
     */
    public BaseJpaRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new BaseJpaRepositoryFactory(entityManager);
    }
}
