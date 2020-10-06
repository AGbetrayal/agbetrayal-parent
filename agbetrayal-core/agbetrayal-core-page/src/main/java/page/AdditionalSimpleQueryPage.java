package page;

import com.ag.core.commons.util.ListResult;
import lombok.EqualsAndHashCode;
import query.QueryModel;

import java.util.List;


/**
 * @Author: zhengaiguo
 * @CreateDate: 2020-08-27 14:43
 */
@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = true)
public class AdditionalSimpleQueryPage<T> extends SimpleQueryPage<T> {

    /**
     * 其它扩展参数
     */
    private Object additional;

    public AdditionalSimpleQueryPage(QueryModel<?> query, ListResult<T> result, Object additional) {
        this(query, result.getResult(), result.getTotalRowCount(), additional);
    }

    public AdditionalSimpleQueryPage(QueryModel<?> query, List<T> data, long totalRow, Object additional) {
        this(data, totalRow, query.getPageIndex(), query.getPageSize(), additional);
    }

    public AdditionalSimpleQueryPage(List<T> data, long totalRow, int pageIndex, int pageSize, Object additional) {
        super(data, totalRow, pageIndex, pageSize);
        this.additional = additional;
    }

    public AdditionalSimpleQueryPage(QueryPage<T> page, Object additional) {
        super(page.getData(), page.getTotalRow(), page.getPageIndex(), page.getPageSize());
        this.additional = additional;
    }

    public Object getAdditional() {
        return additional;
    }

    public void setAdditional(Object additional) {
        this.additional = additional;
    }

    public AdditionalSimpleQueryPage() {
    }
}
