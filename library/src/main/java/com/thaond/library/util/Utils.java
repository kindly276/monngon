package com.thaond.library.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.telephony.TelephonyManager;
import android.text.format.Time;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.thaond.library.R;
import com.thaond.library.dialog.PopupConfirm;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;


/**
 * Created by thaond on 11/4/2015.
 */
public class Utils {
    public static RequestQueue queue;

    public static RequestQueue getQueue(Context context){
        if(queue==null){
            queue= Volley.newRequestQueue(context);
        }
        return queue;
    }
    public static ProgressDialog prDialog = null;
    static PopupConfirm confirm;
    // Remove String accents
    private static Map<Character, Character> MAP_NORM = new HashMap<Character, Character>();


    public static boolean hasConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiNetwork = cm
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null && wifiNetwork.isConnected()) {
            return true;
        }

        NetworkInfo mobileNetwork = cm
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null && mobileNetwork.isConnected()) {
            return true;
        }

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return true;
        }

        return false;
    }
    public static void enableMyLocation(final Context context){
        LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception ex) {}

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch(Exception ex) {}

        if(!gps_enabled && !network_enabled) {
            // notify user
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setMessage(context.getResources().getString(R.string.gps_network_not_enabled));
            dialog.setPositiveButton(context.getResources().getString(R.string.st_setting), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    context.startActivity(myIntent);
                    //get gps
                }
            });
            dialog.setNegativeButton(context.getString(R.string.st_cancel), new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub

                }
            });
            dialog.show();
        }
    }
    public static void showMessage(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

//    public static void makeTextViewResizable(final Context context, final TextView tv,
//                                             final int maxLine, final String expandText, final boolean viewMore) {
//
//        if (tv.getTag() == null) {
//            tv.setTag(tv.getText());
//        }
//        ViewTreeObserver vto = tv.getViewTreeObserver();
//        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//
//            @SuppressWarnings("deprecation")
//            @Override
//            public void onGlobalLayout() {
//
//                ViewTreeObserver obs = tv.getViewTreeObserver();
//                obs.removeGlobalOnLayoutListener(this);
//                if (maxLine == 0) {
//
//                    int lineEndIndex = tv.getLayout().getLineEnd(0);
//                    Utils.logE("thaond", "lineEndIndex 1" + lineEndIndex);
//                    String text = tv.getText().subSequence(0,
//                            lineEndIndex - expandText.length() + 1)
//                            + " " + expandText;
//                    tv.setText(text);
//                    tv.setMovementMethod(LinkMovementMethod.getInstance());
//                    tv.setText(
//                            addClickablePartTextViewResizable(context,
//                                    Html.fromHtml(tv.getText().toString()), tv,
//                                    maxLine, expandText, viewMore),
//                            TextView.BufferType.SPANNABLE);
//                } else if (maxLine > 0 && tv.getLineCount() >= maxLine) {
//                    int lineEndIndex = tv.getLayout().getLineEnd(maxLine - 1);
//                    Utils.logE("thaond", "lineEndIndex 2" + lineEndIndex);
//                    String text = tv.getText().subSequence(0,
//                            lineEndIndex - expandText.length() + 1)
//                            + " " + expandText;
//                    tv.setText(text);
//                    tv.setMovementMethod(LinkMovementMethod.getInstance());
//                    tv.setText(
//                            addClickablePartTextViewResizable(context,
//                                    Html.fromHtml(tv.getText().toString()), tv,
//                                    maxLine, expandText, viewMore),
//                            TextView.BufferType.SPANNABLE);
//                } else {
//                    int lineEndIndex = tv.getLayout().getLineEnd(
//                            tv.getLayout().getLineCount() - 1);
//                    Utils.logE("thaond", "lineEndIndex 3" + lineEndIndex);
//                    String text = tv.getText().subSequence(0, lineEndIndex)
//                            + " " + expandText;
//                    tv.setText(text);
//                    tv.setMovementMethod(LinkMovementMethod.getInstance());
//                    tv.setText(
//                            addClickablePartTextViewResizable(context,
//                                    Html.fromHtml(tv.getText().toString()), tv,
//                                    lineEndIndex, expandText, viewMore),
//                            TextView.BufferType.SPANNABLE);
//                }
//            }
//        });
//    }

//    private static SpannableStringBuilder addClickablePartTextViewResizable(final Context context,
//                                                                            final Spanned strSpanned, final TextView tv, final int maxLine,
//                                                                            final String spanableText, final boolean viewMore) {
//        String str = strSpanned.toString();
//        SpannableStringBuilder ssb = new SpannableStringBuilder(strSpanned);
//        if (str.contains(spanableText)) {
//
//            ssb.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.colorAccent)), str.indexOf(spanableText),
//                    str.indexOf(spanableText)
//                            + spanableText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        }
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (viewMore) {
//                    tv.setLayoutParams(tv.getLayoutParams());
//                    tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
//                    tv.invalidate();
//                    makeTextViewResizable(context, tv, -1, "Thu gọn", false);
//                    Utils.logE("thaond", "Thu gon");
//                } else {
//                    tv.setLayoutParams(tv.getLayoutParams());
//                    tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
//                    Utils.logE("thaond", "string" + tv.getTag().toString());
//                    tv.scrollTo(0, 0);
//                    tv.invalidate();
//                    makeTextViewResizable(context, tv, 1, "Xem thêm", true);
//                    Utils.logE("thaond", "xem them");
//                }
//            }
//        });
//        return ssb;
//
//    }

    public static ActivityOptionsCompat makeOptionsCompat(Activity fromActivity, Pair<View, String>... sharedElements) {
        ArrayList<Pair<View, String>> list = new ArrayList<>(Arrays.asList(sharedElements));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            list.add(Pair.create(fromActivity.findViewById(android.R.id.statusBarBackground), Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME));
            list.add(Pair.create(fromActivity.findViewById(android.R.id.navigationBarBackground), Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME));
        }

        //remove any views that are null
        for (ListIterator<Pair<View, String>> iter = list.listIterator(); iter.hasNext(); ) {
            Pair pair = iter.next();
            if (pair.first == null) iter.remove();
        }

        sharedElements = list.toArray(new Pair[list.size()]);
        return ActivityOptionsCompat.makeSceneTransitionAnimation(
                fromActivity,
                sharedElements
        );
    }

//    public static void showConfimWhenTimeoutToken(final Context context) {
//        confirm = new PopupConfirm(context, context.getResources().getString(R.string.un_authenticated_popup_des), new PopupConfirm.OnPopupDateTimeListener() {
//            @Override
//            public void onConfirmOK() {
//                confirm.dismiss();
//
//                Intent intent = new Intent(context, LoginActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//                context.startActivity(intent);
//                ((Activity) context).overridePendingTransition(R.anim.anim_in_back,
//                        R.anim.anim_out_back);
//            }
//
//            @Override
//            public void onConfirmCancel() {
//                confirm.dismiss();
//            }
//        });
//        confirm.show();
//    }

    public static void storeShared(String label, String value, Context context, String sharePrivate) {
        try {
            SharedPreferences
                    sharedPref = context.getSharedPreferences(
                    sharePrivate, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(label, value);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void storeSharedBoolean(String label, boolean value, Context context, String sharePrivate) {
        try {
            SharedPreferences
                    sharedPref = context.getSharedPreferences(
                    sharePrivate, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean(label, value);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean getSharedBoolean(String label, Context context, String sharePrivate) {
        boolean values = false;
        try {
            SharedPreferences
                    sharedPref = context.getSharedPreferences(
                    sharePrivate, Context.MODE_PRIVATE);
            values = sharedPref

                    .getBoolean(label, false);
        } catch (Exception e) {
            values = false;
            e.printStackTrace();
        }
        return values;
    }

    public static String getSharedString(String label, Context context, String sharePrivate) {
        String stValue = "";
        try {
            SharedPreferences
                    sharedPref = context.getSharedPreferences(
                    sharePrivate, Context.MODE_PRIVATE);
            stValue = sharedPref

                    .getString(label, "");
        } catch (Exception e) {
            stValue = "";
            e.printStackTrace();
        }
        return stValue;
    }

    public static void
    logE(String tag, String contentLogE) {
        Log.e(tag, contentLogE);
    }


    public static void logD(String tag, String contentLogD) {
        Log.d(tag, contentLogD);
    }

    public static void logI(String tag, String contentLogI) {
        Log.i(tag, contentLogI);
    }

    //========= verify searching ========
    public static boolean verifySearchString(String search, String compare) {

        // remove all text accents
        String strSearch = Utils.removeStringAccents(search);
        String strCompare = Utils.removeStringAccents(compare);

        // Split search text
        String[] arrSearch = strSearch.split(" ");

        int totleCompareTimes = 0;

        for (int i = 0; i < arrSearch.length; i++) {
            if (strCompare.indexOf(arrSearch[i]) != -1) {
                totleCompareTimes++;
            }
        }

        Utils.logE("test", "total size = " + arrSearch.length + " - size = " + totleCompareTimes);

        if (totleCompareTimes == arrSearch.length) {
            return true;
        } else
            return false;
    }

    public static String removeStringAccents(String value) {

        if (MAP_NORM.size() == 0) {
            MAP_NORM.put('à', 'a');
            MAP_NORM.put('á', 'a');
            MAP_NORM.put('ạ', 'a');
            MAP_NORM.put('ả', 'a');
            MAP_NORM.put('ã', 'a');
            MAP_NORM.put('â', 'a');
            MAP_NORM.put('ầ', 'a');
            MAP_NORM.put('ấ', 'a');
            MAP_NORM.put('ậ', 'a');
            MAP_NORM.put('ẩ', 'a');
            MAP_NORM.put('ẫ', 'a');
            MAP_NORM.put('ă', 'a');
            MAP_NORM.put('ằ', 'a');
            MAP_NORM.put('ắ', 'a');
            MAP_NORM.put('ặ', 'a');
            MAP_NORM.put('ẳ', 'a');
            MAP_NORM.put('ẵ', 'a');
            MAP_NORM.put('ê', 'e');
            MAP_NORM.put('ề', 'e');
            MAP_NORM.put('ế', 'e');
            MAP_NORM.put('ệ', 'e');
            MAP_NORM.put('ể', 'e');
            MAP_NORM.put('ễ', 'e');
            MAP_NORM.put('è', 'e');
            MAP_NORM.put('é', 'e');
            MAP_NORM.put('ẹ', 'e');
            MAP_NORM.put('ẻ', 'e');
            MAP_NORM.put('ẽ', 'e');
            MAP_NORM.put('ì', 'i');
            MAP_NORM.put('í', 'i');
            MAP_NORM.put('ị', 'i');
            MAP_NORM.put('ỉ', 'i');
            MAP_NORM.put('ĩ', 'i');
            MAP_NORM.put('ò', 'o');
            MAP_NORM.put('ó', 'o');
            MAP_NORM.put('ọ', 'o');
            MAP_NORM.put('ỏ', 'o');
            MAP_NORM.put('õ', 'o');
            MAP_NORM.put('ô', 'o');
            MAP_NORM.put('ồ', 'o');
            MAP_NORM.put('ố', 'o');
            MAP_NORM.put('ộ', 'o');
            MAP_NORM.put('ổ', 'o');
            MAP_NORM.put('ỗ', 'o');
            MAP_NORM.put('ơ', 'o');
            MAP_NORM.put('ờ', 'o');
            MAP_NORM.put('ớ', 'o');
            MAP_NORM.put('ợ', 'o');
            MAP_NORM.put('ở', 'o');
            MAP_NORM.put('ỡ', 'o');
            MAP_NORM.put('ù', 'u');
            MAP_NORM.put('ú', 'u');
            MAP_NORM.put('ụ', 'u');
            MAP_NORM.put('ũ', 'u');
            MAP_NORM.put('ủ', 'u');
            MAP_NORM.put('ư', 'u');
            MAP_NORM.put('ừ', 'u');
            MAP_NORM.put('ứ', 'u');
            MAP_NORM.put('ự', 'u');
            MAP_NORM.put('ử', 'u');
            MAP_NORM.put('ữ', 'u');
            MAP_NORM.put('ỳ', 'y');
            MAP_NORM.put('ý', 'y');
            MAP_NORM.put('ỵ', 'y');
            MAP_NORM.put('ỷ', 'y');
            MAP_NORM.put('ỹ', 'y');
            MAP_NORM.put('đ', 'd');

        }

        if (value == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder(value);

        for (int i = 0; i < value.length(); i++) {
            Character c = MAP_NORM.get(sb.charAt(i));
            if (c != null) {
                sb.setCharAt(i, c.charValue());
            }
        }

        return sb.toString();
    }


    /**
     * @return Application's version code from the {@code PackageManager}.
     */
    public static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    public static void loadImageProgress(final Context activity, final ImageView image, final ProgressBar viewProgres, final String url) {
        Picasso.with(activity)
                .load(url).fit()
                .into(image, new Callback() {
                    @Override
                    public void onSuccess() {
                        if (viewProgres != null) {
                            viewProgres.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError() {
                        if (viewProgres != null) {
                            viewProgres.setVisibility(View.GONE);
                        }
                    }
                });
    }

    public static void loadImageZoomProgress(final Context activity, final ImageView image, final ProgressBar viewProgres, final String url) {
        Picasso.with(activity)
                .load(url)
                .into(new Target() {
                          @Override
                          public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                              Utils.logE("thaond", "success" + bitmap);
                              image.setImageBitmap(bitmap);
                              if (viewProgres != null) {
                                  viewProgres.setVisibility(View.GONE);
                              }
                          }

                          @Override
                          public void onBitmapFailed(Drawable errorDrawable) {
                              Utils.logE("thaond", "fail");

                              if (viewProgres != null) {
                                  viewProgres.setVisibility(View.GONE);
                              }

                          }

                          @Override
                          public void onPrepareLoad(Drawable placeHolderDrawable) {
                              Utils.logE("thaond", "onPrepareLoad");

                          }
                      }
                );
    }

    public static void loadImageAvatar(final Context activity, final ImageView image, final String url) {
//        Picasso.with(activity)
//                .load(url)
//                .networkPolicy(NetworkPolicy.OFFLINE)
//                .into(new Target() {
//                          @Override
//                          public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                              image.setImageBitmap(bitmap);
//
//                          }
//
//                          @Override
//                          public void onBitmapFailed(Drawable errorDrawable) {
//                          }
//
//                          @Override
//                          public void onPrepareLoad(Drawable placeHolderDrawable) {
//                          }
//                      }
//                );
        Picasso.with(activity).load(url).transform(new CircleTransform()).into(image);
    }

    public static void loadImageProgressUnfit(final Context activity, final ImageView image, final ProgressBar viewProgres, final String url) {
        Picasso.with(activity)
                .load(url)
                .into(image, new Callback() {
                    @Override
                    public void onSuccess() {
                        if (viewProgres != null) {
                            viewProgres.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError() {
                        if (viewProgres != null) {
                            viewProgres.setVisibility(View.GONE);
                        }
                    }
                });
    }

    public static void loadImage(final Context activity, final ImageView image, final String url) {
        Picasso.with(activity)
                .load(url).fit()
                .into(image, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(activity)
                                .load(url).fit()
                                .into(image, new Callback() {
                                    @Override
                                    public void onSuccess() {

                                    }

                                    @Override
                                    public void onError() {
                                    }
                                });
                    }
                });
    }

    public static void loadImageUnfit(final Context activity, final ImageView image, final String url) {
        Picasso.with(activity)
                .load(url)
                .into(image, new Callback() {
                    @Override
                    public void onSuccess() {
                        Utils.logE("thaond", "load anh thanh cong");
                    }

                    @Override
                    public void onError() {
                        Utils.logE("thaond", "load anh that bai");

                    }
                });
    }


    public static File getExternalCacheDir(Context context) {
        if (hasExternalCacheDir()) {
            File myFile = context.getExternalCacheDir();
            if (myFile != null) {
                return myFile;
            } else {
                final String cacheDir = "/Android/data/" + context.getPackageName() + "/cache/webview";
                return new File(Environment.getExternalStorageDirectory().getPath() + cacheDir);
            }
        }
        // Before Froyo we need to construct the external cache dir ourselves
        final String cacheDir = "/Android/data/" + context.getPackageName() + "/cache/webview";
        return new File(Environment.getExternalStorageDirectory().getPath() + cacheDir);
    }

    public static boolean hasExternalCacheDir() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
    }

    public static int getFixedHeight(Context context, int height) {
        int result = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                height, context.getResources().getDisplayMetrics());

        return result;
    }

    public static void hideSoftwareKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager)
                context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isAcceptingText()) {
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        }

    }

    public static String getTimeFromMiliSecond(long time) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        String timeFormat = dateFormat.format(cal.getTime());

        return timeFormat;
    }

    public static String getYesterday() {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);

        String yesterday = dateFormat.format(cal.getTime());

        return yesterday;
    }

    public static String getToday() {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);

        String today = dateFormat.format(cal.getTime());

        return today;
    }

    public static String getCurentTime() {

        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);

        String curentTime = today.format("%k:%M") + " " + dateFormat.format(cal.getTime());

        return curentTime;
    }

    public static Long getCurentTimestemp() {
        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        Long curentTime = System.currentTimeMillis();
        return curentTime;
    }

    @SuppressLint("SimpleDateFormat")
    public static String convertDatetoday(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String daytime = "";
        try {
            daytime = simpleDateFormat.format(date);

        } catch (Exception ex) {
        }
        return daytime;
    }

    @SuppressLint("SimpleDateFormat")
    public static String convertDatetohour(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        String daytime = "";
        try {
            daytime = simpleDateFormat.format(date);

        } catch (Exception ex) {
        }
        return daytime;
    }

    @SuppressLint("SimpleDateFormat")
    public static Date convertStringtoDate(String textTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(textTime);

        } catch (ParseException ex) {
        }
        return date;
    }

