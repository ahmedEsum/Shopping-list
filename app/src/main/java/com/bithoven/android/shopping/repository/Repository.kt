package com.bithoven.android.shopping.repository

import com.bithoven.android.shopping.database.ShoppingDataBase
import com.bithoven.android.shopping.database.ShoppingItem
import javax.inject.Inject

class Repository @Inject constructor(private val shoppingDataBase: ShoppingDataBase) {

    suspend fun upsertShoppingItem(item: ShoppingItem) =
        shoppingDataBase.getShoppingDao().upsert(item)

    suspend fun deleteItem(item: ShoppingItem) = shoppingDataBase.getShoppingDao().deleteItem(item)

    suspend fun clear() = shoppingDataBase.getShoppingDao().clearTable()

    fun getShoppingList() = shoppingDataBase.getShoppingDao().getShoppingList()

}