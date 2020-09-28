package com.diet.fragmentView

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import android.widget.ListView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diet.R

import com.diet.ProductInfo
import com.diet.ProductList
import com.diet.RecyclerViewDecoration
import com.diet.adapter.FavoriteNewProductAdapter
import com.diet.adapter.HomeFavoriteNewProductAdapter
import com.diet.adapter.ProductListAdapter
import com.diet.model.ProductDTO
import com.diet.model.retrofits.ProductApiRetrofit
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_product.*
import kotlinx.android.synthetic.main.fragment_status.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

import kotlinx.android.synthetic.main.activity_product_list.listViewProductList


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    private lateinit var newProduct: TextView
    private lateinit var newProductMore: TextView
    private lateinit var recyclerViewNewFavoriteProduct: RecyclerView

    var new_product_company = ""
    var productNo = 0
    var product_code = ""
    var price = 0
    var product_name = ""
    var unit = ""
    var accountList = arrayListOf<ProductDTO>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_status, null)

        newProduct = view.findViewById(R.id.newProduct)
        newProductMore = view.findViewById(R.id.newProductMore)
        recyclerViewNewFavoriteProduct = view.findViewById(R.id.recyclerViewNewFavoriteProduct)


        // 인기 신제품 리스트
        newProductMore.setOnClickListener {
            activity?.let {

                val goNewProduct = Intent(it, ProductList::class.java)
                it.startActivity(goNewProduct)
            }
        }

        getProductList()

        // 인기 신제품


        return view
    }



    private fun getProductList() {
        val product = ProductDTO()

        product.salesStandCode = "NEW_PROD"


        val res: Call<JsonObject> =
            ProductApiRetrofit.getInstance(context).service.getProductList(product)
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
                        item.qty = json.getAsJsonPrimitive("qty")!!.asInt
                        item.unit = json.getAsJsonPrimitive("unit")!!.asString
                        item.price = json.getAsJsonPrimitive("price")!!.asInt
                        item.ranking = json.getAsJsonPrimitive("ranking")!!.asInt
                        item.gpa = json.getAsJsonPrimitive("gpa")!!.asInt
                        item.review = json.getAsJsonPrimitive("review")!!.asInt
                        item.deliveryCost = json.getAsJsonPrimitive("deliveryCost")!!.asInt


                        accountList.add(item)
                        if(accountList.size == 3) {
                            break
                        }

                    }

                    val homeFavoriteNewProductAdapter = HomeFavoriteNewProductAdapter(
                        requireContext(),
                        accountList
                    )

                    val recyclerDecoration = RecyclerViewDecoration(20, 5)

                    recyclerViewNewFavoriteProduct.addItemDecoration(recyclerDecoration)
                    recyclerViewNewFavoriteProduct.adapter =
                        homeFavoriteNewProductAdapter
                    recyclerViewNewFavoriteProduct.layoutManager = GridLayoutManager(requireContext(), 3)





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



                Log.d("message", t.message)

                t.message



            }
        })
    }
}