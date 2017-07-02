package cn.miracle.octts.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tony on 2017/7/1.
 */
public class DateConvert {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date string2Date(String date) throws ParseException{
        return dateFormat.parse(date);
    }

    public static Date string2Datetime(String date) throws ParseException {
        return datetimeFormat.parse(date);
    }

    public static String Datetime2String (Date datetime) throws ParseException {
        return datetimeFormat.format(datetime);
    }

    public static String Date2String (Date date) throws ParseException {
        return dateFormat.format(date);
    }
}
