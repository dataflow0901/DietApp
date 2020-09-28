package com.diet

//import com.blildo.views.fragmentView.LentCertificateFragment
//import com.blildo.views.fragmentView.BorrowedCertificateFragment
//import com.blildo.views.fragmentView.ManagementFragment
//import com.blildo.views.fragmentView.HomeFragment
//import com.blildo.views.hambuger.Hamburger


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.diet.model.OrderDTO
import com.diet.model.ProductDTO
import com.diet.model.retrofits.OrderApiRetrofit
import com.diet.model.retrofits.ProductApiRetrofit
import com.diet.utils.Utils
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

    var productName = ""
    var companyCode = ""
    var companyName = ""
    var qty = 0
    var unit = ""
    var price = 0
    var ranking = 0
    var gpa = 0
    var review = 0
    var deliveryCost = 0


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)


        if (intent.hasExtra("salesStandCode")) {

            println("step ******************************************************** productInfo - 001")
            salesStandCode = "NEW_PROD"

//            salesStandCode = intent.getStringExtra("salesStandCode")
            productCode = intent.getStringExtra("productCode")


            productName = intent.getStringExtra("productName")
            companyCode = intent.getStringExtra("companyCode")
            companyName = intent.getStringExtra("companyName")
            qty = intent.getIntExtra("qty", 0 )
            unit = intent.getStringExtra("unit")
            price = intent.getIntExtra("price", 0)
            ranking = intent.getIntExtra("ranking", 0)
            gpa = intent.getIntExtra("gpa", 0)
            review = intent.getIntExtra("review", 0)
            deliveryCost = intent.getIntExtra("deliveryCost", 0)


            getProduct()

        } else {

            println("step ******************************************************** productInfo - 002")

            salesStandCode = "NEW_PROD1"
            //salesStandCode = intent.getStringExtra("salesStandCode")
            productCode = intent.getStringExtra("productCode")

            productName = intent.getStringExtra("productName")
            companyCode = intent.getStringExtra("companyCode")
            companyName = intent.getStringExtra("companyName")
            qty = intent.getIntExtra("qty", 0 )
            unit = intent.getStringExtra("unit")
            ranking = intent.getIntExtra("ranking", 0)
            gpa = intent.getIntExtra("gpa", 0)
            review = intent.getIntExtra("review", 0)
            deliveryCost = intent.getIntExtra("deliveryCost", 0)

            Log.d("productName" ,  productName)

            getProduct()

        }


        orderButton.setOnClickListener{
            val intent = Intent(this, OrderInfo::class.java)

            intent.putExtra("salesStandCode", salesStandCode)
            //intent.putExtra("salesStandName", salesStandName)
            intent.putExtra("productCode", productCode)
            intent.putExtra("productName", productName)
            intent.putExtra("companyCode", companyCode)
            intent.putExtra("companyName", companyName)
            intent.putExtra("qty", qty)
            intent.putExtra("unit", unit)
            intent.putExtra("price", price)
            intent.putExtra("ranking", ranking)
            intent.putExtra("gpa", gpa)
            intent.putExtra("review", review)
            intent.putExtra("deliveryCost", deliveryCost)

            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;

            startActivity(intent)

        }


        cartButton.setOnClickListener{

            addCart()

        }

        title_zzim.setOnClickListener{

            addZzim()

        }

    }


    private fun getProduct() {

        val product = ProductDTO()

        product.salesStandCode = salesStandCode
        product.productCode = productCode

        Log.d("salesStandCode" ,  salesStandCode)
        Log.d("productCode" ,  productCode)
        Log.d("productName" ,  productName)

        val res: Call<JsonObject> =
            ProductApiRetrofit.getInstance(this).service.getProduct(product)
        res.enqueue(object : Callback<JsonObject?> {
            override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {
                Log.d("response.toString()", response.toString())

                if (response.isSuccessful) {
                    val result = response.body()?.getAsJsonObject("result")

                    textView_productName.text = result?.getAsJsonPrimitive("productName")!!.asString
                    textView_companyName.text = result?.getAsJsonPrimitive("companyName")!!.asString
                    textView_price.text = result?.getAsJsonPrimitive("price")!!.asInt.toString()
                    textView_gpa.text = result?.getAsJsonPrimitive("gpa")!!.asInt.toString()
                    textView_ranking.text = result?.getAsJsonPrimitive("ranking")!!.asInt.toString()
                    textView_review.text = result?.getAsJsonPrimitive("review")!!.asInt.toString()
                    textView_deliveryCost.text = result?.getAsJsonPrimitive("deliveryCost")!!.asInt.toString()


/*
                    productName= result?.getAsJsonPrimitive("productName")!!.asString
                    companyName = result?.getAsJsonPrimitive("companyName")!!.asString
                    price = result?.getAsJsonPrimitive("price")!!.asInt
                    gpa = result?.getAsJsonPrimitive("gpa")!!.asInt
                    ranking = result?.getAsJsonPrimitive("ranking")!!.asInt
                    review = result?.getAsJsonPrimitive("review")!!.asInt
                    deliveryCost = result?.getAsJsonPrimitive("deliveryCost")!!.asInt
*/


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


    private fun addCart() {

        val order = OrderDTO()

        Log.d("productCode" ,  productCode)

        order.userId = Utils.getUserId(applicationContext)
        order.productCode = productCode
        order.salesStandCode = salesStandCode
        //order.salesStandName = salesStandName
        order.productCode = productCode
        order.productName = productName
        order.companyCode = companyCode
        order.companyName = companyName
        order.qty = qty
        order.unit = unit
        order.price = price

        val res: Call<JsonObject> =
            OrderApiRetrofit.getInstance(this).service.addCart(order)
        res.enqueue(object : Callback<JsonObject?> {
            override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {
                Log.d("response.toString()", response.toString())

                val result = response.body()

                if (response.isSuccessful) {

                    Log.d("addOrder", result.toString())
                    Toast.makeText(applicationContext, "해당 상품이 장바구니에 등록되었습니다.", Toast.LENGTH_SHORT).show()

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


    private fun addZzim() {

        val order = OrderDTO()

        Log.d("productCode" ,  productCode)

        order.userId = Utils.getUserId(applicationContext)
        order.productCode = productCode
        order.salesStandCode = salesStandCode
        //order.salesStandName = salesStandName
        order.productCode = productCode
        order.productName = productName
        order.companyCode = companyCode
        order.companyName = companyName
        order.qty = qty
        order.unit = unit
        order.price = price

        val res: Call<JsonObject> =
            OrderApiRetrofit.getInstance(this).service.addZzim(order)
        res.enqueue(object : Callback<JsonObject?> {
            override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {
                Log.d("response.toString()", response.toString())

                val result = response.body()

                if (response.isSuccessful) {

                    Log.d("addOrder", result.toString())
                    Toast.makeText(applicationContext, "해당 상품이 찜 리스트에 등록되었습니다.", Toast.LENGTH_SHORT).show()
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



}