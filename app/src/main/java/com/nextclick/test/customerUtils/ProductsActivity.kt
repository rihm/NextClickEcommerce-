package com.nextclick.test.customerUtils

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nextclick.test.R
import com.nextclick.test.adapter.ProductAdapter
import com.nextclick.test.db.NextclickDatabase
import com.nextclick.test.db.Product
import com.nextclick.test.repository.ProductRpository
import com.nextclick.test.viewModel.ProductViewModel
import com.nextclick.test.viewModel.ProductViewModelFactory
import java.nio.charset.Charset

class ProductsActivity : AppCompatActivity() ,ProductAdapter.ProductClickInterface{
    lateinit var viewModal: ProductViewModel
    lateinit var productRV: RecyclerView
    lateinit var cartView:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        productRV = findViewById<RecyclerView>(R.id.productRv)
        cartView = findViewById<TextView>(R.id.viewCart)

        productRV.layoutManager = LinearLayoutManager(this)

        val productAdapter = ProductAdapter(this, this)
        productRV.adapter=productAdapter

        val dao = NextclickDatabase.getDatabase(applicationContext).productDao()
        val productRpository = ProductRpository(dao)

        viewModal =
            ViewModelProvider(
                this,
                ProductViewModelFactory(productRpository)
            ).get(ProductViewModel::class.java)
        viewModal.getProduct().observe(this, Observer { list ->
            list?.let {
                // on below line we are updating our list.
                productAdapter.updateList(it)          }
        })

        cartView.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@ProductsActivity,CartActivity::class.java))
        })
    }

    override fun onProductClick(product: Product) {
        Log.d("img--", "onProductClick: "+product.img.toString())
        val intent = Intent(this@ProductsActivity, ProductDetailsActivity::class.java)
        intent.putExtra("prd_name", product.name)
        intent.putExtra("prd_rate", product.rate)
        intent.putExtra("prd_desc", product.description)
        startActivity(intent)
    }
}