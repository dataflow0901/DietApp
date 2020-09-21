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
import com.diet.R

import com.diet.ProductInfo
import com.diet.ProductList
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

                it.startActivity(goNewProduct1Name)
            }
        }

        return view
    }


/*

    private fun getProductList() {
//        Log.d("getCreditBillByPeriod userId", userId)
        val product = ProductDTO()

        //certificate.applier = userId
        //certificate.startDate = "2020-09-01"
        //certificate.endDate = "2020-09-30"

        product.newProductCompany = "abc"
        product.productNo = 1
        product.productCode = "abc"
        product.price = 10000
        product.newProduct1Name = "abc"
        product.unit = "개"

        val res: Call<JsonObject> =
            ProductApiRetrofit.getInstance(requireContext()).service.getProductList(
                product
            )
        res.enqueue(object : Callback<JsonObject?> {
            override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {
                Log.d("getCreditBillByPeriod", response.toString())
//                val result = response.body()?.getAsJsonObject("result")

                val result = response.body()

                if (response.isSuccessful) {
                    val result = response.body()!!.getAsJsonArray("result")

                    for (j in result) {
                        val json = j.asJsonObject
                        val item = ProductDTO()

//                        item.name = json.getAsJsonPrimitive("creditorName")!!.asString
//                        item.amount =
//                            Utils.setDecimalFormat((json.getAsJsonPrimitive("principalAmount")!!.asInt).toString()) + "원"
//                        item.startDate = json.getAsJsonPrimitive("startDate")!!.asString
//                        item.endDate = json.getAsJsonPrimitive("expireDate")!!.asString
//                        item.interestRate =
//                            json.getAsJsonPrimitive("interestRate")!!.asFloat.toString() + "%"
//                        item.interestAmount =
//                            "(" + Utils.setDecimalFormat((json.getAsJsonPrimitive("interestAmount")!!.asInt).toString()) + "원)"
//
//                        accountList.add(item)
//
//                        loanTotalMoney += json.getAsJsonPrimitive("principalAmount")!!.asInt
//
//                        Log.d("item", item.toString())


                        item.newProductCompany = json.getAsJsonPrimitive("new_product_company")!!.asString
                    //    item.productNo = json.getAsJsonPrimitive("productNo")!!.asInt
                    //    item.productCode = json.getAsJsonPrimitive("productCode")!!.asString
                        item.price = json.getAsJsonPrimitive("price")!!.asInt
                        item.newProduct1Name = json.getAsJsonPrimitive("product_name")!!.asString
                    //    item.unit = json.getAsJsonPrimitive("unit")!!.asString

                        accountList.add(item)

                    }

                    //textViewGetTotalAmount?.text =
                    //    Utils.setDecimalFormat(loanTotalMoney.toString()) + "원"
                    val productListAdapter = ProductListAdapter(
                        requireContext(),
                        accountList
                    )

                    listViewProduct.adapter = productListAdapter

                    listViewProduct.onItemClickListener =
                        AdapterView.OnItemClickListener { parent, view, position, id ->
                            /**
                             * ListView의 Item을 Click 할 때 수행할 동작
                             * @param parent 클릭이 발생한 AdapterView.
                             * @param view 클릭 한 AdapterView 내의 View(Adapter에 의해 제공되는 View).
                             * @param position 클릭 한 Item의 position
                             * @param id 클릭 된 Item의 Id
                             */


                            // adapter.getItem(position)의 return 값은 Object 형
                            // 실제 Item의 자료형은 CustomDTO 형이기 때문에
                            // 형변환을 시켜야 getResId() 메소드를 호출할 수 있습니다.

                            // new Intent(현재 Activity의 Context, 시작할 Activity 클래스)
                            val intent = Intent(requireContext(), ProductInfo::class.java)
                            //            val loan_no: Int = (.getItem(position) as CustomDTO).getResId()
                            //            // putExtra(key, value)
                            //            intent.putExtra("loan_no", loanNo)
                            val new_product_company: String? =
                                (productListAdapter.getItem(position) as ProductDTO).newProductCompany
//                            val productNo: Int? =
//                                (productListAdapter.getItem(position) as ProductDTO).productNo
//                            val product_code: String? =
//                                (productListAdapter.getItem(position) as ProductDTO).productCode
                            val price: Int? =
                                (productListAdapter.getItem(position) as ProductDTO).price
                            val product_name: String? =
                                (productListAdapter.getItem(position) as ProductDTO).newProduct1Name

                            intent.putExtra("new_product_company", new_product_company)
//                            intent.putExtra("productNo", productNo)
//                            intent.putExtra("product_code", product_code)
                            intent.putExtra("price", price)
                            intent.putExtra("product_name", product_name)

                            startActivity(intent)
                        }

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

                t.message
            }
        })


    }

 */

}

private fun Intent.putExtra(s: String, newProduct1Name: TextView?) {

}
