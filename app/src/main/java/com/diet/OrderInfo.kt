package com.diet

//import com.blildo.views.fragmentView.LentCertificateFragment
//import com.blildo.views.fragmentView.BorrowedCertificateFragment
//import com.blildo.views.fragmentView.ManagementFragment
//import com.blildo.views.fragmentView.HomeFragment
//import com.blildo.views.hambuger.Hamburger

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.diet.model.OrderDTO
import com.diet.model.ProductDTO
import com.diet.model.retrofits.OrderApiRetrofit
import com.diet.model.retrofits.ProductApiRetrofit
import com.diet.utils.Utils
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.activity_order.textView_price
import kotlinx.android.synthetic.main.activity_order.textView_productName
import kotlinx.android.synthetic.main.activity_product.*
import kotlinx.android.synthetic.main.activity_order.textView_name
import kotlinx.android.synthetic.main.activity_order.textView_userCellNo
import kotlinx.android.synthetic.main.activity_order.textView_email
import kotlinx.android.synthetic.main.activity_order.textView_deliveryName
import kotlinx.android.synthetic.main.activity_order.textView_deliveryUserCellNo
import kotlinx.android.synthetic.main.activity_order.textView_deliveryAddress
import kotlinx.android.synthetic.main.activity_order.textView_point_remaining
import kotlinx.android.synthetic.main.activity_order.textView_point_used
import kotlinx.android.synthetic.main.activity_order.textView_point_remaining_predict


class OrderInfo : AppCompatActivity() {

    //val companyName = view.findViewById<TextView>(R.id.companyName)

    var userId = ""
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
    var totalprice: Int = 0
    var name = ""
    var userCellNo = ""
    var email = ""
    var deliveryName = ""
    var deliveryUserCellNo = ""
    var deliveryAddress = ""
    var pointRemaining = 0
    var pointRemaining_used = 0
    var pointRemainingPredict = 0
    private lateinit var textView_input_point: TextView
    lateinit var textView_point_amount: TextView
    lateinit var textView_predict_amount: TextView
    lateinit var textView_point_remaining: TextView
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        textView_input_point = findViewById(R.id.textView_input_point)
        textView_point_amount = findViewById(R.id.textView_point_amount)
        textView_predict_amount = findViewById(R.id.textView_predict_amount)
        textView_point_remaining = findViewById(R.id.textView_point_remaining)
        if (intent.hasExtra("salesStandCode")) {

            println("step ******************************************************** productInfo - 001")
            salesStandCode = "NEW_PROD"

//            salesStandCode = intent.getStringExtra("salesStandCode")
            productCode = intent.getStringExtra("productCode")


            productName = intent.getStringExtra("productName")
            companyCode = intent.getStringExtra("companyCode")
            companyName = intent.getStringExtra("companyName")
            qty = intent.getIntExtra("qty", 0)
            unit = intent.getStringExtra("unit")
            price = intent.getIntExtra("price", 0)
            ranking = intent.getIntExtra("ranking", 0)
            gpa = intent.getIntExtra("gpa", 0)
            review = intent.getIntExtra("review", 0)
            deliveryCost = intent.getIntExtra("deliveryCost", 0)


            //getProduct()

        } else {

            println("step ******************************************************** productInfo - 002")

            salesStandCode = "NEW_PROD1"
//            salesStandCode = intent.getStringExtra("salesStandCode")
            productCode = intent.getStringExtra("productCode")


            productName = intent.getStringExtra("productName")
            companyCode = intent.getStringExtra("companyCode")
            companyName = intent.getStringExtra("companyName")
            qty = intent.getIntExtra("qty", 0)
            unit = intent.getStringExtra("unit")
            price = intent.getIntExtra("price", 0)
            ranking = intent.getIntExtra("ranking", 0)
            gpa = intent.getIntExtra("gpa", 0)
            review = intent.getIntExtra("review", 0)
            deliveryCost = intent.getIntExtra("deliveryCost", 0)


            Log.d("productName", productName)


            //getProduct()
        }

        Log.d("productName", productName)

        //textView_companyName.text = result?.getAsJsonPrimitive("companyName")!!.asString
        textView_productName.text = productName
        textView_price.text = price.toString()
        textView_qty.text = qty.toString()
        textView_amount.text = (qty * price).toString()
        textView_product_amount.text = (qty * price).toString()
        textView_delivery_amount.text = deliveryCost.toString()
        textView_predict_amount.text = (qty * price + deliveryCost).toString()



        getOrderInfoByUserID()

        textView_point_amount.text = (qty * price).toString()


        paymentButton.setOnClickListener {
            //val nextIntent = Intent(this, OrderInfo::class.java)

            addOrder()

            //startActivity(nextIntent)

        }

        totalprice = (qty * price + deliveryCost)

