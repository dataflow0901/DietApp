package com.diet.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diet.R
import com.diet.model.MypageDTO

class MypageAdapter(val mypageList:ArrayList<MypageDTO>) : RecyclerView.Adapter<MypageAdapter.MypageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MypageAdapter.MypageViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.mypage_item, parent,false)

        return MypageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mypageList.size
    }

    override fun onBindViewHolder(holder: MypageAdapter.MypageViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class MypageViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        var myinfo = itemView.findViewById<TextView>(R.id.myinfo)
        var cupon = itemView.findViewById<TextView>(R.id.cupon)
        var my_order_list = itemView.findViewById<TextView>(R.id.my_order_list)
        var address_change= itemView.findViewById<TextView>(R.id.address_change)
        var myreview = itemView.findViewById<TextView>(R.id.myreview)
        var my_product_qna = itemView.findViewById<TextView>(R.id.my_product_qna)
        var notice = itemView.findViewById<TextView>(R.id.notice)
        var FAQ = itemView.findViewById<TextView>(R.id.FAQ)
        var service_center = itemView.findViewById<TextView>(R.id.service_center)
        var notification = itemView.findViewById<TextView>(R.id.notification)
    }

}