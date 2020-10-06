package com.ag.core.httpclient;

import com.ag.core.commons.util.CollectionUtils;
import com.ag.core.httpclient.utils.HttpUtils;
import lombok.Setter;
import org.apache.http.Header;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.util.List;

/**
 * 请求接口抽象实现
 *
 * @author zhengaiguo
 */
public abstract class AbstractHttpExecutor<T> {

    @Setter
    private CloseableHttpClient httpClient;

    /**
     * 响应处理器
     */
    private ResponseHandler<T> responseHandler;

    private List<Header> headers = HttpUtils.DEFAULT_HEADER;

    public AbstractHttpExecutor() {
        this.httpClient = HttpClientUtils.DEFAULT_HTTP_CLIENT;
    }

    public AbstractHttpExecutor(ResponseHandler<T> responseHandler) {
        this();
        this.responseHandler = responseHandler;
    }

    protected final T doExecute(HttpUriRequest request) throws IOException {
        return HttpClientUtils.execute(this.httpClient, request, this.responseHandler);
    }

    protected final void addHeaders(Header... headers) {
        CollectionUtils.addAllNotNull(this.headers, headers);
    }

    protected final Header[] getHeaders() {
        return this.headers.toArray(new Header[0]);
    }
}
