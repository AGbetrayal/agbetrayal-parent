package com.ag.core.httpclient.post;

import com.ag.core.httpclient.UTF8ResponseHandler;
import com.ag.core.httpclient.utils.HttpUtils;
import org.apache.http.HttpEntity;

import java.util.Map;

/**
 * 常规 POST 请求
 *
 * @author zhengaiguo
 * @date 2017年9月28日上午9:31:16
 */
public class SimplePostHttpExecutor extends AbstractPostHttpExecutor<String, Map<String, Object>> {

    public SimplePostHttpExecutor() {
        super(UTF8ResponseHandler.getInstance());
    }

    @Override
    public HttpEntity generateEntity(Map<String, Object> params) {
        return HttpUtils.toHttpEntity(params);
    }

}
