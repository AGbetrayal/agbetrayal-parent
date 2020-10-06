package query;

import java.io.Serializable;

/**
 * Order
 *
 * @Author: zhengaiguo
 * @CreateDate: 2020-08-27 11:40
 */
@SuppressWarnings("serial")
public final class Order implements Serializable {

    private static final String DESC = "DESC";

    private static final String ASC = "ASC";

    /**
     * 排序字段
     */
    private String field;

    /**
     * ASC | DESC
     */
    private boolean desc;



    public static String getDESC() {
        return DESC;
    }

    public static String getASC() {
        return ASC;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public boolean isDesc() {
        return desc;
    }

    public void setDesc(boolean desc) {
        this.desc = desc;
    }

    public Order(String field, boolean desc) {
        this.field = field;
        this.desc = desc;
    }

    public Order() {
    }

    /**
     * ASC
     *
     * @param field field
     * @return Order asc
     */
    public static Order asc(String field) {
        return new Order(field, false);
    }

    /**
     * DESC
     *
     * @param field field
     * @return Order desc
     */
    public static Order desc(String field) {
        return new Order(field, true);
    }

    @Override
    public String toString() {
        return String.format("%s %s", field, (desc ? DESC : ASC));
    }
}
