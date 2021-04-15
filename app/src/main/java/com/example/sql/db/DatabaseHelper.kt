package com.example.sql.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper (context : Context?) : SQLiteOpenHelper (context, "library_rent.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(Contract.BooksTable.SQL_CREATE_BOOKS_TABLE)
        db?.execSQL(Contract.UsersTable.SQL_CREATE_USER_TABLE)
        db?.execSQL(Contract.RentTable.SQL_CREATE_RENT_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }


}