package com.nextclick.test.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface CartDao {
    @Insert
    fun insertCartItem(cartItem: CartItem)

    @Query("SELECT * FROM cart_item")
    fun getAllCartItems(): LiveData<List<CartItem>>

    @Delete
    fun deleteCartItem(cartItem: CartItem)

    @Query("DELETE FROM cart_item")
    fun deleteAllItems()

    @Query("UPDATE cart_item SET quantity=:quantity WHERE id=:id")
    fun updateQuantity(id:Int,quantity:Int)

    @Query("UPDATE cart_item SET totalItemRate=:totalItemRate WHERE id=:id")
    fun updateRate(id :Int,totalItemRate:Double)
}