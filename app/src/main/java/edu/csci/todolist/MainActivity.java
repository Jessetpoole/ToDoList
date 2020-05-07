package edu.csci.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {

    private DbDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataSource = new DbDataSource(getApplicationContext());

        findViewById(R.id.add_task_btn).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        dataSource.open();

        List<TodoTask> tasks = dataSource.getAllTasks();


    }

    @Override
    protected void onStop() {
        super.onStop();
        dataSource.close();
    }

    // Create a menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }
    //Menu handler
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

    @Override
    public void onClick(View view) {
        startActivity(new Intent(getApplicationContext(), AddTask.class));
    }
}
