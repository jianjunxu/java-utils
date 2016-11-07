package com.jxlx.carcar.utils;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by jayden on 16/11/4.
 */
public class HttpClientUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);

    private HttpClient httpClient;

    public HttpClientUtils() throws Exception {
//      <!-- 读取超时 -->
//		<constructor-arg name="socketTimeout" value="1000"/>
//		<!-- 建立连接超时时间 -->
//		<constructor-arg name="connectionTimeout" value="500"/>
//		<!-- 从连接池获取连接的超时时间 -->
//		<constructor-arg name="connectionRequestTimeout" value="500"/>
//		<!-- 是否进行陈旧连接检查, 如果不开启, 则启动陈旧连接关闭线程 -->
//		<constructor-arg name="staleConnCheck" value="true"/>
        this(2000, 2000, 2000, true, 5);
    }

    /**
     * 带参数设置的HttpClient
     *
     * @param socketTimeout    读取超时
     * @param connectionTimeout       建立连接超时时间
     * @param connectionRequestTimeout    从连接池获取连接的超时时间
     * @param staleConnCheck 是否进行陈旧连接检查, 如果不开启, 则启动陈旧连接关闭线程
     * @param tryTimes 重试次数
     */
    public HttpClientUtils(int socketTimeout, int connectionTimeout, int connectionRequestTimeout, boolean staleConnCheck, int tryTimes) throws Exception {

        RequestConfig config = RequestConfig.custom()
                .setSocketTimeout(socketTimeout) // 读取超时
                .setConnectTimeout(connectionTimeout) // 建立连接超时时间
                .setConnectionRequestTimeout(connectionRequestTimeout) // 从连接池获取连接的超时时间
                .setStaleConnectionCheckEnabled(staleConnCheck) // 是否进行陈旧连接检查, 如果不开启, 则启动陈旧连接关闭线程
                .build();
        httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(config)
                .setRetryHandler(new DefaultHttpRequestRetryHandler(tryTimes, true))  //默认失败后重发3次，可用别的构造方法指定重发次数
                .build();
    }

    /**
     * Execute with log
     *
     * @param httpUriRequest the http uri request
     * @param handler        the handler
     * @return the t
     * @throws java.io.IOException the iO doFailure
     * @throws java.io.IOException the iO exception
     */
    public <T> T executeWithLog(HttpUriRequest httpUriRequest, ResponseHandler<T> handler) throws IOException {
        long start = System.currentTimeMillis();
        T result = httpClient.execute(httpUriRequest, handler, new BasicHttpContext());
        LOGGER.info("Execute request to: {}, execute time: {} ms", httpUriRequest.getURI(), System.currentTimeMillis() - start);
        return result;
    }
}
