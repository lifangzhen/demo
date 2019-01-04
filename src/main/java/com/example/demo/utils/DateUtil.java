package com.example.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * User:policy
 * Date:2018/5/14 14:20
 */
public class DateUtil {
    public static final String FULL_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATE_HOUR_PATTERN = "yyyy-MM-dd HH";
    public static final String DATE_MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String NOT_SEPARATOR_DATE_PATTERN = "yyyyMMdd";
    public static final String HOUR_MINTE_SECEND_PATTEN = "HH:mm:ss";

    public DateUtil() {
    }

    public static String dateToString(String pattern, Date date) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static int getWeekOfDate(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return w;
    }

    public static Date getToday(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTime();
    }

    public static Date utcDate() {
        Calendar cal = Calendar.getInstance();
        int zoneOffset = cal.get(15);
        int dstOffset = cal.get(16);
        cal.add(14, -(zoneOffset + dstOffset));
        return cal.getTime();
    }

    public static String currenDateToString() {
        return dateToString("yyyy-MM-dd HH:mm:ss", new Date());
    }

    public static String dateToString(Date date){
        return dateToString("yyyy-MM-dd HH:mm:ss", date);
    }

    public static String currenDateToString(String pattern) {
        return dateToString(pattern, new Date());
    }

    public static boolean compareDate(Date date1, Date date2){
        return date1.before(date2);
    }

    public static boolean compareDate(String pattern, String sFirstDate, String sSecondlyDate) {
        Date firstDate = formatDate(pattern, sFirstDate);
        Date secondlyDate = formatDate(pattern, sSecondlyDate);
        return firstDate.before(secondlyDate);
    }

    public static Date formatDate(String pattern, String sDate) {
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

        try {
            date = dateFormat.parse(sDate);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return date;
    }

    public static Date rollDate(Date date, int field, int step) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, step);
        return calendar.getTime();
    }

    public static Date setDate(Date date, int field, int value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(field, value);
        return calendar.getTime();
    }

    public static int getField(int field) {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(field);
    }

    public static enum TIME_ZONE {
        UTC,
        GMT,
        CST;

        private TIME_ZONE() {
        }
    }
}
