package com.ag.core.data.jpa.domain;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;
import org.springframework.util.ClassUtils;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description: 描述
 *  使用雪花id 算法生成id
 * @Author: zhengaiguo
 * @CreateDate: 2020-08-27 14:32
 */
@MappedSuperclass
//@JsonFilter(value = JsonUtils.IGNORE_ENTITY_SERIALIZE_FIELD_FILTER_ID)
@JsonFilter(value = "fieldFilter")
public abstract class AbstractSnowflakeIdPersistable implements Persistable<Long>, Serializable {


    /**
     * id 生成策略,strategy 的值可以为 {@link org.hibernate.id.factory.internal.DefaultIdentifierGeneratorFactory} 中注册的key，也可以为 class 的全类名
     *
     * @see org.hibernate.id.factory.internal.DefaultIdentifierGeneratorFactory
     */
    @Id
    @GeneratedValue(generator = "system-snowflake-id")
    @GenericGenerator(name = "system-snowflake-id", strategy = "org.hibernate.id.SnowflakeIdentifierGenerator")
    @Column(name = "id", updatable = false)
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Transient // DATAJPA-622
    @JsonIgnore
    public final boolean isNew() {
        return null == id;
    }

    @Override
    public String toString() {
        return String.format("Entity of type %s with id: %s", this.getClass().getName(), getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(ClassUtils.getUserClass(obj))) {
            return false;
        }
        AbstractSnowflakeIdPersistable that = (AbstractSnowflakeIdPersistable) obj;
        return null != this.getId() && this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        int hashCode = 17;
        hashCode += null == getId() ? 0 : getId().hashCode() * 31;
        return hashCode;
    }
}