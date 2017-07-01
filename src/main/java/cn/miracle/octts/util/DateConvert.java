package cn.miracle.octts.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tony on 2017/7/1.
 */
public class DateConvert {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Date string2Date(String date) throws java.text.ParseException{
        return dateFormat.parse(date);
    }
}
