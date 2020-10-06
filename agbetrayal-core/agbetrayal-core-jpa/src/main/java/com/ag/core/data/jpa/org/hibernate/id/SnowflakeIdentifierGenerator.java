package com.ag.core.data.jpa.org.hibernate.id;

import com.ag.core.commons.util.Lazy;
import com.ag.core.commons.util.SnowflakeIdGenerator;
import com.ag.core.commons.util.SnowflakeProperties;
import com.ag.core.commons.util.SpringContextHolder;
import lombok.NoArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * 此类必须要有默认的构造方法，能被实例化
 * hibernate 使用 雪花 算法生成id
 *
 * @Author: zhengaiguo
 * @CreateDate: 2020-08-27 14:43
 */
@NoArgsConstructor
public class SnowflakeIdentifierGenerator implements IdentifierGenerator {

    private static final Lazy<SnowflakeIdGenerator> SNOWFLAKE_ID_GENERATOR = Lazy.of(() -> SpringContextHolder.getBeanIfExist(SnowflakeProperties.class)
            .map(item -> new SnowflakeIdGenerator(item.getWorkerId(), item.getDataCenterId()))
            .orElse(new SnowflakeIdGenerator()));

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return SNOWFLAKE_ID_GENERATOR.get().generate();
    }

}
