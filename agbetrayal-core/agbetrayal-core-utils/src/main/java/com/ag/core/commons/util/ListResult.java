package com.ag.core.commons.util;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.var;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author zhengaiguo
 * @date 2018-06-06 17:58
 */
@NoArgsConstructor
@AllArgsConstructor
public class ListResult<T> implements Iterable<T> {

    /**
     * 查詢总记录数
     */
    @Getter
    private long totalRowCount;

    /**
     * 结果集
     */
    private List<T> result;

    public List<T> getResult() {
        return result == null ? new ArrayList<>(0) : result;
    }

    @Override
    public Iterator<T> iterator() {
        return getResult().iterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (var t : getResult()) {
            action.accept(t);
        }
    }
}
