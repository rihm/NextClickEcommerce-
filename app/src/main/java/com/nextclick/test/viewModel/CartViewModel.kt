package com.nextclick.test.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextclick.test.db.CartItem
import com.nextclick.test.repository.CartRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(private val cartRepository: CartRepository):ViewModel() {

    var count:Int = 0

    fun getCartItem():LiveData<List<CartItem>>{
        return cartRepository.getCart()
    }
    fun insertCartItem(cartItem: CartItem){
        viewModelScope.launch (Dispatchers.IO){
            cartRepository.insertProduct(cartItem)
        }

    }

    fun deleteCartItem(cartItem: CartItem){
        viewModelScope.launch(Dispatchers.IO){
            cartRepository.deleteCartItem(cartItem)
        }
    }
    fun deleteAllCartItem(){
        viewModelScope.launch {
            cartRepository.deleteAllItem()
        }
    }

   fun updateQuantity(id:Int,quantity:Int){
       viewModelScope.launch (Dispatchers.IO){
           cartRepository.updateQuantity(id, quantity)
       }
   }
    fun updateRate(id:Int,totalRate:Double){
        viewModelScope.launch (Dispatchers.IO){
            cartRepository.updateRate(id,totalRate)
        }
    }
}