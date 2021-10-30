package com.example.android.shopping.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoppingItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItem)

    @Delete
    suspend fun deleteItem (item: ShoppingItem)

    @Query("Delete FROM shopping_table")
    suspend fun clearTable ()

    @Query("SELECT * FROM shopping_table")
    fun getShoppingList () : LiveData<List<ShoppingItem>>
}