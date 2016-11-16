package com.jayden.lbs.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.jayden.lbs.entity.params.KeySearchParams;
import com.jayden.lbs.entity.result.KeySearchResult;
import com.jayden.lbs.utils.ConvertUtils;
import com.jxlx.carcar.common.Constant;
import com.jxlx.carcar.utils.AbstractResponseHandler;
import com.jxlx.carcar.utils.HttpClientUtils;
import com.jxlx.carcar.utils.NetWorkURL;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User : jianjun.xu
 * Date : 2016/11/16
 * Time : 10:10
 * Desc :
 */
public class LbsTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(LbsTest.class);

	/**
	 * 单个查询
	 * eg.http://restapi.amap.com/v3/place/text?key=您的key&keywords=北京大学&types=高等院校&city=北京&children=0&offset=1&page=1&extensions=all
	 * 限制：最多支持20个子查询
	 * @param paramsList
	 * @return
	 */
	public static List<KeySearchResult> batchKeySearch(List<KeySearchParams> paramsList) {
		Preconditions.checkArgument(!CollectionUtils.isEmpty(paramsList));
		Preconditions.checkArgument(paramsList.size() <= 20);
		//子请求组装参数
		JSONArray arr = new JSONArray();
		for (KeySearchParams keySearchParam : paramsList) {
			Map<String, String> map = ConvertUtils.convertBean2Map(keySearchParam);
			String url = NetWorkURL.toURL(Constant.KEY_SEARCH_URI, map);
			JSONObject entity = new JSONObject();
			entity.put("url", url);
			arr.add(entity);
		}
		//批量接口参数设置
		JSONObject params = new JSONObject();
		params.put("ops", arr);
		LOGGER.info("url:" + Constant.MAP_API_HOST + Constant.BATCH_REQUEST_URI);
		LOGGER.info("params:" + JSON.toJSONString(params));
		String response = httpBatchPost(params);
		//请求结果处理
		JSONArray resultArr = JSON.parseArray(response);
		int size = resultArr.size();
		List<KeySearchResult> resultList = new ArrayList<KeySearchResult>(size);
		for (int i = 0; i < size; i++) {
			JSONObject object = resultArr.getJSONObject(i);
			JSONObject result = object.getJSONObject("body");
			KeySearchResult ksResult = JSON.parseObject(JSON.toJSONString(result), KeySearchResult.class);
			/** 请求中唯一标识参数绑定到结果中 */
			ksResult.setId(paramsList.get(i).getId());
			resultList.add(ksResult);
		}
		LOGGER.info(JSON.toJSONString(resultList));
		return resultList;
	}

	/**
	 * http post
	 *
	 * @param params
	 * @return
	 */
	private static String httpBatchPost(JSONObject params) {
		String result = "";
		HttpPost httpPost = new HttpPost(Constant.MAP_API_HOST + Constant.BATCH_REQUEST_URI);
		if (params != null && params.size() > 0) {
			StringEntity stringEntity = new StringEntity(params.toJSONString(), Charsets.UTF_8);
			stringEntity.setContentEncoding(Charsets.UTF_8.name());
			stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			httpPost.setEntity(stringEntity);
		}
		for (int i = 0; i < 3; i++) {
			try {
				HttpClientUtils httpClient = new HttpClientUtils();
				result = httpClient.executeWithLog(httpPost, new AbstractResponseHandler<String>() {
					@Override
					public String handle(HttpEntity entity) throws IOException {
						return EntityUtils.toString(entity, Charsets.UTF_8);
					}
				});
				break;
			} catch (Exception e) {
				LOGGER.error("Exception. try {} times.ex:{}", (i + 1), e.getMessage(), e);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		List<KeySearchParams> paramsList = Lists.newArrayList(
				mock("天安门"),
				mock("故宫"),
				mock("天坛"),
				mock("北京大学"),
				mock("北京首都国际机场")
		);
		List<KeySearchResult> results = batchKeySearch(paramsList);
		System.out.println("----" + JSON.toJSONString(results));
	}

	private static KeySearchParams mock(String keywords){
		KeySearchParams params = new KeySearchParams();
		params.setKey(Constant.KEY_CAR_LINE);
		params.setChildren("0");
		params.setCity("北京");
		params.setCitylimit("true");
		params.setExtensions("all");
		params.setKeywords(keywords);
		params.setOffset("1");
		params.setPage("1");
		params.setOutput("JSON");
		params.setId(RandomUtils.nextLong());
		return params;
	}
}
