package com.jxlx.jayden.guava;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import com.jxlx.jayden.entity.Entity;

import java.util.Set;

/**
 * @author jianjun.xu
 * @description
 * @email jianjun.xu@vipshop.com
 * @date 16/8/16 下午9:42
 */
public class SetsOper {

    public static void define() {
        Set<String> set1 = Sets.newHashSet();
        Set<String> set2 = Sets.newHashSet("hello", "world");
        Set<String> set3 = Sets.newHashSetWithExpectedSize(20);

        Set<String> set4 = Sets.newLinkedHashSet();
        Set<String> set5 = Sets.newLinkedHashSetWithExpectedSize(20);

        Set<String> set6 = Sets.newConcurrentHashSet();
    }

    /**
     * 常用过滤元素
     *
     * @param entitySet
     * @return
     */
    public static Set<Entity> filterEle(Set<Entity> entitySet) {
        return Sets.filter(entitySet, new Predicate<Entity>() {
            @Override
            public boolean apply(Entity input) {
                return input.getName().contains("hello");
            }
        });
    }

    /**
     * 并集 返回不可修改SetView
     *
     * @param set1
     * @param set2
     * @return
     */
    public static Set<String> unionSet(Set<String> set1, Set<String> set2) {
        return Sets.union(set1, set2);
    }

    /**
     * 差集 所有属于set1且不属于set1的元素构成的集合 返回不可修改SetView
     *
     * @param set1
     * @param set2
     * @return
     */
    public static Set<String> differenceSet(Set<String> set1, Set<String> set2) {
        return Sets.difference(set1, set2);
    }

    /**
     * 交集 返回不可修改SetView
     *
     * @param set1
     * @param set2
     * @return
     */
    public static Set<String> intersectionSet(Set<String> set1, Set<String> set2) {
        return Sets.intersection(set1, set2);
    }

    /**
     * symmetricDifference：返回两个set集合的对称差的不可修改SetView
     * 对称差，即两个集合的对称差是只属于其中一个集合，而不属于另一个集合的元素组成的集合
     *
     * @param set1
     * @param set2
     * @return
     */
    public static Set<String> symmetricDifferenceSet(Set<String> set1, Set<String> set2) {
        return Sets.symmetricDifference(set1, set2);
    }

    public static void main(String[] args) {
        Set<String> set1 = Sets.newHashSet("aa", "bb");
        Set<String> set2 = Sets.newHashSet("cc", "bb");
        System.out.println("并集:" + unionSet(set1, set2));
        System.out.println("差集:" + differenceSet(set1, set2));
        System.out.println("交集:" + intersectionSet(set1, set2));
        System.out.println("对称集差:" + symmetricDifferenceSet(set1, set2));
    }
}
