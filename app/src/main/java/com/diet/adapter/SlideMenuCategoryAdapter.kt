package com.diet.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diet.R
import com.diet.model.ProductDTO
import com.diet.model.retrofits.ProductApiRetrofit
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.item_slide_menu_category.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class SlideMenuCategoryAdapter(val context: Context, private val productList: ArrayList<ProductDTO>) :
    RecyclerView.Adapter<SlideMenuCategoryAdapterHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideMenuCategoryAdapterHolder {
        //레이아웃 생성
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_slide_menu_category,
            parent, false
        )

        return SlideMenuCategoryAdapterHolder(view, context)
    }


    override fun getItemCount(): Int {
        //리스트 아이템수
        return productList.size
    }

    override fun onBindViewHolder(holder: SlideMenuCategoryAdapterHolder, position: Int) {
        //데이터 삽입
        val data = productList[position]
        holder.setListData(data)
    }




}

//매개변수로 있는 아이템은 하나의 리스트 아이엩ㅁ을 아답터로부터 전달받는 변수
class SlideMenuCategoryAdapterHolder(itemView: View, context: Context) :
    RecyclerView.ViewHolder(itemView) {  // 뷰홀더를 사용하기위해 상속

    val context = context
    var category1Code = ""
    val recyclerViewCategory2: RecyclerView  = itemView.findViewById(R.id.recyclerViewCategory2)
    var count = 0
    fun setListData(productList: ProductDTO) {

        itemView.textView_category.text = productList.category1Name
        category1Code = productList.category1Code.toString()
        itemView.setOnClickListener {
            if (count == 0){
                count = 1
                getProductList()
                recyclerViewCategory2.visibility = View.VISIBLE

            }else {
                count = 0
                recyclerViewCategory2.visibility = View.GONE
            }
        }

    }

    private fun getProductList() {
        val product = ProductDTO()
        var accountList = arrayListOf<ProductDTO>()

        product.category1Code = category1Code


        val res: Call<JsonObject> =
            ProductApiRetrofit.getInstance(context).service.getCategoryListDetail(product)
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


                        item.category2Code =json.getAsJsonPrimitive("category2Code")!!.asString
                        Log.d("category2Code",json.getAsJsonPrimitive("category2Code")!!.asString)
                        item.category2Name =json.getAsJsonPrimitive("category2Name")!!.asString
                        Log.d("category2Name",json.getAsJsonPrimitive("category2Name")!!.asString)


                        accountList.add(item)

                    }
                    val slideMenuCategoryAdapter = SlideMenuCategoryAdapter2(
                        context  ,
                        accountList
                    )



                    recyclerViewCategory2
                    recyclerViewCategory2.adapter =
                        slideMenuCategoryAdapter
                    recyclerViewCategory2.layoutManager = LinearLayoutManager(context)





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
