package page;

import java.util.List;

/**
 * @Author: zhengaiguo
 * @CreateDate: 2020-08-27 14:43
 */
@SuppressWarnings("serial")
abstract class AbstractQueryPage<T> implements QueryPage<T> {

    /**
     * 数据集
     */
    private List<T> data;

    /**
     * 总记录数
     */
    private long totalRow;

    @Override
    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public long getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(long totalRow) {
        this.totalRow = totalRow;
    }
}
