package com.example.philippe.testapp.utils;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by haroldbouley on 22/09/2014.
 */
public class DateUtils {

    public static SimpleDateFormat dateFormatterForJSONSerialization() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.FRANCE);
    }

    public static SimpleDateFormat dateFormatterForJSONSerializationWithoutHours() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
    }

    public static Date parseDateForJSONSerialization(String date, boolean displayHours) {
        try {
            if (displayHours)
                return dateFormatterForJSONSerialization().parse(date);
            return dateFormatterForJSONSerializationWithoutHours().parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String formatDateForJSONSerialization(Date date, String timeZone, boolean displayHours) {
        if (date != null)
        {
            SimpleDateFormat dateFormat;
            if (displayHours)
                dateFormat = dateFormatterForJSONSerialization();
            else
                dateFormat = dateFormatterForJSONSerializationWithoutHours();
            if (timeZone != null && !timeZone.equals(""))
                dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
            return dateFormat.format(date);
        }
        return "";
    }

    public static String formatDateForJSONSerialization(Date date, boolean displayHours) {
        return formatDateForJSONSerialization(date, null, displayHours);
    }

    public static boolean isDateToday(Date date)
    {
        if (date == null)
            return false;
        return (getDaysBetweenDates(date, new Date()) == 0);/*
        Date dateWithoutHours = null;
        Date currentDateWithoutHours = null;

        SimpleDateFormat dateFormatter = dateFormatterForJSONSerializationWithoutHours();
        try {
            dateWithoutHours = dateFormatter.parse(dateFormatter.format(date));
            currentDateWithoutHours = dateFormatter.parse(dateFormatter.format(new Date()));
        } catch (ParseException e) {
            return false;
        }

        return dateWithoutHours.compareTo(currentDateWithoutHours) == 0;*/
    }

    public static long daysBetweenDates(Date fromDate, Date toDate)
    {
        Date fromDateWithoutHours = null;
        Date toDateWithoutHours = null;

        SimpleDateFormat dateFormatter = dateFormatterForJSONSerializationWithoutHours();
        try {
            fromDateWithoutHours = dateFormatter.parse(dateFormatter.format(fromDate));
            toDateWithoutHours = dateFormatter.parse(dateFormatter.format(toDate));
        } catch (ParseException e) {
            return 0;
        }

        return (toDateWithoutHours.getTime() - fromDateWithoutHours.getTime()) / (24 * 3600 * 1000);
    }

    public static int getAgeFromDateOfBirth(Date dateOfBirth)
    {
        Calendar dob = Calendar.getInstance();
        dob.setTime(dateOfBirth);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
            age--;
        } else if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
                && today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
            age--;
        }

        return age;
    }

    public static String getFormatedBirthday(Date birthdayDate)
    {
        if(birthdayDate != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd / MM / yyyy");
            return simpleDateFormat.format(birthdayDate.getTime());
        }
        return "";
    }

    // YYYY-MM-DD h:mm:ss
    public static String getLogDateAndTime(String timeZoneStr, boolean appendTimezone)
    {
        return formatDate("yyyy-MM-dd HH:mm:ss", timeZoneStr) + (appendTimezone ? timeZoneStr : "");
        /*Calendar cal = Calendar.getInstance();
        return Integer.toString(cal.get(Calendar.YEAR)) + '-' + addZeroToDate(cal.get(Calendar.MONTH)) + '-'
                + addZeroToDate(cal.get(Calendar.DAY_OF_MONTH)) + ' ' + cal.get(Calendar.HOUR_OF_DAY) + ':'
                + addZeroToDate(cal.get(Calendar.MINUTE)) + ':' + addZeroToDate(cal.get(Calendar.SECOND));*/
    }


    public static String getLogLineDateAndTime(String timeZoneStr, boolean appendTimezone)
    {
        return formatDate("yyyy-MM-dd'T'HH:mm:ssZ", timeZoneStr) + (appendTimezone ? timeZoneStr : "");
        /*Calendar cal = Calendar.getInstance();
        return Integer.toString(cal.get(Calendar.YEAR)) + '-' + addZeroToDate(cal.get(Calendar.MONTH)) + '-'
                + addZeroToDate(cal.get(Calendar.DAY_OF_MONTH)) + ' ' + cal.get(Calendar.HOUR_OF_DAY) + ':'
                + addZeroToDate(cal.get(Calendar.MINUTE)) + ':' + addZeroToDate(cal.get(Calendar.SECOND));*/
    }

    /**
     * Generate a string for the current time in the given timezone and format
     * @param formatStr Format for the returned String (e.g. "yyyyMMdd")
     * @param timeZoneStr Timezone for which to display the date
     * @return A String for the current time in the given timezone and format
     */
    private static String formatDate(String formatStr, String timeZoneStr)
    {
        TimeZone timeZone = TimeZone.getTimeZone(timeZoneStr);
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(formatStr, Locale.FRANCE);
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat.format(getCurrentDateInTimeZone(timeZoneStr));
    }

    public static Date getCurrentDateInTimeZone(String timeZone)
    {
        if (timeZone == null || timeZone.equals(""))
            return new Date();
        return Calendar.getInstance(TimeZone.getTimeZone(timeZone)).getTime();
    }

    public static int getRoundedMinutesFromSeconds(int seconds)
    {
        int minutes = seconds / 60;
        return (seconds % 60 >= 30 ? minutes + 1 : minutes);
    }

    public static int getDaysBetweenDates(Date start, Date end)
    {
        return getDaysBetweenDates(start, end, false);
    }

    public static int getDaysBetweenDates(Date start, Date end, boolean ceil) {
        if (start == null || end == null)
            return 0;
        /*
        double nMilliSecondsInADay = 1000 * 24 * 3600;
        double diff = (end.getTime() - start.getTime()) / (nMilliSecondsInADay);
        if (ceil)
            return (int) Math.ceil(diff);
        return (int) Math.floor(diff);
        */
        long diff = roundToDay(end).getTime() - roundToDay(start).getTime();
        // Why + 1?
        //return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static String formatSeconds(int time)
    {
        int minutes = time / 60;
        int seconds = time % 60;
        return String.format("%d:%02d", minutes, seconds);
    }

    public static Date roundToDay(Date date) {
        if (date == null)
            return null;
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }
}
