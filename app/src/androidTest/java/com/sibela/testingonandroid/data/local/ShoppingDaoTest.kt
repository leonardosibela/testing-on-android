package com.sibela.testingonandroid.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.sibela.testingonandroid.getOrAwaitValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ShoppingDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var database: ShoppingItemDatabase
    private lateinit var shoppingDao: ShoppingDao

    @Before
    fun setup() {
        hiltRule.inject()
        shoppingDao = database.shoppingDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertShoppingItem() = runBlockingTest {
        val shoppingItem = ShoppingItem(1, "Chair", 5, 150.50f, "url")
        shoppingDao.insertShoppingItem(shoppingItem)
        val shoppingItems = shoppingDao.observeAllShoppingItems().getOrAwaitValue()

        assertThat(shoppingItems).contains(shoppingItem)
    }

    @Test
    fun deleteShoppingItem() = runBlockingTest {
        val shoppingItem = ShoppingItem(1, "Chair", 5, 150.50f, "url")
        shoppingDao.insertShoppingItem(shoppingItem)
        shoppingDao.deleteShoppingItem(shoppingItem)
        val shoppingItems = shoppingDao.observeAllShoppingItems().getOrAwaitValue()

        assertThat(shoppingItems).doesNotContain(shoppingItem)
    }

    @Test
    fun observeTotalPriceSum() = runBlockingTest {
        val shoppingItemOne = ShoppingItem(1, "Chair", 1, 150f, "url")
        val shoppingItemTwo = ShoppingItem(2, "Chair", 2, 150f, "url")
        val shoppingItemThree = ShoppingItem(3, "Chair", 10, 150.50f, "url")
        shoppingDao.insertShoppingItem(shoppingItemOne)
        shoppingDao.insertShoppingItem(shoppingItemTwo)
        shoppingDao.insertShoppingItem(shoppingItemThree)

        val totalPriceSum = shoppingDao.observeTotalPrice().getOrAwaitValue()

        assertThat(totalPriceSum).isEqualTo(150 + 2 * 150 + 10 * 150.5f)
    }
}