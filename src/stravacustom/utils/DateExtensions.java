package stravacustom.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateExtensions {
    public static Date parseFromString(String s, String format) {

        DateFormat dateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
        Date date = null;
        try {
            date = dateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
