package edu.csci.todolist;

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
