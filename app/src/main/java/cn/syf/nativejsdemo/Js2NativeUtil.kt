package cn.syf.nativejsdemo

import android.util.Log
import android.webkit.JavascriptInterface
import android.widget.Toast
import com.google.gson.Gson

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

    /**
     * 获取person字符串传Html
     * @return
     */
    @JavascriptInterface
    fun getData(): String {
        val mlist = mutableListOf("测试分理处","测试分公司","测试地址","郑州分理处",
                "测试分理处","测试分公司","测试地址","郑州分理处","测试分理处","测试分公司","测试地址",
                "郑州分理处","测试分理处","测试分公司","测试地址","郑州分理处")

        val gson = Gson()
        val d = gson.toJson(mlist)
        Log.d("tag", "getData: dddd$d")
        return d
    }


}