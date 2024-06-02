package com.nextclick.test.customerUtils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.nextclick.test.R
import com.nextclick.test.db.CartItem
import com.nextclick.test.db.NextclickDatabase
import com.nextclick.test.repository.CartRepository
import com.nextclick.test.viewModel.CartViewModel
import com.nextclick.test.viewModel.CartViewModelFactory

class ProductDetailsActivity : AppCompatActivity() {

    lateinit var viewModal: CartViewModel
    lateinit var cartItem: CartItem
    lateinit var bitmap:Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        val name= intent.getStringExtra("prd_name")
        val rate= intent.getStringExtra("prd_rate")
        val description= intent.getStringExtra("prd_desc")


        val prd_name=findViewById<TextView>(R.id.prd_name)
        val prd_rate=findViewById<TextView>(R.id.prd_rate)
        val prd_desc=findViewById<TextView>(R.id.prd_discription)
        val prd_img=findViewById<ImageView>(R.id.prd_img)
        val cart=findViewById<Button>(R.id.btn_add_to_cart)

        prd_name.text=name
        prd_rate.text=rate
        prd_desc.text=description
        prd_img.setImageResource(R.drawable.products)
        val cartDao= NextclickDatabase.getDatabase(applicationContext).cartDao()
        val cartRepository= CartRepository(cartDao)
        viewModal=
            ViewModelProvider(
                this,CartViewModelFactory(cartRepository)
            ).get(CartViewModel::class.java)

        cart.setOnClickListener(View.OnClickListener {

            val cartItem = CartItem(
                0,
                name.toString(),
                rate.toString(),
                1,
                0.0,

            );
           viewModal.insertCartItem(cartItem)
           this.finish()
        })


    }

//    @OptIn(ExperimentalEncodingApi::class)
//    private fun byteArrayToBitmap(data: String): Bitmap {
//        val imageBytes = Base64.decode(data, 0)
//        val image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
//        return image
//    }

    fun byteArrayToBitmap(data: ByteArray?): Bitmap {
        return BitmapFactory.decodeByteArray(data, 0, data!!.size)
    }
}