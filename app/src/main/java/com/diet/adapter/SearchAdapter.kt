package com.diet.adapter

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.util.Log
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

class SearchAdapter(val context: Context, private val searchList: ArrayList<ProductDTO>, val keyword : String) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    lateinit var product_name: TextView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.search_item_layout, parent, false)
        product_name = view.findViewById(R.id.textView_product_name)


        return SearchViewHolder(view, context,keyword)
    }


    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val data = searchList[position]
        holder.setData(data)
    }

    class SearchViewHolder(itemView: View, context: Context,keyword: String) : RecyclerView.ViewHolder(itemView) {
        val context = context
        val keywords = keyword.split(" ");
        fun setData(searchList: ProductDTO) {

            if(keywords.size > 0){

                var starts = ArrayList<Integer>()
                var ends = ArrayList<Integer>()
                val bold_text = searchList.productName!!
                keywords.forEach { keyword ->
                    if(bold_text.contains(keyword)){
                        starts.add(Integer(bold_text.indexOf(keyword)))
                        ends.add(Integer(bold_text.indexOf(keyword) + keyword.length))
                    }

                }
                val span : SpannableStringBuilder = SpannableStringBuilder()
                span.append(bold_text)
                for(i in 0 until starts.size){
                    span.setSpan(StyleSpan(Typeface.BOLD),starts[i] as Int,ends[i] as Int,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

                }
                itemView.textView_product_name.text = span
            }
            itemView.setOnClickListener {  }



        }
    }
}







