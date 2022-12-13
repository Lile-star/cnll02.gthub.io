package com.crm.commons;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final String nowDateTimeToString(){
        return sf.format(new Date());
    }
}
