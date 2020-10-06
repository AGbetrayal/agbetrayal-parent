package com.ag.core.commons.query;


/**
 * And Or
 *
 * @author zhengaiguo
 */
public enum AndOr {

    AND, OR;

    public String toSqlString() {
        return toString();
    }

}
