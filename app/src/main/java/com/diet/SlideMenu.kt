package com.diet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diet.adapter.SlideMenuCategoryAdapter
import com.diet.adapter.SlideMenuCategoryAdapterHolder
import com.diet.model.ProductDTO
import com.diet.model.retrofits.ProductApiRetrofit
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class SlideMenu : AppCompatActivity() {
    lateinit var recyclerViewSlideMenu : RecyclerView
    var accountList = arrayListOf<ProductDTO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide_menu)

        recyclerViewSlideMenu = findViewById(R.id.recyclerViewCategory1)

        getProductList()

    }
    private fun getProductList() {
        val product = ProductDTO()

        val res: Call<JsonObject> =
            ProductApiRetrofit.getInstance(applicationContext).service.getCategoryList(product)
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


                        item.category1Code =json.getAsJsonPrimitive("category1Code")!!.asString
                        Log.d("category1Code",json.getAsJsonPrimitive("category1Code")!!.asString)
                        item.category1Name =json.getAsJsonPrimitive("category1Name")!!.asString
                        Log.d("category1Name",json.getAsJsonPrimitive("category1Name")!!.asString)


                        accountList.add(item)

                    }
                    val slideMenuCategoryAdapter = SlideMenuCategoryAdapter(
                        applicationContext  ,
                        accountList
                    )

                    val recyclerDecoration = RecyclerViewDecoration(20, 5)


                    recyclerViewSlideMenu.addItemDecoration(recyclerDecoration)
                    recyclerViewSlideMenu.adapter =
                        slideMenuCategoryAdapter
                    recyclerViewSlideMenu.layoutManager = LinearLayoutManager(applicationContext)





                } else {
                    try {


                        println("step ******************************************************** 00f");

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