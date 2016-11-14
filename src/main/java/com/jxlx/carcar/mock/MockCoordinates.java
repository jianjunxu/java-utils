package com.jxlx.carcar.mock;

import com.google.common.collect.Lists;
import com.jxlx.carcar.entity.Coordinates;
import com.jxlx.carcar.entity.PositionDo;

import java.util.List;

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

    public static List<PositionDo> initPosition(){
        return Lists.newArrayList(
                new PositionDo("1001", "北京", "望京SOHO", "116.480665,39.996404"),
                new PositionDo("1002", "北京", "天安门", "116.397477,39.908692"),
                new PositionDo("1003", "北京", "故宫", "116.397026,39.918058"),
                new PositionDo("1004", "北京", "天坛", "116.410886,39.881949"),
                new PositionDo("1005", "北京", "奥林匹克森林公园", "116.394548,40.015022"),
                new PositionDo("1006", "北京", "长城", "116.016802,40.356188"),
                new PositionDo("1007", "北京", "鸟巢", "116.395946,39.993427"),
                new PositionDo("1008", "北京", "水立方", "116.390395,39.992856"),
                new PositionDo("1009", "北京", "首都机场国际酒店(豪雅店)", "116.585432,40.078522"),
                new PositionDo("1000", "北京", "北京首都国际机场", "116.587922,40.081577")

//                new PositionDo("6",   "昆明汉唐莲花酒店", "102.740258,24.946102"),
//                new PositionDo("779", "方舟大酒店", "102.700337,25.023536"),
//                new PositionDo("785", "*佳信明珠（穿金店）", "102.755196,25.081704"),
//                new PositionDo("787", "*星耀酒店", "102.773365,24.955223"),
//                new PositionDo("793", "*友华之星大酒店", "102.715913,25.07799"),
//                new PositionDo("797", "润都大酒店", "102.782613,25.011986"),
//                new PositionDo("798", "*恒兴酒店", "102.701011,25.053925"),
//                new PositionDo("799", "*旺穗酒店", "102.738913,25.033707"),
//                new PositionDo("802", "*西城花园酒店", "102.69401,25.059576"),
//                new PositionDo("803", "*弘旭大酒店", "102.728257,25.013767"),
//                new PositionDo("805", "*鸿润大酒店", "102.737802,25.032872"),
//                new PositionDo("807", "星河宾馆", "102.789741,25.007214"),
//                new PositionDo("821", "泰隆宏瑞饭店", "102.733664,25.025206"),
//                new PositionDo("837", "*玖安大酒店（含温泉）", "102.782418,24.948425"),
//                new PositionDo("838", "唐韵大酒店（东三环店）", "102.782371,25.048455"),
//                new PositionDo("839", "唐韵大酒店（古镇店）", "102.75983,24.959596"),
//                new PositionDo("840", "唐韵大酒店（人民西路店）", "102.682013,25.050852"),
//                new PositionDo("844", "文汇酒店", "102.702671,25.068691"),
//                new PositionDo("845", "南亚豪生", "102.833574,24.891909"),
//                new PositionDo("848", "*滇池春天温泉会馆", "102.671447,24.987057"),
//                new PositionDo("852", "怡雅阳光铂金店", "102.785121,24.950841"),
//                new PositionDo("853", "怡雅阳光金汁店", "102.74546,25.021134"),
//                new PositionDo("860", "温德姆至尊豪庭", "102.690042,25.01504"),
//                new PositionDo("864", "中心皇冠假日酒店", "102.723405,25.040643"),
//                new PositionDo("865", "天恒大酒店", "102.722137,25.043724"),
//                new PositionDo("866", "君乐酒店", "102.706836,25.050894"),
//                new PositionDo("867", "绿洲大酒店", "102.731446,25.040361"),
//                new PositionDo("868", "世纪金源大饭店南楼", "102.771992,24.981363"),
//                new PositionDo("875", "盛方大酒店", "102.751468,25.036149"),
//                new PositionDo("884", "海之伦大酒店", "102.744928,24.970308"),
//                new PositionDo("885", "*耀星酒店（不含温泉）", "102.764962,25.090307"),
//                new PositionDo("890", "世纪金源大饭店（北楼）", "102.771848,24.981461"),
//                new PositionDo("914", "耀星酒店含温泉", "102.764926,25.08998"),
//                new PositionDo("919", "桂花大酒店", "102.704649,25.027007"),
//                new PositionDo("930", "*君逸豪庭酒店", "102.753068,24.953896"),
//                new PositionDo("931", "*雨花大酒店", "102.851001,24.840635"),
//                new PositionDo("933", "*玖安大酒店（不含温泉）", "102.782418,24.948425"),
//                new PositionDo("935", "佳信明珠（经开区店）", "102.84827,24.958855"),
//                new PositionDo("943", "云安会都（贵宾楼）", "102.651182,25.029495"),
//                new PositionDo("947", "宝海明珠酒店", "102.745818,25.021269"),
//                new PositionDo("951", "朗庭酒店", "102.752537,24.981685"),
//                new PositionDo("960", "朗客美登酒店", "102.805841,24.968391"),
//                new PositionDo("961", "荷泰花园酒店", "102.689839,25.00387"),
//                new PositionDo("965", "*温泉半岛凯莱度假酒店", "102.46035,24.949244"),
//                new PositionDo("1127", "花之城豪生大酒店", "102.798935,25.125909"),
//                new PositionDo("1526", "*红杉湖温泉酒店", "103.296396,24.780249"),
//                new PositionDo("2049", "石林太阳花客栈", "103.292529,24.78321"),
//                new PositionDo("2056", "花之城钟点房", "102.796686,25.059805"),
//                new PositionDo("2077", "海坤酒店", "102.749318,25.020758"),
//                new PositionDo("2080", "*昆明峨山酒店", "102.749318,25.020758"),
//                new PositionDo("2132", "昆明八骏酒店", "102.749972,25.018965"),
//                new PositionDo("1000", "昆明长水国际机场", "102.928293,25.096153")

        );
    }

    public static List<String> initQueryData(){
        return Lists.newArrayList(
                "1001",
                "1002",
                "1003",
                "1004",
                "1005",
                "1006",
                "1007",
                "1008",
                "1009",
                "1000"
//                "6",
//                "779",
//                "785",
//                "787",
//                "793",
//                "797",
//                "798",
//                "799",
//                "802",
//                "803",
//                "805",
//                "807",
//                "821",
//                "837",
//                "838",
//                "839",
//                "840",
//                "844",
//                "845",
//                "848",
//                "852",
//                "853",
//                "860",
//                "864",
//                "865",
//                "866",
//                "867",
//                "868",
//                "875",
//                "884",
//                "885",
//                "890",
//                "914",
//                "919",
//                "930",
//                "931",
//                "933",
//                "935",
//                "943",
//                "947",
//                "951",
//                "960",
//                "961",
//                "965",
//                "1127",
//                "1526",
//                "2049",
//                "2056",
//                "2077",
//                "2080",
//                "2132"
        );
    }
}
