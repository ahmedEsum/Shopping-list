package com.bithoven.android.shopping.database

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object  DataBaseModule {

    @Provides
    @Singleton
     fun getDataBase(app: Application) = Room.databaseBuilder(
        app,
        ShoppingDataBase::class.java,
        "shoppingDataBase"
    ).build()



    @Provides
    @Singleton
    fun getDao (dataBase: ShoppingDataBase) = dataBase.getShoppingDao()
}