package com.base.project.util;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.Time;
import android.view.View;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {
    // 一天
    private final static long ONE_DAYS = 86400 * 1000;

    private static DateFormat dateFormate_list = null;

    private static DateFormat dateFormate_week = null;

    private static DateFormat dateFormate_month = null;

    private static final String dateFormate_list_PATTEN = "HH:mm";
    private static final String dateFormate_week_PATTEN = "EEE HH:mm";
    private static final String dateFormate_month_PATTEN_EN = "MMM dd HH:mm";
    private static final String dateFormate_week_format = "EEE";
    private static final String dateFormate_month_day = "MMM dd";
    private static final String dateFormate_year_month_day = "yyyy.MM.dd";
    private static final String formate_month_day = "MM/dd";
    // 5.0
    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;


    public static boolean compare_date(String datatime, String Systemdatatime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt1 = df.parse(datatime);
            Date dt2 = df.parse(Systemdatatime);
            long time = dt2.getTime() - dt1.getTime();
            if (time >= ONE_MINUTE) {
                return true;
            } else {
                return false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }


    /**
     * language change(area change area)
     */
    public static void laguageOrTimeZoneChanged() {
        dateFormate_week = null;
        dateFormate_month = null;
        dateFormate_list = null;
    }

    private static final int HOUR_BY_MIN = 60;
    private static final int DAY_BY_MIN = HOUR_BY_MIN * 24;


    /**
     * 格式化时间
     *
     * @param milliseconds
     * @param pattern
     * @return
     */
    public static String getFormatDateTime(long milliseconds, String pattern) {
        Date date = new Date(milliseconds);
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        String str = sf.format(date);
        return str;
    }

    public static Long getHours(long time) {
        long diff = System.currentTimeMillis() - time;// 这样得到的差值是微秒级别
        long days = diff / (1000 * 60 * 60 * 24);
        long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        return hours;
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }

    public static Date getDateTime(String currentTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = formatter.parse(currentTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public static String getDateTime(Long currentTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date(currentTime);
        String stringa = formatter.format(date);
        return stringa;
    }



    /**
     * 显示时间(xxHxxm)和日期（xxMxxD）
     *
     * @param context
     * @param when
     * @return
     */
    public static String formatTimeStampString(Context context, long when) {
        Time then = new Time();
        then.set(when);
        Time now = new Time();
        now.setToNow();
        String date = "";
        // Basic settings for formatDateTime() we want for all cases.
        int format_flags = android.text.format.DateUtils.FORMAT_NO_NOON_MIDNIGHT |
                android.text.format.DateUtils.FORMAT_ABBREV_ALL |
                android.text.format.DateUtils.FORMAT_CAP_AMPM;

        // If the message is from a different year, show the date and year.
        if (then.year != now.year) {
            format_flags |= android.text.format.DateUtils.FORMAT_SHOW_YEAR | android.text.format.DateUtils.FORMAT_SHOW_DATE;
            date = getDateToStringYear(when);
        } else if (then.yearDay != now.yearDay) {
            // If it is from a different day than today, show only the date.
            format_flags |= android.text.format.DateUtils.FORMAT_SHOW_DATE;
            date = getDateToStringDay(when);
        } else {
            // Otherwise, if the message is from today, show the time.
            format_flags |= android.text.format.DateUtils.FORMAT_SHOW_TIME;
            date = android.text.format.DateUtils.formatDateTime(context, when, format_flags);
        }
        return date;
    }


    public static String getDateToStringDay(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("MM/dd");
        return sf.format(d);
    }

    public static String getDateToStringYear(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
        return sf.format(d);
    }

    public static String getFileDateToString(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yyyy");
        return sf.format(d);
    }

    /**
     * 比较时间
     *
     * @param yearText
     * @param monthText
     * @param dayText
     * @param view
     * @return
     */
    public static boolean compareDate(CharSequence yearText, CharSequence monthText, CharSequence dayText, View view) {

        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date selected = df.parse(yearText + "-" + monthText + "-" + dayText);
            Date today = new Date();
            if (selected.getTime() - today.getTime() < 0) {
                view.setEnabled(true);
                view.setClickable(true);
                return true;
            }
        } catch (ParseException e) {

            e.printStackTrace();
        }
        view.setEnabled(false);
        view.setClickable(false);
        return false;
    }

    /**
     * 年月日 转日月年
     *
     * @param dateStr
     * @return
     */
    public static String yyyyMMddToddMMyyyy(String dateStr) {
        String resultStr = dateStr;
        try {

            if (!TextUtils.isEmpty(dateStr) && dateStr.contains("-")) {
                String[] str = dateStr.split("-");
                if (str != null && str.length == 3 && str[2].length() == 2 && str[0].length() == 4) {
                    resultStr = str[2] + "-" + str[1] + "-" + str[0];

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultStr;
    }


    private synchronized static DateFormat getDateYYYY_MM_dd() {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormate_year_month_day);
        //sdf.setTimeZone(TimeZone.getDefault());
        return sdf;
    }


    private synchronized static DateFormat getMM_dd() {
        SimpleDateFormat sdf = new SimpleDateFormat(formate_month_day);
        return sdf;
    }

    /**
     * 获取6个月前当前时间戳
     * @return
     */
    public static long getSMSstartTs() {
        Date da = new Date();//获取当前时间
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(da);//把当前时间赋给日历
        calendar.add(calendar.MONTH, -6);  //设置为前6月
        da = calendar.getTime();//获取2个月前的时间
        return da.getTime();
    }


}
