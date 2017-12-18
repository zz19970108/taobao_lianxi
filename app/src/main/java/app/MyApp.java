package app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by dell on 2017/12/12.
 */

public class MyApp extends Application{
    public static MyApp mInstance;

    private static Context context;
    private static Handler handler;
    private static int mainId;
    public static boolean isLoginSuccess;

    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(configuration);
        mInstance = this;

        context =getApplicationContext();
        //初始化handler
        handler=new Handler();
        //主线程id
        mainId= Process.myTid();
    }
    public static MyApp getInstance() {
        return mInstance;
    }

    public static Context getAppContext(){

        return context;
    }

    //得到全局的handler
    public static Handler getAppHandler(){

        return handler;
    }

    //获取主线程id
    public static int getMainThreadId(){

        return mainId;
    }
}