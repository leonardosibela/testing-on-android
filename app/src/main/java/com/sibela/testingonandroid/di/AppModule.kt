package com.sibela.testingonandroid.di

import android.content.Context
import androidx.room.Room
import com.sibela.testingonandroid.data.local.ShoppingDao
import com.sibela.testingonandroid.data.local.ShoppingItemDatabase
import com.sibela.testingonandroid.data.remote.PixabayAPI
import com.sibela.testingonandroid.other.Constants.BASE_URL
import com.sibela.testingonandroid.other.Constants.DATABASE_NAME
import com.sibela.testingonandroid.repositories.DefaultShoppingRepository
import com.sibela.testingonandroid.repositories.ShoppingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideShoppingItemDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ShoppingItemDatabase::class.java, DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideDefaultShoppingRepository(dao: ShoppingDao, api: PixabayAPI) =
        DefaultShoppingRepository(dao, api) as ShoppingRepository

    @Provides
    @Singleton
    fun provideShoppingDao(database: ShoppingItemDatabase) =
        database.shoppingDao()

    @Provides
    @Singleton
    fun providePixabayApi() = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(PixabayAPI::class.java)



}