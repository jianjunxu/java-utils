package com.jxlx.jayden.date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by jayden on 16/11/14.
 */
public class JodaTimeDemo {
    public static void main(String[] args) {
//        initDate();
//        format();
//        caleDays();
//        addTime();
//        hasLeapMonth();
        convertDate();
    }

    /**
     * 初始化时间
     */
    public static void initDate() {
        //初始化时间
        //now
        DateTime now = new DateTime();
        // 年,月,日,时,分
        DateTime yMdHm = new DateTime(2016, 11, 11, 11, 11);
        // 年,月,日,时,分,秒
        DateTime yMdHms = new DateTime(2013, 12, 2, 9, 0, 0);
        // 年,月,日,时,分,秒,毫秒
        DateTime yMdHmsMs = new DateTime(2016, 7, 8, 18, 30, 0, 111);
        System.out.println("now:" + now);
        System.out.println("年月日时分:" + yMdHm);
        System.out.println("年月日时分秒:" + yMdHms);
        System.out.println("年月日时分秒毫秒:" + yMdHmsMs);
    }

    /**
     * 格式化
     */
    public static void format() {
        //下面就是按照一点的格式输出时间
        DateTime dateTime = new DateTime();
        String str2 = dateTime.toString("MM/dd/yyyy hh:mm:ss.SSSa");
        String str3 = dateTime.toString("dd-MM-yyyy HH:mm:ss");
        String str4 = dateTime.toString("EEEE dd MMMM, yyyy HH:mm:ssa");
        String str5 = dateTime.toString("MM/dd/yyyy HH:mm ZZZZ");
        String str6 = dateTime.toString("MM/dd/yyyy HH:mm Z");
        String str7 = dateTime.toString("yyyy-MM-dd HH:mm:ss");
        System.out.println("格式化当前时间：" + str2);
        System.out.println("格式化当前时间：" + str3);
        System.out.println("格式化当前时间：" + str4);
        System.out.println("格式化当前时间：" + str5);
        System.out.println("格式化当前时间：" + str6);
        System.out.println("格式化当前时间：" + str7);

        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        //时间解析
        DateTime dateTime2 = DateTime.parse("2012-12-26 03:27:39", format);
        System.out.println("格式化：" + dateTime2);

        // 根据指定格式,将时间字符串转换成DateTime对象,这里的格式和上面的输出格式是一样的
        DateTime dt2 = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime("2012-12-26 03:27:39");
        System.out.println("格式化：" + dt2);

        //时间格式化，输出==> 2012/12/21 23:22:45 Fri
        String dt1 = dateTime2.toString("yyyy-MM-dd HH:mm:ss");
        System.out.println("格式化：" + dt1);
    }

    /**
     * 计算两个日期间隔的天数
     */
    public static void caleDays() {
        LocalDate start = new LocalDate(2012, 12, 14);
        LocalDate end = new LocalDate(2013, 01, 15);
        int days = Days.daysBetween(start, end).getDays();
        System.out.println("相隔" + days + "天.");
    }

    /**
     * 增加时间
     */
    public static void addTime() {
        //增加日期
        DateTime dateTime = new DateTime();

        dateTime = dateTime.plusDays(1) // 增加天
                .plusYears(1)// 增加年
                .plusMonths(1)// 增加月
                .plusWeeks(1)// 增加星期
                .minusMillis(1)// 减分钟
                .minusHours(1)// 减小时
                .minusSeconds(1);// 减秒数
        System.out.println("-------" + dateTime);
    }

    /**
     * 判断是否闰月
     */
    public static void hasLeapMonth() {
        DateTime dt4 = new DateTime();
        org.joda.time.DateTime.Property month = dt4.monthOfYear();
        System.out.println("是否闰月:" + month.isLeap());
    }

    /**
     * DateTime与java.util.Date对象,当前系统TimeMillis转换
     */
    public static void convertDate() {
        DateTime dateTime = new DateTime(new Date());
        System.out.println("----dateTime:" + dateTime);
        Date date = dateTime.toDate();
        System.out.println("----date:" + date);
        DateTime dateTime1 = new DateTime(System.currentTimeMillis());
        System.out.println("----dateTime1:" + dateTime1);
        Calendar calendar = Calendar.getInstance();
        DateTime dateTime2 = new DateTime(calendar);
        System.out.println("----dateTime2:" + dateTime2);
    }
}