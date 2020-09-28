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
import kotlinx.android.synthetic.main.item_category_list.view.*

class FavoriteNewProductAdapter(val context: Context, private val productList: ArrayList<ProductDTO>) :
    RecyclerView.Adapter<FavoriteNewProductAdapterHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteNewProductAdapterHolder {
        //레이아웃 생성
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_category_list,
            parent, false
        )
        return FavoriteNewProductAdapterHolder(view, context)
    }


    override fun getItemCount(): Int {
        //리스트 아이템수
        return productList.size
    }

    override fun onBindViewHolder(holder: FavoriteNewProductAdapterHolder, position: Int) {
        //데이터 삽입
        val data = productList[position]
        holder.setListData(data)
    }


}

//매개변수로 있는 아이템은 하나의 리스트 아이템을 아답터로부터 전달받는 변수
class FavoriteNewProductAdapterHolder(itemView: View, context: Context) :
    RecyclerView.ViewHolder(itemView) {  // 뷰홀더를 사용하기위해 상속

    val context = context
    fun setListData(productList: ProductDTO) {
        itemView.companyName.text = productList.companyName
        itemView.productName.text = productList.productName
        itemView.qty.text = productList.qty.toString()
        itemView.price.text = productList.price.toString()
        itemView.unit.text = productList.unit
        itemView.gpa.text = productList.gpa.toString()
        itemView.review.text = productList.review.toString()


        itemView.setOnClickListener {
            val intent = Intent(context, ProductInfo::class.java)
            intent.putExtra("salesStandCode",productList.salesStandCode)
            intent.putExtra("productCode",productList.productCode)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(context, intent, Bundle())
        }
    }


}
