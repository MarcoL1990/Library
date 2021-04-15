package com.example.sql.db

object Contract {

    object BooksTable {
        const val TABLE_NAME = "books"
        const val COLUMN_NAME_BOOK_ID = "id"
        const val COLUMN_NAME_BOOK_TITLE = "title"
        const val COLUMN_NAME_AUTHOR = "author"
        const val COLUMN_NAME_PUBLISHING_HOUSE = "publishing"

        const val SQL_CREATE_BOOKS_TABLE = "CREATE TABLE ${BooksTable.TABLE_NAME} (" +
                "${BooksTable.COLUMN_NAME_BOOK_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${BooksTable.COLUMN_NAME_BOOK_TITLE} TEXT," +
                "${BooksTable.COLUMN_NAME_AUTHOR} TEXT," +
                "${BooksTable.COLUMN_NAME_PUBLISHING_HOUSE} TEXT)"
    }

    object UsersTable {
        const val TABLE_NAME = "users"
        const val COLUMN_NAME_FC = "fiscal_code"
        const val COLUMN_NAME_FIRST_NAME = "first_name"
        const val COLUMN_NAME_LAST_NAME = "last_name"
        const val COLUMN_NAME_AGE = "age"

        const val SQL_CREATE_USER_TABLE = "CREATE TABLE ${UsersTable.TABLE_NAME} (" +
                "${UsersTable.COLUMN_NAME_FC} TEXT PRIMARY KEY," +
                "${UsersTable.COLUMN_NAME_FIRST_NAME} TEXT," +
                "${UsersTable.COLUMN_NAME_LAST_NAME} TEXT," +
                "${UsersTable.COLUMN_NAME_AGE} INTEGER)"
    }

    object RentTable {
        const val TABLE_NAME = "rents"
        const val COLUMN_NAME_RENT_ID = "id"
        const val COLUMN_NAME_BOOK_NAME = "book_name"
        const val COLUMN_NAME_USER = "user"
        const val COLUMN_NAME_START_DATE = "start_date"
        const val COLUMN_NAME_END_DATE = "end_date"
        const val COLUMN_NAME_PRICE = "price"

        const val SQL_CREATE_RENT_TABLE = "CREATE TABLE ${RentTable.TABLE_NAME} (" +
                "${RentTable.COLUMN_NAME_RENT_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${RentTable.COLUMN_NAME_BOOK_NAME} TEXT," +
                "${RentTable.COLUMN_NAME_USER} TEXT," +
                "${RentTable.COLUMN_NAME_START_DATE} TEXT," +
                "${RentTable.COLUMN_NAME_END_DATE} TEXT," +
                "${RentTable.COLUMN_NAME_PRICE} INTEGER)"
    }


}