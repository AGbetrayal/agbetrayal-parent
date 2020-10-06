package com.ag.core.elasticsearch.domain;

import com.ag.core.commons.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

/**
 * @author zhengaiguo
 * @date 2019-04-14 13:17
 */
public abstract class AbstractIDPersistable implements Persistable<String> {

    @Id
    private String id;

    @Override
    @Transient
    @JsonIgnore
    public boolean isNew() {
        return StringUtils.isEmpty(getId());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
