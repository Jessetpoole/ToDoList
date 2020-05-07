package edu.csci.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TodoTask todoTask = new TodoTask(1, 30, "test");
    }
    // This is just some code for a sample menu that I currently have, since you are doing the code
    // for the interface if you think we do not need a menu feel free to delete it as I put a
    // option on the ui to possibly use a button located at the bottom.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.task1:
                Toast.makeText(this, "Task 1 selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.task2:
                Toast.makeText(this, "Task 2 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.minortask:
                Toast.makeText(this, "Minor Task 1 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.minortask2:
                Toast.makeText(this, "Minor Task 2 selected", Toast.LENGTH_SHORT).show();
                return true;
            default:return super.onOptionsItemSelected(item);
        }

    }
}
