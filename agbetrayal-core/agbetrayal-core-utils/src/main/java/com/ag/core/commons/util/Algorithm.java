package com.ag.core.commons.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhengaiguo
 * @date 2019-05-14 22:40
 */
@Getter
@AllArgsConstructor
public enum Algorithm {

    MD5("MD5"),

    SHA_1("SHA-1"),

    SHA_256("SHA-256"),

    SHA_512("SHA-512");

    private String name;

}