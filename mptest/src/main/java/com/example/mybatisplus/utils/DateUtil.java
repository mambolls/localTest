package com.example.mybatisplus.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * 常见日期类型处理：字符串、日期Date、长整数new Date(long)，parse(time).getTime()
 *
 * @author hongwei
 */
public class DateUtil {

    public static final SimpleDateFormat YEAR = new SimpleDateFormat("yyyy");
    public static final SimpleDateFormat DAY = new SimpleDateFormat(
            "yyyy-MM-dd");
    public static final SimpleDateFormat DAY4 = new SimpleDateFormat(
            "yyyy年MM月dd日");
    public static final SimpleDateFormat DAY3 = new SimpleDateFormat("yyyyMMdd");
    public static final SimpleDateFormat DAY2 = new SimpleDateFormat(
            "yyyy/MM/dd");
    public static final SimpleDateFormat DAYTIME = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DAYTIME2 = new SimpleDateFormat(
            "yyyy/MM/dd HH:mm:ss");
    public static final SimpleDateFormat DAYTIME3 = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm");
    public static final SimpleDateFormat DAYTIME4 = new SimpleDateFormat(
            "MMddHHmmss");
    public static final SimpleDateFormat DAYTIME5 = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss.S");
    public static final SimpleDateFormat MICROSECONDS = new SimpleDateFormat(
            "yyyyMMddHHmmssSSS");
    public static final SimpleDateFormat MICROSECONDSID = new SimpleDateFormat(
            "yyMMddHHmmssSSS");
    public static final SimpleDateFormat SMALLMICROSECONDS = new SimpleDateFormat(
            "yyMMddHHmmss");
    public static final SimpleDateFormat MIDDLEMICROSECONDS = new SimpleDateFormat(
            "yyyyMMddHHmmss");
    public static final SimpleDateFormat SMALLMIHHMMSS = new SimpleDateFormat(
            "HHmmss");
    public static final SimpleDateFormat SMALLMIHHMMSS2 = new SimpleDateFormat(
            "HH:mm:ss");
    public static final SimpleDateFormat SMALLMIHHMMSS3 = new SimpleDateFormat(
            "HHMMSSSSS");
    public static final SimpleDateFormat DIAN = new SimpleDateFormat(
            "yyyy.MM.dd");
    public static final SimpleDateFormat DAY5 = new SimpleDateFormat(
            "yyyy年MM月dd日 HH:mm");
    // 月份
    public static final SimpleDateFormat Month = new SimpleDateFormat("yyyy-MM");

    /**
     * 开始时间时分秒
     */
    public static final String DATE_BEGIN_TIME_STR = " 00:00:00";

    /**
     * 结束时间时分秒
     */
    public static final String DATE_END_TIME_STR = " 23:59:59";

    /**
     * @author 周杰伦 2016-12-9
     */
    public enum FormatType {
        /**
         * yyyy, curdate()
         */
        YEAR,
        /**
         * yyyy-MM-dd, curdate()
         */
        DAY,
        /**
         * yyyy-MM-dd HH:mm:ss, now()
         */
        DAYTIME,
        /**
         * yyyyMMddHHmmssSSS, 17位毫秒时间序列号
         */
        MICROSECONDS,
        /**
         * yyyyMMddHHmmss, 14位毫秒时间序列号
         */
        MIDDLEMICROSECONDS,
        /**
         * yyMMddHHmmss, 12位毫秒时间序列号
         */
        SMALLMICROSECONDS,
        /**
         * 1406167122870, System.currentTimeInMillis()
         */
        JAVA,
        /**
         * 1406166160, unix_timestamp(now( ))
         */
        MYSQL,
        /**
         * yyyyMMdd
         */
        DAY3,
        /**
         * yyyy年MM月dd日
         */
        DAY4,
        /**
         * HHMMSS
         */
        SMALLMIHHMMSS,
        /**
         * HH:MM:SS
         */
        SMALLMIHHMMSS2,
        /**
         * MMddhhmmss
         */
        DAYTIME4,
        /**
         * yyyy.MM.dd
         */
        DIAN,
        /**
         * yyyy年MM月dd日 HH：mm
         */
        DAY5,
        /**
         * yyyy-MM
         */
        Month,
        /**
         * yyMMddHHmmssSSS
         */
        MICROSECONDSID
    }

    ;

