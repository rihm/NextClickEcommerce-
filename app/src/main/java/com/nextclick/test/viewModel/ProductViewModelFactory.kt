package com.nextclick.test.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nextclick.test.repository.ProductRpository

class ProductViewModelFactory(private val productRpository: ProductRpository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel(productRpository) as T
    }
}