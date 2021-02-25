package com.example.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToDoDao {
    @Insert
    fun addToDoItem(toDoItem: ToDoItem)
    @Update
    fun updateToDoItem(toDoItem: ToDoItem)
    @Delete
    suspend fun deleteToDoItem(toDoItem: ToDoItem)

    @Query("SELECT * FROM to_do_table ORDER BY done, creationTime DESC")
    fun getAllToDoItems() : LiveData<List<ToDoItem>>

//    @Query("SELECT * FROM to_do_table WHERE creationTime >= :begin AND creationTime < :end ORDER BY done, creationTime")
//    suspend fun getAllToDoItemsWithinTimeRange(begin: Long, end:Long) : LiveData<List<ToDoItem>>
}