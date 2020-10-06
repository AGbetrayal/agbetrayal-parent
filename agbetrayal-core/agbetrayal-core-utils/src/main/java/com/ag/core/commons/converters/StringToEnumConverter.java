package com.ag.core.commons.converters;

/**
 * String To Enum 转换
 *
 * @param <T>
 * @author zhengaiguo
 * @date 2017年09月20日上午9:05:45
 */
public class StringToEnumConverter<T extends Enum<T>> extends StringGenericConverter<Enum<T>> {

    private final Class<Enum<T>> enumType;

    public StringToEnumConverter(Class<Enum<T>> targetType) {
        super(targetType);
        this.enumType = targetType;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Enum<T> doConvert(String source) {
        return Enum.valueOf((Class<T>) enumType, source);
    }

}
