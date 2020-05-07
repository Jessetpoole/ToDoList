package edu.csci.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqlLiteHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "todo.sqlite";
    private static final int DB_VERSION = 1;

    public static final String TODO_TABLE = "Todo";

    public enum Todo_Columns{
        todo_id, hour, minute, task;

        public static String[] names() {
            Todo_Columns[] v = values();
            String[] names = new String[v.length];

            for (int i = 0; i < v.length; i++) {
                names[i] = v[i].toString();
            }
            return names;
        }
    }

    public MySqlLiteHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TODO_TABLE + " (" +
                Todo_Columns.todo_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Todo_Columns.hour + " INTEGER NOT NULL, " +
                Todo_Columns.minute + " INTEGER NOT NULL, " +
                Todo_Columns.task +  " TEXT NOT NULL )";

        db.execSQL(sql);
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase , int i , int i1) {

    }

    @Override
    public void onDowngrade(SQLiteDatabase db , int oldVersion , int newVersion) {
        super.onDowngrade(db , oldVersion , newVersion);
    }
}
