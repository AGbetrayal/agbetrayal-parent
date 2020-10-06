package com.ag.core.validator;

import com.ag.core.commons.util.ArrayUtils;
import com.ag.core.commons.util.StringUtils;
import com.ag.core.validator.constraints.EnumString;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author kevin
 * @date 2018-08-31 10:57
 */
public class EnumStringValidator implements ConstraintValidator<EnumString, CharSequence> {

    private CharSequence[] enumValue;

    private boolean notEmpty;

    @Override
    public void initialize(EnumString constraintAnnotation) {
        this.notEmpty = constraintAnnotation.notEmpty();
        this.enumValue = constraintAnnotation.values();
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        return StringUtils.isEmpty(value) ? !notEmpty : ArrayUtils.contains(enumValue, value);
    }
}
