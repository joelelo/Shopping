package com.example.myapplication.ui.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.MAEAss.ui.data.Cart
import com.example.mae.ui.Data.AppDatabase
import com.example.mae.ui.Data.Product
import com.example.mae.ui.Data.ProductDAO
import com.example.myapplication.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ProductDAOTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: ProductDAO

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.productDAO
    }

    @After
    fun teardown(){
        database.close()
    }

    //TEST PRODUCT

    @Test
    fun insert() = runBlockingTest{
        val product = Product(1,1,"name",100,"Description")
        dao.insert(product)

        val allProducts = dao.getAllProducts().getOrAwaitValue()

        assertThat(allProducts).contains(product)
    }

    @Test
    fun searchProduct() = runBlockingTest{
        val product = Product(1,1,"name",100,"Description")
        dao.insert(product)

        val searchProducts = dao.search(product.prodName).getOrAwaitValue()

        assertThat(searchProducts).contains(product)
    }

    //TEST CART
    @Test
    fun addTC() = runBlockingTest{
        val cart = Cart("name",1,100,10)
        dao.addTC(cart)

        val allProducts = dao.getCart().getOrAwaitValue()

        assertThat(allProducts).contains(cart)
    }

    @Test
    fun removeFC() = runBlockingTest{
        val cart = Cart("name",1,100,10)
        val cart2 = Cart("name2",1,100,10)
        dao.addTC(cart)
        dao.addTC(cart2)
        dao.removeFC(cart.name)
        val allProducts = dao.getCart().getOrAwaitValue()

        assertThat(allProducts).doesNotContain(cart)
        assertThat(allProducts).contains(cart2)
    }
}