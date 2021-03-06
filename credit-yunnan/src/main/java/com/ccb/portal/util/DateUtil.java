package com.ccb.portal.util;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Title 说明：日期处理
 * @Date
 * @author
 *
 *
 */
public class DateUtil {

    //private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
    //private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
    //private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");
    //private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //private final static SimpleDateFormat sdfTimes = new SimpleDateFormat("yyyyMMddHHmmss");
    //private final static SimpleDateFormat sdfTimesS = new SimpleDateFormat("yyyyMMddHHmmssS");

    /**
     * 获取yyyyMMddHHmmss格式
     * @return
     */
    public static String getSdfTimes() {
        SimpleDateFormat sdfTimes = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdfTimes.format(new Date());
    }

    /**
     * yyyyMMddHHmmssS
     *
     * @return
     */
    public static String getSdfTimesS() {
        SimpleDateFormat sdfTimesS = new SimpleDateFormat("yyyyMMddHHmmssS");
        return sdfTimesS.format(new Date());
    }

    /**
     * 获取YYYY格式
     *
     * @return
     */
    public static String getYear() {
        SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
        return sdfYear.format(new Date());
    }

    /**
     * 获取mm格式
     *
     * @return
     */
    public static String getMeth() {
        SimpleDateFormat sdfYear = new SimpleDateFormat("MM");
        return sdfYear.format(new Date());
    }

    /**
     * 获取dd格式
     *
     * @return
     */
    public static String getDayss() {
        SimpleDateFormat sdfYear = new SimpleDateFormat("dd");
        return sdfYear.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD格式
     *
     * @return
     */
    public static String getDay() {
        SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
        return sdfDay.format(new Date());
    }


    /**
     * 获取YYYYMMDD格式
     *
     * @return
     */
    public static String getDays2() {
        SimpleDateFormat sdfDay = new SimpleDateFormat("yyyyMMdd");
        return sdfDay.format(new Date());
    }


    public static String getDays() {
        SimpleDateFormat sdfDays = new SimpleDateFormat("yyyy年MM月dd日");
        return sdfDays.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     *
     * @return
     */
    public static String getTime() {

        SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdfTime.format(new Date());
    }


    public static String getTimes() {

        SimpleDateFormat sdfTime = new SimpleDateFormat("HHmmss");
        return sdfTime.format(new Date());
    }

    public static String getDateTimes() {

        SimpleDateFormat sdfTime = new SimpleDateFormat("yyyyMMddHHmm");
        return sdfTime.format(new Date());
    }

    /**
     * @param s
     * @param e
     * @return boolean
     * @throws
     * @Title: compareDate
     * @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
     * @author prod
     *
     */
    public static boolean compareDate(String s, String e) {
        if (fomatDate(s) == null || fomatDate(e) == null)
        {
            return false;
        }
        return fomatDate(s).getTime() >= fomatDate(e).getTime();
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static Date fomatDate(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            return fmt.parse(date);
        } catch (ParseException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static String dateToDateStr(Date date) {
        SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
        if(date!=null){
        	return sdfDay.format(date);
        }else{
        	return "";
        }
        
    }

    public static Date fomatDateTime(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            return fmt.parse(date);
        } catch (ParseException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 校验日期是否合法
     *
     * @return
     */
    public static boolean isValidDate(String s) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            fmt.parse(s);
            return true;
        } catch (Exception e)
        {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
    }

    /**
     * @param startTime
     * @param endTime
     * @return
     */
    public static int getDiffYear(String startTime, String endTime) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            //long aa=0;
            int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
            return years;
        } catch (Exception e)
        {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return 0;
        }
    }

    /**
     * <li>功能描述：时间相减得到天数
     *
     * @param beginDateStr
     * @param endDateStr
     * @return long
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr, String endDateStr) {
        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;

        try
        {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        //System.out.println("相隔的天数="+day);

        return day;
    }

    public static long getDaySub(Date beginDate, Date endDate) {
        long day = 0;
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     * 得到n天之后的日期
     *
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);

        return dateStr;
    }

    /**
     * 得到n天之后是周几
     *
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
        int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        return dateStr;
    }
    public static String LongToDate(String lon){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
        Date  lo=new Date(Long.valueOf(lon));
        String da=simpleDateFormat.format(lo);
        return da;
    }
    public static void main(String[] args) {
//        System.out.println(getDays());
//        System.out.println(getAfterDayWeek("3"));
//
//        String str = "0123456789";
//        String substring = StringUtils.substring(str, 0, 5);
//        System.out.println("substring = " + substring);
//
//        System.out.println("getSdfTimesS() = " + getSdfTimesS());

//        System.out.println(da);

    }

}
