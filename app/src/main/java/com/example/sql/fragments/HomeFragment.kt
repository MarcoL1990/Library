package com.example.sql.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sql.R
import com.example.sql.adapter.RentAdapter
import com.example.sql.adapter.UserAdapter
import com.example.sql.db.Contract
import com.example.sql.db.DatabaseHelper
import com.example.sql.models.Rent
import com.example.sql.models.User

class HomeFragment : Fragment() {

    lateinit var newUserButton : Button
    lateinit var newRentButton : Button
    lateinit var newBookButton : Button
    lateinit var userRecyclerView: RecyclerView
    lateinit var rentRecyclerView: RecyclerView
    lateinit var userAdapter : UserAdapter
    lateinit var rentAdapter: RentAdapter
    var userList = mutableListOf<User>()
    var rentList = mutableListOf<Rent>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dbHelper = DatabaseHelper(context)
        val db = dbHelper.readableDatabase
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

        userRecyclerView = view.findViewById(R.id.user_recyclerView)
        userAdapter = UserAdapter(requireContext())
        userAdapter.userList = userList
        userRecyclerView.adapter = userAdapter
        userRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val rentCursor = db.query(
            Contract.RentTable.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            "${Contract.RentTable.COLUMN_NAME_RENT_ID}"
        )

            with(rentCursor) {
                while (moveToNext()) {
                    val newRent = Rent(
                        rentId = getInt(getColumnIndex(Contract.RentTable.COLUMN_NAME_RENT_ID)),
                        bookName = getString(getColumnIndex(Contract.RentTable.COLUMN_NAME_BOOK_NAME)),
                        user = getString(getColumnIndex(Contract.RentTable.COLUMN_NAME_USER)),
                        startDate = getString(getColumnIndex(Contract.RentTable.COLUMN_NAME_START_DATE)),
                        endDate = getString(getColumnIndex(Contract.RentTable.COLUMN_NAME_END_DATE)),
                        price = getInt(getColumnIndex(Contract.RentTable.COLUMN_NAME_PRICE))
                    )
                    rentList.add(newRent)
                }
            }

        rentRecyclerView = view.findViewById(R.id.rent_recyclerView)
        rentAdapter = RentAdapter(requireContext())
        rentAdapter.rentList = rentList
        rentRecyclerView.adapter = rentAdapter
        rentRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        newUserButton = view.findViewById(R.id.addUser_button)
        newUserButton.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_homeFragment_to_userFragment)
        }
        newRentButton = view.findViewById(R.id.addRent_button)
        newRentButton.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_homeFragment_to_rentFragment)
        }
        newBookButton = view.findViewById(R.id.addBook_button)
        newBookButton.setOnClickListener {

            NavHostFragment.findNavController(this).navigate(R.id.action_homeFragment_to_bookFragment)
        }
    }
}