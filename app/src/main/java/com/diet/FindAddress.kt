package com.diet

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import androidx.annotation.RequiresApi

class FindAddress : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_address)

        var daum_address_api = findViewById<WebView>(R.id.daum_address_api)
        daum_address_api.settings.javaScriptEnabled
        daum_address_api.webChromeClient
        daum_address_api.webViewClient
/*
        daum_address_api.addJavascriptInterface()*/
    }
}