package com.diet.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.diet.ProductInfo
import com.diet.R
import com.diet.model.ProductDTO
import kotlinx.android.synthetic.main.item_main_new_favorite_product.view.*
class HomeFavoriteNewProductAdapter(val context: Context, private val productList: ArrayList<ProductDTO>) :
    RecyclerView.Adapter<HomeFavoriteNewProductAdapterHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFavoriteNewProductAdapterHolder {
        //레이아웃 생성
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_main_new_favorite_product,
            parent, false
        )
        return HomeFavoriteNewProductAdapterHolder(view, context)
    }


    override fun getItemCount(): Int {
        //리스트 아이템수
        return productList.size
    }

    override fun onBindViewHolder(holder: HomeFavoriteNewProductAdapterHolder, position: Int) {
        //데이터 삽입
        val data = productList[position]
        holder.setListData(data)
    }


}

//매개변수로 있는 아이템은 하나의 리스트 아이엩ㅁ을 아답터로부터 전달받는 변수
class HomeFavoriteNewProductAdapterHolder(itemView: View, context: Context) :
    RecyclerView.ViewHolder(itemView) {  // 뷰홀더를 사용하기위해 상속

    val context = context
    fun setListData(productList: ProductDTO) {
        itemView.textView_company_name.text = productList.companyName
        itemView.textView_product_name.text = productList.productName


        itemView.setOnClickListener {
            val intent = Intent(context, ProductInfo::class.java)
            intent.putExtra("salesStandCode",productList.salesStandCode)
            intent.putExtra("productCode",productList.productCode)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK;
            startActivity(context, intent, Bundle())
        }
    }


}
