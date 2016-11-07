package com.jxlx.jayden.guava;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * User : jianjun.xu
 * Date : 2016/8/17
 * Time : 13:02
 * Desc :
 */
public class MapsOper {
	public static void define() {
		Map<String, String> map1 = Maps.newHashMap();
		Map<String, String> map2 = Maps.newHashMapWithExpectedSize(20);

		Map<String, String> map3 = Maps.newConcurrentMap();
		Map<String, String> map4 = Maps.newLinkedHashMap();
		Map<String, String> map5 = Maps.newTreeMap();
	}

	/**
	 * Maps.transformValues
	 * 返回一个map映射，其键值为给定fromMap的键值，其value为给定formMap中value通过Function转换后 的值
	 */
	public static void testTransformValues() {
		Map<String, String> key2value = Maps.newHashMap();
		key2value.put("key1", "value1");
		key2value.put("key2", "value2");
		key2value.put("key3", "value3");
		Map<String, String> resultMap = Maps.transformValues(key2value, new Function<String, String>() {
			@Override
			public String apply(String input) {
				return input.toUpperCase();
			}
		});
		printResult("testTransformValues", key2value, resultMap);
	}

	/**
	 * Maps.transformEntries(Map<K, V1> fromMap, EntryTransformer<? super K, ? super V1, V2> transformer)
	 * 返回一个map映射，其Entry为给定fromMap.Entry通过给定EntryTransformer转换后的值(将entry的value值改为transformEntry的返回值)。
	 */
	public static void testTransformEntries() {
		Map<String, String> key2value = Maps.newHashMap();
		key2value.put("key1", "value1");
		key2value.put("key2", "value2");
		key2value.put("key3", "value3");
		final String pre = "pre-";
		Map<String, String> resultMap = Maps.transformEntries(key2value, new Maps.EntryTransformer<String, String, String>() {
			@Override
			public String transformEntry(String key, String value) {
				return pre + value;
			}
		});
		printResult("testTransformEntries", key2value, resultMap);
	}

	/**
	 * Maps.filterKeys
	 * 返回一个map映射,按key过滤
	 */
	public static void testFilterKeys() {
		Map<String, String> key2value = Maps.newHashMap();
		key2value.put("key1", "value1");
		key2value.put("key2", "value2");
		key2value.put("key3", "value3");
		Map<String, String> resultMap = Maps.filterKeys(key2value, new Predicate<String>() {
			@Override
			public boolean apply(String input) {
				return input.contains("key1");
			}
		});
		printResult("testFilterKeys", key2value, resultMap);
	}

	/**
	 * Maps.filterValues
	 * 按value过滤
	 */
	public static void testFilterValue() {
		Map<String, String> key2value = Maps.newHashMap();
		key2value.put("key1", "value1");
		key2value.put("key2", "value2");
		key2value.put("key3", "value3");
		Map<String, String> resultMap = Maps.filterValues(key2value, new Predicate<String>() {
			@Override
			public boolean apply(String input) {
				return input.contains("value1");
			}
		});
		printResult("testFilterValue", key2value, resultMap);
	}

	/**
	 * Maps.filterEntries
	 * 同时按照key和value过滤
	 */
	public static void testFilterEntries() {
		Map<String, String> key2value = Maps.newHashMap();
		key2value.put("key1", "value1");
		key2value.put("key2", "value2");
		key2value.put("key3", "value3");
		key2value.put("TEST", "Test");
		Map<String, String> resultMap = Maps.filterEntries(key2value, new Predicate<Map.Entry<String, String>>() {
			@Override
			public boolean apply(Map.Entry<String, String> input) {
				return StringUtils.endsWithIgnoreCase(input.getKey(), input.getValue());
			}
		});
		printResult("resultMap", key2value, resultMap);
	}



	public static void main(String[] args) {
		testTransformValues();
	}

	/**
	 * print
	 *
	 * @param obj1
	 * @param obj2
	 */
	private static void printResult(String desc, Object obj1, Object obj2) {
		System.out.println("----------------begin----------------");
		System.out.println(desc + ", origin:" + JSON.toJSONString(obj1));
		System.out.println(desc + ", now:" + JSON.toJSONString(obj2));
		System.out.println("----------------end----------------");
	}
}
