package com.diet


import android.content.ContentProvider
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.diet.adapter.ProductListAdapter
import com.diet.fragmentView.*
import com.diet.model.ProductDTO
import com.diet.model.retrofits.ProductApiRetrofit
import com.google.gson.JsonObject
import com.diet.utils.Utils
//import com.blildo.views.fragmentView.LentCertificateFragment
//import com.blildo.views.fragmentView.BorrowedCertificateFragment
//import com.blildo.views.fragmentView.ManagementFragment
//import com.blildo.views.fragmentView.HomeFragment
//import com.blildo.views.hambuger.Hamburger
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.textColor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


import kotlinx.android.synthetic.main.activity_product_list.*
import com.diet.fragmentView.HomeFragment


import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diet.adapter.CustomAdapter

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_product_list.*
import kotlinx.android.synthetic.main.fragment_status.*
import kotlinx.android.synthetic.main.activity_product_list.listViewProductList
import kotlinx.android.synthetic.main.item_product_list.*


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

    var accountList = arrayListOf<ProductDTO>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        getProductList()
///////////////////


        //////

        //createCertificate = view.findViewById(R.id.createLoanCertificate)
        listViewProduct =  findViewById(R.id.listViewProductList)

        //supportFragmentManager.beginTransaction()
        //    .replace(R.id.frameLayout, ProductFragment()).commit()

        System.out.println("step ******************************************************** 00b");




/*
        myInfoContainer.setOnClickListener {
            val goMyInfo = Intent (this,  MyInfo::class.java)
            startActivity(goMyInfo)
        }
        setting_container.setOnClickListener{
            val goMyInfo = Intent (this,  Setting::class.java)
            startActivity(goMyInfo)
        }

        notice_container.setOnClickListener {
            val goNotice = Intent (this, Notice::class.java)
            startActivity(goNotice)
        }
        customer_center_container.setOnClickListener {
            val goCustomerCenter = Intent(this, CustomerCenter::class.java)
            startActivity(goCustomerCenter)
        }
        termsPolicy_container.setOnClickListener {
            val goTermsOfUsePrivacyPolicy = Intent (this, TermsOfUsePrivacyPolicy::class.java)
            startActivity(goTermsOfUsePrivacyPolicy)
        }
        app_info_container
*/

    }


    private fun getProductList() {
//        Log.d("getCreditBillByPeriod userId", userId)
        val product = ProductDTO()

        //certificate.applier = userId
        //certificate.startDate = "2020-09-01"
        //certificate.endDate = "2020-09-30"

        product.salesStandCode = "NEW_PROD"
        //product.newProduct = newProduct

        println("step ******************************************************** 00c");

        val res: Call<JsonObject> =
            ProductApiRetrofit.getInstance(applicationContext).service.getProductList(product)
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

                    }

                    //textViewGetTotalAmount?.text =
                    //    Utils.setDecimalFormat(loanTotalMoney.toString()) + "원"
                    val productListAdapter = ProductListAdapter(
                        applicationContext,
                        accountList
                    )

                    listViewProduct.adapter = productListAdapter

                    ////
                    recyclerView = findViewById(R.id.recyclerView)
                    var adapter = CustomAdapter(applicationContext, accountList)                   // CustomaApdater() 를 저장하는 apdater 변수 생성
                    val recyclerDecoration = RecyclerViewDecoration(20,5)

                    recyclerView.addItemDecoration(recyclerDecoration)
                    recyclerView.adapter = adapter // CustomAdapter에 선언한 ProductList 변수에 data  변수 전달
                    //ListView 형식을 위한 LinearLayoutManager\
                    recyclerView.layoutManager = GridLayoutManager(applicationContext,2)


                    ////

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
                            //val intent = Intent(applicationContext, ProductList::class.java)
                            val intent = Intent(applicationContext, ProductInfo::class.java)
                            //            val loan_no: Int = (.getItem(position) as CustomDTO).getResId()
                            //            // putExtra(key, value)
                            //            intent.putExtra("loan_no", loanNo)
                            val salesStandCode: String? =
                                (productListAdapter.getItem(position) as ProductDTO).salesStandCode
                            val salesStandName: String? =
                                (productListAdapter.getItem(position) as ProductDTO).salesStandName
                            val productCode: String? =
                                (productListAdapter.getItem(position) as ProductDTO).productCode
                            val productName: String? =
                                (productListAdapter.getItem(position) as ProductDTO).productName
                            val companyCode: String? =
                                (productListAdapter.getItem(position) as ProductDTO).companyCode
                            val companyName: String? =
                                (productListAdapter.getItem(position) as ProductDTO).companyName
                            val qty: Int? =
                                (productListAdapter.getItem(position) as ProductDTO).qty
                            val unit: String? =
                                (productListAdapter.getItem(position) as ProductDTO).unit
                            val price: Int? =
                                (productListAdapter.getItem(position) as ProductDTO).price
                            val ranking: Int? =
                                (productListAdapter.getItem(position) as ProductDTO).ranking
                            val gpa: Int? =
                                (productListAdapter.getItem(position) as ProductDTO).gpa
                            val review: Int? =
                                (productListAdapter.getItem(position) as ProductDTO).review
                            val deliveryCost: Int? =
                                (productListAdapter.getItem(position) as ProductDTO).deliveryCost

                            intent.putExtra("salesStandCode", salesStandCode)
                            intent.putExtra("salesStandName", salesStandName)
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

                            Log.d("companyName", companyName )


                            startActivity(intent)
                        }

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