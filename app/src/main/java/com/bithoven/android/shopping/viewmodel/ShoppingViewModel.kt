package com.bithoven.android.shopping.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bithoven.android.shopping.database.ShoppingItem
import com.bithoven.android.shopping.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun upsertItem(item: ShoppingItem) {
        viewModelScope.launch(Dispatchers.IO) { repository.upsertShoppingItem(item) }
        Log.d("okaay", "upsertItem:  done ")
    }

    fun deleteItem(item: ShoppingItem) {
        viewModelScope.launch(Dispatchers.IO) { repository.deleteItem(item) }
    }

    fun clear() {
        viewModelScope.launch(Dispatchers.IO) { repository.clear() }
    }

    fun getShoppingList() =
        repository.getShoppingList()

}

