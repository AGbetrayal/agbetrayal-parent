package com.ag.core.data.jpa.util;

/**
 * spring data jpa 审计字段
 *
 * @author zhengaiguo
 */
public interface AuditField {

    /**
     * 字段属性名数组
     */
    String[] AUDIT_FIELD_ARRAY = {"createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate"};

    /**
     * 字段名：创建者
     */
    String CREATED_BY = "created_by";

    /**
     * 字段名：创建时间
     */
    String CREATED_DATE = "created_date";

    /**
     * 字段名：最后更新人
     */
    String LAST_MODIFIED_BY = "last_modified_by";

    /**
     * 字段名：最后更新时间
     */
    String LAST_MODIFIED_DATE = "last_modified_date";
}
