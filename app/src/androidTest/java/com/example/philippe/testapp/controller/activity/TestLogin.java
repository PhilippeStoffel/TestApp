package com.example.philippe.testapp.controller.activity;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.philippe.testapp.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestLogin {

    @Rule
    public ActivityTestRule<GuideTour> mActivityTestRule = new ActivityTestRule<>(GuideTour.class);

    @Test
    public void testLogin() {

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.buttonLogin), withText("LOG IN"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editText), isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editText), isDisplayed()));
        appCompatEditText2.perform(replaceText("philippe4500@yopmail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.editText2), isDisplayed()));
        appCompatEditText3.perform(click());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.editText2), isDisplayed()));
        appCompatEditText4.perform(replaceText("fizzup"), closeSoftKeyboard());
/*
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.button2), withText("LOG IN"), isDisplayed()));
        appCompatButton2.perform(click());


        String successString = "Niveau";
        onView(withId(R.id.textViewLevel)).check(matches(allOf(withText(successString), isDisplayed())));*/
    }
}