    /**
     * @param time 支持格式：<li>yyyy-MM-dd, mysql: curdate()</li> <li>yyyy-MM-dd
     *             HH:mm:ss, mysql: now()</li> <li>1406167122870，java:
     *             System.currentTimeInMillis()</li> <li>1406166160，mysql:
     *             unix_timestamp(now())</li>
     */
    public static Date parse(String time) {
        if (time == null || time.isEmpty()) {
            return null;
        }
        try {
            synchronized (DAYTIME) {
                return DAYTIME.parse(time);
            }
        } catch (Exception ex) {
            try {
                synchronized (DAYTIME2) {
                    return DAYTIME2.parse(time);
                }
            } catch (ParseException e) {
                try {
                    synchronized (DAYTIME3) {
                        return DAYTIME3.parse(time);
                    }
                } catch (ParseException e1) {
                }
            }
        }
        try {
            synchronized (DAY) {
                return DAY.parse(time);
            }
        } catch (Exception e) {
            try {
                synchronized (DAY2) {
                    return DAY2.parse(time);
                }
            } catch (ParseException e1) {
            }
        }
        if (time.matches("\\d{10,17}")) {
            final int a = 17;
            final int b = 13;
            final int c = 12;
            final int d = 10;
            final int f = 14;
            final int g = 1000;
            if (time.length() == a) {
                try {
                    synchronized (MICROSECONDS) {
                        return MICROSECONDS.parse(time);
                    }
                } catch (Exception e) {
                }
            } else if (time.length() == b) {
                return new Date(Long.valueOf(time));
            } else if (time.length() == c) {
                try {
                    synchronized (SMALLMICROSECONDS) {
                        return SMALLMICROSECONDS.parse(time);
                    }
                } catch (Exception e) {
                }
            } else if (time.length() == d) {
                return new Date(Long.valueOf(time) * g);
            } else if (time.length() == f) {
                try {
                    synchronized (MIDDLEMICROSECONDS) {
                        return MIDDLEMICROSECONDS.parse(time);
                    }
                } catch (Exception e) {
                }
            }
        }
        return null;
    }

    /**
     * @param type 格式化日期<li>DAY yyyy-MM-dd, mysql: curdate()</li> <li>DAYTIME
     *             yyyy-MM-dd HH:mm:ss, mysql: now()</li> <li>JAVA
     *             1406167122870，java: System.currentTimeInMillis()</li> <li>
     *             MYSQL 1406166160，mysql: unix_timestamp(now())</li>
     */
    public static String format(Date date, FormatType type) {
        if (date == null) {
            return null;
        }
        final int a = 1000;
        switch (type) {
            case YEAR:
                synchronized (YEAR) {
                    return YEAR.format(date);
                }
            case DAY:
                synchronized (DAY) {
                    return DAY.format(date);
                }
            case DAYTIME:
                synchronized (DAYTIME) {
                    return DAYTIME.format(date);
                }
            case DAYTIME4:
                synchronized (DAYTIME4) {
                    return DAYTIME4.format(date);
                }
            case MICROSECONDS:
                synchronized (MICROSECONDS) {
                    return MICROSECONDS.format(date);
                }
            case SMALLMICROSECONDS:
                synchronized (SMALLMICROSECONDS) {
                    return SMALLMICROSECONDS.format(date);
                }
            case JAVA:
                return String.valueOf(date.getTime());
            case MYSQL:
                return String.valueOf(date.getTime() / a);
            case DAY3:
                synchronized (DAY3) {
                    return DAY3.format(date);
                }
            case DAY4:
                synchronized (DAY4) {
                    return DAY4.format(date);
                }
            case SMALLMIHHMMSS:
                synchronized (SMALLMIHHMMSS) {
                    return SMALLMIHHMMSS.format(date);
                }
            case SMALLMIHHMMSS2:
                synchronized (SMALLMIHHMMSS2) {
                    return SMALLMIHHMMSS2.format(date);
                }
            case MIDDLEMICROSECONDS:
                synchronized (MIDDLEMICROSECONDS) {
                    return MIDDLEMICROSECONDS.format(date);
                }
            case DIAN:
                synchronized (DIAN) {
                    return DIAN.format(date);
                }
            case DAY5:
                synchronized (DAY5) {
                    return DAY5.format(date);
                }
            case Month:
                synchronized (Month) {
                    return Month.format(date);
                }
            case MICROSECONDSID:
                synchronized (MICROSECONDSID) {
                    return MICROSECONDSID.format(date);
                }
        }
        return null;
    }

