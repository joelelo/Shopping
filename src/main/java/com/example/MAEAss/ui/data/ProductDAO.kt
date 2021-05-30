package com.example.mae.ui.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.MAEAss.ui.data.Cart

@Dao
interface ProductDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: Product)

    @Query("SELECT * FROM product_table ORDER BY id DESC")
    fun getAllProducts(): LiveData<List<Product>>

    @Query("SELECT * FROM product_table WHERE prodName LIKE :query")
    fun search(query: String): LiveData<List<Product>>

    @Query("SELECT * FROM cart_table ORDER BY name DESC")
    fun getCart(): LiveData<List<Cart>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTC(cart: Cart)

    @Query("UPDATE cart_table SET quantity = :quan WHERE name = :name")
    suspend fun updateQCart(name:String,quan:Int)

    @Query("UPDATE cart_table SET quantity = quantity+ :quan WHERE name = :name")
    suspend fun appendQCart(name:String,quan:Int)

    @Query("SELECT COUNT(name) FROM cart_table where name = :name")
    suspend fun checkExist(name: String):Int

    @Query("DELETE FROM cart_table WHERE name = :name")
    suspend fun removeFC(name: String)

    @Query("SELECT SUM(price*quantity)FROM cart_table")
    fun getTotal():LiveData<Int>
}