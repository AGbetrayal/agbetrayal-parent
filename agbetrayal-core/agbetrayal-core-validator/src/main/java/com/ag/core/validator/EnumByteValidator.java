package com.ag.core.validator;

import com.ag.core.commons.util.ArrayUtils;
import com.ag.core.validator.constraints.EnumByte;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author kevin
 * @date 2018-08-31 10:57
 */
public class EnumByteValidator implements ConstraintValidator<EnumByte, Byte> {

    private byte[] enumValue;

    private boolean notNull;

    @Override
    public void initialize(EnumByte constraintAnnotation) {
        this.notNull = constraintAnnotation.notNull();
        this.enumValue = constraintAnnotation.values();
    }

    @Override
    public boolean isValid(Byte value, ConstraintValidatorContext context) {
        return null == value ? !notNull : ArrayUtils.contains(enumValue, value);
    }
}
