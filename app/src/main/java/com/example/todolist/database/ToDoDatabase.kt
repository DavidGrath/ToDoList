package com.example.todolist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
import java.util.*

@Database(entities = arrayOf(ToDoItem::class), version = 1)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun toDoDao() : ToDoDao

    companion object {
        @Volatile
        private var INSTANCE : ToDoDatabase? = null
        fun getInstance(context: Context, scope: CoroutineScope) : ToDoDatabase{
            val instance = INSTANCE
            if(instance != null) {
                return instance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, ToDoDatabase::class.java, "todo_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(ToDoDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class ToDoDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            INSTANCE?.let {database ->
                scope.launch(Dispatchers.IO){
                    populateDb(database.toDoDao())
                }
            }
        }

        fun populateDb(toDoDao: ToDoDao) {
//            INSTANCE?.let {
                var currentTime = Calendar.getInstance().timeInMillis
                toDoDao.addToDoItem(ToDoItem("Finish Up App", "This app is not yet complete", currentTime, currentTime + 86400, 0, false))
                currentTime = Calendar.getInstance().timeInMillis
                toDoDao.addToDoItem(ToDoItem("Buy Milk", "Let descriptions be null", currentTime, currentTime + 86400, 0, false))
                currentTime = Calendar.getInstance().timeInMillis
                toDoDao.addToDoItem(ToDoItem("Kill Sans", "Also Play Megalovania", currentTime, currentTime + 86400, 0, false))
                currentTime = Calendar.getInstance().timeInMillis
                toDoDao.addToDoItem(ToDoItem("Review Project", "I'll forget everything I've learnt otherwise", currentTime, currentTime + 86400, 0, false))
//            }
        }
    }
}