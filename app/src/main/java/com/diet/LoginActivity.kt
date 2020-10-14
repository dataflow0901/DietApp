package com.diet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    val test_id: String = "testid"
    val test_pwd: String = "1234qwerq"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var login_id_et = findViewById<EditText>(R.id.login_id_et)
        var login_pwd_et = findViewById<EditText>(R.id.login_pwd_et)
        var login_btn = findViewById<Button>(R.id.login_btn)
        var find_id_tv = findViewById<TextView>(R.id.find_id_tv)
        var find_pwd_tv = findViewById<TextView>(R.id.find_pwd_tv)
        var no_signup = findViewById<TextView>(R.id.no_signup)
        signup_btn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        login_btn.setOnClickListener {
            if (login_id_et.text.toString() != test_id || login_pwd_et.text.toString() != test_pwd) {
                Toast.makeText(this, "아이디 또는 비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
            } else if (login_id_et.text.toString() == test_id || login_pwd_et.text.toString() == test_pwd) {
                Toast.makeText(this, "로그인에 성공", Toast.LENGTH_SHORT).show()
            }
        }
        no_signup.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }

    }


}