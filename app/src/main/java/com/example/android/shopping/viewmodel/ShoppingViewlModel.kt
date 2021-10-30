package com.example.android.shopping.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.android.shopping.database.ShoppingItem
import com.example.android.shopping.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ShoppingViewlModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun upsertItem (item:ShoppingItem) {
        CoroutineScope(Dispatchers.Main).launch { repository.upsertShoppingItem(item) }
        Log.d("okaay", "upsertItem:  done ")}
    fun deleteItem(item:ShoppingItem) = CoroutineScope(Dispatchers.Main).launch { repository.deleteItem(item) }
    fun clear()=CoroutineScope(Dispatchers.Main).launch { repository.clear() }
    fun getShoppingList()= repository.getShoppingList()
}