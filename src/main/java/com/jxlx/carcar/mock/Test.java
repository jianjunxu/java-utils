package com.jxlx.carcar.mock;

import com.alibaba.fastjson.JSON;
import com.jxlx.carcar.entity.DistanceResDo;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by jayden on 16/11/7.
 */
public class Test {
    public static void main(String[] args) {
        String distance = "[{\"destId\":\"1002\",\"distance\":\"19949\",\"duration\":\"2940\",\"oriId\":\"1001\"},{\"destId\":\"1003\",\"distance\":\"20145\",\"duration\":\"3000\",\"oriId\":\"1001\"},{\"destId\":\"1004\",\"distance\":\"19664\",\"duration\":\"2700\",\"oriId\":\"1001\"},{\"destId\":\"1005\",\"distance\":\"10062\",\"duration\":\"2040\",\"oriId\":\"1001\"},{\"destId\":\"1006\",\"distance\":\"64111\",\"duration\":\"4260\",\"oriId\":\"1001\"},{\"destId\":\"1007\",\"distance\":\"8952\",\"duration\":\"1740\",\"oriId\":\"1001\"},{\"destId\":\"1008\",\"distance\":\"9747\",\"duration\":\"1920\",\"oriId\":\"1001\"},{\"destId\":\"1009\",\"distance\":\"18769\",\"duration\":\"1560\",\"oriId\":\"1001\"},{\"destId\":\"1003\",\"distance\":\"1102\",\"duration\":\"420\",\"oriId\":\"1002\"},{\"destId\":\"1004\",\"distance\":\"5544\",\"duration\":\"1560\",\"oriId\":\"1002\"},{\"destId\":\"1005\",\"distance\":\"13925\",\"duration\":\"3240\",\"oriId\":\"1002\"},{\"destId\":\"1006\",\"distance\":\"65422\",\"duration\":\"4920\",\"oriId\":\"1002\"},{\"destId\":\"1007\",\"distance\":\"11871\",\"duration\":\"2580\",\"oriId\":\"1002\"},{\"destId\":\"1008\",\"distance\":\"12666\",\"duration\":\"2760\",\"oriId\":\"1002\"},{\"destId\":\"1009\",\"distance\":\"30894\",\"duration\":\"2880\",\"oriId\":\"1002\"},{\"destId\":\"1004\",\"distance\":\"6374\",\"duration\":\"1440\",\"oriId\":\"1003\"},{\"destId\":\"1005\",\"distance\":\"14784\",\"duration\":\"2520\",\"oriId\":\"1003\"},{\"destId\":\"1006\",\"distance\":\"64061\",\"duration\":\"4560\",\"oriId\":\"1003\"},{\"destId\":\"1007\",\"distance\":\"11655\",\"duration\":\"2160\",\"oriId\":\"1003\"},{\"destId\":\"1008\",\"distance\":\"12439\",\"duration\":\"2340\",\"oriId\":\"1003\"},{\"destId\":\"1009\",\"distance\":\"28140\",\"duration\":\"2640\",\"oriId\":\"1003\"},{\"destId\":\"1005\",\"distance\":\"25302\",\"duration\":\"3540\",\"oriId\":\"1004\"},{\"destId\":\"1006\",\"distance\":\"74036\",\"duration\":\"5580\",\"oriId\":\"1004\"},{\"destId\":\"1007\",\"distance\":\"23000\",\"duration\":\"3000\",\"oriId\":\"1004\"},{\"destId\":\"1008\",\"distance\":\"23795\",\"duration\":\"3120\",\"oriId\":\"1004\"},{\"destId\":\"1009\",\"distance\":\"32095\",\"duration\":\"3060\",\"oriId\":\"1004\"},{\"destId\":\"1006\",\"distance\":\"57369\",\"duration\":\"3780\",\"oriId\":\"1005\"},{\"destId\":\"1007\",\"distance\":\"4652\",\"duration\":\"1200\",\"oriId\":\"1005\"},{\"destId\":\"1008\",\"distance\":\"5447\",\"duration\":\"1320\",\"oriId\":\"1005\"},{\"destId\":\"1009\",\"distance\":\"25799\",\"duration\":\"2280\",\"oriId\":\"1005\"},{\"destId\":\"1007\",\"distance\":\"59516\",\"duration\":\"4020\",\"oriId\":\"1006\"},{\"destId\":\"1008\",\"distance\":\"60311\",\"duration\":\"4140\",\"oriId\":\"1006\"},{\"destId\":\"1009\",\"distance\":\"71493\",\"duration\":\"4320\",\"oriId\":\"1006\"},{\"destId\":\"1008\",\"distance\":\"702\",\"duration\":\"120\",\"oriId\":\"1007\"},{\"destId\":\"1009\",\"distance\":\"26643\",\"duration\":\"2280\",\"oriId\":\"1007\"},{\"destId\":\"1009\",\"distance\":\"28036\",\"duration\":\"2220\",\"oriId\":\"1008\"}]";
        List<DistanceResDo> doList = JSON.parseArray(distance, DistanceResDo.class);
        for (DistanceResDo resDo : doList) {
            if (Long.valueOf(StringUtils.trim(resDo.getDistance())) <= 5000L) { //距离在5km内
                System.out.println(JSON.toJSONString(resDo));
            }
        }
    }
}
