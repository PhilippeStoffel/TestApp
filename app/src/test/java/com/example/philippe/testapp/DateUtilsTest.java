import com.example.philippe.testapp.utils.DateUtils;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by philippe on 06/07/2017.
 */

public class DateUtilsTest {

    @Test
    public void roundToDay_ReturnsCurrentRoundedTime() {
        Date now = new Date();
        Calendar c = new GregorianCalendar();
        c.setTime(now);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Date rounded = c.getTime();
        assertThat(DateUtils.roundToDay(new Date()), is(rounded));
    }

    @Test
    public void formatSeconds() {
        int time = 30 * 60 + 15; // 30:15
        assertThat(DateUtils.formatSeconds(time), is("30:15"));
        time = 0;
        assertThat(DateUtils.formatSeconds(time), is("0:00"));
        time = 2 * 60; // 2:00
        assertThat(DateUtils.formatSeconds(time), is("2:00"));
        time = 32; // 0:32
        assertThat(DateUtils.formatSeconds(time), is("0:32"));
    }

    @Test
    public void parseDateForJSONSerialization() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.FRANCE);
        SimpleDateFormat formatWithoutHour = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
        String date = "1723-01-30 13:32:48"; // 30:15
        assertThat(DateUtils.parseDateForJSONSerialization(date, true), is(format.parse(date)));
        assertThat(DateUtils.parseDateForJSONSerialization(date, false), is(formatWithoutHour.parse(date)));
    }

    @Test
    public void getDaysBetweenDates() {
        Date start = DateUtils.parseDateForJSONSerialization("1723-01-30 13:32:48", true);
        Date end = DateUtils.parseDateForJSONSerialization("1723-02-03 16:32:48", true);
        assertThat(DateUtils.getDaysBetweenDates(start, end), is(4));
        start = DateUtils.parseDateForJSONSerialization("1996-05-30 20:32:48", true);
        end = DateUtils.parseDateForJSONSerialization("2017-07-06 16:32:48", true);
        assertThat(DateUtils.getDaysBetweenDates(start, end), is(7707));
        start = DateUtils.parseDateForJSONSerialization("2017-07-06 18:32:48", true);
        end = DateUtils.parseDateForJSONSerialization("2017-07-06 16:32:48", true);
        assertThat(DateUtils.getDaysBetweenDates(start, end), is(0));
        start = DateUtils.parseDateForJSONSerialization("2018-07-06 18:32:48", true);
        end = DateUtils.parseDateForJSONSerialization("2017-07-06 16:32:48", true);
        assertThat(DateUtils.getDaysBetweenDates(start, end), is(-365));
    }

    @Test
    public void isDateToday() {
        Date now = new Date();
        assertThat(DateUtils.isDateToday(now), is(true));
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.add(Calendar.DATE, 1);  // number of days to add
        assertThat(DateUtils.isDateToday(c.getTime()), is(false));
        c.setTime(now);
        c.add(Calendar.DATE, -1);  // number of days to add
        assertThat(DateUtils.isDateToday(c.getTime()), is(false));
    }

    @Test
    public void getFormatedBirthday() {
        Date now = DateUtils.parseDateForJSONSerialization("1988-01-26 12:30:24", true);
        assertThat(DateUtils.getFormatedBirthday(now), is("26 / 01 / 1988"));
    }

    @Test
    public void getAgeFromDateOfBirth() {
        Date now = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.add(Calendar.YEAR, -30);  // number of days to add
        assertThat(DateUtils.getAgeFromDateOfBirth(c.getTime()), is(30));
        now = c.getTime();
        c.setTime(now);
        c.add(Calendar.DAY_OF_YEAR, 1);  // number of days to add
        assertThat(DateUtils.getAgeFromDateOfBirth(c.getTime()), is(29));
        c.setTime(now);
        c.add(Calendar.YEAR, 1);  // number of days to add
        assertThat(DateUtils.getAgeFromDateOfBirth(c.getTime()), is(29));
        c.setTime(now);
        c.add(Calendar.MONTH, -1);  // number of days to add
        assertThat(DateUtils.getAgeFromDateOfBirth(c.getTime()), is(30));
        c.setTime(now);
        c.add(Calendar.YEAR, -1);  // number of days to add
        c.add(Calendar.MONTH, 1);  // number of days to add
        assertThat(DateUtils.getAgeFromDateOfBirth(c.getTime()), is(30));
        c.setTime(now);
        c.add(Calendar.YEAR, -1);  // number of days to add
        assertThat(DateUtils.getAgeFromDateOfBirth(c.getTime()), is(31));
    }
/*
    @Test
    public void getCurrentDateInTimeZone() {
        Date now = new Date();
        Date timeZone = DateUtils.getCurrentDateInTimeZone(null);
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(timeZone);
        assertThat(c2.get(Calendar.HOUR), is(c.get(Calendar.HOUR)));
        now = DateUtils.getCurrentDateInTimeZone("CET");
        timeZone = DateUtils.getCurrentDateInTimeZone("UTC");
        c.setTime(now);
        c2.setTime(timeZone);
        assertThat(c2.get(Calendar.HOUR), is(c.get(Calendar.HOUR) + 2));
    }*/
}
