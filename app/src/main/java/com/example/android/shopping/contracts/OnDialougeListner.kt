package com.example.android.shopping.contracts

import com.example.android.shopping.database.ShoppingItem

interface OnDialougeListner {
    fun onDialougeListner(item:ShoppingItem)
}

interface OnItemUpdated {
    fun update(item:ShoppingItem)
    fun delete(item: ShoppingItem)
}