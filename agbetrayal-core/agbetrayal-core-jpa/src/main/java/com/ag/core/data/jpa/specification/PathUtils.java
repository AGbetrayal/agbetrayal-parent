package com.ag.core.data.jpa.specification;

import com.ag.core.commons.util.StringUtils;
import lombok.var;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

/**
 * @Author: zhengaiguo
 * @CreateDate: 2020-08-27 14:43
 */
class PathUtils {

    static <X> Path<X> getPath(Root<X> root, String propertyName) {
        Path<X> path;
        if (StringUtils.contains(propertyName, ".")) {
            var names = StringUtils.splitByComma(propertyName);
            path = root.get(names[0]);
            for (var name : names) {
                path = path.get(name);
            }
        } else {
            path = root.get(propertyName);
        }
        return path;

    }
}
