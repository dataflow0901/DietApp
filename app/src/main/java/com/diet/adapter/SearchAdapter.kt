package com.diet.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diet.R
import com.diet.model.SearchDTO
import java.util.ArrayList

/*class SearchAdapter( val context: Context, private val searchList: ArrayList<SearchDTO>) : BaseAdapter(){
     override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
         val view:View = LayoutInflater.from(context).inflate(R.layout.search_item_layout, null)

         var search_text : TextView = view.findViewById(R.id.search_text)
         val search = searchList[position]
         search_text.text = search.search_item
         return view
     }

     override fun getItem(position: Int): Any {
         return searchList[position]
     }

     override fun getItemId(position: Int): Long {
         TODO("Not yet implemented")
     }

     override fun getCount(): Int {
         return searchList.size
     }



}*/

class SearchAdapter(val searchList: ArrayList<SearchDTO>):RecyclerView.Adapter<SearchAdapter.SearchViewHolder>(){
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.SearchViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.search_item_layout, parent,false)

         return SearchViewHolder(view)
    }

    override fun getItemCount(): Int {
       return searchList.size
    }

    override fun onBindViewHolder(holder: SearchAdapter.SearchViewHolder, position: Int) {
       holder.search_item.text = searchList.get(position).search_item
    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var search_item = itemView.findViewById<TextView>(R.id.search_text) //검색어 내용

    }
}