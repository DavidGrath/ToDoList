package com.example.todolist.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class ToDoItemRepository(private val toDoDao: ToDoDao) {

    var allToDoItems : LiveData<List<ToDoItem>>

    init {
        allToDoItems = toDoDao.getAllToDoItems()
    }

    @WorkerThread
    suspend fun addToDoItem(toDoItem: ToDoItem) {
        toDoDao.addToDoItem(toDoItem)
    }

    @WorkerThread
    suspend fun updateToDoItem(toDoItem: ToDoItem) {
        toDoDao.updateToDoItem(toDoItem)
    }

    @WorkerThread
    suspend fun deleteToDoItem(toDoItem: ToDoItem) {
        toDoDao.deleteToDoItem(toDoItem)
    }

//    @WorkerThread
//    suspend fun getAllToDoItemsWithinTimeRange(begin: Long, end:Long) : LiveData<List<ToDoItem>> {
//        return toDoDao.getAllToDoItemsWithinTimeRange(begin, end)
//    }

}