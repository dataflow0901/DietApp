package com.diet.fragmentView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diet.R
import com.diet.adapter.SearchAdapter
import com.diet.model.ProductDTO
import com.diet.model.SearchDTO
import com.diet.model.retrofits.ProductApiRetrofit
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_product_list.*
import kotlinx.android.synthetic.main.fragment_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {
    lateinit var recyclerView :RecyclerView
    var searchList = arrayListOf<ProductDTO>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View? {
        var view = inflater.inflate(R.layout.fragment_search, container, false)
         recyclerView = view.findViewById<RecyclerView>(R.id.search_recyclerview)

        val lately_search = view.findViewById<Button>(R.id.lately_search)
        val popularity_search = view.findViewById<Button>(R.id.popularity_search)
        val recommend_search = view.findViewById<Button>(R.id.recommend_search)
        val search_area = view.findViewById<SearchView>(R.id.search_area)


        var searchList :ArrayList<SearchDTO> = arrayListOf()

        if (search_area.hasFocus()){
            search_layout.setBackgroundColor(123456)
        }


        return view
    }

    private fun search_product(searchKeyWord : String){
        val product = ProductDTO()
        //검색어
        product.searchKeyWord = searchKeyWord
        searchList.clear()

        val res:Call<JsonObject> = ProductApiRetrofit.getInstance(activity?.applicationContext).service.getProductSearch(product)
        res.enqueue(object :Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {
                val result = response.body()
                if (response.isSuccessful) {
                    val result = response.body()!!.getAsJsonArray("result")

                    for (j in result) {
                        val json = j.asJsonObject
                        val item = ProductDTO()

                        item.productName = result?.getAsJsonPrimitive("productName")!!.asString
                        item.productName = result?.asJsonPrimitive("productName")!!.asString
                        item.companyCode = result?.getAsJsonPrimitive("companyCode")!!.asString
                        item.companyName = result?.getAsJsonPrimitive("companyName")!!.asString
                        item.qty = result?.getAsJsonPrimitive("ranking")!!.asInt
                        item.unit = result?.getAsJsonPrimitive("unit")!!.asString
                        item.price = result?.getAsJsonPrimitive("price")!!.asInt
                        item.ranking = result?.getAsJsonPrimitive("ranking")!!.asInt
                        item.gpa = result?.getAsJsonPrimitive("gpa")!!.asInt
                        item.review = result?.getAsJsonPrimitive("review")!!.asInt
                        item.deliveryCost = result?.getAsJsonPrimitive("deliveryCost")!!.asInt

                        searchList.add(item)
                    }
                    recyclerView = view!!.findViewById<RecyclerView>(R.id.search_recyclerview)

                    recyclerView.adapter = SearchAdapter
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}
