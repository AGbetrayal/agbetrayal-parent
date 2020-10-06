package com.ag.core.httpclient.get;

import com.ag.core.httpclient.AbstractHttpExecutor;
import com.ag.core.httpclient.HttpExecutor;
import com.ag.core.httpclient.UTF8ResponseHandler;
import com.ag.core.httpclient.utils.HttpUtils;
import org.apache.http.Header;
import org.apache.http.concurrent.FutureCallback;

import java.io.IOException;
import java.util.Map;


/**
 * Http Get 请求
 *
 * @author zhengaiguo
 * @see com.ag.commons.http.HttpClientUtils#get(String, Map, Header...)
 * @see com.ag.commons.http.async.AsyncHttpClientUtils#get(String, Map, FutureCallback, Header...)
 */
public final class HttpGetHttpExecutor extends AbstractHttpExecutor<String> implements HttpExecutor<String, Map<String, Object>> {

    public HttpGetHttpExecutor() {
        super(UTF8ResponseHandler.getInstance());
    }

    @Override
    public String execute(String uri, Map<String, Object> params) throws IOException {
        return doExecute(HttpUtils.newHttpGet(uri, params, getHeaders()));
    }
}
