package com.diet.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.diet.R
import com.diet.model.OrderDTO

class OrderAdapter(val context: Context,private val OrderList:ArrayList<OrderDTO>):BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_myorder, null)
        val order_num = view.findViewById<TextView>(R.id.order_num)
        val order_date = view.findViewById<TextView>(R.id.order_date)
        val detail_order= view.findViewById<TextView>(R.id.detail_order)
        val order_product_image = view.findViewById<ImageView>(R.id.order_product_image)
        val product_name = view.findViewById<TextView>(R.id.product_name)
        val product_price = view.findViewById<TextView>(R.id.product_price)
        val product_delivery = view.findViewById<TextView>(R.id.product_delivery)
        val order_complete = view.findViewById<TextView>(R.id.order_complete)
        var write_review_btn = view.findViewById<TextView>(R.id.write_review_btn)
        val delevery_info = view.findViewById<TextView>(R.id.delevery_info)

        var order = OrderList[position]



        order_complete.setOnClickListener {
            order_complete.visibility = View.GONE
            write_review_btn.visibility = View.VISIBLE
        }

        write_review_btn.setOnClickListener {
            order_complete.visibility = View.VISIBLE
            write_review_btn.visibility = View.GONE
        }

     return view
    }

    override fun getItem(position: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getItemId(position: Int): Long {
        TODO("Not yet implemented")
    }

    override fun getCount(): Int {
        TODO("Not yet implemented")
    }
}