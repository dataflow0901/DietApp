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
import com.diet.model.ZzimDTO

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ShoppingFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fargment_shopping,null)

        var zzim_list = view.findViewById<RecyclerView>(R.id.zzim_list)

        var zzimList: ArrayList<ZzimDTO> = arrayListOf(
            ZzimDTO(R.drawable.foodiamge1,"일렇게구나", 20000),
            ZzimDTO(R.drawable.foodiamge1,"이렇게구나", 30000),
            ZzimDTO(R.drawable.foodiamge1,"삼렇게구나", 40000),
            ZzimDTO(R.drawable.foodiamge1,"사렇게구나", 10000)
        )
        zzim_list.adapter = ZzimAdapter(zzimList)
        zzim_list.layoutManager = GridLayoutManager(context, 2)


        return view
    }
}