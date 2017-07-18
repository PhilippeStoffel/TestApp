package com.example.philippe.testapp.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v4.content.LocalBroadcastManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.example.philippe.testapp.BuildConfig;
import com.example.philippe.testapp.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by haroldbouley on 18/08/2014. -
 */

public class SystemUtils {


    public static final String SUBSCRIPTION_PRICE_LOWER = "price_lower";
    public static final String SUBSCRIPTION_PRICE_HIGHER = "price_higher";

    public static final String FRENCH_LANGUAGE = "fr";
    public static final String ENGLISH_LANGUAGE = "en";
    public static final String DEFAULT_LANGUAGE = ENGLISH_LANGUAGE;

    private static final String[] mSupportedLanguages = {FRENCH_LANGUAGE, ENGLISH_LANGUAGE};

    public static final Locale FRENCH_LOCALE = Locale.FRANCE;
    public static final Locale ENGLISH_LOCALE = Locale.US;
    public static final Locale DEFAULT_LOCALE = ENGLISH_LOCALE;

    private static final Locale[] mSupportedLocales = {FRENCH_LOCALE, ENGLISH_LOCALE};


    public static final char FIZZUP_SYMBOL_CREDITS = 0x20A3;

    // Calculate free storage space in internal memory
    public static long getAvailableStorage()
    {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = (long)stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return blockSize * availableBlocks;
    }

    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;

        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        is.close();

