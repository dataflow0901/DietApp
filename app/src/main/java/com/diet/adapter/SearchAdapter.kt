package com.diet.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diet.R
import com.diet.fragmentView.SearchFragment
import com.diet.model.ProductDTO
import com.diet.model.SearchDTO
import kotlinx.android.synthetic.main.item_myorder.view.*
import java.util.ArrayList

class SearchAdapter(val context: Context, private val searchList: ArrayList<ProductDTO>) :
    BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.search_item_layout, null)
        val search_text: TextView = view.findViewById(R.id.search_text)
        val searchList = searchList[position]

        search_text.text = searchList.productName

        return view
    }

    override fun getItem(position: Int): Any {
        return searchList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return searchList.size
    }

    class SearchViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {
        val context = context
        fun setData(searchList: ProductDTO) {

            itemView.product_name.text = searchList.productName

        }
    }
}


