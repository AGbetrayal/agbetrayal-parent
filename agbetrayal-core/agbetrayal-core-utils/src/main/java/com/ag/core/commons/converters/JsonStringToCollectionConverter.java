package com.ag.core.commons.converters;

import com.ag.core.commons.util.JsonUtils;
import lombok.var;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * json string 转换为 Collection
 *
 * @author zhengaiguo
 * @date 2019-7-8 9:23
 */
public class JsonStringToCollectionConverter implements GenericConverter {

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(String.class, Collection.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null) {
            return null;
        }
        var elementDesc = targetType.getElementTypeDescriptor();
        return JsonUtils.deserialize(source.toString(), targetType.getType(), elementDesc.getType());
    }
}
