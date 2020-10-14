package com.blildo.views.fragmentView


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.diet.R

class ViewPager2Adapter(
    private val views: Array<Int>,

    val context: Context
) : RecyclerView.Adapter<ViewPager2Adapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_pager, parent, false)
        )

    override fun getItemCount(): Int = views.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

      if (position ==0 ){
          holder.pagerImageView.setImageResource(views[position])
          holder.pagerImageView.scaleType = ImageView.ScaleType.FIT_XY
      }else if(position ==1){
          holder.pagerImageView.setImageResource(views[position])
          holder.pagerImageView.scaleType = ImageView.ScaleType.FIT_XY

      }


    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val pagerImageView: ImageView = view.findViewById(R.id.pagerImageView)

    }



}