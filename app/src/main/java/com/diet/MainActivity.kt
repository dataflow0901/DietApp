package com.diet

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_intro.*

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_product_list.*
import kotlinx.android.synthetic.main.fragment_status.*
import kotlinx.android.synthetic.main.activity_product_list.listViewProductList
import kotlinx.android.synthetic.main.activity_product_list.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
//        setSupportActionBar(toolbar)

        intro_button.setOnClickListener{
            val nextIntent = Intent(this, Home::class.java)
            startActivity(nextIntent)
        }

    }
}