package com.example.philippe.testapp.matcher;

import android.view.View;

import org.hamcrest.Matcher;

/**
 * Created by philippe on 13/07/2017.
 */

public class EspressoTestsMatchers {

    public static Matcher<View> withDrawable(final int resourceId) {
        return new DrawableMatcher(resourceId);
    }

    public static Matcher<View> noDrawable() {
        return new DrawableMatcher(-1);
    }
}
