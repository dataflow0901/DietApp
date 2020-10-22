package com.diet.fragmentView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import com.diet.R

class WirteEndReviewFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_wirte_end_review, container, false)

      /*  var my_review_ratingbar = view.findViewById<RatingBar>(R.id.my_review_ratingbar)
        my_review_ratingbar.numStars = 5

*/
        return view
    }
}