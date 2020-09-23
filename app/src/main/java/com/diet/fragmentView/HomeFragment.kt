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
import com.diet.R

import com.diet.ProductInfo
import com.diet.ProductList
import com.diet.RecyclerViewDecoration
import com.diet.adapter.CustomAdapter
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
    private lateinit var newProduct1Name: TextView

    var new_product_company = ""
    var productNo = 0
    var product_code = ""
    var price = 0
    var product_name = ""
    var unit = ""

    var salesStandCode = ""
    var productCode = ""

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
        newProduct1Name = view.findViewById(R.id.newProduct1Name)

        getProductList()


        // 인기 신제품 리스트
        newProduct.setOnClickListener {
            activity?.let {

                val goNewProduct = Intent(it, ProductList::class.java)
                it.startActivity(goNewProduct)
            }
        }

        // 인기 신제품
        newProduct1Name.setOnClickListener {
            activity?.let {

                val goNewProduct1Name = Intent(it, ProductInfo::class.java)

                //goNewProduct1Name.putExtra("companyName", newProduct1Name)

                var salesStandCode = "NEW_PROD"
                //var productCode = "PROD_001"
                var standOrder = 1

                goNewProduct1Name.putExtra("salesStandCode", salesStandCode)
                goNewProduct1Name.putExtra("productCode", productCode)

                it.startActivity(goNewProduct1Name)
            }
        }

        return view
    }



    private fun getProductList() {
//        Log.d("getCreditBillByPeriod userId", userId)
        val product = ProductDTO()

        //certificate.applier = userId
        //certificate.startDate = "2020-09-01"
        //certificate.endDate = "2020-09-30"

        product.salesStandCode = "NEW_PROD"

        println("step ******************************************************** 00c");

        val res: Call<JsonObject> =
            ProductApiRetrofit.getInstance(context).service.getProductList(product)
        res.enqueue(object : Callback<JsonObject?> {
            override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {
                Log.d("getProductList", response.toString())
//                val result = response.body()?.getAsJsonObject("result")

                val result = response.body()

                println("step ******************************************************** 00d");

                if (response.isSuccessful) {
                    val result = response.body()!!.getAsJsonArray("result")
                    Log.d("ProductList result", result.toString())

                    println("step ******************************************************** 00e");


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

                        newProduct1Name.text = item.salesStandCode

                    }


                    //textViewGetTotalAmount?.text =
                    //    Utils.setDecimalFormat(loanTotalMoney.toString()) + "원"

                    ////



                } else {
                    try {


                        println("step ******************************************************** 00f");

                        val errorMessage = response.errorBody()!!.string()
                        Log.d("getProductList", errorMessage)
                    } catch (e: IOException) {
                        e.toString()
                    }
                }

                println("step ******************************************************** 00g");

//                monthTotalAmount.text = Utils.setDecimalFormat(loanTotalMoney.toString())+"원"
            }

            override fun onFailure(call: Call<JsonObject?>, t: Throwable) {


                println("step ******************************************************** 00h");

                Log.d("message", t.message )

                t.message


                println("step ******************************************************** 00i");

            }
        })
    }





}

private fun Intent.putExtra(s: String, newProduct1Name: TextView?) {

}
