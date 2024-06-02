package com.nextclick.test.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nextclick.test.repository.CartRepository
import com.nextclick.test.repository.ProductRpository

class CartViewModelFactory(private val cartRepository: CartRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CartViewModel(cartRepository) as T
    }
}