package com.example.MAEAss.ui.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "cart_table")
data class Cart (
        @PrimaryKey
        val name:String,
        val img:Int,
        val price:Int,
        val quantity:Int

)