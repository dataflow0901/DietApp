package com.diet.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diet.R
import com.diet.adapter.QuestionAdapter.QuestionViewHolder
import com.diet.model.QuestionDTO

class QuestionAdapter(val questionList: ArrayList<QuestionDTO>) :
    RecyclerView.Adapter<QuestionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_my_question, parent, false)

        return QuestionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return questionList.size
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.response_status.text =questionList.get(position).response_status
        holder.question_date.text =questionList.get(position).question_date
        holder.question_product_name.text =questionList.get(position).question_product_name
        holder.question_content.text =questionList.get(position).question_content
    }

    class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val response_status: TextView = itemView.findViewById(R.id.response_status)
        val question_date: TextView = itemView.findViewById(R.id.question_date)
        val question_product_name: TextView = itemView.findViewById(R.id.question_product_name)
        val question_content: TextView = itemView.findViewById(R.id.question_content)
    }
}