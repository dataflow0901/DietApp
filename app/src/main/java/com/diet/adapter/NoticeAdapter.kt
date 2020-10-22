package com.diet.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.diet.R
import com.diet.model.NoticeDTO

class NoticeAdapter(val NoticeList: ArrayList<NoticeDTO>) :
    RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_notice_list, null)

        return NoticeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return NoticeList.size
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.notice_title.text = NoticeList.get(position).notice_title
        holder.notice_date.text = NoticeList.get(position).notice_date

        /*holder.notice_content.text = NoticeList.get(position).notice_content*/
    }

    class NoticeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val notice_title = itemView.findViewById<TextView>(R.id.notice_title)
        val notice_date: TextView = itemView.findViewById(R.id.notice_date)

        /*val notice_content : TextView = itemView.findViewById(R.id.notice_detatil_content)*/
    }
}