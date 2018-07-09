package cn.syf.nativejsdemo

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import kotlinx.android.synthetic.main.activity_main.*
import android.app.AlertDialog
import android.view.View
import android.content.DialogInterface
import android.view.KeyEvent
import android.webkit.JsResult
import android.webkit.WebView
import android.webkit.WebChromeClient


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        val webSettings: WebSettings = webView.settings
        val jsUtils = JsUtils(this)
        val js2NativeUtil = Js2NativeUtil(this)
        webSettings.javaScriptEnabled = true
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        WebView.setWebContentsDebuggingEnabled(true)
        webView.addJavascriptInterface(jsUtils, "jsUtil")
        webView.addJavascriptInterface(js2NativeUtil, "js2NativeUtil")
        val map = HashMap<String, String>()
        map.put("accountId", "")
        map.put("token", "")
        webView.loadUrl("http://192.168.1.89:9527/#/orderEntry",map)
        btn1.setOnClickListener(View.OnClickListener {
            // 通过Handler发送消息
            webView.post(Runnable {
                // 注意调用的JS方法名要对应上
                // 调用javascript的callJS()方法
                webView.loadUrl("javascript:moreCityClick()")
            })

        })

        webView.setWebChromeClient(object : WebChromeClient() {
            override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
                val b = AlertDialog.Builder(this@MainActivity)
                b.setTitle("Alert")
                b.setMessage(message)
                b.setPositiveButton(android.R.string.ok, DialogInterface.OnClickListener { dialog, which -> result.confirm() })
                b.setCancelable(false)
                b.create().show()
                return true
            }

        })


    }

    //go back
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        // Check if the key event was the Back button and if there's history
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }
        //  return true;
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event)
    }

}
