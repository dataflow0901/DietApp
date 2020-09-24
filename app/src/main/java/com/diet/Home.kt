package com.diet

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.diet.fragmentView.EventFragment
import com.diet.fragmentView.HomeFragment
import com.diet.fragmentView.ShoppingFragment
import com.diet.fragmentView.TrendFragment
//import com.blildo.views.fragmentView.LentCertificateFragment
//import com.blildo.views.fragmentView.BorrowedCertificateFragment
//import com.blildo.views.fragmentView.ManagementFragment
//import com.blildo.views.fragmentView.HomeFragment
//import com.blildo.views.hambuger.Hamburger
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.textColor

import android.view.View
import android.widget.TextView



class Home : AppCompatActivity() {
    //    lateinit var pieChart: PieChart
//    val yValues = ArrayList<PieEntry>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


//        hamburgerButton.setOnClickListener {
//            val nextIntent = Intent(this, Hamburger::class.java)
//            startActivity(nextIntent)
//        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, HomeFragment()).commit()


        val goSlideMenu : Intent = Intent(this, SlideMenu::class.java)
        button_slide_menu.setOnClickListener {
            startActivity(goSlideMenu)
        }
        //프래그먼트
        statusTextView.setOnClickListener {
            changeFragment(0)
            changeText(it.id)
        }
        shoppingTextView.setOnClickListener {
            changeFragment(1)
            changeText(it.id)
        }
        eventTextView.setOnClickListener {
            changeFragment(2)
            changeText(it.id)
        }
        trendTextView.setOnClickListener {
            changeFragment(3)
            changeText(it.id)
        }

    }


/*
    private fun showDialog() {
        var title: String = getString(R.string.app_title)
        var description: String = getString(R.string.new_account_text)
        var confirm: String = getString(R.string.confirm)
        var cancel: String = getString(R.string.cancel)
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.dialog_custom, null)
        val title_text: TextView = view.findViewById(R.id.text_title)
        title_text.text = title
        val description_text: TextView = view.findViewById(R.id.text_description)
        description_text.text = description
        val postive_button: Button = view.findViewById(R.id.btn_positive)
        postive_button.text = confirm
        val negative_button: Button = view.findViewById(R.id.btn_negative)
        negative_button.text = cancel

        val alertDialog = AlertDialog.Builder(this)
            .create()
        postive_button.setOnClickListener {
            Toast.makeText(applicationContext, "확인", Toast.LENGTH_SHORT).show()
            val nextIntent = Intent(this, CreateCertificate::class.java)
            startActivity(nextIntent)
            alertDialog.dismiss()
        }
        negative_button.setOnClickListener {
            Toast.makeText(applicationContext, "취소", Toast.LENGTH_SHORT).show()
            alertDialog.dismiss()
        }

        alertDialog.setView(view)
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()
    }
*/
    fun changeFragment(frag: Int) {
        val ft = supportFragmentManager.beginTransaction()
        when (frag) {
            0 -> {
                ft.replace(R.id.frameLayout, HomeFragment()).addToBackStack(null).commit()
            }
            1 -> {
                ft.replace(R.id.frameLayout,
                    ShoppingFragment()
                ).addToBackStack(null)
                    .commit()
            }
            2 -> {
                ft.replace(R.id.frameLayout,
                    EventFragment()
                ).addToBackStack(null)
                    .commit()
            }
            3 -> {
                ft.replace(R.id.frameLayout,
                    TrendFragment()
                ).addToBackStack(null)
                    .commit()
            }
        }
    }

    fun changeText(id: Int) {

        statusTextView.textColor = Color.rgb(97,97,97)
        shoppingTextView.textColor = Color.rgb(97,97,97)
        eventTextView.textColor = Color.rgb(97,97,97)
        trendTextView.textColor = Color.rgb(97,97,97)

        when (id) {

            R.id.statusTextView -> {
                statusTextView.textColor = Color.BLACK
            }
            R.id.shoppingTextView -> {
                shoppingTextView.textColor = Color.BLACK
            }
            R.id.eventTextView -> {
                eventTextView.textColor = Color.BLACK
            }
            R.id.trendTextView -> {
                trendTextView.textColor = Color.BLACK
            }

        }
    } 

}