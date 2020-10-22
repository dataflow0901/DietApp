package com.diet.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diet.R
import com.diet.fragmentView.SearchFragment
import com.diet.model.ProductDTO
import com.diet.model.SearchDTO
import kotlinx.android.synthetic.main.search_item_layout.view.*
import java.util.ArrayList

class SearchAdapter(val context: Context, private val searchList: ArrayList<ProductDTO>) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    lateinit var product_name : TextView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.search_item_layout, parent, false)
        product_name = view.findViewById(R.id.textView_product_name)


        return SearchViewHolder(view, context)
    }



    override fun getItemCount(): Int {
        return searchList.size
    }
    override fun onBindViewHolder(holder: SearchViewHolder, position: Int){
        val data = searchList[position]
        holder.setData(data)
    }

    class SearchViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {
        val context = context

        fun setData(searchList: ProductDTO) {

            itemView.textView_product_name.text = searchList.productName


        }
    }
}