        return sb.toString();
    }

    public static int getVersionNumber() {
        return BuildConfig.VERSION_CODE;
    }

    public static long getCurrentTimestamp() { return System.currentTimeMillis()/1000; }

    public static String convertDateToStringTimestamp(Date date) {
        if (date == null) {
            return null;
        }
        return Long.toString(date.getTime());
    }

    public static Date convertStringTimestampToDate(String timestampStr) {
        // Check if String contains else
        if (timestampStr == null) {
            return null;
        }
        try {
            Long timestamp = Long.parseLong(timestampStr);
            return new Date(timestamp);
        } catch (Exception e) {
            return null;
        }
    }

    public static int getScreenWidth(Activity activity)
    {
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public static int getScreenHeight(Activity activity)
    {
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    public static int getScreenOrientation(Activity activity) {
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        int orientation;
        // if the device's natural orientation is portrait:
        if ((rotation == Surface.ROTATION_0
                || rotation == Surface.ROTATION_180) && height > width ||
                (rotation == Surface.ROTATION_90
                        || rotation == Surface.ROTATION_270) && width > height) {
            switch(rotation) {
                case Surface.ROTATION_0:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
                case Surface.ROTATION_90:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    break;
                case Surface.ROTATION_180:
                    orientation =
                            ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
                    break;
                case Surface.ROTATION_270:
                    orientation =
                            ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
                    break;
                default:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
            }
        }
        // if the device's natural orientation is landscape or if the device
        // is square:
        else {
            switch(rotation) {
                case Surface.ROTATION_0:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    break;
                case Surface.ROTATION_90:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    break;
                case Surface.ROTATION_180:
                    orientation =
                            ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
                    break;
                case Surface.ROTATION_270:
                    orientation =
                            ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
                    break;
                default:
                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    break;
            }
        }

        return orientation;
    }

    public static boolean isOnline(Context ctx)
    {
        if (ctx != null) {
            ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo netInfo = cm.getActiveNetworkInfo();

            if (netInfo != null && netInfo.isConnected()) {
                return true;
            }
        }
        return false;
    }

    public static double getRadiusFromBoost(double boost)
    {
        return boost * 60;
    }

    public static void customedAlertView (Context ctx, String title, String message, String button) {

        Activity activity = (Activity) ctx;
        if (activity != null && !activity.isFinishing())
        {
            AlertDialog alertDialog = new AlertDialog.Builder(ctx).create();
            alertDialog.setTitle(title);
            alertDialog.setMessage(message);
            alertDialog.setButton(button, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // here you can add functions
                }
            });
            alertDialog.show();
        }
    }

    public static boolean checkEmailFormat (String email) {

        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(email);

        return m.matches();
    }

    public static boolean contains( String haystack, String needle ) {
        haystack = haystack == null ? "" : haystack;
        needle = needle == null ? "" : needle;

        return haystack.toLowerCase().contains(needle.toLowerCase());
    }

    public static int randomInt(int min, int max)
    {
        Random rand = new Random();
        return rand.nextInt(max - min) + min;
    }

    public static void setImage(View imageView, Drawable bd) {
        /*
        if(Build.VERSION.SDK_INT > 16) {
            imageView.setBackground(bd);
        } else {
            imageView.setBackgroundDrawable(bd);
        }
        */
        imageView.setBackgroundDrawable(bd);
    }

    public static String firstLetterUppercase(String userIdea)
    {
        char[] stringArray = userIdea.toCharArray();
        stringArray[0] = Character.toUpperCase(stringArray[0]);
        return userIdea = new String(stringArray);
    }


    public static Typeface getRobotoRegularFont(Context context)
    {
        return Typeface.createFromAsset(context.getAssets(),"fonts/Roboto-Regular2.ttf");
    }

    public static Typeface getBebasNeueFont(Context context)
    {
        return Typeface.createFromAsset(context.getAssets(),"fonts/BebasNeue.otf");
    }

    public static Typeface getBebasNeueBoldFont(Context context)
    {
        return Typeface.createFromAsset(context.getAssets(),"fonts/BebasNeue-Bold.otf");
    }

    public static Typeface getRobotoBoldFont(Context context)
    {
        return Typeface.createFromAsset(context.getAssets(),"fonts/Roboto-Bold.ttf");
    }

    public static String getAndroidVersion()
    {
        return Build.VERSION.RELEASE;
    }

    public static String getDeviceName()
    {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    public static String capitalize(String s)
    {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

    public static Intent getFacebookIntent(Context context, String facebookId) {
        try{
            // open in Facebook app
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/" + facebookId));
        } catch (Exception e) {
            // open in browser
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/" + facebookId));
        }
    }

    public static CharSequence noTrailingWhiteLines(CharSequence text) {

        while (text.charAt(text.length() - 1) == '\n') {
            text = text.subSequence(0, text.length() - 1);
        }
        return text;
    }

    public static String getLanguage()
    {
        return Locale.getDefault().getLanguage();
    }

    public static Locale getLocale()
    {
        return Locale.getDefault();
    }

    public static String convertSpecialCharacters(String string)
    {
        String res = string;

        if(res != null) {
            res = res.replace("+", "%2B");
            res = res.replace("\"", "%22");
            res = res.replace("&", "%26");
            res = res.replace("'", "%27");
        }
        return res;
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            java.net.URL url = new java.net.URL(src);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap loadContactPhoto(ContentResolver cr, long  id) {
        Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id);
        InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(cr, uri);
        if (input == null) {
            return null;
        }
        return BitmapFactory.decodeStream(input);
    }


    public static String getStringResourceByName(Context context, String aString) {
        String packageName = context.getPackageName();
        int resId = context.getResources().getIdentifier(aString, "string", packageName);
        if(resId != 0) {
            return context.getString(resId);
        }else {
            return "";
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if(view == null) {
            view = new View(activity);
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static String getTimeZoneString()
    {
        return Calendar.getInstance().getTimeZone().getID();
    }

    // Deeplink


    public static int getIdentifierWithString(String batchDeeplink, int pushDefault){
        int push_id = pushDefault;

        String Deeplink = "";

        if (batchDeeplink.contains("?")) {
            Deeplink = batchDeeplink.substring(batchDeeplink.indexOf("/") + 2, batchDeeplink.indexOf("?"));
        }else{
            Deeplink = batchDeeplink.substring(batchDeeplink.indexOf("/") + 2, batchDeeplink.length());
        }

        try {
            push_id = Integer.parseInt(Deeplink);
        } catch (Exception e) {

        }
        //Log.e("Fizzup", "DeepLink " + batchDeeplink.toString() + " deeplink " + Deeplink + " id " + push_id);

        return push_id;
    }

    public static long getCurrentLanguageId()
    {
        String language = getAppLanguage();
        if (language.equals(FRENCH_LANGUAGE))
            return 1;
        return 2;
    }

    public static void copyFile(File src, File dst) throws IOException
    {
        String directoryPath = dst.getPath().substring(0, dst.getPath().lastIndexOf('/') + 1);
        File directory = new File(directoryPath);
        if (!directory.exists())
            directory.mkdirs();
        FileChannel inChannel = new FileInputStream(src).getChannel();
        FileChannel outChannel = new FileOutputStream(dst).getChannel();
        try
        {
            inChannel.transferTo(0, inChannel.size(), outChannel);
        }
        finally
        {
            if (inChannel != null)
                inChannel.close();
            if (outChannel != null)
                outChannel.close();
        }
    }

    public static void moveFile(File src, File dst) throws IOException
    {
        copyFile(src, dst);
        src.delete();
    }

    public static String getAppLanguage(){
        String language = getLanguage();
        if (isLanguageSupported(language)) {
            return language;
        } else {
            return DEFAULT_LANGUAGE;
        }
    }

    public static Locale getAppLocale(){
        Locale locale = getLocale();
        if (isLocaleSupported(locale)) {
            return locale;
        } else {
            return DEFAULT_LOCALE;
        }
    }

    private static boolean isLocaleSupported(Locale language)
    {
        if (Arrays.asList(mSupportedLocales).contains(language))
            return true;
        return false;
    }

    private static boolean isLanguageSupported(String language)
    {
        if (Arrays.asList(mSupportedLanguages).contains(language))
            return true;
        return false;
    }

    /*
    public static boolean isFrench() {
        return Language.getFavorite(FizzupDatabase.getSQLiteDatabase()).getCode().equals(FRENCH_LANGUAGE);
    }

    public static boolean isEnglish() {
        return Language.getFavorite(FizzupDatabase.getSQLiteDatabase()).getCode().equals(ENGLISH_LANGUAGE);
    }

    public static Locale getLanguageLocale()
    {
        Language language = Language.getFavorite(FizzupDatabase.getSQLiteDatabase());
        if (language != null && SystemUtils.FRENCH_LANGUAGE.equals(language.getCode())) {
            return Locale.FRANCE;
        }
        return Locale.US;
    }
    */
}
