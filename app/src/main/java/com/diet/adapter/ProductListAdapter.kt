package com.diet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter
import android.widget.PopupMenu;
import android.widget.TextView;

import com.diet.R;
import com.diet.model.ProductDTO;

import java.util.ArrayList;
//import android.content.Context;

import com.diet.utils.Utils;



open class ProductListAdapter ( val context: Context, private val productList: ArrayList<ProductDTO>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_product_list, null)
        val productName = view.findViewById<TextView>(R.id.productName)
        val companyName = view.findViewById<TextView>(R.id.companyName)
        val qty = view.findViewById<TextView>(R.id.qty)
        val unit = view.findViewById<TextView>(R.id.unit)
        val price = view.findViewById<TextView>(R.id.price)
        val gpa = view.findViewById<TextView>(R.id.gpa)
        val review = view.findViewById<TextView>(R.id.review)

        val product = productList[position]

//        principalAmount.setOnClickListener {
//            showPopupMenu(view)
//        }

        productName.text = product.productName
        companyName.text = product.companyName
        qty.text = product.qty.toString()
        unit.text = product.unit
        price.text = product.price.toString()
        gpa.text = product.gpa.toString()
        review.text = product.review.toString()

        return view
    }

//    private fun showPopupMenu(view : View){
//        val popup: PopupMenu = PopupMenu(context, view)
//        popup.menuInflater.inflate(R.menu.popup_menu, popup.menu)
//        popup.show()
//    }


    override fun getItem(position: Int): Any {

        return productList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return productList.size
    }
}