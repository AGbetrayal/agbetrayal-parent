package com.ag.core.validator;

import com.ag.core.commons.util.StringUtils;
import com.ag.core.commons.util.ValidateUtils;
import com.ag.core.validator.constraints.IdCard;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author kevin
 * @date 2018-08-31 10:59
 */
public class IdCardValidator implements ConstraintValidator<IdCard, CharSequence> {

    private boolean notEmpty;

    @Override
    public void initialize(IdCard constraintAnnotation) {
        this.notEmpty = constraintAnnotation.notEmpty();
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        return StringUtils.isEmpty(value) ? !notEmpty : ValidateUtils.isIDCard(value);
    }
}
