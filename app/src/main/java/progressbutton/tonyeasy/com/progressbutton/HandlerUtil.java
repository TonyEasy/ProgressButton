package progressbutton.tonyeasy.com.progressbutton;

import android.content.Context;
import android.os.Handler;

import java.lang.ref.WeakReference;

/**
 * 作者：张恒同
 * 时间：2017/2/16 0016   10:43
 * 描述：-- 统一Handler
 */
public class HandlerUtil extends Handler {
    private static HandlerUtil instance = null;
    WeakReference<Context> mWeakReference;

    public static HandlerUtil getInstance(Context context){
        if (instance == null)
            instance = new HandlerUtil(context.getApplicationContext());
        return instance;
    }

    HandlerUtil(Context context){
        mWeakReference = new WeakReference<>(context);
    }

}