//    public static String comparebetweenDate(Date date, Context context) {
//        String time = Utils.convertDatetoday(date) + " "
//                + context.getResources().getString(R.string.notification_luc)
//                + " " + Utils.convertDatetohour(date);
//
//        String timeminute;
//        String texttimehour;
//        Date tmpdate = new Date();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        Calendar tmpcalendar = Calendar.getInstance();
//        tmpcalendar.setTime(tmpdate);
//
//        if (calendar.get(Calendar.YEAR) == tmpcalendar.get(Calendar.YEAR)) {
//            if (calendar.get(Calendar.MONTH) == tmpcalendar.get(Calendar.MONTH)) {
//                if (calendar.get(Calendar.DAY_OF_MONTH) <= tmpcalendar
//                        .get(Calendar.DAY_OF_MONTH)) {
//                    long hour = (tmpcalendar.get(Calendar.DAY_OF_MONTH) * 24
//                            * 60 * 60 + tmpcalendar.get(Calendar.HOUR_OF_DAY)
//                            * 60 * 60 + tmpcalendar.get(Calendar.MINUTE) * 60 + tmpcalendar
//                            .get(Calendar.SECOND))
//                            - (calendar.get(Calendar.DAY_OF_MONTH) * 24 * 60
//                            * 60 + calendar.get(Calendar.HOUR_OF_DAY)
//                            * 60 * 60 + calendar.get(Calendar.MINUTE)
//                            * 60 + calendar.get(Calendar.SECOND));
//                    if (hour < 86400 && hour > 0) {// thoi gian nho hon 1
//                        // ngay
//                        if (hour < 60) {
//                            timeminute = context.getResources().getString(
//                                    R.string.notification_distence)
//                                    + " "
//                                    + hour
//                                    + " "
//                                    + context.getResources().getString(
//                                    R.string.notification_second);
//                            return timeminute;
//                        } else if (hour < 3600) {// thoi gian nho hon 1 gio
//                            timeminute = context.getResources().getString(
//                                    R.string.notification_distence)
//                                    + " "
//                                    + (hour / 60)
//                                    + " "
//                                    + context.getResources().getString(
//                                    R.string.notification_minute);
//                            return timeminute;
//                        } else {
//                            texttimehour = context.getResources().getString(
//                                    R.string.notification_distence)
//                                    + " "
//                                    + (hour / 3600)
//                                    + " "
//                                    + context.getResources().getString(
//                                    R.string.notification_hour);
//                            return texttimehour;
//                        }
//                    } else if (hour < 172800 && hour > 86400) {
//                        return String.format(context.getString(R.string.notification_yesterday), Utils.convertDatetohour(date));
//                    }
//                    return time;
//                }
//
//            }
//            return time;
//        }
//        return time;
//
//    }

    public static String getImeiDevice(Activity activity) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
            return telephonyManager.getDeviceId();
        } catch (Exception e) {
            return "";
        }

    }

    public static boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isPhoneValid(CharSequence phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }



}
