package com.nextclick.test.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_item")
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,
    val rate:String,
    val quantity:Int,
    val totalItemRate:Double,
    )
