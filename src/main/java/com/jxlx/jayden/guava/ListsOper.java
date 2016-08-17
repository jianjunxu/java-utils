package com.jxlx.jayden.guava;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.jxlx.jayden.entity.Entity;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author jianjun.xu
 * @description
 * @email jianjun.xu@vipshop.com
 * @date 16/8/16 下午9:30
 */
public class ListsOper {


    public static void define() {
        /** 定义 */
        List<String> list1 = Lists.newArrayList();
        List<String> list2 = Lists.newArrayList("hello", "world");
        List<String> list3 = Lists.newArrayListWithCapacity(20);
        List<String> list4 = Lists.newLinkedList();
        /** http://ifeve.com/java-copy-on-write/ */
        CopyOnWriteArrayList cowlist = Lists.newCopyOnWriteArrayList();
    }

    /**
     * 常用.抽取对象列表中属性生成新的列表
     * 也可以是其他操作.如过滤等
     * @param entityList
     * @return
     */
    public static List<String> generatAttrList(List<Entity> entityList) {
        return Lists.transform(entityList, new Function<Entity, String>() {
            @Override
            public String apply(Entity input) {
                return input.getName();
            }
        });
    }

    /**
     * 翻转列表
     * @param strList
     * @return
     */
    public static List<String> reverseList(List<String> strList){
        return Lists.reverse(strList);
    }
}
