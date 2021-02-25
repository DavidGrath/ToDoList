package com.example.todolist.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application) : AndroidViewModel(application) {

    private val toDoItemRepository : ToDoItemRepository
    val toDoItems : LiveData<List<ToDoItem>>

    init {
        val toDoDao = ToDoDatabase.getInstance(application, viewModelScope).toDoDao()
        toDoItemRepository = ToDoItemRepository(toDoDao)
        toDoItems = toDoItemRepository.allToDoItems
    }

    fun addToDoItem(toDoItem: ToDoItem) = viewModelScope.launch(Dispatchers.IO){
        toDoItemRepository.addToDoItem(toDoItem)
    }

    fun updateToDoItem(toDoItem: ToDoItem) = viewModelScope.launch(Dispatchers.IO){
        toDoItemRepository.updateToDoItem(toDoItem)
    }
    fun deleteToDoItem(toDoItem: ToDoItem) = viewModelScope.launch(Dispatchers.IO){
        toDoItemRepository.deleteToDoItem(toDoItem)
    }


}