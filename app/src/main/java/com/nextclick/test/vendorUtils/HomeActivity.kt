package com.nextclick.test.vendorUtils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.cardview.widget.CardView
import com.nextclick.test.R

class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val cardView=findViewById<CardView>(R.id.card_product)

        cardView.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@HomeActivity,AddProductActivity::class.java))
        })

    }
}