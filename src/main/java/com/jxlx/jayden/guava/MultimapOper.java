package com.jxlx.jayden.guava;


import com.google.common.collect.*;

/**
 * User : jianjun.xu
 * Date : 2016/8/17
 * Time : 14:36
 * Desc : 实现Map<K, Collection<V>>数据结构
 *        http://ifeve.com/google-guava-newcollectiontypes/
 */
public class MultimapOper {

	public static void define() {
		// HashMap<K, HashSet<V>>
		Multimap<Integer, String> map1 = HashMultimap.create();
		// HashMap<K, List<V>>
		Multimap<Integer, String> map2 = ArrayListMultimap.create();

		// LinkedHashMap<K, List<V>>
		Multimap<Integer, String> map3 = LinkedListMultimap.create();
		// LinkedHashMap<K, Set<V>>
		Multimap<Integer, String> map4 = LinkedHashMultimap.create();
	}

	public static void test() {
		Multimap<Integer, String> map1 = HashMultimap.create();
		map1.put(1, "hello");
		map1.put(1, "world");
		System.out.println(map1.asMap());
	}

	public static void main(String[] args) {
		test();
	}
}
