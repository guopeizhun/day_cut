package com.letg.day_cut.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 公共工具类
 */
public class CommonUtil {
    /**
     * 获取当月第一天
     * @return
     */
    public static Date getMonthFirstDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,1);
        return calendar.getTime();
    }

    /**
     * 获取当月的最后一天
     * @return
     */
    public static Date getMonthLastDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 获取当年的最后一天
     * @return
     */
    public static Date getYearLastDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
        return calendar.getTime();
    }

    /**
     * 获取当月第一天
     * @return
     */
    public static Date getYearFirstDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR,1);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        System.out.println(getYearLastDay());
    }
}
