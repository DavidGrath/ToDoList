package com.example.todolist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.todolist.MainActivity
import com.example.todolist.R
import com.example.todolist.database.ToDoItem
import com.example.todolist.database.ToDoViewModel
import kotlinx.android.synthetic.main.add_todo_layout.*
import java.util.*

class AddToDoFragment : Fragment(), View.OnClickListener {

    lateinit var viewModel: ToDoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.add_todo_layout, null, false)
        val mainActivity = activity as MainActivity
        viewModel = ViewModelProviders.of(mainActivity).get(ToDoViewModel::class.java)
        val doneButton = view.findViewById<Button>(R.id.done_adding_item)
        doneButton.setOnClickListener(this)
        return view
    }

    override fun onDetach() {
        Toast.makeText(context, "Item Discarded", Toast.LENGTH_SHORT).show()
        super.onDetach()
    }
    override fun onClick(view : View?) {
        view.let {
            when(it) {
                done_adding_item -> {
                    val thisTime = Date().time
                    viewModel.addToDoItem(ToDoItem(add_title.text.toString(), add_description.text.toString(),
                        thisTime, thisTime, thisTime, false))
                    (activity as MainActivity).finishToDoAdd()
                }
                cancel_add_item -> {
                    (activity as MainActivity).finishToDoAdd()
                }
            }
        }
    }
}