package com.diet.fragmentView

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.diet.R
import com.diet.model.ProductDTO
import com.diet.model.retrofits.ProductApiRetrofit
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_product_review.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReviewWriteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReviewWriteFragment : Fragment() {
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


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product_review, container, false)
        val write_review = view.findViewById<Button>(R.id.write_review)
        write_review.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            val dialogView = inflater.inflate(R.layout.fragment_wirte_review,null)

            builder.setView(dialogView).show()

          /*var ft = activity?.supportFragmentManager?.beginTransaction()
            if (ft != null) {
                ft.replace(R.id.frameLayout, WriteReviewFragment())
                ft.disallowAddToBackStack()
                ft.commit()
            }*/
        }

        return view
    }

    private fun getProduct() {

        val product = ProductDTO()

        product.salesStandCode = salesStandCode
        product.productCode = productCode

        Log.d("salesStandCode", salesStandCode)
        Log.d("productCode", productCode)
        Log.d("productName", productName)

        val res: Call<JsonObject> =
            ProductApiRetrofit.getInstance(context).service.getProduct(product)
        res.enqueue(object : Callback<JsonObject?> {
            override fun onResponse(call: Call<JsonObject?>, response: Response<JsonObject?>) {
                Log.d("response.toString()", response.toString())

                if (response.isSuccessful) {
                    val result = response.body()?.getAsJsonObject("result")
/*
                    textView_productName.text = result?.getAsJsonPrimitive("productName")!!.asString
                    textView_companyName.text = result?.getAsJsonPrimitive("companyName")!!.asString*/
                    /* textView_price.text = result?.getAsJsonPrimitive("price")!!.asInt.toString()
                     textView_gpa.text = result?.getAsJsonPrimitive("gpa")!!.asInt.toString()
                     textView_ranking.text = result?.getAsJsonPrimitive("ranking")!!.asInt.toString()*/
                    textView_review.text = result?.getAsJsonPrimitive("review")!!.asInt.toString()
                    /*textView_deliveryCost.text = result?.getAsJsonPrimitive("deliveryCost")!!.asInt.toString()*/


                    productName = result?.getAsJsonPrimitive("productName")!!.asString
                    companyCode = result?.getAsJsonPrimitive("companyCode")!!.asString
                    companyName = result?.getAsJsonPrimitive("companyName")!!.asString
                    qty = result?.getAsJsonPrimitive("ranking")!!.asInt
                    unit = result?.getAsJsonPrimitive("unit")!!.asString
                    price = result?.getAsJsonPrimitive("price")!!.asInt
                    ranking = result?.getAsJsonPrimitive("ranking")!!.asInt
                    gpa = result?.getAsJsonPrimitive("gpa")!!.asInt
                    review = result?.getAsJsonPrimitive("review")!!.asInt
                    deliveryCost = result?.getAsJsonPrimitive("deliveryCost")!!.asInt

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