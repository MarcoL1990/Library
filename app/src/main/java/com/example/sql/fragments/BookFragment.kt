package com.example.sql.fragments

import android.content.ContentValues
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.NavHostFragment
import com.example.sql.R
import com.example.sql.db.Contract
import com.example.sql.db.DatabaseHelper

class BookFragment : Fragment() {

    lateinit var newBookButton : Button
    lateinit var bookTitle : EditText
    lateinit var bookAuthor : EditText
    lateinit var bookPublishingHouse : EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dbHelper = DatabaseHelper(context)

        bookTitle = view.findViewById(R.id.bookName_editText)
        bookAuthor = view.findViewById(R.id.authorName_editText)
        bookPublishingHouse = view.findViewById(R.id.publishingHouse_editText)

        newBookButton = view.findViewById(R.id.newBook_button)
        newBookButton.setOnClickListener {
            val values = ContentValues().apply {
                put(Contract.BooksTable.COLUMN_NAME_BOOK_TITLE, bookTitle.text.toString())
                put(Contract.BooksTable.COLUMN_NAME_AUTHOR, bookAuthor.text.toString())
                put(Contract.BooksTable.COLUMN_NAME_PUBLISHING_HOUSE, bookPublishingHouse.text.toString())
            }
            val db = dbHelper.writableDatabase
            db.insert(Contract.BooksTable.TABLE_NAME, null, values)
            NavHostFragment.findNavController(this).navigate(R.id.action_bookFragment_to_homeFragment)
        }
    }
}