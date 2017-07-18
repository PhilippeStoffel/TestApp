package com.example.philippe.testapp.utils;

import com.example.philippe.testapp.R;
import com.example.philippe.testapp.TestActivity;
import com.example.philippe.testapp.utils.ViewManager.ViewManager;
import com.example.philippe.testapp.utils.ViewManager.ViewUtils;

import android.support.test.rule.ActivityTestRule;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.philippe.testapp.matcher.EspressoTestsMatchers.withDrawable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by philippe on 13/07/2017.
 */

public class ViewUtilsTest {

    @Rule
    public ActivityTestRule<TestActivity> rule  = new ActivityTestRule<TestActivity>(TestActivity.class);



    private ViewManager viewManager;

    public ViewUtilsTest() {
    }

    @Before
    public void initManager()
    {
        viewManager = new ViewManager(rule.getActivity());
    }

    @Test
    public void getViews()
    {
        assertThat(viewManager.getView(R.id.activity_test_button).getId(), is(R.id.activity_test_button));
        assertThat(viewManager.getView(R.id.activity_test_textview).getId(), is(R.id.activity_test_textview));
        assertThat(viewManager.getView(R.id.activity_test_imageview).getId(), is(R.id.activity_test_imageview));
        assertThat(viewManager.getView(R.id.activity_test_edittext).getId(), is(R.id.activity_test_edittext));
    }

    @Test
    public void showActionBar()
    {
        onView(withId(R.id.activity_test_button_showactionbar)).perform(click());
        assertThat(rule.getActivity().getSupportActionBar().isShowing(), is(false));
        onView(withId(R.id.activity_test_button_showactionbar)).perform(click());
        assertThat(rule.getActivity().getSupportActionBar().isShowing(), is(true));
        /*
        ViewUtils.showActionBar(rule.getActivity(), false);
        assertThat(rule.getActivity().getSupportActionBar().isShowing(), is(false));
        ViewUtils.showActionBar(rule.getActivity(), true);
        assertThat(rule.getActivity().getSupportActionBar().isShowing(), is(true));*/
    }

    @Test
    public void setTextView()
    {
        onView(withId(R.id.activity_test_button_settextview)).perform(click());
        onView(withId(R.id.activity_test_textview)).check(matches(withText(TestActivity.TEST_STRING)));
    }

    @Test
    public void setImageView()
    {
        onView(withId(R.id.activity_test_button_setimageview)).perform(click());
        onView(withId(R.id.activity_test_imageview)).check(matches(withDrawable(R.mipmap.ic_launcher)));
    }

    @Test
    public void setOnClickListener()
    {
        onView(withId(R.id.activity_test_button_setonclicklistener)).perform(click());
        onView(withId(R.id.activity_test_imageview)).perform(click());
        onView(withId(R.id.activity_test_imageview)).check(matches(not(isDisplayed())));
    }

    @Test
    public void setVisibility()
    {
        onView(withId(R.id.activity_test_button_setvisibility)).perform(click());
        onView(withId(R.id.activity_test_imageview)).check(matches(isDisplayed()));
    }

}
