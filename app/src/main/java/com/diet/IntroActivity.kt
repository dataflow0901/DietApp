package com.diet

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.telephony.TelephonyManager
import android.util.Log
import androidx.core.app.ActivityCompat
//import com.blildo.views.model.UserAccountDTO
//import com.blildo.views.model.retrofits.UserApiRetrofit
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_intro.*
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
import java.io.IOException
import java.util.*

import android.view.View
import android.widget.TextView
import com.diet.fragmentView.HomeFragment
import com.diet.fragmentView.SearchFragment
/*import com.diet.fragmentView.SignUpFragment*/


class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        intro_button.setOnClickListener {
            val nextIntent = Intent(this, Home::class.java)
            startActivity(nextIntent)
        }
        login_check.setOnClickListener {
            val nextIntent = Intent(this, LoginActivity::class.java)
            startActivity(nextIntent)
            /*val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.test_layout, SearchFragment()).addToBackStack(null).commit()*/
        }

//        Log.d("폰",getPhoneNumber().replace("-",""))
//        findUserList(getPhoneNumber().replace("-",""))


    }

/*
    fun getPhoneNumber(): String {
        val telephony =
            getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        var phoneNumber = ""
        try {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_SMS
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_PHONE_NUMBERS
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_PHONE_STATE
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                return ""
            }
            if (telephony.line1Number != null) {
                phoneNumber = telephony.line1Number
            } else {
                if (telephony.simSerialNumber != null) {
                    phoneNumber = telephony.simSerialNumber
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (phoneNumber.startsWith("+82")) {
            phoneNumber = phoneNumber.replace("+82", "0")
        }
        phoneNumber = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            PhoneNumberUtils.formatNumber(
                phoneNumber,
                Locale.getDefault().country
            )
        } else {
            PhoneNumberUtils.formatNumber(phoneNumber)
        }
        return phoneNumber
    }

    fun findUserList(userCellNo : String){

        var user = UserAccountDTO()
        user.userCellNo = userCellNo

        val res: Call<JsonObject> =
            UserApiRetrofit.getInstance(this).service.findRegisteredUserByUserCellNo(user)
        res.enqueue(object : Callback<JsonObject?> {
            override fun onResponse(
                call: Call<JsonObject?>,
                response: Response<JsonObject?>
            ) {
                Log.d("findRegisteredUsersByUserCellNo", response.toString())
                val result = response.body()?.getAsJsonObject("result")
                if (response.isSuccessful) {
                    Log.d("findRegisteredUsersByUserCellNo", result.toString())
                    if(result?.getAsJsonPrimitive("userId")!!.asString.length > 2){
                        Log.d("userId  : ", result?.getAsJsonPrimitive("userId")!!.asString )
                    }

                } else {
                    try {
                        val errorMessage = response.errorBody()!!.string()
                        Log.d("findRegisteredUsersByUserCellNo", errorMessage)
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

*/

}