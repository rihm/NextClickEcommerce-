package com.nextclick.test.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nextclick.test.db.Product
import com.nextclick.test.repository.ProductRpository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(private val productRpository: ProductRpository) :ViewModel() {

    fun getProduct():LiveData<List<Product>>{
        return productRpository.getProduct()
    }
    fun insertProduct(product: Product){
        viewModelScope.launch(Dispatchers.IO){
             productRpository.insertProduct(product)
        }


    }
}