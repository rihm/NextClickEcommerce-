package com.nextclick.test.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
 data class Product(
 @PrimaryKey(autoGenerate = true)
 val id:Int,
  val name:String,
  val rate:String,
  val description:String,
 @ColumnInfo(name = "imageData", typeAffinity = ColumnInfo.BLOB)
 val img:ByteArray) {
}