package com.example.todolist.adapters

import android.graphics.Paint
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.database.ToDoItem
import java.text.SimpleDateFormat
import java.util.*

class ToDoRecyclerAdapter(val toDoItems : List<ToDoItem>) : RecyclerView.Adapter<ToDoRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.to_do_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = toDoItems.get(position).title
        holder.description.text = toDoItems.get(position).description
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = toDoItems.get(position).creationTime
        //Thursday Jan 1, 1960 | 12:00 AM
        val simpleDateFormat = SimpleDateFormat("EE MMM dd, yyyy | hh:mm a", Locale.US)
        holder.dateTime.text = simpleDateFormat.format(calendar.time)
        if(toDoItems.get(position).isDone) {
            holder.title.paintFlags = holder.title.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            holder.title.setTypeface(null, Typeface.BOLD_ITALIC)
            holder.description.paintFlags = holder.description.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            holder.description.setTypeface(null, Typeface.ITALIC)
            holder.dateTime.setTypeface(null, Typeface.ITALIC)
        }
    }

    override fun getItemCount(): Int {
        return toDoItems.size
    }
    inner class ViewHolder(view : View): RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.title)
        val description = view.findViewById<TextView>(R.id.description)
        val dateTime = view.findViewById<TextView>(R.id.date_time)
    }
}