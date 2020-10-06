/**
 *
 */
package com.ag.core.commons.util;

import lombok.var;
import org.springframework.core.ResolvableType;

/**
 * @author zhengaiguo
 */
public abstract class TypeUtils {

    /**
     * 获取为集合泛型属性的泛型类型
     *
     * @param beanClass
     *            beanClass
     * @param propertyName
     *            propertyName
     * @return Class
     */
    public static <T> Class<T> getCollectionParameterizedTypeClass(Class<?> beanClass, String propertyName) {
        return getParameterizedTypeClass(beanClass, 0, propertyName);
    }

    /**
     * 获取为泛型属性的泛型类型
     *
     * <pre>
     *     获取 Map Key 类型: getParameterizedTypeClass(beanClass,0,propertyName)
     *     获取 Map Value 类型: getParameterizedTypeClass(beanClass,1,propertyName)
     * </pre>
     *
     * @param beanClass
     *            beanClass
     * @param index
     *            index
     * @param propertyName
     *            propertyName
     * @return Class
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> getParameterizedTypeClass(Class<?> beanClass, int index, String propertyName) {
        var field = FieldUtils.findField(beanClass, propertyName);
        var type = field.getGenericType();
        if (type.getClass() == Class.class) {
            return (Class<T>) type;
        }
        return (Class<T>) ResolvableType.forField(field).getGeneric(index).resolve();
    }

}