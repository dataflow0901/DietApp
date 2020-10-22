package com.diet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent

class OrderDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (event != null) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.repeatCount == 0) {
                finish()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}