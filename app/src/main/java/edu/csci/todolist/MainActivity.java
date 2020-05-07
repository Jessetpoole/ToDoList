package edu.csci.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

//TODO: have a method that checks every now and then if data is still valid by their dates


public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {

    private DbDataSource dataSource;

    private String[] months = {
            "Jan", "Feb", "Mar",
            "Apr", "May", "Jun",
            "Jul", "Aug", "Sep",
            "Oct", "Nov", "Dec"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        int temp;

        TextView dayTv = findViewById(R.id.date_day_tv);
        TextView monthTv = findViewById(R.id.date_month_tv);
        TextView yearTv = findViewById(R.id.date_year_tv);

        //Set Day
        dayTv.setText(((Integer) new Date().getDate()).toString());

        //Set Month
        monthTv.setText(months[new Date().getMonth()]);

        //Set year
        temp = (new Date()).getYear() + 1900;
        yearTv.setText(((Integer) temp).toString());


        dataSource = new DbDataSource(getApplicationContext());

        findViewById(R.id.add_task_btn).setOnClickListener(this);
        Log.i("TLD", "Main act");
    }

    @Override
    protected void onStart() {
        super.onStart();

        dataSource.open();

        List<TodoTask> tasks = dataSource.getAllTasks();
        //dataSource.createTask(1, 1, "test");


        //TODO if current date does not match earliest data entry, reset database

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
