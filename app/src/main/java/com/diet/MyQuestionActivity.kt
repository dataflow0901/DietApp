package com.diet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.diet.adapter.ViewPagerAdapter
import com.diet.fragmentView.CompleteQuestionFragment
import com.diet.fragmentView.MyQuestionFragment
import com.diet.fragmentView.WirteEndReviewFragment
import com.diet.fragmentView.WritableReviewFragment
import kotlinx.android.synthetic.main.activity_my_question.*
import kotlinx.android.synthetic.main.fragment_my_review.*
import kotlinx.android.synthetic.main.fragment_my_review.review_viewpager

class MyQuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_question)

        Tablayout_item()
    }
    private fun Tablayout_item() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(MyQuestionFragment(),"진행중인 문의")
        adapter.addFragment(CompleteQuestionFragment(),"완료된 문의")
        question_viewpager.adapter = adapter
        question_tablayout.setupWithViewPager(question_viewpager)

    }
}