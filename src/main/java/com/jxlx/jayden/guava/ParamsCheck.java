package com.jxlx.jayden.guava;

import com.google.common.base.Preconditions;

/**
 * @author jianjun.xu
 * @description
 * @email jianjun.xu@vipshop.com
 * @date 16/8/16 下午8:26
 */
public class ParamsCheck {
    public static void main(String[] args) {
        Object object = null;
//        checkArgumentWithoutMsg(object);
//        checkArgumentWithMsg(object);
//        checkArgumentWithMultiMsg(object);
        boolean state = false;
//        checkStateWithoutMsg(state);
        checkNotNullWithMsg(object);
    }

    /**
     * throw IllegalArgumentException exception.
     *
     * @param object
     */
    public static void checkArgumentWithoutMsg(Object object) {
        Preconditions.checkArgument(object != null);
    }

    /**
     * throw IllegalArgumentException exception.
     * IllegalArgumentException: object is null.
     *
     * @param object
     */
    public static void checkArgumentWithMsg(Object object) {
        Preconditions.checkArgument(object != null, "object is null.");
    }

    /**
     * throw IllegalArgumentException exception.
     * use error msg template.
     *
     * @param object
     */
    public static void checkArgumentWithMultiMsg(Object object) {
        String errorMessageTemplate = "check argument error. error1:%s, error2:%s";
        String errMsg1 = "errorMessageArgs1";
        String errMsg2 = "errorMessageArgs2";
        Preconditions.checkArgument(object != null, errorMessageTemplate, errMsg1, errMsg2);
    }

    /**
     * throw IllegalStateException exception.
     *
     * @param state
     */
    public static void checkStateWithoutMsg(boolean state) {
        Preconditions.checkState(state);
    }

    /**
     * throw NullPointerException exception.
     *
     * @param object
     */
    public static void checkNotNull(Object object) {
        Preconditions.checkNotNull(object);
    }

    /**
     * throw NullPointerException exception.
     *
     * @param object
     */
    public static void checkNotNullWithMsg(Object object) {
        Preconditions.checkNotNull(object, "object is null.");
    }

    /**数组,字符串越界检查*/
//    checkElementIndex
//    badElementIndex
//    ...


}
