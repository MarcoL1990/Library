package com.example.sql.models

data class User(
    val fiscalCode: String,
    val firstName: String,
    val lastName: String,
    val age: Int
) {

    @Override
    override fun toString(): String {
        return "$firstName $lastName"
    }

}