    public static Date getDayBegin(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 时间字符串转换成date
     *
     * @param timeStr
     * @return
     */
    public static Date isStrToDate(String timeStr) {
        Date date = null;
        try {
            date = DAYTIME.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date getNextDayBegin(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,
                calendar.get(Calendar.DAY_OF_MONTH) + 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date dayAdd(Date date, int interval) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)
                + interval);
        return calendar.getTime();
    }

    /**
     * 获取N月前的起始时间
     *
     * @param months 月份数
     * @return
     */
    public static Date getMonthHeadTime(int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - months);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取N月天前的起始时间
     *
     * @param days 天数
     * @return
     */
    public static Date getDayHeadTime(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)
                - days);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取time时间 N天前的起始时间
     *
     * @param time
     * @param days
     * @return
     */
    public static Date getDayHeadTime(Date time, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.DATE, -days);
        return calendar.getTime();
    }

    /**
     * 获取N小时前的起始时间
     *
     * @param hours 小时数
     * @return
     */
    public static Date getHourHeadTime(int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY)
                - hours);
        return calendar.getTime();
    }

    /**
     * 获取minute分钟前时间
     *
     * @param minute
     * @return
     */
    public static Date getMinuteHeadTime(int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - minute);
        return calendar.getTime();
    }

    /**
     * 将string类型的20151202 转化为2015-12-02
     *
     * @param date
     * @return
     */
    public static String formatString(String date) {
        String returnDate = "";
        if (ObjectHelper.isNotEmpty(date)) {
            SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
            try {
                returnDate = sf2.format(sf1.parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return returnDate;
    }

    // 获得本周一0点时间
    public static Date getTimesWeekmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY),
                cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    // 获得本周日24点时间
    public static Date getTimesWeeknight() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getTimesWeekmorning());
        final int a = 7;
        cal.add(Calendar.DAY_OF_WEEK, a);
        return cal.getTime();
    }

    // 获得本月第一天0点时间
    public static Date getTimesMonthmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY),
                cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH,
                cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    // 获得本月最后一天24点时间
    public static Date getTimesMonthnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY),
                cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH,
                cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        final int a = 24;
        cal.set(Calendar.HOUR_OF_DAY, a);
        return cal.getTime();
    }

    /**
     * 当前季度的开始时间
     *
     * @return
     */
    public static Date getCurrentQuarterStartTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            final int a = 3;
            final int b = 4;
            final int d = 6;
            final int f = 7;
            final int g = 9;
            final int h = 10;
            final int i = 12;

            if (currentMonth >= 1 && currentMonth <= a) {
                c.set(Calendar.MONTH, 0);
            } else if (currentMonth >= b && currentMonth <= d) {
                c.set(Calendar.MONTH, a);
            } else if (currentMonth >= f && currentMonth <= g) {
                c.set(Calendar.MONTH, d);
            } else if (currentMonth >= h && currentMonth <= i) {
                c.set(Calendar.MONTH, g);
            }
            c.set(Calendar.DATE, 1);
            // now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
            synchronized (DAYTIME) {
                now = DAYTIME.parse(DAY.format(c.getTime()) + " 00:00:00");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前季度的结束时间
     *
     * @return
     */
    public static Date getCurrentQuarterEndTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            final int a = 3;
            final int b = 31;
            final int d = 4;
            final int f = 6;
            final int g = 5;
            final int q = 30;
            final int w = 7;
            final int e = 9;
            final int r = 8;
            final int m = 10;
            final int v = 12;
            final int p = 11;
            if (currentMonth >= 1 && currentMonth <= a) {
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, b);
            } else if (currentMonth >= d && currentMonth <= f) {
                c.set(Calendar.MONTH, g);
                c.set(Calendar.DATE, q);
            } else if (currentMonth >= w && currentMonth <= e) {
                c.set(Calendar.MONTH, r);
                c.set(Calendar.DATE, q);
            } else if (currentMonth >= m && currentMonth <= v) {
                c.set(Calendar.MONTH, p);
                c.set(Calendar.DATE, b);
            }
            // now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
            synchronized (DAYTIME) {
                now = DAYTIME.parse(DAY.format(c.getTime()) + " 23:59:59");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获得时间差<天>
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static Integer getDays(String startTime, String endTime) {
        if (startTime.equals(endTime))
            return 1;
        Date start = parse(startTime);
        Date end = parse(endTime);
        Long timeLongs = end.getTime() - start.getTime();
        Double day = (double) (timeLongs / (1000 * 60 * 60 * 24));
        return day.intValue() + 1;
    }

    /**
     * 时间比较
     *
     * @param time1
     * @param time2
     * @return time1小于time2=true,否则false
     */
    public static boolean timeCompare(String time1, String time2) {
        try {
            Date time_1 = DAY.parse(time1);
            Date time_2 = DAY.parse(time2);
            return time_1.before(time_2);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 日期时间比较
     *
     * @param time1
     * @param time2
     * @return time1小于time2=true,否则false
     */
    public static boolean dateTimeCompare(String time1, String time2) {
        try {
            Date time_1 = DAYTIME.parse(time1);
            Date time_2 = DAYTIME.parse(time2);
            return time_1.before(time_2);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获得流水号 yyyyMMddHHmmssSSS
     *
     * @return
     */
    public static String getBizNo() {
        return DateUtil.format(new Date(), FormatType.MICROSECONDS);
    }

    /**
     * 获取当月的第一天
     *
     * @return
     */
    public static String currentMonthfirstDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        return format.format(c.getTime());

    }

    /**
     * 获取当月的最后一天
     *
     * @return
     */
    public static String currentMonthLastDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH,
                ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        return format.format(ca.getTime());

    }

    /**
     * 获取前月的第一天
     *
     * @return
     */
    public static String preMonthfirstDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal_1 = Calendar.getInstance();// 获取当前日期
        cal_1.add(Calendar.MONTH, -1);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        return format.format(cal_1.getTime());

    }

    /**
     * 获取前月的最后一天
     *
     * @return
     */
    public static String preMonthLastDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH, 0);// 设置为1号,当前日期既为本月第一天
        return format.format(cale.getTime());

    }

    public static void main(String[] args) {
        ArrayList<String> linkedList = new ArrayList<String>();
        linkedList.add(null);
        linkedList.add(null);
        linkedList.add(null);
        System.out.println(linkedList.size());
    }
}
