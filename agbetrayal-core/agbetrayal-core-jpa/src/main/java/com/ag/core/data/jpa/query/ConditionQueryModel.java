package com.ag.core.data.jpa.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import query.QueryModel;

/**
 * @Author: zhengaiguo
 * @CreateDate: 2020-08-27 14:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ConditionQueryModel extends QueryModel<CompositeCondition> {

    public ConditionQueryModel() {
        setParam(new CompositeCondition());
    }

}
