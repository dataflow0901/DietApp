package com.diet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.TextView

class MyOrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_order)

        //뒤로가기 버튼으로 변경해야한다
        val page_back = findViewById<Button>(R.id.page_back)
        page_back.setOnClickListener {
            val nextIntent = Intent(this, OrderDetailActivity::class.java)
            startActivity(nextIntent)
        }

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



