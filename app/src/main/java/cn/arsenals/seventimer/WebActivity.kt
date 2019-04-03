package cn.arsenals.seventimer

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.webkit.WebViewClient
import android.widget.Toast
import cn.arsenals.seventimer.utils.LogUtil
import kotlinx.android.synthetic.main.activity_web.*


class WebActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "WebActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        val url = intent.getStringExtra("url")
        LogUtil.i(TAG, "url : $url")

        this.webView.setOnLongClickListener {
            LogUtil.i(TAG, "onLongClick, url : $url")
            Snackbar.make(this.webView, "open in browser?", Snackbar.LENGTH_LONG).setAction("DO IT") {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            }.show()
            true
        }
        loadWebInfo(url)
    }

    private fun loadWebInfo(url: String) {
        this.webView.webViewClient = WebViewClient()

        this.webView.settings.setSupportZoom(true)
        this.webView.settings.builtInZoomControls = true
        this.webView.settings.displayZoomControls = false
        this.webView.settings.loadWithOverviewMode = true
        this.webView.settings.useWideViewPort = true
        this.webView.loadUrl(url)
    }

    private var exitTime: Long = 0
    override fun onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            webView.goBack()
            Toast.makeText(this, "back once more to exit", Toast.LENGTH_SHORT).show()
            exitTime = System.currentTimeMillis()
        } else {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.finish()
    }
}
