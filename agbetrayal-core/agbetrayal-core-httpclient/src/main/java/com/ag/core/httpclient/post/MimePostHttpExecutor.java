package com.ag.core.httpclient.post;

import com.ag.core.httpclient.HttpExecutor;
import com.ag.core.httpclient.UTF8ResponseHandler;
import com.ag.core.httpclient.utils.HttpUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.content.ContentBody;

import java.util.Map;

/**
 * 支持文件上传的 POST 请求
 *
 * @author zhengaiguo
 * @date 2017年10月12日下午4:58:33
 */
public class MimePostHttpExecutor extends AbstractPostHttpExecutor<String, Map<String, ContentBody>>
        implements HttpExecutor<String, Map<String, ContentBody>> {

    public MimePostHttpExecutor() {
        super(UTF8ResponseHandler.getInstance());
    }

    @Override
    public HttpEntity generateEntity(Map<String, ContentBody> params) {
        return HttpUtils.contentBodyToHttpEntity(params);
    }

}
