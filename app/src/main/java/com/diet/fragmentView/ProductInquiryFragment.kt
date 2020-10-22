package com.diet.fragmentView

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.diet.R
import com.diet.model.ProductDTO
import com.diet.model.retrofits.ProductApiRetrofit
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_product.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class ProductInquiryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_product_inquiry, container, false)


        return view
    }

    /*private fun getProduct() {

        val product = ProductDTO()

        product.salesStandCode = salesStandCode
        product.productCode = productCode

        Log.d("salesStandCode", salesStandCode)
        Log.d("productCode", productCode)
        Log.d("productName", productName)

        val res: Call<JsonObject> =
            ProductApiRetrofit.getInstance(this).service.getProduct(product)
        res.enqueue(object : Callback<JsonObject?> {
            override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {
                Log.d("response.toString()", response.toString())

                if (response.isSuccessful) {
                    val result = response.body()?.getAsJsonObject("result")

                    textView_productName.text = result?.getAsJsonPrimitive("productName")!!.asString
                    textView_companyName.text = result?.getAsJsonPrimitive("companyName")!!.asString
                    *//* textView_price.text = result?.getAsJsonPrimitive("price")!!.asInt.toString()
                     textView_gpa.text = result?.getAsJsonPrimitive("gpa")!!.asInt.toString()
                     textView_ranking.text = result?.getAsJsonPrimitive("ranking")!!.asInt.toString()*//*
                    *//* textView_review.text = result?.getAsJsonPrimitive("review")!!.asInt.toString()*//*
                    *//*textView_deliveryCost.text =*//*
                    result?.getAsJsonPrimitive("deliveryCost")!!.asInt.toString()


                    productName = result?.getAsJsonPrimitive("productName")!!.asString
                    companyCode = result?.getAsJsonPrimitive("companyCode")!!.asString
                    companyName = result?.getAsJsonPrimitive("companyName")!!.asString
                    qty = result?.getAsJsonPrimitive("ranking")!!.asInt
                    unit = result?.getAsJsonPrimitive("unit")!!.asString
                    price = result?.getAsJsonPrimitive("price")!!.asInt
                    ranking = result?.getAsJsonPrimitive("ranking")!!.asInt
                    gpa = result?.getAsJsonPrimitive("gpa")!!.asInt
                    review = result?.getAsJsonPrimitive("review")!!.asInt
                    deliveryCost =  result?.getAsJsonPrimitive("deliveryCost")!!.asInt

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


    }*/
}