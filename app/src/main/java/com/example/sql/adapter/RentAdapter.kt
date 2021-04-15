package com.example.sql.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sql.R
import com.example.sql.models.Rent

class RentAdapter (val context : Context) : RecyclerView.Adapter<RentAdapter.RentViewHolder>(){

    var rentList = listOf<Rent>()

    class RentViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView) {
        val startDate : TextView = itemView.findViewById(R.id.startDateToEdit)
        val endDate : TextView = itemView.findViewById(R.id.endDateToEdit)
        val price : TextView = itemView.findViewById(R.id.priceToEdit)
        val bookTitle : TextView = itemView.findViewById(R.id.titleToEdit)
        val userName : TextView = itemView.findViewById(R.id.userToEdit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RentAdapter.RentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.rent_adapter_layout, parent, false)
        return RentViewHolder(view)
    }

    override fun onBindViewHolder(holder: RentAdapter.RentViewHolder, position: Int) {
        val item = rentList[position]
        holder.startDate.text = item.startDate
        holder.endDate.text = item.endDate
        holder.price.text = item.price.toString()
        holder.bookTitle.text = item.bookName
        holder.userName.text = item.user
    }

    override fun getItemCount(): Int {
        return rentList.size
    }


}