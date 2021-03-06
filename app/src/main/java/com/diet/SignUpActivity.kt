package com.diet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.widget.*
import androidx.core.widget.addTextChangedListener
import com.diet.model.UserDTO
import com.diet.model.retrofits.OrderApiRetrofit
import com.diet.model.retrofits.UserApiRetrofit
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class SignUpActivity : AppCompatActivity() {
    lateinit var et_id: EditText
    lateinit var et_pwd_check: EditText
    lateinit var et_pwd: EditText
    lateinit var name_et: EditText
    lateinit var phone_et: EditText
    lateinit var email_et: EditText
    lateinit var birth_day : EditText
    lateinit var sign_up_finish: Button
    lateinit var all_agree: CheckBox
    lateinit var use_agree_cb: CheckBox
    lateinit var privacy_cb1: CheckBox
    lateinit var privacy_cb2: CheckBox
    lateinit var age_check_db: CheckBox
    lateinit var event_agree_cb: CheckBox
    lateinit var address_tv: TextView
    lateinit var required_pwd_tv: TextView


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
        name_et = findViewById(R.id.name_et)
        phone_et = findViewById(R.id.phone_et)
        email_et = findViewById(R.id.email_et)
        et_pwd_check = findViewById(R.id.et_pwd_check)
        all_agree = findViewById(R.id.all_agree)
        use_agree_cb = findViewById(R.id.use_agree_cb)
        privacy_cb1 = findViewById(R.id.privacy_cb1)
        privacy_cb2 = findViewById(R.id.privacy_cb2)
        age_check_db = findViewById(R.id.age_check_db)
        address_tv = findViewById(R.id.address_tv)
        sign_up_finish = findViewById(R.id.sign_up_finish)


        sign_up_finish.setOnClickListener {
            SingUp_user()
        }

        address_tv.setOnClickListener {
            /*val intent = Intent(this, FindAddressActivity::class.java)
            startActivity(intent)*/
        }

        /*//비밀번호 정규식 체크
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
        })*/

        //체크박스가 체크되어있는지 확인
        all_select_checkbox()

       /* et_id.imeOptions*/


    }

    private fun SingUp_user() {
        val user = UserDTO()

        var user_seq_no: Int = 0 // 유저번호
        var user_id: String = et_id.text.toString() // 유저 아이디
        var password: String = et_pwd.text.toString() // 비밀번호
        var name: String = name_et.text.toString() // 이름
        var user_info: String = birth_day.text.toString() // 유저 생년월일
        var user_cell_no: String = phone_et.text.toString() // 핸드폰번호
        var user_gender: String = "" // 유저 성별
        var user_email: String = email_et.text.toString() // 이메일
        var reg_date: String = "" //가입일
        var approved: String = "" // (?)
        var approved_date: String = "" //(?)
        var auth_id: String = "" //등급
        var address: String = "" // 주소
        var registrationtokens: String = "" // 토큰값
        var last_login_time: String = "" // 마지막 로그인날짜
        var create_date: String = "" // 생성일
        var update_date: String = "" // 수정일
        var create_uesr: String = "" // 생성유저(?)
        var update_user: String = "" // 수정유저(?)

        user.user_id = user_id
        user.password = password
        user.name = name
        user.user_cell_no = user_cell_no
        user.user_email = user_email
        user.user_info = user_info
        val res: Call<JsonObject> =
            UserApiRetrofit.getInstance(this).service.register(user)
        res.enqueue(object : Callback<JsonObject?> {
            override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {
                Log.d("response.toString()", response.toString())

                val result = response.body()

                if (response.isSuccessful) {

                    Log.d("register", result.toString())
                    Toast.makeText(applicationContext, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT)
                        .show()

                    //finish()

                } else {
                    try {
                        val errorMessage = response.errorBody()!!.string()
                        Log.d("isSuccessful", errorMessage)
                    } catch (e: IOException) {
                        e.toString()
                    }
                }
            }

            override fun onFailure(call: Call<JsonObject?>, t: Throwable) {
                t.message
            }
        })
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