import com.example.philippe.testapp.utils.DateUtils;
import com.example.philippe.testapp.utils.SystemUtils;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by philippe on 10/07/2017.
 */

public class LocaleTest {

    @Test
    public void getLanguage() {
        String lang = Locale.getDefault().getLanguage();
        assertThat(SystemUtils.getLanguage(), is(lang));
    }

    @Test
    public void getAppLanguage() {
        String lang = Locale.getDefault().getLanguage();
        assertThat(SystemUtils.getAppLanguage(), anyOf(is(lang), is(SystemUtils.DEFAULT_LANGUAGE)));
    }

}
