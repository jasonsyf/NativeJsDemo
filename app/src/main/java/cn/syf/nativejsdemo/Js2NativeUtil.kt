package cn.syf.nativejsdemo

import android.content.Intent
import android.util.Log
import android.webkit.JavascriptInterface
import android.widget.Toast

/**
 * @author : Jason_Sunyf
 * @date   : 2018年06月12日14时14分
 * E-mail  : jason_sunyf@163.com
 */
class Js2NativeUtil(val main: MainActivity) {

    @JavascriptInterface
    fun jsToast(msg: String) {
        Toast.makeText(main, msg, Toast.LENGTH_SHORT).show()
    }

    @JavascriptInterface
    fun jsLog(msg: String) {
        Log.i("jslog", main.localClassName+msg)
    }


}