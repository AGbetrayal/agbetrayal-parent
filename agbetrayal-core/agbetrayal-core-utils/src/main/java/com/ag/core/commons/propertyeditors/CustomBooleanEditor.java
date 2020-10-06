package com.ag.core.commons.propertyeditors;

import com.ag.core.commons.util.BooleanUtils;
import com.ag.core.commons.util.ObjectUtils;
import com.ag.core.commons.util.StringUtils;
import lombok.var;

/**
 * BeanWraper Editor
 *
 * @author zhengaiguo
 * @date 2018-05-08 09:55
 */
public class CustomBooleanEditor extends org.springframework.beans.propertyeditors.CustomBooleanEditor {

    public static final CustomBooleanEditor ALLOW_EMPTY_INSTANCE = new CustomBooleanEditor(true);

    private CustomBooleanEditor(boolean allowEmpty) {
        super(allowEmpty);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        text = StringUtils.trimWhitespace(text);
        if (StringUtils.equals(BooleanUtils.TRUE_CHINESE, text)) {
            setValue(Boolean.TRUE);
        } else if (StringUtils.equals(BooleanUtils.FALSE_CHINESE, text)) {
            setValue(Boolean.FALSE);
        } else {
            super.setAsText(text);
        }
    }

    @Override
    public String getAsText() {
        var value = ObjectUtils.toString(getValue());
        if (StringUtils.equalsAny(value, BooleanUtils.TRUE_CHINESE, BooleanUtils.FALSE_CHINESE)) {
            return value;
        }
        return super.getAsText();
    }
}
