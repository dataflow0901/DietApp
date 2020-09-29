package com.diet

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.diet.fragmentView.EventFragment
import com.diet.fragmentView.HomeFragment
import com.diet.fragmentView.ShoppingFragment
import com.diet.fragmentView.TrendFragment
//import com.blildo.views.fragmentView.LentCertificateFragment
//import com.blildo.views.fragmentView.BorrowedCertificateFragment
//import com.blildo.views.fragmentView.ManagementFragment
//import com.blildo.views.fragmentView.HomeFragment
//import com.blildo.views.hambuger.Hamburger
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.textColor

import android.view.View
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diet.adapter.SlideMenuCategoryAdapter
import com.diet.model.ProductDTO
import com.diet.model.retrofits.ProductApiRetrofit
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class Home : AppCompatActivity() {

    lateinit var recyclerViewSlideMenu : RecyclerView
    var accountList = arrayListOf<ProductDTO>()
    lateinit var drawer_layout : DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        drawer_layout = findViewById(R.id.drawer_layout)
        recyclerViewSlideMenu = findViewById(R.id.recyclerViewCategory1)

        getProductList()





        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, HomeFragment()).commit()


        val goSlideMenu : Intent = Intent(this, SlideMenu::class.java)
        button_slide_menu.setOnClickListener {
//            startActivity(goSlideMenu)


            drawer_layout.openDrawer(drawer);


        }
        //프래그먼트
        statusTextView.setOnClickListener {
            changeFragment(0)
            changeText(it.id)
        }
        shoppingTextView.setOnClickListener {
            changeFragment(1)
            changeText(it.id)
        }
        eventTextView.setOnClickListener {
            changeFragment(2)
            changeText(it.id)
        }
        trendTextView.setOnClickListener {
            changeFragment(3)
            changeText(it.id)
        }

    }


/*
    private fun showDialog() {
        var title: String = getString(R.string.app_title)
        var description: String = getString(R.string.new_account_text)
        var confirm: String = getString(R.string.confirm)
        var cancel: String = getString(R.string.cancel)
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.dialog_custom, null)
        val title_text: TextView = view.findViewById(R.id.text_title)
        title_text.text = title
        val description_text: TextView = view.findViewById(R.id.text_description)
        description_text.text = description
        val postive_button: Button = view.findViewById(R.id.btn_positive)
        postive_button.text = confirm
        val negative_button: Button = view.findViewById(R.id.btn_negative)
        negative_button.text = cancel

        val alertDialog = AlertDialog.Builder(this)
            .create()
        postive_button.setOnClickListener {
            Toast.makeText(applicationContext, "확인", Toast.LENGTH_SHORT).show()
            val nextIntent = Intent(this, CreateCertificate::class.java)
            startActivity(nextIntent)
            alertDialog.dismiss()
        }
        negative_button.setOnClickListener {
            Toast.makeText(applicationContext, "취소", Toast.LENGTH_SHORT).show()
            alertDialog.dismiss()
        }

        alertDialog.setView(view)
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()
    }
*/
    fun changeFragment(frag: Int) {
        val ft = supportFragmentManager.beginTransaction()
        when (frag) {
            0 -> {
                ft.replace(R.id.frameLayout, HomeFragment()).addToBackStack(null).commit()
            }
            1 -> {
                ft.replace(R.id.frameLayout,
                    ShoppingFragment()
                ).addToBackStack(null)
                    .commit()
            }
            2 -> {
                ft.replace(R.id.frameLayout,
                    EventFragment()
                ).addToBackStack(null)
                    .commit()
            }
            3 -> {
                ft.replace(R.id.frameLayout,
                    TrendFragment()
                ).addToBackStack(null)
                    .commit()
            }
        }
    }

    fun changeText(id: Int) {

        statusTextView.textColor = Color.rgb(97,97,97)
        shoppingTextView.textColor = Color.rgb(97,97,97)
        eventTextView.textColor = Color.rgb(97,97,97)
        trendTextView.textColor = Color.rgb(97,97,97)

        when (id) {

            R.id.statusTextView -> {
                statusTextView.textColor = Color.BLACK
            }
            R.id.shoppingTextView -> {
                shoppingTextView.textColor = Color.BLACK
            }
            R.id.eventTextView -> {
                eventTextView.textColor = Color.BLACK
            }
            R.id.trendTextView -> {
                trendTextView.textColor = Color.BLACK
            }

        }
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

    override fun onBackPressed() {

        if (drawer_layout.isDrawerOpen(Gravity.LEFT)){
            drawer_layout.closeDrawer(Gravity.LEFT)
        }
        else {
            finish()
        }
    }
}