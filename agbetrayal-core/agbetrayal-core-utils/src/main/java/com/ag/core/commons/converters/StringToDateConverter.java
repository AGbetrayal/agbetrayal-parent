package com.ag.core.commons.converters;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Locale;

import static com.ag.core.commons.util.date.DateTimeUtils.PATTERNS;
import static com.ag.core.commons.util.date.DateTimeUtils.stringToDate;

/**
 * String 转换为日期
 *
 * @author zhengaiguo
 * @date 2017年9月1日上午11:34:17
 */
public class StringToDateConverter extends StringGenericConverter<Date> {

    /**
     * locale 默认为 Locale.getDefault()
     */
    @Setter
    @Getter
    private Locale locale;

    public StringToDateConverter() {
        super(Date.class);
    }

    @Override
    protected Date doConvert(String source) {
        return stringToDate(source, locale, PATTERNS);
    }

}
