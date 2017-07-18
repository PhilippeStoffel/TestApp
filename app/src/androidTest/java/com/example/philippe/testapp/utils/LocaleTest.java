package com.example.philippe.testapp.utils;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.example.philippe.testapp.model.core.localization.Language;
import com.example.philippe.testapp.model.database.FizzupDatabase;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Locale;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by philippe on 10/07/2017.
 */

@RunWith(AndroidJUnit4.class)
@SmallTest
public class LocaleTest {

    @Test
    public void getLanguageLocale() {
        Locale lang = Locale.getDefault();
        if (lang.getLanguage().equals("fr"))
            assertThat(SystemUtils.getLanguageLocale(), is(lang));
        else
            assertThat(SystemUtils.getLanguageLocale(), is(Locale.US));
    }

    @Test
    public void getFavoriteLanguage() {
        Locale lang = Locale.getDefault();
        if (lang.getLanguage().equals("fr"))
            assertThat(Language.getFavorite(FizzupDatabase.getSQLiteDatabase()).getCode(), is(lang.getLanguage()));
        else
            assertThat(Language.getFavorite(FizzupDatabase.getSQLiteDatabase()).getCode(), is(SystemUtils.DEFAULT_LANGUAGE));
    }
}
