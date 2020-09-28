package com.diet


import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.diet.model.ProductDTO
import com.diet.model.retrofits.ProductApiRetrofit
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diet.adapter.FavoriteNewProductAdapter


class ProductList() : AppCompatActivity() {

    private lateinit var newProduct: TextView
    private lateinit var listViewProduct: ListView
    private lateinit var recyclerView: RecyclerView

    var salesStandCode = ""
    var salesStandName = ""
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

    var category2Code = ""
    var category2Name = ""

    var accountList = arrayListOf<ProductDTO>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
        listViewProduct =  findViewById(R.id.listViewProductList)
        newProduct = findViewById(R.id.newProduct)

        //카테고리 선택해서 들어왔을때
        if(intent.hasExtra("category2Code")){

            category2Code = intent.getStringExtra("category2Code")
            category2Name = intent.getStringExtra("category2Name")
            newProduct.text = category2Name
            Log.d("category",category2Code+ category2Name)

            getProductList(category2Code)
        }

        //메인화면에서 인기신제품 더보기로 들어왔을때
        else{
            getProductList()

        }




    }


    private fun getProductList() {
        val product = ProductDTO()


        product.salesStandCode = "NEW_PROD"


        val res: Call<JsonObject> =
            ProductApiRetrofit.getInstance(applicationContext).service.getProductList(product)
        res.enqueue(object : Callback<JsonObject?> {
            override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {
                Log.d("getProductList", response.toString())

                val result = response.body()


                if (response.isSuccessful) {
                    val result = response.body()!!.getAsJsonArray("result")
                    Log.d("ProductList result", result.toString())



                    for (j in result) {
                        val json = j.asJsonObject
                        val item = ProductDTO()

                        item.salesStandCode = json.getAsJsonPrimitive("salesStandCode")!!.asString
                        item.salesStandName = json.getAsJsonPrimitive("salesStandName")!!.asString
                        item.productCode = json.getAsJsonPrimitive("productCode")!!.asString
                        item.productName = json.getAsJsonPrimitive("productName")!!.asString
                        item.companyCode = json.getAsJsonPrimitive("companyCode")!!.asString
                        item.companyName = json.getAsJsonPrimitive("companyName")!!.asString
                        item.qty = json.getAsJsonPrimitive("qty")!!.asInt
                        item.unit = json.getAsJsonPrimitive("unit")!!.asString
                        item.price = json.getAsJsonPrimitive("price")!!.asInt
                        item.ranking = json.getAsJsonPrimitive("ranking")!!.asInt
                        item.gpa = json.getAsJsonPrimitive("gpa")!!.asInt
                        item.review = json.getAsJsonPrimitive("review")!!.asInt
                        item.deliveryCost = json.getAsJsonPrimitive("deliveryCost")!!.asInt


                        accountList.add(item)

                    }


                    ////
                    recyclerView = findViewById(R.id.recyclerView)
                    var favoriteNewProductAdapter = FavoriteNewProductAdapter(applicationContext, accountList)                   // CustomaApdater() 를 저장하는 apdater 변수 생성
                    val recyclerDecoration = RecyclerViewDecoration(20,5)

                    recyclerView.addItemDecoration(recyclerDecoration)
                    recyclerView.adapter = favoriteNewProductAdapter // CustomAdapter에 선언한 ProductList 변수에 data  변수 전달
                    //ListView 형식을 위한 LinearLayoutManager\
                    recyclerView.layoutManager = GridLayoutManager(applicationContext,2)




                } else {
                    try {



                        val errorMessage = response.errorBody()!!.string()
                        Log.d("getProductList", errorMessage)
                    } catch (e: IOException) {
                        e.toString()
                    }
                }


//                monthTotalAmount.text = Utils.setDecimalFormat(loanTotalMoney.toString())+"원"
            }

            override fun onFailure(call: Call<JsonObject?>, t: Throwable) {



                Log.d("message", t.message )

                t.message


                println("step ******************************************************** 00i");

            }
        })
    }
    private fun getProductList(category2Code : String) {
        val product = ProductDTO()

        product.category2Code = category2Code


        val res: Call<JsonObject> =
            ProductApiRetrofit.getInstance(applicationContext).service.getCategoryListByCategory2Code(product)
        res.enqueue(object : Callback<JsonObject?> {
            override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {
                Log.d("getProductList", response.toString())
//                val result = response.body()?.getAsJsonObject("result")

                val result = response.body()

                if (response.isSuccessful) {
                    val result = response.body()!!.getAsJsonArray("result")
                    Log.d("ProductList result", result.toString())



                    for (j in result) {
                        val json = j.asJsonObject
                        val item = ProductDTO()

                        item.salesStandCode = json.getAsJsonPrimitive("salesStandCode")!!.asString
                        item.salesStandName = json.getAsJsonPrimitive("salesStandName")!!.asString
                        item.productCode = json.getAsJsonPrimitive("productCode")!!.asString
                        item.productName = json.getAsJsonPrimitive("productName")!!.asString
                        item.companyCode = json.getAsJsonPrimitive("companyCode")!!.asString
                        item.companyName = json.getAsJsonPrimitive("companyName")!!.asString
//                        item.qty = json.getAsJsonPrimitive("qty")!!.asInt
                        item.unit = json.getAsJsonPrimitive("unit")!!.asString
//                        item.price = json.getAsJsonPrimitive("price")!!.asInt
                        item.ranking = json.getAsJsonPrimitive("ranking")!!.asInt
                        item.gpa = json.getAsJsonPrimitive("gpa")!!.asInt
                        item.review = json.getAsJsonPrimitive("review")!!.asInt
                        item.deliveryCost = json.getAsJsonPrimitive("deliveryCost")!!.asInt


                        accountList.add(item)

                    }

                    recyclerView = findViewById(R.id.recyclerView)
                    var favoriteNewProductAdapter = FavoriteNewProductAdapter(applicationContext, accountList)                   // CustomaApdater() 를 저장하는 apdater 변수 생성
                    val recyclerDecoration = RecyclerViewDecoration(20,5)

                    recyclerView.addItemDecoration(recyclerDecoration)
                    recyclerView.adapter = favoriteNewProductAdapter // CustomAdapter에 선언한 ProductList 변수에 data  변수 전달
                    //ListView 형식을 위한 LinearLayoutManager\
                    recyclerView.layoutManager = GridLayoutManager(applicationContext,2)


                } else {
                    try {
                        val errorMessage = response.errorBody()!!.string()
                        Log.d("getProductList", errorMessage)
                    } catch (e: IOException) {
                        e.toString()
                    }
                }
            }

            override fun onFailure(call: Call<JsonObject?>, t: Throwable) {



                Log.d("message", t.message)

                t.message

            }
        })
    }


}