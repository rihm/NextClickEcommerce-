package com.nextclick.test.customerUtils

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nextclick.test.R
import com.nextclick.test.adapter.CartAdapter
import com.nextclick.test.db.CartItem
import com.nextclick.test.db.NextclickDatabase
import com.nextclick.test.repository.CartRepository
import com.nextclick.test.viewModel.CartViewModel
import com.nextclick.test.viewModel.CartViewModelFactory


class CartActivity : AppCompatActivity(),CartAdapter.CartClickListeners {
    lateinit var viewModel: CartViewModel
    lateinit var cartRV:RecyclerView
    lateinit var  cartAdapter:CartAdapter
    lateinit var btn_place_order:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        cartRV=findViewById(R.id.recyclerView)
        btn_place_order=findViewById(R.id.btn_place_order)

        cartRV.layoutManager=LinearLayoutManager(this)
        cartAdapter=CartAdapter(this,this)
        val cartDao= NextclickDatabase.getDatabase(applicationContext).cartDao()
        val cartRepository=CartRepository(cartDao)
        cartRV.adapter=cartAdapter
        viewModel=
                ViewModelProvider(
                    this,CartViewModelFactory(cartRepository)
                ).get(CartViewModel::class.java)
        viewModel.getCartItem().observe(this,{
                list ->
            list?.let {
                // on below line we are updating our list.
                cartAdapter.updateList(it)
            }
        })

        btn_place_order.setOnClickListener(View.OnClickListener {
            val fragment = PlaceOrderFragment()
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameLayout, fragment)
            transaction.commit()
        })

    }

    override fun onDeleteClicked(cartItem: CartItem) {
        viewModel.deleteCartItem(cartItem)
    }

    override fun onPlusClicked(cartItem: CartItem) {
        val quantity:Int = cartItem.quantity+1
        val parseRate = cartItem.rate.toInt()
        val totalRate:Double=quantity*parseRate.toDouble()
        viewModel.updateQuantity(cartItem.id,quantity)
        viewModel.updateRate(cartItem.id,totalRate)
        cartAdapter.notifyDataSetChanged()


    }

    override fun onMinusClicked(cartItem: CartItem) {
        val quantity:Int = cartItem.quantity-1
        val parseRate = cartItem.rate.toInt()
        val totalRate:Double=quantity*parseRate.toDouble()
        if(quantity!=0){
            viewModel.updateQuantity(cartItem.id,quantity)
            viewModel.updateRate(cartItem.id,totalRate)
            cartAdapter.notifyDataSetChanged()

        }else{
            viewModel.deleteCartItem(cartItem)
        }
    }
}