        textView_input_point.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //textView_input_point에 내용이 있을경우
                if (textView_input_point.length() >= 1) {
                    var writePoint = textView_input_point.text.toString().toInt()
                    //결제 예상금액
                    textView_predict_amount.text =
                        ((totalprice - writePoint).toString())
                    textView_point_amount.text = writePoint.toString()
                    //보유포인트보다 textView_input_point에 작성한 포인트가 더 클경우 textView_input_point에 보유포인트만큼 작성
                    if (textView_point_remaining.text.toString().toInt() < textView_input_point.text.toString().toInt()) {
                        textView_input_point.text = textView_point_remaining.text.toString()
                    }
                    // textView_input_point에 작성한 포인트가 전체가격(개수 * 가격 + 배달료)보다 더 클경우 textView_input_point에 전체가격만큼 작성
                    if (textView_input_point.text.toString().toInt() > totalprice.toString().toInt()){
                        textView_input_point.text =  totalprice.toString()
                    }
                } else if (textView_input_point.length() == 0) {
                    //textView_input_point에 작성한 내용이 없을경우 textView_input_point를 0으로 초기화시킨다
                    textView_point_amount.text = "0"
                    textView_predict_amount.text = ((qty * price + deliveryCost).toString())
                }
            }

        })


        //전액 사용 버튼 클릭시
        pointButton.setOnClickListener {

            textView_point_amount.text = totalprice.toString() + "원"
            textView_predict_amount.text =
                (totalprice - (qty * price + deliveryCost)).toString() + "원"
            textView_input_point.text = totalprice.toString()
            Log.d("포인트", textView_point_remaining.text.toString())
            //보유포인트가 textView_input_point의 작성한 포인트 보다 작을경우 textView_input_point에 보유포인트만큼 기입
            if (textView_point_remaining.text.toString()
                    .toInt() < textView_input_point.text.toString().toInt()
            ) {
                textView_input_point.text = textView_point_remaining.text.toString()
            }
            //val nextIntent = Intent(this, OrderInfo::class.java)

            //textView_input_point.value = pointRemaining.toString()

            //textView_input_point.text = findViewById(R.id.textView_point_remaining)

            //startActivity(nextIntent)

        }


    }


    private fun getOrderInfoByUserID() {

        val order = OrderDTO()

        Log.d("productCode", productCode)

        order.userId = Utils.getUserId(applicationContext)
        //order.userId = userId

        val res: Call<JsonObject> =
            OrderApiRetrofit.getInstance(this).service.getOrderInfoByUserID(order)
        res.enqueue(object : Callback<JsonObject?> {
            override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {
                Log.d("response.toString()", response.toString())

                val result = response.body()

                if (response.isSuccessful) {

                    val result = response.body()?.getAsJsonObject("result")

                    Log.d("getOrderInfoByUserID", result.toString())

                    println("step ******************************************************** orderInfo api 00e");

                    textView_name.text = result?.getAsJsonPrimitive("name")!!.asString
                    textView_userCellNo.text = result?.getAsJsonPrimitive("userCellNo")!!.asString
                    textView_email.text = result?.getAsJsonPrimitive("email")!!.toString()
                    textView_deliveryName.text =
                        result?.getAsJsonPrimitive("deliveryName")!!.toString()
                    textView_deliveryUserCellNo.text =
                        result?.getAsJsonPrimitive("deliveryUserCellNo")!!.toString()
                    textView_deliveryAddress.text =
                        result?.getAsJsonPrimitive("deliveryAddress")!!.toString()
                    textView_point_remaining.text =
                        result?.getAsJsonPrimitive("pointRemaining")!!.asInt.toString()
                    textView_point_used.text =
                        result?.getAsJsonPrimitive("pointUsed")!!.asInt.toString()
                    textView_point_remaining_predict.text =
                        result?.getAsJsonPrimitive("pointRemaining")!!.asInt.toString()


                    println("step ******************************************************** orderInfo api 00f");

                    pointRemaining = result?.getAsJsonPrimitive("pointRemaining")!!.asInt

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


    private fun getProduct() {

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
                    textView_price.text = result?.getAsJsonPrimitive("price")!!.asInt.toString()
                    textView_gpa.text = result?.getAsJsonPrimitive("gpa")!!.asInt.toString()
                    textView_ranking.text = result?.getAsJsonPrimitive("ranking")!!.asInt.toString()
                    textView_review.text = result?.getAsJsonPrimitive("review")!!.asInt.toString()
                    textView_deliveryCost.text =
                        result?.getAsJsonPrimitive("deliveryCost")!!.asInt.toString()


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


    private fun addOrder() {

        val order = OrderDTO()

        Log.d("productCode", productCode)

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
        order.ranking = ranking
        order.gpa = gpa
        order.review = review
        order.deliveryCost = deliveryCost

        order.originalAmount = price * qty
        order.discountAmount = 0
        order.purchaseAmount = price * qty
        order.deliveryCost = deliveryCost
        //order.paymentNo = paymentNo
        //order.paymentCode = paymentCode
        //order.paymentName = paymentName
        //order.paymentDate = paymentDate
        order.paymentTotalAmount = price * qty + deliveryCost
        order.paidRealAmount = price * qty + deliveryCost
        order.paidCouponAmount = 0
        order.paidPointAmount = 0

        val res: Call<JsonObject> =
            OrderApiRetrofit.getInstance(this).service.addOrder(order)
        res.enqueue(object : Callback<JsonObject?> {
            override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {
                Log.d("response.toString()", response.toString())

                val result = response.body()

                if (response.isSuccessful) {

                    Log.d("addOrder", result.toString())
                    Toast.makeText(applicationContext, "결제가 성공적으로 완료되었습니다.", Toast.LENGTH_SHORT)
                        .show()
                    finish()


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