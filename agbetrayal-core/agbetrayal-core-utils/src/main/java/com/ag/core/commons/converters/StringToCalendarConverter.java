package com.ag.core.commons.converters;

import com.ag.core.commons.util.date.DateTimeUtils;
import lombok.var;

import java.util.Calendar;

public class StringToCalendarConverter extends StringGenericConverter<Calendar> {

    public StringToCalendarConverter() {
        super(Calendar.class);
    }

    @Override
    protected Calendar doConvert(String source) {
        var date = DateTimeUtils.stringToDate(source);
        return (null == date) ? null : DateTimeUtils.dateToCalendar(date);
    }

}
