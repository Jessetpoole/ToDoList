package edu.csci.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

public class DbDataSource {
    private SQLiteDatabase database;
    private MySqlLiteHelper databaseHelper;

    DbDataSource(Context context){
        databaseHelper = new MySqlLiteHelper(context);
    }

    public void open(){
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        database.close();
    }

    public TodoTask createTask(int iHour, int iMinute, String iTask){
        ContentValues contentValues = new ContentValues();

        contentValues.put(MySqlLiteHelper.Todo_Columns.task.toString(), iTask);
        contentValues.put(MySqlLiteHelper.Todo_Columns.hour.toString(), iHour);
        contentValues.put(MySqlLiteHelper.Todo_Columns.minute.toString(), iMinute);

        long id = database.insert(MySqlLiteHelper.TODO_TABLE,
                null, contentValues);

        String[] columnNames = MySqlLiteHelper.Todo_Columns.names();

        //select * from todo where comment_id = *
        Cursor cursor = database.query(MySqlLiteHelper.TODO_TABLE,
                columnNames,
                MySqlLiteHelper.Todo_Columns.todo_id + " = " + id,
                null, null, null, null);

        cursor.moveToFirst();
        TodoTask task = cursorToTask(cursor);
        cursor.close();

        return task;
    }

    public List<TodoTask> getAllTasks(){
        List<TodoTask> todos = new ArrayList<>();

        String columns[] = MySqlLiteHelper.Todo_Columns.names();

        Cursor cursor = database.query(MySqlLiteHelper.TODO_TABLE,
                columns,
                null, null, null, null, null);
        cursor.moveToNext();
        while(!cursor.isAfterLast()){
            TodoTask todoTask = cursorToTask(cursor);
            todos.add(todoTask);
            cursor.moveToNext();
        }
        cursor.close();
        return todos;
    }

    private TodoTask cursorToTask(Cursor cursor){
        TodoTask task = new TodoTask();

        int todoId = cursor.getInt(MySqlLiteHelper.Todo_Columns.todo_id.ordinal());
        task.setTaskId(todoId);

        String cTask = cursor.getString(MySqlLiteHelper.Todo_Columns.task.ordinal());
        task.setTask(cTask);

        int cHour = cursor.getInt((MySqlLiteHelper.Todo_Columns.hour.ordinal()));
        task.setHour(cHour);

        int cMinute = cursor.getInt(MySqlLiteHelper.Todo_Columns.minute.ordinal());
        task.setMinute(cMinute);

        return task;
    }
}
