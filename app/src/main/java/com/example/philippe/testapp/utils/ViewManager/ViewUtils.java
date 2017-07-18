package com.example.philippe.testapp.utils.ViewManager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewGroupCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.HashMap;

/**
 * Created by philippe on 11/07/2017.
 */

public class ViewUtils {

    public static SparseArrayCompat<View> getViews(ViewGroup viewGroup)
    {
        SparseArrayCompat<View> views = new SparseArrayCompat<View>();
        fetchChildViews(viewGroup, views);
        return views;
    }

    private static void fetchChildViews(ViewGroup viewGroup, SparseArrayCompat<View> array)
    {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            final View child = viewGroup.getChildAt(i);
            if (child != null) {
                if (child instanceof ViewGroup) {
                    fetchChildViews((ViewGroup) child, array);
                }
                array.append(child.getId(), child);
            }
        }
    }

    public static void showActionBar(AppCompatActivity context, boolean show)
    {
        if (context.getSupportActionBar() != null) {
            if (show)
                context.getSupportActionBar().show();
            else
                context.getSupportActionBar().hide();
        }
    }

    public static void setImageView(Activity context, int viewId, int imgId)
    {
        ImageView img = (ImageView) context.findViewById(viewId);
        setImageView(context, img, imgId);
    }

    public static void setImageView(Context context, ImageView view, int imgId)
    {
        if (view != null)
            view.setImageDrawable(ContextCompat.getDrawable(context, imgId));
    }

    public static void loadImageView(Activity context, int viewId, String imgUrl)
    {
        ImageView img = (ImageView) context.findViewById(viewId);
        loadImageView(context, img, imgUrl);
    }

    public static void loadImageView(Context context, ImageView view, String imgUrl)
    {
        if (view != null) {
            Glide.with(context).load(imgUrl).into(view);
        }
    }

    public static void loadImageViewWithPlaceholder(Activity context, int viewId, String imgUrl, int placeholderId)
    {
        ImageView img = (ImageView) context.findViewById(viewId);
        loadImageViewWithPlaceholder(context, img, imgUrl, placeholderId);
    }

    public static void loadImageViewWithPlaceholder(Context context, ImageView view, String imgUrl, int placeholderId)
    {
        if (view != null) {
            Glide.with(context).load(imgUrl).fallback(placeholderId)
                    .error(placeholderId).into(view);
        }
    }

    public static void setTextView(Activity context, int textViewId, int stringId)
    {
        TextView txt = (TextView) context.findViewById(textViewId);
        setTextView(context, txt, stringId);
    }

    public static void setTextView(Context context, TextView textView, int stringId)
    {
        if (textView != null)
            textView.setText(context.getString(stringId));
    }

    public static void setTextView(Activity context, int textViewId, String text)
    {
        TextView txt = (TextView) context.findViewById(textViewId);
        setTextView(txt, text);
    }

    public static void setTextView(TextView textView, String text)
    {
        if (textView != null && text != null)
            textView.setText(text);
    }

    public static void setFont(Activity context, int textViewId, Typeface typeface)
    {
        TextView txt = (TextView) context.findViewById(textViewId);
        setFont(txt, typeface);
    }

    public static void setFont(TextView textView, Typeface typeface)
    {
        if (textView != null && typeface != null)
            textView.setTypeface(typeface);
    }

    public static void setOnClickListener(Activity context, int viewId, View.OnClickListener listener)
    {
        View v = context.findViewById(viewId);
        setOnClickListener(v, listener);
    }

    public static void setOnClickListener(View view, View.OnClickListener listener)
    {
        if (view != null && listener != null)
        {
            view.setOnClickListener(listener);
        }
    }

    public static void setVisibility(Activity context, int viewId, int visibility)
    {
        View v = context.findViewById(viewId);
        setVisibility(v, visibility);
    }

    public static void setVisibility(View view, int visibility)
    {
        if (view != null)
        {
            view.setVisibility(visibility);
        }
    }
}
