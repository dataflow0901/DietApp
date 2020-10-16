package com.diet.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diet.R
import com.diet.model.ProductDTO
import com.diet.model.ZzimDTO
import java.util.ArrayList

class ZzimAdapter(val zzimList: ArrayList<ZzimDTO>):RecyclerView.Adapter<ZzimAdapter.ZzimViewHolder>(){
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZzimAdapter.ZzimViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.zzim_item, parent,false)


        return ZzimViewHolder(view).apply {

        }
    }

    override fun getItemCount(): Int {
       return zzimList.size
    }

    override fun onBindViewHolder(holder: ZzimAdapter.ZzimViewHolder, position: Int) {
      /*  val zzim = zzimList[position]*/
 /*       holder.zzim_product_name.text = zzim.product_name
        holder.zzim_product_price.text = zzim.product_price.toString()*/

        holder.image.setImageResource(zzimList.get(position).image)
        holder.zzim_product_name.text = zzimList.get(position).product_name
        holder.zzim_product_price.text = zzimList.get(position).product_price.toString()
    }

    class ZzimViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        /*val zzim_image = itemView.findViewById<ImageView>(R.id.zzim_image)*/
        val image = itemView.findViewById<ImageView>(R.id.zzim_image)
        var zzim_product_name = itemView.findViewById<TextView>(R.id.zzim_product_name)
        var zzim_product_price = itemView.findViewById<TextView>(R.id.zzim_product_price)
    }
}