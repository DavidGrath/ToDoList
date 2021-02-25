package com.example.todolist.database;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "to_do_table")
public class ToDoItem {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;
    private long creationTime;
    private long expectedTime;
    private long finishedTime;
    private boolean done;

    public ToDoItem(String title, String description, long creationTime, long expectedTime, long finishedTime, boolean done) {
        this.title = title;
        this.description = description;
        this.creationTime = creationTime;
        this.expectedTime = expectedTime;
        this.finishedTime = finishedTime;
        this.done = done;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public long getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(long expectedTime) {
        this.expectedTime = expectedTime;
    }

    public long getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(long finishedTime) {
        this.finishedTime = finishedTime;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}