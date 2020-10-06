package com.ag.core.data.jpa;

import com.ag.core.data.jpa.repository.BaseSimpleJpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.RepositoryMetadata;

import javax.persistence.EntityManager;

/**
 * @Author: zhengaiguo
 * @CreateDate: 2020-08-27 14:43
 */
public class BaseJpaRepositoryFactory extends JpaRepositoryFactory {

    /**
     * Creates a new {@link JpaRepositoryFactory}.
     *
     * @param entityManager must not be {@literal null}
     */
    public BaseJpaRepositoryFactory(EntityManager entityManager) {
        super(entityManager);
    }

    /**
     * @param metadata metadata
     * @return Class
     */
    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        return BaseSimpleJpaRepository.class;
    }


}
