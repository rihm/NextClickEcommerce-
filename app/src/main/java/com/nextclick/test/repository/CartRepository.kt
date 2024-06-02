package com.nextclick.test.repository

import androidx.lifecycle.LiveData
import com.nextclick.test.db.CartDao
import com.nextclick.test.db.CartItem

class CartRepository(private val cartDao: CartDao) {
    fun getCart():LiveData<List<CartItem>>{
        return cartDao.getAllCartItems()
    }
    suspend fun insertProduct(cartItem: CartItem){
        cartDao.insertCartItem(cartItem)
    }
    suspend fun deleteCartItem(cartItem: CartItem){
        cartDao.deleteCartItem(cartItem)
    }
    suspend fun deleteAllItem(){
        cartDao.deleteAllItems()
    }

    suspend fun updateQuantity(id:Int,quantity:Int){
        cartDao.updateQuantity(id,quantity)
    }
    suspend fun updateRate(id:Int,rate:Double){
        cartDao.updateRate(id,rate)

    }

}