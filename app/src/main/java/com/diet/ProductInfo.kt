package com.diet


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


        if (intent.hasExtra("salesStandCode")) {
            salesStandCode = intent.getStringExtra("salesStandCode")
            productCode = intent.getStringExtra("productCode")
            getProductList()

        } else {
            salesStandCode = "NEW_PROD1"
            getProductList()
        }

    }


    private fun getProductList() {

        val product = ProductDTO()

        Log.d("salesStandCode" ,  salesStandCode)
        Log.d("productCode" ,  productCode)
        product.salesStandCode = salesStandCode
        product.productCode = productCode


        val res: Call<JsonObject> =
            ProductApiRetrofit.getInstance(this).service.getProduct(product)
        res.enqueue(object : Callback<JsonObject?> {
            override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {
                Log.d("response.toString()", response.toString())

                if (response.isSuccessful) {
                    val result = response.body()?.getAsJsonObject("result")

                        productName.text = result?.getAsJsonPrimitive("productName")!!.asString
                        companyName.text = result?.getAsJsonPrimitive("companyName")!!.asString
                        price.text = result?.getAsJsonPrimitive("price")!!.asInt.toString()
                        gpa.text = result?.getAsJsonPrimitive("gpa")!!.asInt.toString()
                        ranking.text = result?.getAsJsonPrimitive("ranking")!!.asInt.toString()
                        review.text = result?.getAsJsonPrimitive("review")!!.asInt.toString()
                        deliveryCost.text = result?.getAsJsonPrimitive("deliveryCost")!!.asInt.toString()



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