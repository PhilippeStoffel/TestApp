package com.example.philippe.testapp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.util.SparseArrayCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.philippe.testapp.R;
import com.example.philippe.testapp.utils.ViewManager.ViewUtils;

/**
 * Created by philippe on 12/07/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class ViewUtilsTest {

    private static final String FAKE_STRING = "HELLO WORLD";
    private static final int FAKE_TEXTVIEW_ID = 13;
    private static final int FAKE_IMAGEVIEW_ID = 14;
    private static final int FAKE_BUTTON_ID = 15;
    private static final int FAKE_RELATIVELAYOUT_ID = 16;

    @Mock
    Context mMockContext;
    @Mock
    LinearLayout mMockLinearLayout;
    @Mock
    TextView mMockTextView;
    @Mock
    ImageView mMockImageView;
    @Mock
    Button mMockButton;
    @Mock
    RelativeLayout mMockRelativeLayout;

    @Before
    public void buildMockLayout()
    {
        when(mMockTextView.getId())
                .thenReturn(FAKE_TEXTVIEW_ID);
        when(mMockImageView.getId())
                .thenReturn(FAKE_IMAGEVIEW_ID);
        when(mMockButton.getId())
                .thenReturn(FAKE_BUTTON_ID);
        when(mMockRelativeLayout.getId())
                .thenReturn(FAKE_RELATIVELAYOUT_ID);
        when(mMockLinearLayout.getChildCount())
                .thenReturn(3);
        when(mMockRelativeLayout.getChildCount())
                .thenReturn(1);
        when(mMockLinearLayout.getChildAt(0))
                .thenReturn(mMockTextView);
        when(mMockLinearLayout.getChildAt(1))
                .thenReturn(mMockImageView);
        when(mMockLinearLayout.getChildAt(2))
                .thenReturn(mMockRelativeLayout);
        when(mMockRelativeLayout.getChildAt(0))
                .thenReturn(mMockButton);
    }

    @Test
    public void getViews() {
        SparseArrayCompat<View> array = ViewUtils.getViews(mMockLinearLayout);
        assertThat((TextView)array.get(FAKE_TEXTVIEW_ID), is(mMockTextView));
        assertThat((ImageView)array.get(FAKE_IMAGEVIEW_ID), is(mMockImageView));
        assertThat((Button)array.get(FAKE_BUTTON_ID), is(mMockButton));
        assertThat((RelativeLayout)array.get(FAKE_RELATIVELAYOUT_ID), is(mMockRelativeLayout));
        // Given a mocked Context injected into the object under test...
    }
}