package com.diet.fragmentView

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import android.widget.TextView
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
import org.jetbrains.anko.sdk25.coroutines.onQueryTextListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    var searchList = arrayListOf<ProductDTO>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ):
            View? {
        var view = inflater.inflate(R.layout.fragment_search, container, false)
        recyclerView = view.findViewById(R.id.search_recyclerview)

        val lately_search = view.findViewById<Button>(R.id.lately_search)
        val popularity_search = view.findViewById<Button>(R.id.popularity_search)
        val recommend_search = view.findViewById<Button>(R.id.recommend_search)
        val search_area = view.findViewById<SearchView>(R.id.search_area)

/*        var textView_product_name : TextView = view.findViewById(R.id.textView_product_name)*/

       /* var bold_text : String =textView_product_name.text.toString()*/

        var searchList: ArrayList<SearchDTO> = arrayListOf()
       search_area.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
           override fun onQueryTextSubmit(query: String?): Boolean {
               search_product(query!!)
               return true
           }

           override fun onQueryTextChange(newText: String?): Boolean {
                      search_product(newText!!)
                  /* if (newText == bold_text){
                       val span =SpannableString(bold_text)
                       span.setSpan(StyleSpan(Typeface.BOLD),bold_text.length)
                   }*/
            return true
           }

       })


        if (search_area.hasFocus()) {
            search_layout.setBackgroundColor(123456)
        }


        return view
    }

    private fun search_product(searchKeyWord: String) {
        val product = ProductDTO()
        //검색어
        product.keyword = searchKeyWord
        searchList.clear()

        val res: Call<JsonObject> =
            ProductApiRetrofit.getInstance(requireContext()).service.getProductSearch(product)
        res.enqueue(object : Callback<JsonObject> {
            @SuppressLint("LongLogTag")
            override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {
                val result = response.body()
                Log.d("result response.body()", result.toString())
                if (response.isSuccessful) {
                    val result = response.body()!!.getAsJsonArray("result")

                    Log.d("result response.body()!!.getAsJsonArray(result)", result.toString())

                    for (j in result) {
                        val json = j.asJsonObject
                        val item = ProductDTO()
//
//                        item.productCode = json?.getAsJsonPrimitive("productCode")!!.asString
                        item.productName = json?.getAsJsonPrimitive("productName")!!.asString
//                        item.companyCode = json?.getAsJsonPrimitive("companyCode")!!.asString
                        item.companyName = json?.getAsJsonPrimitive("companyName")!!.asString
//                        item.qty = json?.getAsJsonPrimitive("ranking")!!.asInt
//                        item.unit = json?.getAsJsonPrimitive("unit")!!.asString
//                        item.price = json?.getAsJsonPrimitive("price")!!.asInt
//                        item.ranking = json?.getAsJsonPrimitive("ranking")!!.asInt
//                        item.gpa = json?.getAsJsonPrimitive("gpa")!!.asInt
//                        item.review = json?.getAsJsonPrimitive("review")!!.asInt
//                        item.deliveryCost = json?.getAsJsonPrimitive("deliveryCost")!!.asInt

                        searchList.add(item)
                    }

                    val searchAdapter = SearchAdapter(requireContext(), searchList,searchKeyWord)
                    recyclerView.adapter = searchAdapter
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}
