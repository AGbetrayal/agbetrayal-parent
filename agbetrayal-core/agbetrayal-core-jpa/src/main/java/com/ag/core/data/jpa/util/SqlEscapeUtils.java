package com.ag.core.data.jpa.util;

import com.ag.core.commons.util.ArrayUtils;
import com.ag.core.commons.util.StringUtils;

/**
 * @Author: zhengaiguo
 * @CreateDate: 2020-08-27 14:43
 */
abstract class SqlEscapeUtils {

    private static final String[] SQL_KEY_WORD = {"CREATE", "AND", "OR", "USE", "--", ";",
            "INSERT", "UPDATE", "DELETE", "DROP", "ALTER", "GRANT", "EXECUTE", "EXEC"};

    static String escape(String args) {
        return (StringUtils.isEmpty(args) || ArrayUtils.contains(SQL_KEY_WORD, args.trim().toUpperCase()))
                ? null : args;
    }
}
