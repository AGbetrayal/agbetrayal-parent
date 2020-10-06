package com.ag.core.commons.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 雪花算法参数
 *
 * @author zhengaiguo
 * @date 2019-7-2 18:00
 */
@Data
@ConfigurationProperties(prefix = "agbetrayal.snowflake")
public class SnowflakeProperties {

    /**
     * 工作节点
     */
    private int workerId = 1;

    /**
     * 数据中心
     */
    private int dataCenterId = 1;
}
