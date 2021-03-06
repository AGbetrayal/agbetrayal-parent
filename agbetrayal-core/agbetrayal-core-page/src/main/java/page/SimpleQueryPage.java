package page;


import com.ag.core.commons.util.ListResult;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import query.QueryModel;

import java.util.List;

/**
 * @Author: zhengaiguo
 * @date 2018年1月24日上午9:58:21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuppressWarnings("serial")
public class SimpleQueryPage<T> extends AbstractQueryPage<T> {

    /**
     * 当前页号
     */
    private int pageIndex;

    /**
     * 每页显示记录数
     */
    private int pageSize;

    /**
     * @param query  query
     * @param result result
     */
    public SimpleQueryPage(QueryModel<?> query, ListResult<T> result) {
        this(query, result.getResult(), result.getTotalRowCount());
    }

    /**
     * @param query    query
     * @param totalRow totalRow
     * @param data     data
     */
    public SimpleQueryPage(QueryModel<?> query, List<T> data, long totalRow) {
        this(data, totalRow, query.getStartRowIndex(), query.getPageSize());
    }

    /**
     * @param data      data
     * @param totalRow  totalRow
     * @param pageIndex pageIndex
     * @param pageSize  pageSize
     */
    public SimpleQueryPage(List<T> data, long totalRow, int pageIndex, int pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        setData(data);
        setTotalRow(totalRow);
    }

}
