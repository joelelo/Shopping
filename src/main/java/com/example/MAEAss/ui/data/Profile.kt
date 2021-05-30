package com.example.MAEAss.ui.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Profile")
data class Profile (
        @PrimaryKey
        val id:Int,
        val img:Int,
        val name: String,
        val address: String
)