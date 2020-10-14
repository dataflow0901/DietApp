package com.diet.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diet.R
import com.diet.model.ProductDTO
import java.util.ArrayList

class ZzimAdapter(val zzimList: ArrayList<ProductDTO>):RecyclerView.Adapter<ZzimAdapter.ZzimViewHolder>(){
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZzimAdapter.ZzimViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.search_item_layout, parent,false)

        return ZzimViewHolder(view).apply {

        }
    }

    override fun getItemCount(): Int {
       return zzimList.size
    }

    override fun onBindViewHolder(holder: ZzimAdapter.ZzimViewHolder, position: Int) {


    }

    class ZzimViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // var zzim_name = itemView.findViewById<TextView>(R.id.search_text)
        // var zzim_image = itemView.findViewById<ImageView>()
        //
        // var zzim_price = itemView.findViewById<TextView>()

    }
}