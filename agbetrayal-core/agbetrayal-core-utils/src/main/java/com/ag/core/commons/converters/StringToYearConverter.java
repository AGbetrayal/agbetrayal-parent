package com.ag.core.commons.converters;

import com.ag.core.commons.util.date.DatePattern;

import java.time.Year;
import java.time.format.DateTimeFormatter;

/**
 * String To Year
 *
 * @author zhengaiguo
 * @date 2017年11月16日上午9:26:20
 */
public class StringToYearConverter extends StringGenericConverter<Year> {

    public StringToYearConverter() {
        super(Year.class);
    }

    @Override
    protected Year doConvert(String source) {
        return Year.parse(source, DateTimeFormatter.ofPattern(DatePattern.YYYY.getPattern()));
    }

}
