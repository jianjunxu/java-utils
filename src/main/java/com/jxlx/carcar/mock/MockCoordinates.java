package com.jxlx.carcar.mock;

import com.google.common.collect.Lists;
import com.jxlx.carcar.entity.Coordinates;

import java.util.List;
import java.util.Map;

/**
 * Created by jayden on 16/11/4.
 */
public class MockCoordinates {

    public static List<Coordinates> initCoordinates() {
        List<Coordinates> list = Lists.newArrayList();
        Coordinates soho = new Coordinates("望京SOHO", "116.480665", "39.996404");
        list.add(soho);
        Coordinates tam = new Coordinates("天安门", "116.397477", "39.908692");
        list.add(tam);
        Coordinates gg = new Coordinates("故宫", "116.397026", "39.918058");
        list.add(gg);
        Coordinates tt = new Coordinates("天坛", "116.410886", "39.881949");
        list.add(tt);
        Coordinates alpk = new Coordinates("奥林匹克森林公园", "116.394548", "40.015022");
        list.add(alpk);
        Coordinates cc = new Coordinates("长城", "116.016802", "40.356188");
        list.add(cc);
        Coordinates nc = new Coordinates("鸟巢", "116.395946", "39.993427");
        list.add(nc);
        Coordinates slf = new Coordinates("水立方", "116.390395", "39.992856");
        list.add(slf);
        Coordinates airPort = new Coordinates("北京首都国际机场", "116.587922", "40.081577");
        list.add(airPort);
        return list;
    }

    public static List<String> initDistance() {
        return Lists.newArrayList(
                "1001,北京,望京SOHO",
                "1002,北京,天安门",
                "1003,北京,故宫",
                "1004,北京,天坛",
                "1005,北京,奥林匹克森林公园",
                "1006,北京,长城",
                "1007,北京,鸟巢",
                "1008,北京,水立方");
    }

    public static String initDistanceTransfer() {
        return "1000,北京,北京首都国际机场";
    }
}
