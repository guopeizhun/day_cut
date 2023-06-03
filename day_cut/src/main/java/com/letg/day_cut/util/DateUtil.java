package com.letg.day_cut.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String getFormatDate(Date date,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
}
