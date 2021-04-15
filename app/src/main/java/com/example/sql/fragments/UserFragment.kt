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

class UserFragment : Fragment() {

    lateinit var newUserButton : Button
    lateinit var firstName : EditText
    lateinit var lastName : EditText
    lateinit var fiscalCode : EditText
    lateinit var age : EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dbHelper = DatabaseHelper(context)

        firstName = view.findViewById(R.id.firstName_editText)
        lastName = view.findViewById(R.id.lastName_editText)
        fiscalCode = view.findViewById(R.id.fiscalCode_editText)
        age = view.findViewById(R.id.age_editText)

        newUserButton = view.findViewById(R.id.newUser_button)
        newUserButton.setOnClickListener {
            val values = ContentValues().apply {
                put(Contract.UsersTable.COLUMN_NAME_FC, fiscalCode.text.toString())
                put(Contract.UsersTable.COLUMN_NAME_FIRST_NAME, firstName.text.toString())
                put(Contract.UsersTable.COLUMN_NAME_LAST_NAME, lastName.text.toString())
                put(Contract.UsersTable.COLUMN_NAME_AGE, Integer.parseInt(age.text.toString()))
            }
            val db = dbHelper.writableDatabase
            db.insert(Contract.UsersTable.TABLE_NAME, null, values)
            NavHostFragment.findNavController(this).navigate(R.id.action_userFragment_to_homeFragment)
        }
    }

}