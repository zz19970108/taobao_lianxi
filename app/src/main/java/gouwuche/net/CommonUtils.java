package gouwuche.net;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.View;

import app.MyApp;

import static app.MyApp.getAppContext;

/**
 * Created by dell on 2017/12/18.
 */

public class CommonUtils {
    public static final String TAG = "Dash";
    private static SharedPreferences sharedPreferences;

    public static View inflate(int layoutId){
        View view = View.inflate(getAppContext(), layoutId, null);
        return view;
    }

    public static int dip2px(int dip) {
        //获取像素密度
        float density = getAppContext().getResources().getDisplayMetrics().density;
        int px = (int) (dip * density + 0.5f);
        return px;
    }

    public static int px2dip(int px) {
        //获取像素密度
        float density = getAppContext().getResources().getDisplayMetrics().density;
        int dip = (int) (px / density + 0.5f);
        return dip;
    }

    //获取资源中的字符串
    public static String getString(int stringId) {
        return getAppContext().getResources().getString(stringId);
    }

    public static Drawable getDrawable(int did) {
        return MyApp.getAppContext().getResources().getDrawable(did);
    }

    public static int getDimens(int id) {
        return MyApp.getAppContext().getResources().getDimensionPixelSize(id);
    }

    //sp存入字符串类型的值
    public static void saveSp(String flag, String str) {
        if (sharedPreferences == null) {
            sharedPreferences = MyApp.getAppContext().getSharedPreferences(TAG, MyApp.getAppContext().MODE_PRIVATE);
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(flag, str);
        edit.commit();
    }

    public static String getSp(String flag) {
        if (sharedPreferences == null) {
            sharedPreferences = MyApp.getAppContext().getSharedPreferences(TAG, MyApp.getAppContext().MODE_PRIVATE);
        }
        return sharedPreferences.getString(flag, "");
    }

    public static boolean getBoolean(String tag) {
        if (sharedPreferences == null) {
            sharedPreferences = MyApp.getAppContext().getSharedPreferences(TAG, MyApp.getAppContext().MODE_PRIVATE);
        }
        return sharedPreferences.getBoolean(tag, false);
    }

    public static void putBoolean(String tag, boolean content) {
        if (sharedPreferences == null) {
            sharedPreferences = MyApp.getAppContext().getSharedPreferences(TAG, MyApp.getAppContext().MODE_PRIVATE);
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(tag, content);
        edit.commit();
    }
    //清除sp数据
    public static void clearSp(String tag) {
        if (sharedPreferences == null) {
            sharedPreferences = MyApp.getAppContext().getSharedPreferences(TAG, MyApp.getAppContext().MODE_PRIVATE);
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove(tag);
        edit.commit();
    }

    //如果是主线程,执行任务,否则使用handler发送到主线程中去执行
    public static void runOnUIThread(Runnable runable) {
        //先判断当前属于子线程还是主线程
        if (android.os.Process.myTid() == MyApp.getMainThreadId()) {
            runable.run();
        } else {
            //子线程
            MyApp.getAppHandler().post(runable);
        }
    }
}