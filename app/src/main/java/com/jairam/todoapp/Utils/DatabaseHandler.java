package com.jairam.todoapp.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jairam.todoapp.Model.ToDoModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NAME = "toDoListDatabase";
    private static final String TODO_TABLE = "todo";
    private static final String ID = "id";
    private static final String TASK = "task";
    private static final String INITIAL_VALUE = "initialValue";
    private static final String FINAL_VALUE = "finalValue";
    private static final String CREATE_TODO_TABLE = "CREATE TABLE " + TODO_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + TASK + "TEXT, " + INITIAL_VALUE + "TEXT, " + FINAL_VALUE + "TEXT" + ");";
    private SQLiteDatabase db;

    public DatabaseHandler(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop the old tables
        db.execSQL(" DROP TABLE IF EXISTS " + TODO_TABLE);
        onCreate(db);
    }

    public void openDatabase() {
        db = this.getWritableDatabase();
    }

    public void insertTask(ToDoModel task) {
        ContentValues cv = new ContentValues();
        cv.put(TASK, task.getTask());
        cv.put(INITIAL_VALUE, task.getInitialValue());
        cv.put(FINAL_VALUE, task.getFinalValue());
        db.insert(TODO_TABLE, null, cv);
    }

    public List<ToDoModel> getAllTasks() {
        List<ToDoModel> taskList = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try{
            cur = db.query(TODO_TABLE, null, null, null, null, null, null, null);
            if(cur!=null){
                if(cur.moveToFirst()){
                    do {
                        ToDoModel task = new ToDoModel();
                        task.setIdTask(cur.getInt(cur.getColumnIndexOrThrow(ID)));
                        task.setTask(cur.getString(cur.getColumnIndexOrThrow(TASK)));
                        task.setTask(cur.getString(cur.getColumnIndexOrThrow(INITIAL_VALUE)));
                        task.setTask(cur.getString(cur.getColumnIndexOrThrow(FINAL_VALUE)));
                        taskList.add(task);
                    }while (cur.moveToNext());
                }
            }
        }finally {
            db.endTransaction();
            cur.close();
        }
        return taskList;


    }

//    public void updateTask(int id, String task){
//        ContentValues cv = new ContentValues();
//        cv.put(TASK, task);
//        db.update(TODO_TABLE, cv, ID+ "=?", new String[]{String.valueOf(id)});
//    }

    public void updateInitialValue(int id, String initialValue){
        ContentValues cv = new ContentValues();
        cv.put(INITIAL_VALUE, initialValue);
        db.update(TODO_TABLE, cv, ID+ "=?", new String[]{String.valueOf(initialValue)});
    }

//    public void updateFinalValue(int id, String finalValue){
//        ContentValues cv = new ContentValues();
//        cv.put(FINAL_VALUE, finalValue);
//        db.update(TODO_TABLE, cv, ID+ "=?", new String[]{String.valueOf(finalValue)});
//    }

    public void deleteTask(int id){
        db.delete(TODO_TABLE, ID+ "=?", new String[]{String.valueOf(id)});
    }



}
