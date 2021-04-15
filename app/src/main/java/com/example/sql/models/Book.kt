package com.example.sql.models

data class Book (val bookId : Int, val title : String, val author : String, val publishingHouse : String) {

    @Override
    override fun toString(): String {
        return title;
    }
}