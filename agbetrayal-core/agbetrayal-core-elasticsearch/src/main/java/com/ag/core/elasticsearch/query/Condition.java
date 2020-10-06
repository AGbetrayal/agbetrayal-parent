package com.ag.core.elasticsearch.query;

import com.ag.core.commons.util.CollectionUtils;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;

import java.util.List;
import java.util.Objects;

/**
 * @author zhengaiguo
 * @date 2019/3/18 10:38
 */
public interface Condition {

    Criteria toElasticsearchCriteria();

    static void addCriteria(CriteriaQuery query, List<Condition> conditions) {
        if (CollectionUtils.isNotEmpty(conditions)) {
            conditions.forEach(item -> {
                Criteria criteria = item.toElasticsearchCriteria();
                if (Objects.nonNull(criteria)) {
                    query.addCriteria(criteria);
                }
            });
        }
    }

}
