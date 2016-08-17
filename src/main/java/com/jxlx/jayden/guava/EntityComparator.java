package com.jxlx.jayden.guava;

import com.jxlx.jayden.entity.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author jianjun.xu
 * @description
 * @email jianjun.xu@vipshop.com
 * @date 16/8/16 下午9:09
 */
public class EntityComparator {

    public static void main(String[] args){
        Entity entity1 = new Entity();
        entity1.setId(10);
        entity1.setName("unknow");
        Entity entity2 = new Entity();
        entity2.setId(3);
        entity2.setName("xx");
        List<Entity> entityList = new ArrayList<Entity>();
        entityList.add(entity1);
        entityList.add(entity2);
        System.out.println(entityList);
        Collections.sort(entityList);
        System.out.println(entityList);
    }
}
