package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.adapters.ToDoRecyclerAdapter
import com.example.todolist.database.ToDoItem
import com.example.todolist.database.ToDoViewModel
import com.example.todolist.fragments.AddToDoFragment
import com.example.todolist.fragments.ToDoFragment
import kotlinx.android.synthetic.main.to_do_fragment.*

class MainActivity : AppCompatActivity() {


    lateinit var toDoFragment: ToDoFragment
    lateinit var addToDoFragment: AddToDoFragment
    lateinit var lastFragment : Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toDoFragment = ToDoFragment()
        supportFragmentManager.beginTransaction().add(toDoFragment, null)
            .replace(R.id.default_frame, toDoFragment).addToBackStack(null).commit()
    }

    fun addToDoItem() {
        addToDoFragment = AddToDoFragment()
        supportFragmentManager.beginTransaction().add(addToDoFragment, null)
            .replace(R.id.default_frame, addToDoFragment).addToBackStack(null).commit()
    }


    fun finishToDoAdd() {
        var transaction = supportFragmentManager.beginTransaction()
        transaction.remove(addToDoFragment).replace(R.id.default_frame,toDoFragment).commit()
    }
}
