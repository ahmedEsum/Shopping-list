package com.bithoven.android.shopping.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [ShoppingItem::class],version = 1,exportSchema = false)
abstract class ShoppingDataBase : RoomDatabase() {

    abstract fun getShoppingDao(): ShoppingItemDao

//    companion object {
//        @Volatile
//        private var sInstance: ShoppingDataBase? = null
//        private val lock = Any()
//        operator fun invoke (context: Context) = sInstance ?: synchronized(lock){
//            sInstance?: getDataBase(context).also { sInstance=it }
//        }


//        private fun getDataBase(context: Context) = Room.databaseBuilder(
//            context.applicationContext,
//            ShoppingDataBase::class.java,
//            "shoppingDataBase"
//        ).build()

//    }
}