package com.ag.core.data.jpa.query;

import com.ag.core.commons.query.AndOr;
import com.ag.core.commons.util.CollectionUtils;
import com.ag.core.commons.util.StringUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.var;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: zhengaiguo
 * @CreateDate: 2020-08-27 14:43
 */
public class CompositeCondition implements Condition {

    @Setter
    @Getter
    private AndOr andOr;

    @Setter
    @Getter
    private List<Condition> conditions = new ArrayList<>();

    /**
     *
     */
    public CompositeCondition(Condition... conditions) {
        this(AndOr.AND, conditions);
    }

    /**
     * @param andOr      andOr
     * @param conditions conditions
     */
    public CompositeCondition(AndOr andOr, List<Condition> conditions) {
        this.andOr = andOr;
        this.conditions = conditions;
    }

    /**
     * @param andOr      andOr
     * @param conditions conditions
     */
    public CompositeCondition(AndOr andOr, Condition... conditions) {
        this.andOr = andOr;
        CollectionUtils.addAllNotNull(this.conditions, conditions);
    }

    /**
     * 添加条件
     *
     * @param condition condition
     */
    public CompositeCondition addCondition(Condition condition) {
        conditions.add(condition);
        return this;
    }

    public CompositeCondition addConditions(Collection<? extends Condition> conditions) {
        this.conditions.addAll(conditions);
        return this;
    }

    /**
     * 添加条件
     *
     * @param conditions conditions
     */
    public CompositeCondition addConditions(Condition... conditions) {
        CollectionUtils.addAllNotNull(this.conditions, conditions);
        return this;
    }

    /**
     * 删除指定条件
     *
     * @param condition condition
     */
    public CompositeCondition removeCondition(Condition condition) {
        conditions.remove(condition);
        return this;
    }

    /**
     * 删除指定条件
     *
     * @param index index
     */
    public CompositeCondition removeCondition(int index) {
        conditions.remove(index);
        return this;
    }

    /**
     * 清除所有条件
     */
    public void clearConditions() {
        conditions.clear();
    }

    @Override
    public String toSqlString(List<Object> parameters) {
        var sb = new StringBuilder();
        var index = 0;
        for (var c : conditions) {
            if (null != c) {
                var sql = c.toSqlString(parameters);
                if (StringUtils.isNotEmpty(sql)) {
                    if (index++ > 0) {
                        sb.append(StringUtils.SPACE).append(andOr.toSqlString()).append(StringUtils.SPACE);
                    }
                    sb.append(sql);
                }
            }
        }
        return sb.toString();
    }
}
