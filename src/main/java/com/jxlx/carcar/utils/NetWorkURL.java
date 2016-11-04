package com.jxlx.carcar.utils;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by jayden on 16/11/4.
 */
public class NetWorkURL {
    /**
     * 组装url
     *
     * @param uri        eg.http://restapi.amap.com/v3/distance
     * @param parameters
     * @return
     */
    public static String toURL(String uri, Map<String, String> parameters) {
        /**
         * check params
         */
        Preconditions.checkArgument(StringUtils.isNotBlank(uri), "uri is blank.");

        StringBuilder sb = new StringBuilder();
        if (uri.endsWith("/")) {
            sb.append(uri.substring(0, uri.length() - 1));
        } else {
            sb.append(uri);
        }
        List<NameValuePair> params = Lists.newArrayList();
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        if (!CollectionUtils.isEmpty(params)) {
            sb.append("?").append(URLEncodedUtils.format(params, Charsets.UTF_8));
        }
        return sb.toString();
    }
}
