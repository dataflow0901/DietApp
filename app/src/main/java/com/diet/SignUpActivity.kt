package com.diet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.webkit.WebView
import android.widget.*
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    lateinit var et_id: EditText
    lateinit var et_pwd_check: EditText

    lateinit var et_pwd: EditText
    lateinit var required_pwd_tv: TextView
    lateinit var all_agree: CheckBox
    lateinit var use_agree_cb: CheckBox
    lateinit var privacy_cb1: CheckBox
    lateinit var privacy_cb2: CheckBox

    lateinit var age_check_db: CheckBox
    lateinit var event_agree_cb: CheckBox
    lateinit var address_tv : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        //아이디 정규식
        val check_id_pattern = Regex("(^[A-Za-z0-9*]{5,15}\$)")
        val check_pwd_pattern =
            Regex("^(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#\$%^&*])(?=.*[0-9!@#\$%^&*]).{9}\$")
        required_pwd_tv = findViewById(R.id.required_pwd_tv)
        et_id = findViewById(R.id.et_id)
        et_pwd = findViewById(R.id.et_pwd)
        et_pwd_check = findViewById(R.id.et_pwd_check)
        all_agree = findViewById(R.id.all_agree)
        use_agree_cb = findViewById(R.id.use_agree_cb)
        privacy_cb1 = findViewById(R.id.privacy_cb1)
        privacy_cb2 = findViewById(R.id.privacy_cb2)
        age_check_db = findViewById(R.id.age_check_db)
        address_tv = findViewById(R.id.address_tv)

        address_tv.setOnClickListener {
            /*val intent = Intent(this, FindAddressActivity::class.java)
            startActivity(intent)*/
        }

        //비밀번호 정규식 체크
        et_pwd.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (et_pwd.text.matches(check_pwd_pattern)) {
                    required_pwd_tv.text = "이렇게 바뀌도록 해보자"
                }
            }
        })
        // 비밀번호와 비밀번호확인을 비교하기 위함
        et_pwd_check.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (et_pwd.text.toString() == et_pwd_check.text.toString()) {
                    required_pwd_check_tv.text = "둘이 같아요"
                } else {
                    required_pwd_check_tv.text = "둘이 달라요"
                }
            }
        })

        //체크박스가 체크되어있는지 확인
        all_select_checkbox()


        et_id.imeOptions

    }

    private fun all_select_checkbox() {
        all_agree.setOnCheckedChangeListener { buttonView, isChecked ->
            if (all_agree.isChecked) {
                use_agree_cb.isChecked = true
                privacy_cb1.isChecked = true
                privacy_cb2.isChecked = true
                event_agree_cb.isChecked = true

                age_check_db.isChecked = true
            } else {
                use_agree_cb.isChecked = false
                privacy_cb1.isChecked = false
                privacy_cb2.isChecked = false
                event_agree_cb.isChecked = false

                age_check_db.isChecked = false
            }
        }
    }
}