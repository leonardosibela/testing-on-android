package com.sibela.testingonandroid.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_items")
data class ShoppingItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    var name: String,

    var amount: Int,

    var price: Float,

    var imageUrl: String
)