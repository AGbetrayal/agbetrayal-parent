package com.ag.core.commons.converters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * String To LocalDate
 *
 * @author zhengaiguo
 * @date 2017年11月16日上午9:26:49
 */
public class StringToLocalDateConverter extends StringGenericConverter<LocalDate> {

    public StringToLocalDateConverter() {
        super(LocalDate.class);
    }

    @Override
    protected LocalDate doConvert(String source) {
        return LocalDate.parse(source, DateTimeFormatter.ISO_LOCAL_DATE);
    }

}
