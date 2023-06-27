package com.example.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class WebNewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_news_activity)

        val webview=findViewById<WebView>(R.id.webView)
        val newsUrl = intent.getStringExtra("news_url")

        webview.webViewClient = WebViewClient()
        if (newsUrl != null) {
            webview.loadUrl(newsUrl)
        }
    }
}