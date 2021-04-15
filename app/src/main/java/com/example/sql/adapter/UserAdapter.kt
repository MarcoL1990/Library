package com.example.sql.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sql.R
import com.example.sql.models.User

class UserAdapter(context: Context) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var userList = mutableListOf<User>()

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName: TextView = itemView.findViewById(R.id.userNameToEdit)
        val age: TextView = itemView.findViewById(R.id.ageToEdit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.user_adapter_layout, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        val item = userList[position]
        holder.userName.text = "Name: ${item.firstName} ${item.lastName}"
        holder.age.text = "Age: ${item.age.toString()}"
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}