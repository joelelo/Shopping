package com.example.mae.ui.Data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "product_table")
data class Product (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val prodImg:Int,
    val prodName:String,
    val prodPrice:Int,
    val prodDesc:String
):Parcelable