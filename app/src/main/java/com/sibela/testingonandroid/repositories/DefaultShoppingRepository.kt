package com.sibela.testingonandroid.repositories

import androidx.lifecycle.LiveData
import com.sibela.testingonandroid.data.local.ShoppingDao
import com.sibela.testingonandroid.data.local.ShoppingItem
import com.sibela.testingonandroid.data.remote.PixabayAPI
import com.sibela.testingonandroid.data.remote.response.ImageResponse
import com.sibela.testingonandroid.other.Resource
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixabayAPI: PixabayAPI
) : ShoppingRepository {

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeShoppingItem(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchImageFor(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixabayAPI.searchForImage(imageQuery)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: return Resource.error("An unknowm error ocurred", null)
            } else {
                return Resource.error("An unknowm error ocurred", null)
            }
        } catch (e: Exception) {
            return Resource.error("Couldn't rearch the server. Check your internet connection", null)
        }
    }
}