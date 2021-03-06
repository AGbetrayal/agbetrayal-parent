package com.ag.core.commons.util;

import com.ag.core.commons.annotations.EnumDisplay;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;
import lombok.var;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * EnumDisplay Util
 *
 * @author zhengaiguo
 * @date 2017年10月20日上午9:33:12
 * @see EnumDisplay
 */
public abstract class EnumDisplayUtils {

    /**
     * 获取 EnumDisplay注解修饰的value
     *
     * @param enumValue enumValue
     * @return {@link EnumDisplay#value()}
     */
    public static String getDisplayText(Object enumValue) {
        var enumDisplay = getEnumDisplay(enumValue);
        return null == enumDisplay ? null :
                SpringContextHolder.getMessageWithDefault(enumDisplay.value(), enumDisplay.value());
    }

    /**
     * @param enumClass enumClass
     * @param order     order
     * @return {@link EnumDisplay#value()}
     */
    public static String getDisplayText(Class<? extends Enum<?>> enumClass, int order) {
        var enumDisplay = getEnumDisplayByOrder(enumClass, order);
        return Objects.isNull(enumDisplay) ? null : SpringContextHolder.getMessageWithDefault(enumDisplay.value(), null);
    }

    /**
     * 根据枚举类与 order 获取注解
     *
     * @param enumClass enumClass
     * @param order     order
     * @return {@link EnumDisplay}
     */
    public static EnumDisplay getEnumDisplayByOrder(Class<? extends Enum<?>> enumClass, int order) {
        var enumConstants = enumClass.getEnumConstants();
        for (var enumConstant : enumConstants) {
            var enumDisplay = getEnumDisplay(enumConstant);
            if (null != enumDisplay && enumDisplay.order() == order) {
                return enumDisplay;
            }
        }
        return null;
    }

    /**
     * 获取EnumDisplay注解
     *
     * @param enumValue enumValue
     * @return {@link EnumDisplay}
     */
    @Nullable
    @SneakyThrows(value = {NoSuchFieldException.class, SecurityException.class})
    public static EnumDisplay getEnumDisplay(Object enumValue) {
        if (null == enumValue) {
            return null;
        }
        var type = enumValue.getClass();
        var en = (Enum<?>) enumValue;
        var field = type.getField(en.name());
        return field.getAnnotation(EnumDisplay.class);
    }

    /**
     * 获取 EnumDisplay注解修饰的order
     *
     * @param enumValue enumValue
     * @return order value
     */
    public static int getDisplayOrder(Object enumValue) {
        var enumDisplay = getEnumDisplay(enumValue);
        return null == enumDisplay ? 0 : enumDisplay.order();
    }

    /**
     * 根据 枚举值 与类型获取 text
     *
     * @param enumValue enumValue
     * @param enumClass enumClass
     * @return {@link EnumDisplay#value()}
     */
    public static <T extends Enum<T>> String getDisplayText(String enumValue, Class<T> enumClass) {
        var value = Enum.valueOf(enumClass, enumValue);
        return getDisplayText(value);
    }

    /**
     * 读取枚举项列表。
     *
     * @param enumClass 枚举类
     * @return 枚举项的列表 1.value为枚举值value
     * 2.如果枚举项有@EnumDisplay标注，text则取标注的value属性，order取标注的order属性
     * 3.如果没有@EnumDisplay标注，text值为枚举值value，order为0
     */
    @SneakyThrows(value = {IllegalAccessException.class})
    public static <TEnum extends Enum<?>> List<EnumItem> getEnumItemList(Class<TEnum> enumClass) {
        List<EnumItem> items = new ArrayList<>();
        var fields = enumClass.getFields();
        EnumItem item;
        for (var field : fields) {
            if (field.isEnumConstant()) {
                item = new EnumItem();
                var value = field.get(null);
                item.setValue(value);
                item.setText(value.toString());
                item.setOrder(0);
                EnumDisplay ed = field.getAnnotation(EnumDisplay.class);
                if (null != ed) {
                    item.setText(SpringContextHolder.getMessageWithDefault(ed.value(), null));
                    item.setOrder(ed.order());
                }
                items.add(item);
            }
        }
        return items.stream()
                .sorted(Comparator.comparingInt(EnumItem::getOrder))
                .collect(Collectors.toList());
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @SuppressWarnings("serial")
    public static class EnumItem extends TextValueItem {

        private int order;

    }

}
