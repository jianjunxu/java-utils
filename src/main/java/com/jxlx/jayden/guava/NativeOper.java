package com.jxlx.jayden.guava;

import com.google.common.primitives.Ints;

import java.util.List;

/**
 * @author jianjun.xu
 * @description 原生类型支持
 * Java的原生类型就是指基本类型：byte、short、int、long、float、double、char和boolean。
 * byte    Bytes, SignedBytes, UnsignedBytes
 * short   Shorts
 * int     Ints, UnsignedInteger, UnsignedInts
 * long    Longs, UnsignedLong, UnsignedLongs
 * float   Floats
 * double  Doubles
 * char    Chars
 * boolean Booleans
 * @email jianjun.xu@vipshop.com
 * @date 16/8/17 下午10:33
 */
public class NativeOper {
    /**
     * http://ifeve.com/google-guava-primitives/
     */
    public static void main(String[] args) {
        int[] ints = new int[]{2,1,3,5,4};
        /**
         * 把数组转为相应包装类的List
         */
        List<Integer> intList = Ints.asList(ints);
        System.out.println("intList:"+intList);
        /**
         * 把集合拷贝为数组，和collection.toArray()一样线程安全
         */
        int[] intArr = Ints.toArray(intList);
        System.out.println("intArr:"+intArr);
        /**
         * 把给定long值转为某一原生类型，若给定值不符合该原生类型，则抛出IllegalArgumentException
         */
        int i = Ints.checkedCast(12L);
        System.out.println("intArr:"+intArr);
    }
}
