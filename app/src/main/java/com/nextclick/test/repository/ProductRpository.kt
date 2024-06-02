package com.nextclick.test.repository

import androidx.lifecycle.LiveData
import com.nextclick.test.db.Product
import com.nextclick.test.db.ProductDao

class ProductRpository(private val productDao: ProductDao) {
    fun getProduct() :LiveData<List<Product>>{
        return productDao.getProducts()
    }

    suspend fun insertProduct(product: Product){
        productDao.insertProduct(product)
    }
}