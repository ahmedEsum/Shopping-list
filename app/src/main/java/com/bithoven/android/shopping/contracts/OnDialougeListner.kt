package com.bithoven.android.shopping.contracts

import com.bithoven.android.shopping.database.ShoppingItem

interface OnDialougeListner {
    fun onDialougeListner(item: ShoppingItem)
}

interface OnItemUpdated {
    fun update(item: ShoppingItem)
    fun delete(item: ShoppingItem)
}