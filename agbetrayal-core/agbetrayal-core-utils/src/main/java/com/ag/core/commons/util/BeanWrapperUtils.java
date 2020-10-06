package com.ag.core.commons.util;

import com.ag.core.commons.propertyeditors.CustomBooleanEditor;
import com.ag.core.commons.propertyeditors.CustomLocalDateEditor;
import com.ag.core.commons.propertyeditors.CustomLocalDateTimeEditor;
import com.ag.core.commons.propertyeditors.CustomLocalTimeEditor;

import lombok.var;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyAccessorFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * BeanWrapper Create util
 *
 * @author zhengaiguo
 * @date 2018-05-04 13:55
 */
public abstract class BeanWrapperUtils {

    /**
     * create BeanWrapper by Obj
     *
     * @param obj obj
     * @return BeanWrapper
     */
    public static BeanWrapper createBeanWrapper(Object obj) {
        var beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(obj);
        registryDefaultEditor(beanWrapper);
        return beanWrapper;
    }

    /**
     * create BeanWrapper by clazz
     *
     * @param clazz clazz
     * @return BeanWrapper
     */
    public static BeanWrapper createBeanWrapper(Class<?> clazz) {
        var beanWrapper = new BeanWrapperImpl(clazz);
        registryDefaultEditor(beanWrapper);
        return beanWrapper;
    }

    private static void registryDefaultEditor(BeanWrapper beanWrapper) {
        beanWrapper.setAutoGrowNestedPaths(true);
        beanWrapper.setConversionService(ConverterUtils.DEFAULT_CONVERSION_SERVICE);
        beanWrapper.registerCustomEditor(Boolean.class, CustomBooleanEditor.ALLOW_EMPTY_INSTANCE);
        beanWrapper.registerCustomEditor(LocalDate.class, CustomLocalDateEditor.INSTANCE);
        beanWrapper.registerCustomEditor(LocalTime.class, CustomLocalTimeEditor.INSTANCE);
        beanWrapper.registerCustomEditor(LocalDateTime.class, CustomLocalDateTimeEditor.INSTANCE);
    }
}
