package com.example.shoppinglist

import android.graphics.Bitmap

data class Product (
    val name: String,
    val count: Int,
    val value: Double,
    val image: Bitmap?
) {}