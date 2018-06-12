package cn.syf.nativejsdemo;

import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * @author : Jason_Sunyf
 * @date : 2018年06月12日14时27分
 * E-mail  : jason_sunyf@163.com
 */
public class JsUtils {
    private MainActivity mMainActivity;

    public JsUtils(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }
   @JavascriptInterface
    public void js2Native(String className) {
        try {
            Class clz = Class.forName(className);
            Intent intent = new Intent();
            intent.setClass(mMainActivity, clz);
            mMainActivity.startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.d("error", e.toString());
        }
    }
}
