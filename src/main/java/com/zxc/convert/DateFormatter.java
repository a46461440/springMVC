package com.zxc.convert;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DateFormatter implements Formatter<Date> {

    private String pattern;

    public Date parse(String s, Locale locale) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.pattern);
        try {
            Date date = simpleDateFormat.parse(s);
            return date;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String print(Date date, Locale locale) {
        return date.toString();
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}