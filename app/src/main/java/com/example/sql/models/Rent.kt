package com.example.sql.models

data class Rent(
    val rentId: Int,
    val bookName: String,
    val user: String,
    val startDate: String,
    val endDate: String,
    val price: Int
)
