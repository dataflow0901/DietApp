package com.diet.fragmentView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diet.R
import com.diet.adapter.SearchAdapter
import com.diet.model.SearchDTO
import kotlinx.android.synthetic.main.fragment_search.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View? {
        var view = inflater.inflate(R.layout.fragment_search, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.search_recyclerview)
        val lately_search = view.findViewById<Button>(R.id.lately_search)
        val popularity_search = view.findViewById<Button>(R.id.popularity_search)
        val recommend_search = view.findViewById<Button>(R.id.recommend_search)
        val search_area = view.findViewById<SearchView>(R.id.search_area)


        var searchList :ArrayList<SearchDTO> = arrayListOf()

        lately_search.setOnClickListener {
            var searchList = arrayListOf(
                SearchDTO("1. 검색내용"),
                SearchDTO("2. 검색내용"),
                SearchDTO("3. 검색내용"),
                SearchDTO("4. 검색내용")
            )
            recyclerView.adapter = SearchAdapter(searchList)
        }
        popularity_search.setOnClickListener {
            var searchList = arrayListOf(
                SearchDTO("1. 인기내용"),
                SearchDTO("2. 인기내용"),
                SearchDTO("3. 인기내용"),
                SearchDTO("4. 인기내용")
            )
            recyclerView.adapter = SearchAdapter(searchList)
        }
        recommend_search.setOnClickListener {
            var searchList = arrayListOf(
                SearchDTO("1. 추천내용"),
                SearchDTO("2. 추천내용"),
                SearchDTO("3. 추천내용"),
                SearchDTO("4. 추천내용")
            )
            recyclerView.adapter = SearchAdapter(searchList)
        }

//        search_area.setOnCloseListener {
////
////        }
        /*search_recyclerview = view.findViewById(R.id.search_recyclerview)*/
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)

        recyclerView.adapter = SearchAdapter(searchList)
        (recyclerView.adapter as SearchAdapter).notifyDataSetChanged()

        return view
    }

}
