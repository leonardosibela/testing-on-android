package com.sibela.testingonandroid.repositories

import androidx.lifecycle.LiveData
import com.sibela.testingonandroid.data.local.ShoppingItem
import com.sibela.testingonandroid.data.remote.response.ImageResponse
import com.sibela.testingonandroid.other.Resource

interface ShoppingRepository {

    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeShoppingItem(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchImageFor(imageQuery: String): Resource<ImageResponse>

}