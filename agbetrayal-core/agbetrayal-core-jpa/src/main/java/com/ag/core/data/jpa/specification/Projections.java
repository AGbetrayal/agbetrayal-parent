package com.ag.core.data.jpa.specification;

@Deprecated
public class Projections {

    public static AggregateProjection max(String propertyName) {
        return new AggregateProjection(propertyName, Aggregate.MAX);
    }

    public static AggregateProjection min(String propertyName) {
        return new AggregateProjection(propertyName, Aggregate.MIN);
    }

    public static AggregateProjection avg(String propertyName) {
        return new AggregateProjection(propertyName, Aggregate.AVG);
    }

    public static AggregateProjection sum(String propertyName) {
        return new AggregateProjection(propertyName, Aggregate.SUM);
    }

}
