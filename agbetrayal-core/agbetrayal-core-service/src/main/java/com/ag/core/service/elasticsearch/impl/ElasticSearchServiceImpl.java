package com.ag.core.service.elasticsearch.impl;

import com.ag.core.commons.util.CollectionUtils;
import com.ag.core.elasticsearch.query.Condition;
import com.ag.core.elasticsearch.repository.BaseElasticsearchRepository;
import com.ag.core.service.elasticsearch.ElasticSearchService;
import com.ag.core.service.impl.BaseServiceImpl;
import org.springframework.data.domain.Persistable;
import page.QueryPage;
import query.Order;
import query.QueryModel;

import java.util.Collection;
import java.util.List;

/**
 * ElasticSearchServiceImpl
 *
 * @author zhengaiguo
 * @date 2019-6-28 11:40
 */
public abstract class ElasticSearchServiceImpl<T extends Persistable<String>> extends BaseServiceImpl<T, String> implements ElasticSearchService<T> {

    /**
     * @return {@link BaseElasticsearchRepository}
     */
    @Override
    protected abstract BaseElasticsearchRepository<T> getBaseRepository();

    @Override
    public QueryPage<T> queryForPage(QueryModel<T> query) {
        return getBaseRepository().findByPage(query);
    }

    @Override
    public long count(T t) {
        return getBaseRepository().count(t);
    }

//    @Override
//    public SearchResponse suggest(SuggestBuilder suggestBuilder) {
//        return getBaseRepository().suggest(suggestBuilder);
//    }

    @Override
    public List<T> findAll(List<Condition> conditions, Order... orders) {
        return getBaseRepository().findAll(conditions, orders);
    }

    @Override
    public List<T> insertOrUpdate(Collection<T> entities) {
        return CollectionUtils.toList(getBaseRepository().saveAll(entities));
    }

    @Override
    public List<T> insertOrUpdateSelective(Collection<T> entities) {
        getBaseRepository().bulkUpdate(entities);
        return CollectionUtils.toList(entities);
    }

    @Override
    public void deleteByIds(Iterable<String> ids) {
        getBaseRepository().deleteByIds(ids);
    }

    @Override
    public T updateByIdSelective(T t) {
        getBaseRepository().bulkUpdate(t);
        return t;
    }

    @Override
    public QueryPage<T> findByPage(List<Condition> conditions, int pageIndex, int pageSize, Order... orders) {
        return getBaseRepository().findByPage(conditions, pageIndex, pageSize, orders);
    }
}
