package com.example.philippe.testapp.utils.ViewManager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.SparseArrayCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by philippe on 11/07/2017.
 */

public class ViewManager {

    private Context mContext;
    private SparseArrayCompat<View> mViews;

    public ViewManager(Activity activity)
    {
        //this(activity, (ViewGroup) activity.findViewById(android.R.id.content));
        this(activity, (ViewGroup) activity.getWindow().getDecorView().getRootView());
    }

    public ViewManager(Context context, ViewGroup rootView)
    {
        mContext = context;
        mViews = ViewUtils.getViews(rootView);
    }

    public void setImageView(int viewId, int imgId)
    {
        ViewUtils.setImageView(mContext, (ImageView) getView(viewId), imgId);
    }

    public void loadImageView(int viewId, String imgUrl)
    {
        ViewUtils.loadImageView(mContext, (ImageView) getView(viewId), imgUrl);
    }

    public void loadImageViewWithPlaceholder(int viewId, String imgUrl, int placeholderId)
    {
        ViewUtils.loadImageViewWithPlaceholder(mContext, (ImageView) getView(viewId), imgUrl, placeholderId);
    }

    public void setTextView(int textViewId, int stringId)
    {
        ViewUtils.setTextView(mContext, (TextView) getView(textViewId), stringId);
    }

    public void setTextView(int textViewId, String text)
    {
        ViewUtils.setTextView((TextView) getView(textViewId), text);
    }

    public void setFont(int textViewId, Typeface typeface)
    {
        ViewUtils.setFont((TextView) getView(textViewId), typeface);
    }

    public void setOnClickListener(int viewId, View.OnClickListener listener)
    {
        ViewUtils.setOnClickListener(getView(viewId), listener);
    }

    public void setVisibility(int viewId, int visibility)
    {
        ViewUtils.setVisibility(getView(viewId), visibility);
    }

    public View getView(int viewId)
    {
        if (mViews != null)
            return mViews.get(viewId, null);
        return null;
    }
}
