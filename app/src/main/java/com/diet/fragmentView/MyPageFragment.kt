package com.diet.fragmentView

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.diet.*

class MyPageFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.mypage_item, container, false)


        val review_image = view.findViewById<TextView>(R.id.myreview)
        val my_order_list = view.findViewById<TextView>(R.id.my_order_list)
        val notice = view.findViewById<TextView>(R.id.notice)
        val myreview = view.findViewById<TextView>(R.id.myreview)
        val my_product_qna = view.findViewById<TextView>(R.id.my_product_qna)
        my_product_qna.setOnClickListener {
            val nextIntent = Intent(context, MyQuestionActivity::class.java)
            startActivity(nextIntent)
        }
        myreview.setOnClickListener {
            val nextIntent = Intent(context, MyReviewActivity::class.java)
            startActivity(nextIntent)
        }
        notice.setOnClickListener {
            val nextIntent = Intent(context, NoticeListActivity::class.java)
            startActivity(nextIntent)
        }
/*
        cart_page.setOnClickListener {
            val nextIntent = Intent(context, CartInfo::class.java)
            startActivity(nextIntent)
        }*/

        my_order_list.setOnClickListener {
            val nextIntent = Intent(context, MyOrderActivity::class.java)
            startActivity(nextIntent)
        }
        /*myreview.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            if (transaction != null) {
                transaction.replace(R.id.frameLayout, ReviewWriteFragment())
                transaction.disallowAddToBackStack()
                transaction.commit()
            }
        }*/
        return view
    }

}