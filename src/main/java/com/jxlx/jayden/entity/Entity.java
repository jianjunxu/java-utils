package com.jxlx.jayden.entity;

import com.google.common.collect.ComparisonChain;

/**
 * @author jianjun.xu
 * @description
 * @email jianjun.xu@vipshop.com
 * @date 16/8/16 下午9:08
 */
public class Entity implements Comparable<Entity> {
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Entity o) {
        return ComparisonChain.start()
                .compare(id, o.getId())
                .compare(name, o.getName())
                .result();
    }
}
