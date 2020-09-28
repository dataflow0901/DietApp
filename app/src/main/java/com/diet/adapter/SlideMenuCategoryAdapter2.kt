package com.diet.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diet.ProductList
import com.diet.R
import com.diet.RecyclerViewDecoration
import com.diet.model.ProductDTO
import com.diet.model.retrofits.ProductApiRetrofit
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_slide_menu.view.*
import kotlinx.android.synthetic.main.item_slide_menu_category.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class SlideMenuCategoryAdapter2(val context: Context, private val productList: ArrayList<ProductDTO>) :
    RecyclerView.Adapter<SlideMenuCategoryAdapterHolder2>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideMenuCategoryAdapterHolder2 {
        //레이아웃 생성
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_slide_menu_category2,
            parent, false
        )

        return SlideMenuCategoryAdapterHolder2(view, context)
    }


    override fun getItemCount(): Int {
        //리스트 아이템수
        return productList.size
    }

    override fun onBindViewHolder(holder: SlideMenuCategoryAdapterHolder2, position: Int) {
        //데이터 삽입
        val data = productList[position]
        holder.setListData(data)
    }




}

//매개변수로 있는 아이템은 하나의 리스트 아이엩ㅁ을 아답터로부터 전달받는 변수
class SlideMenuCategoryAdapterHolder2(itemView: View, context: Context) :
    RecyclerView.ViewHolder(itemView) {  // 뷰홀더를 사용하기위해 상속

    val context = context
    var count = 0
    private var category2Code  = ""
    private var category2Name  = ""
    var productList = arrayListOf<ProductDTO>()


    fun setListData(productList: ProductDTO) {

        itemView.textView_category.text = productList.category2Name


        itemView.setOnClickListener {

            val goProductList = Intent(context, ProductList::class.java)
            goProductList.putExtra("category2Code",  productList.category2Code.toString())
            goProductList.putExtra("category2Name", productList.category2Name.toString())
            goProductList.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(context, goProductList, Bundle())
        }


    }



}
