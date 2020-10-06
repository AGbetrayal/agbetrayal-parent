package com.ag.core.httpclient.post;

import com.ag.core.commons.util.JsonUtils;
import com.ag.core.httpclient.HttpExecutor;
import com.ag.core.httpclient.UTF8ResponseHandler;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;


/**
 * Json Post 请求,请求头包含 Content-Type:application/json;charset=utf-8
 *
 * @author zhengaiguo
 * @date 2017年9月28日上午9:31:24
 */
public class JsonPostHttpExecutor extends AbstractPostHttpExecutor<String, Object> implements HttpExecutor<String, Object> {

    public JsonPostHttpExecutor() {
        super(UTF8ResponseHandler.getInstance());
        addHeaders(new BasicHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString()));
    }

    @Override
    public HttpEntity generateEntity(Object params) {
        return new StringEntity(JsonUtils.serialize(params), Consts.UTF_8);
    }

}
