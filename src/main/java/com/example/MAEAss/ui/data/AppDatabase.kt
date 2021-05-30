package com.example.mae.ui.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.MAEAss.ui.data.Cart
import com.example.MAEAss.ui.data.Profile
import com.example.MAEAss.ui.data.ProfileDAO

@Database(entities = [Product::class, Profile::class, Cart::class],version = 1, exportSchema = false)
abstract class AppDatabase :RoomDatabase(){
    abstract val productDAO: ProductDAO
    abstract val profileDAO: ProfileDAO
    companion object{
        @Volatile
        private var INSTANCE: com.example.mae.ui.Data.AppDatabase? = null

        fun getInstance(context: Context): com.example.mae.ui.Data.AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }


}