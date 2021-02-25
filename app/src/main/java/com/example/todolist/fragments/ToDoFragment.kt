package com.example.todolist.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.MainActivity
import com.example.todolist.R
import com.example.todolist.adapters.ToDoRecyclerAdapter
import com.example.todolist.database.ToDoViewModel
import kotlinx.android.synthetic.main.to_do_fragment.*

class ToDoFragment : Fragment() {

    lateinit var adapter: ToDoRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.to_do_fragment, container, false)
        val to_do_recycler = view.findViewById<RecyclerView>(R.id.todo_recycler)
        setHasOptionsMenu(true)
        to_do_recycler.layoutManager = LinearLayoutManager(context)
        val mainActivity = activity as MainActivity
        val todoViewModel = ViewModelProviders.of(mainActivity).get(ToDoViewModel::class.java)

        var simpleCallback: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                var toDoItem = adapter.toDoItems.get(viewHolder.adapterPosition)
                toDoItem.isDone = true
                todoViewModel.updateToDoItem(toDoItem)
            }
        }
        var itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(to_do_recycler)
        todoViewModel.toDoItems.observe(this, Observer {toDoItems->
            toDoItems.let {
                adapter = ToDoRecyclerAdapter(it)
                to_do_recycler.adapter = adapter
            }
        })
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.to_do_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            (R.id.add_todo_item_menuitem) -> {
                (activity as MainActivity).addToDoItem()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}