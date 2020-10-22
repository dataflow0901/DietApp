package com.diet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.diet.adapter.ViewPagerAdapter
import com.diet.fragmentView.WirteEndReviewFragment
import com.diet.fragmentView.WritableReviewFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_product.*
import kotlinx.android.synthetic.main.fragment_my_review.*

class MyReviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_my_review)
        val review_tablayout = findViewById<TabLayout>(R.id.review_tablayout)

        Tablayout_item()
    }
    private fun Tablayout_item() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(WritableReviewFragment(),"작성 가능한 리뷰 ")
        adapter.addFragment(WirteEndReviewFragment(),"작성한 리뷰")
        review_viewpager.adapter = adapter
        review_tablayout.setupWithViewPager(review_viewpager)

    }
}