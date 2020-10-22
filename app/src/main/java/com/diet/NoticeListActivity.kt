package com.diet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diet.adapter.NoticeAdapter
import com.diet.fragmentView.NoticeDeatailFragment
import com.diet.model.NoticeDTO

class NoticeListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_list)
        val notice_list_view :RecyclerView  = findViewById(R.id.notice_list_view)
        val page_back = findViewById<Button>(R.id.page_back)
        page_back.setOnClickListener {
            val ft = supportFragmentManager.beginTransaction()
            val notice_list : FrameLayout = findViewById(R.id.notice_list)
            notice_list.removeView(notice_list_view)
            ft.replace(R.id.notice_list, NoticeDeatailFragment()).commit()
        }

        var NoticeList = arrayListOf<NoticeDTO>(
            NoticeDTO("2020-10-21", "결제는 어떻게 이루어지나요", "결제는 이렇게이루어집니다.")
            , NoticeDTO("2020-10-22", "배송은 어떻게 이루어지나요", "배송은 이렇게이루어집니다.")
            , NoticeDTO("2020-10-19", "환불은 어떻게 이루어지나요", "환불은 이렇게이루어집니다.")
            , NoticeDTO("2020-10-20", "장바구니에 어떻게 담을수있나요", "장바구니는 이렇게이루어집니다.")
        )

        notice_list_view.adapter = NoticeAdapter(NoticeList)
        notice_list_view.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        notice_list_view.setHasFixedSize(false)
    }
}