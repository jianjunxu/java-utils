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
        String distance = "[{\"destId\":\"1002\",\"distance\":\"18639\",\"duration\":\"2400\",\"oriId\":\"1001\"},{\"destId\":\"1003\",\"distance\":\"18835\",\"duration\":\"2460\",\"oriId\":\"1001\"},{\"destId\":\"1004\",\"distance\":\"18156\",\"duration\":\"2220\",\"oriId\":\"1001\"},{\"destId\":\"1005\",\"distance\":\"12412\",\"duration\":\"1860\",\"oriId\":\"1001\"},{\"destId\":\"1006\",\"distance\":\"65302\",\"duration\":\"4380\",\"oriId\":\"1001\"},{\"destId\":\"1007\",\"distance\":\"10117\",\"duration\":\"1320\",\"oriId\":\"1001\"},{\"destId\":\"1008\",\"distance\":\"10912\",\"duration\":\"1440\",\"oriId\":\"1001\"},{\"destId\":\"1009\",\"distance\":\"16317\",\"duration\":\"1800\",\"oriId\":\"1001\"},{\"destId\":\"1003\",\"distance\":\"1102\",\"duration\":\"420\",\"oriId\":\"1002\"},{\"destId\":\"1004\",\"distance\":\"5544\",\"duration\":\"1440\",\"oriId\":\"1002\"},{\"destId\":\"1005\",\"distance\":\"21450\",\"duration\":\"3000\",\"oriId\":\"1002\"},{\"destId\":\"1006\",\"distance\":\"70727\",\"duration\":\"5340\",\"oriId\":\"1002\"},{\"destId\":\"1007\",\"distance\":\"21856\",\"duration\":\"2460\",\"oriId\":\"1002\"},{\"destId\":\"1008\",\"distance\":\"22651\",\"duration\":\"2640\",\"oriId\":\"1002\"},{\"destId\":\"1009\",\"distance\":\"30894\",\"duration\":\"2820\",\"oriId\":\"1002\"},{\"destId\":\"1004\",\"distance\":\"6374\",\"duration\":\"1320\",\"oriId\":\"1003\"},{\"destId\":\"1005\",\"distance\":\"14784\",\"duration\":\"2400\",\"oriId\":\"1003\"},{\"destId\":\"1006\",\"distance\":\"64061\",\"duration\":\"4680\",\"oriId\":\"1003\"},{\"destId\":\"1007\",\"distance\":\"13803\",\"duration\":\"2040\",\"oriId\":\"1003\"},{\"destId\":\"1008\",\"distance\":\"14598\",\"duration\":\"2160\",\"oriId\":\"1003\"},{\"destId\":\"1009\",\"distance\":\"28140\",\"duration\":\"2520\",\"oriId\":\"1003\"},{\"destId\":\"1005\",\"distance\":\"28408\",\"duration\":\"2940\",\"oriId\":\"1004\"},{\"destId\":\"1006\",\"distance\":\"77092\",\"duration\":\"5340\",\"oriId\":\"1004\"},{\"destId\":\"1007\",\"distance\":\"26113\",\"duration\":\"2340\",\"oriId\":\"1004\"},{\"destId\":\"1008\",\"distance\":\"26908\",\"duration\":\"2520\",\"oriId\":\"1004\"},{\"destId\":\"1009\",\"distance\":\"35151\",\"duration\":\"2700\",\"oriId\":\"1004\"},{\"destId\":\"1006\",\"distance\":\"56101\",\"duration\":\"4020\",\"oriId\":\"1005\"},{\"destId\":\"1007\",\"distance\":\"4652\",\"duration\":\"1200\",\"oriId\":\"1005\"},{\"destId\":\"1008\",\"distance\":\"5447\",\"duration\":\"1380\",\"oriId\":\"1005\"},{\"destId\":\"1009\",\"distance\":\"25799\",\"duration\":\"2280\",\"oriId\":\"1005\"},{\"destId\":\"1007\",\"distance\":\"59516\",\"duration\":\"3960\",\"oriId\":\"1006\"},{\"destId\":\"1008\",\"distance\":\"60311\",\"duration\":\"4080\",\"oriId\":\"1006\"},{\"destId\":\"1009\",\"distance\":\"71493\",\"duration\":\"4560\",\"oriId\":\"1006\"},{\"destId\":\"1008\",\"distance\":\"702\",\"duration\":\"120\",\"oriId\":\"1007\"},{\"destId\":\"1009\",\"distance\":\"26771\",\"duration\":\"2160\",\"oriId\":\"1007\"},{\"destId\":\"1009\",\"distance\":\"27928\",\"duration\":\"2160\",\"oriId\":\"1008\"}]";
        List<DistanceResDo> doList = JSON.parseArray(distance, DistanceResDo.class);
        for (DistanceResDo resDo : doList) {
            if (Long.valueOf(StringUtils.trim(resDo.getDistance())) <= 5000L) { //距离在5km内
                System.out.println(JSON.toJSONString(resDo));
            }
        }
    }
}
