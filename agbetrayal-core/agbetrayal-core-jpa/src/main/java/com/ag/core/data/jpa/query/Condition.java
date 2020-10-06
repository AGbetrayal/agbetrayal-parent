package com.ag.core.data.jpa.query;

import java.util.List;

/**
 * @Author: zhengaiguo
 * @CreateDate: 2020-08-27 14:43
 */
public interface Condition {

    /**
     * 条件转换为 sql
     *
     * @param parameters parameters
     * @return sql
     */
    String toSqlString(List<Object> parameters);
}
