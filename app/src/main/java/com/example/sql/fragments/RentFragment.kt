package com.example.sql.fragments

import android.content.ContentValues
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.navigation.fragment.NavHostFragment
import com.example.sql.R
import com.example.sql.models.User
import com.example.sql.db.Contract
import com.example.sql.db.DatabaseHelper
import com.example.sql.models.Book

class RentFragment : Fragment() {

    lateinit var newRentButton: Button
    lateinit var userListSpinner: Spinner
    lateinit var bookListSpinner: Spinner
    lateinit var startDate : EditText
    lateinit var endDate : EditText
    lateinit var price : EditText
    val userList = mutableListOf<User>()
    val bookList = mutableListOf<Book>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startDate = view.findViewById(R.id.startDate_editText)
        endDate = view.findViewById(R.id.endDate_editText)
        price = view.findViewById(R.id.price_editText)

        userListSpinner = view.findViewById(R.id.user_list)
        bookListSpinner = view.findViewById(R.id.book_list)

        val dbHelper = DatabaseHelper(context)
        var db = dbHelper.readableDatabase

        val usersCursor = db.query(
            Contract.UsersTable.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            "${Contract.UsersTable.COLUMN_NAME_LAST_NAME}"
        )

        with (usersCursor) {
            while (moveToNext()){
                val newUser = User(fiscalCode = getString(getColumnIndex(Contract.UsersTable.COLUMN_NAME_FC)),
                    firstName =  getString(getColumnIndex(Contract.UsersTable.COLUMN_NAME_FIRST_NAME)),
                    lastName = getString(getColumnIndex(Contract.UsersTable.COLUMN_NAME_LAST_NAME)),
                    age = getInt(getColumnIndex(Contract.UsersTable.COLUMN_NAME_AGE)))
                userList.add(newUser)
            }
        }
        usersCursor.close()

        val usersAdapter = ArrayAdapter<User> (requireContext(), android.R.layout.simple_spinner_item, userList)
        usersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        userListSpinner.adapter = usersAdapter


        val booksCursor = db.query(
            Contract.BooksTable.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            "${Contract.BooksTable.COLUMN_NAME_BOOK_TITLE}"
        )

        with (booksCursor) {
            while (moveToNext()){
                val newBook = Book(bookId = getInt(getColumnIndex(Contract.BooksTable.COLUMN_NAME_BOOK_ID)),
                    title = getString(getColumnIndex(Contract.BooksTable.COLUMN_NAME_BOOK_TITLE)),
                    author = getString(getColumnIndex(Contract.BooksTable.COLUMN_NAME_AUTHOR)),
                    publishingHouse = getString(getColumnIndex(Contract.BooksTable.COLUMN_NAME_PUBLISHING_HOUSE)))
                bookList.add(newBook)
            }
        }
        booksCursor.close()

        val booksAdapter = ArrayAdapter<Book> (requireContext(), android.R.layout.simple_spinner_item, bookList)
        booksAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        bookListSpinner.adapter = booksAdapter

        newRentButton = view.findViewById(R.id.newRent_button)
        newRentButton.setOnClickListener {
            val values = ContentValues().apply {
                var pos = bookListSpinner.selectedItemPosition
                put(Contract.RentTable.COLUMN_NAME_BOOK_NAME, bookList[pos].title)
                pos = userListSpinner.selectedItemPosition
                put(Contract.RentTable.COLUMN_NAME_USER, "${userList[pos].firstName} ${userList[pos].lastName}")
                put(Contract.RentTable.COLUMN_NAME_START_DATE, startDate.text.toString())
                put(Contract.RentTable.COLUMN_NAME_END_DATE, endDate.text.toString())
                put(Contract.RentTable.COLUMN_NAME_PRICE, Integer.parseInt(price.text.toString()))
            }
            db = dbHelper.writableDatabase
            db.insert(Contract.RentTable.TABLE_NAME, null, values)
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_rentFragment_to_homeFragment)
        }
    }

}