package com.ag.core.httpclient.delete;

import com.ag.core.httpclient.AbstractHttpExecutor;
import com.ag.core.httpclient.HttpExecutor;
import com.ag.core.httpclient.UTF8ResponseHandler;
import com.ag.core.httpclient.utils.HttpUtils;
import org.apache.http.Header;
import org.apache.http.concurrent.FutureCallback;

import java.io.IOException;
import java.util.Map;


/**
 * Http Delete 请求
 *
 * @param <T>
 * @author zhengaiguo
 * @see com.ag.commons.http.HttpClientUtils#delete(String, Map, Header...)
 * @see com.ag.commons.http.async.AsyncHttpClientUtils#delete(String, Map, FutureCallback, Header...)
 */
public final class HttpDeleteHttpExecutor extends AbstractHttpExecutor<String> implements HttpExecutor<String, Map<String, Object>> {

    public HttpDeleteHttpExecutor() {
        super(UTF8ResponseHandler.getInstance());
    }

    @Override
    public String execute(String uri, Map<String, Object> params) throws IOException {
        return doExecute(HttpUtils.newHttpDelete(uri, params, getHeaders()));
    }

}
