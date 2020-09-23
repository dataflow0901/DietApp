package com.diet

//import com.blildo.views.fragmentView.LentCertificateFragment
//import com.blildo.views.fragmentView.BorrowedCertificateFragment
//import com.blildo.views.fragmentView.ManagementFragment
//import com.blildo.views.fragmentView.HomeFragment
//import com.blildo.views.hambuger.Hamburger


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.diet.model.ProductDTO
import com.diet.model.retrofits.ProductApiRetrofit
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_product.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class ProductInfo : AppCompatActivity() {


    //val companyName = view.findViewById<TextView>(R.id.companyName)

    //var companyName = ""

    //private var companyName = ""
    var salesStandCode = ""
    var productCode = ""


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

//        productName.text = intent.getStringExtra("productName")
//        companyName.text = intent.getStringExtra("companyName")
//        gpa.text = intent.getIntExtra("gpa", 0).toString()
//        price.text = intent.getIntExtra("price", 0).toString() + " 원"
//        ranking.text = intent.getIntExtra("ranking", 0).toString()
//        deliveryCost.text = intent.getIntExtra("deliveryCost", 0).toString() + " 원"
//        review.text = intent.getIntExtra("review", 0).toString()
//
//



        if (intent.hasExtra("salesStandCode")) {
            salesStandCode = intent.getStringExtra("salesStandCode")
            productCode = intent.getStringExtra("productCode")
            getProduct()

        } else {
            salesStandCode = "NEW_PROD1"
            getProduct()
        }


/*
        myInfoContainer.setOnClickListener {
            val goMyInfo = Intent (this,  MyInfo::class.java)
            startActivity(goMyInfo)
        }
        setting_container.setOnClickListener{
            val goMyInfo = Intent (this,  Setting::class.java)
            startActivity(goMyInfo)
        }

        notice_container.setOnClickListener {
            val goNotice = Intent (this, Notice::class.java)
            startActivity(goNotice)
        }
        customer_center_container.setOnClickListener {
            val goCustomerCenter = Intent(this, CustomerCenter::class.java)
            startActivity(goCustomerCenter)
        }
        termsPolicy_container.setOnClickListener {
            val goTermsOfUsePrivacyPolicy = Intent (this, TermsOfUsePrivacyPolicy::class.java)
            startActivity(goTermsOfUsePrivacyPolicy)
        }
        app_info_container
*/

    }


    private fun getProduct() {

        val product = ProductDTO()

        Log.d("salesStandCode" ,  salesStandCode)
        Log.d("productCode" ,  productCode)
        product.salesStandCode = salesStandCode
        product.productCode = productCode


        val res: Call<JsonObject> =
            ProductApiRetrofit.getInstance(this).service.getProduct(product)
        res.enqueue(object : Callback<JsonObject?> {
            @SuppressLint("LongLogTag")
            override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {
                Log.d("response.toString()", response.toString())
                Log.d("response.body.toString()", response.body().toString())

                if (response.isSuccessful) {

                    val result = response.body()!!.getAsJsonObject("result")

                    println("step ******************************************************** 00i-b");

                    Log.d("response.body.getAsJsonArray.toString", result.toString())


                        val json = result

                        productName.text = json.getAsJsonPrimitive("productName")!!.asString
                        companyName.text = json.getAsJsonPrimitive("companyName")!!.asString
                        price.text = json.getAsJsonPrimitive("price")!!.asInt.toString()
                        gpa.text = json.getAsJsonPrimitive("gpa")!!.asInt.toString()
                        ranking.text = json.getAsJsonPrimitive("ranking")!!.asInt.toString()
                        review.text = json.getAsJsonPrimitive("review")!!.asInt.toString()
                        deliveryCost.text =
                            json.getAsJsonPrimitive("deliveryCost")!!.asInt.toString()

                        println("step ******************************************************** 00i-3");

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

}