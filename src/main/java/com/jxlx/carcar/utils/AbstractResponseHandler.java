package com.jxlx.carcar.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author jianjun.xu
 * @description http请求返回结果处理
 * @email jianjun.xu@vipshop.com
 * @date 16/8/13 下午6:11
 */
public abstract class AbstractResponseHandler<T> implements ResponseHandler<T> {

    public static final int HTTP_FAILED_CODE = 300;

    @Override
    public T handleResponse(HttpResponse response) throws IOException {
        StatusLine statusLine = response.getStatusLine();
        HttpEntity entity = response.getEntity();
        if (statusLine.getStatusCode() >= HTTP_FAILED_CODE) {
            String errMsg = EntityUtils.toString(entity);
            throw new HttpResponseException(statusLine.getStatusCode(), errMsg);
        }
        return handle(entity);
    }

    public abstract T handle(HttpEntity entity) throws IOException;
}