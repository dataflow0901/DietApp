package com.diet.fragmentView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diet.R
import com.diet.adapter.ZzimAdapter
import com.diet.model.ProductDTO
import com.diet.model.SearchDTO
import com.diet.model.ZzimDTO

class ZzimFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_zzim, container, false)
        var zzim_list = view.findViewById<RecyclerView>(R.id.zzim_list)

        var zzimList: ArrayList<ZzimDTO> = arrayListOf(
            ZzimDTO(R.drawable.foodiamge1,"이렇게구나", 20000),
            ZzimDTO(R.drawable.foodiamge1,"이렇게구나", 30000),
            ZzimDTO(R.drawable.foodiamge1,"이렇게구나", 40000),
            ZzimDTO(R.drawable.foodiamge1,"이렇게구나", 10000)
        )
        zzim_list.adapter = ZzimAdapter(zzimList)
        zzim_list.layoutManager = GridLayoutManager(context, 3)

        return view
    }


}