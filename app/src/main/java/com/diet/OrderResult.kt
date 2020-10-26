package com.diet

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_order_result.*

class OrderResult : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_result)

//        lateinit var text1 : TextView
//        lateinit var text2 : TextView
//        lateinit var text3 : TextView
//        lateinit var text4 : TextView
//        lateinit var text5 : TextView
//        lateinit var text6 : TextView
//        lateinit var text7 : TextView
//
//        text1 = findViewById(R.id.textview1)
//        text2 = findViewById(R.id.textview2)
//        text3 = findViewById(R.id.textview3)
//        text4 = findViewById(R.id.textview4)
//        text5 = findViewById(R.id.textview5)
//        text6 = findViewById(R.id.textview6)
       // text7 = findViewById(R.id.textview7)

        var action = intent.getStringExtra("action")
        var price = intent.getStringExtra("price")
        var item_name = intent.getStringExtra("item_name")
        var payment_name = intent.getStringExtra("payment_name")
        var card_no = intent.getStringExtra("card_no")
        var card_code = intent.getStringExtra("card_code")
        var card_name = intent.getStringExtra("card_name")

        textview1.text = action
        textview2.text = price
        textview3.text = item_name
        textview4.text = card_no
        textview5.text = card_code
        textview6.text = card_name



    }
}