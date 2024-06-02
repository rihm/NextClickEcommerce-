package com.nextclick.test.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Product::class,CartItem::class], version = 1)
abstract class NextclickDatabase:RoomDatabase() {
    abstract fun productDao() :ProductDao
    abstract fun cartDao() :CartDao

    companion object{
        private var INSTANCE:NextclickDatabase?=null
        fun getDatabase(context: Context):NextclickDatabase{
            if(INSTANCE==null){
                synchronized(this){
                    INSTANCE=Room.databaseBuilder(context,
                        NextclickDatabase::class.java,
                        "next_click_db")
                        .build()                }
            }
            return INSTANCE!!
        }
    }

}