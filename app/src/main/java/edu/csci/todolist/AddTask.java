package edu.csci.todolist;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import javax.sql.DataSource;

public class AddTask extends AppCompatActivity
implements View.OnClickListener {

    DbDataSource dataSource;

    int hour = 0;
    int minute = 0;
    boolean isAM = true;


    enum Operations{
        ADD, SUB
    }
    enum Operands{
        HOUR, MINUTE
    }

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_create);

        findViewById(R.id.task_add_hour).setOnClickListener(this);
        findViewById(R.id.task_add_minute).setOnClickListener(this);
        findViewById(R.id.task_sub_hour).setOnClickListener(this);
        findViewById(R.id.task_sub_minute).setOnClickListener(this);
        findViewById(R.id.am_pm_btn).setOnClickListener(this);

        editText = findViewById(R.id.task_et);
    }

    //This controls when the user adds or subtracts from the desired time
    void changeTaskTime(Operations operation, Operands operand){
        int op = 0;

        if (operation.equals(Operations.ADD))
            op = 1;
        else if (operation.equals(Operations.SUB))
            op = -1;
        else
            Log.i("TDLError", "Error with operation in changeTaskTime, AddTask.java");

        if(operand.equals(Operands.HOUR))
            hour = (hour + op) % 12;

        else if(operand.equals(Operands.MINUTE))
            minute = (minute + (op * 15)) % 60;

        else
            Log.i("TDLError", "Error with Operand in changeTaskTime, AddTask.java");

        if(hour < 0)
            hour += 12;
        if(minute < 0)
            minute += 60;

        updateTextViews();
    }

    public void toggleAM(){
        Button btn = findViewById(R.id.am_pm_btn);
        if(isAM){
            isAM = false;
            btn.setText("P.M.");
        }
        else {
            isAM = true;
            btn.setText("A.M.");
        }
    }

    public void updateTextViews(){
        TextView hourTV = findViewById(R.id.task_hour_tv);
        TextView minTV = findViewById(R.id.task_minute_tv);

        //Handle Hours
        int tempHour = hour + 1;
        String tempHr;
        if (tempHour < 10)
            tempHr = "0" + tempHour;
        else
            tempHr = ((Integer) tempHour).toString();
        hourTV.setText(tempHr);

        //Handle Minutes
        String tempMin;
        if (minute < 10)
            tempMin = "0" + minute;
        else
            tempMin = ((Integer)minute).toString();

        minTV.setText(tempMin);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.task_add_hour)
            changeTaskTime(Operations.ADD, Operands.HOUR);
        else if (view.getId() == R.id.task_add_minute)
            changeTaskTime(Operations.ADD, Operands.MINUTE);
        else if (view.getId() == R.id.task_sub_hour)
            changeTaskTime(Operations.SUB, Operands.HOUR);
        else if (view.getId() == R.id.task_sub_minute)
            changeTaskTime(Operations.SUB, Operands.MINUTE);
        else if (view.getId() == R.id.am_pm_btn)
            toggleAM();
        else{
            Log.i("TLDError", "Something went wrong with the AddTask onCLickListener");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        String givenTask = editText.getText().toString();

        if(!(givenTask.equals(""))){
            if(!isAM)
                hour += 12;
            TodoTask task = dataSource.createTask(hour, minute, givenTask);
            Log.i("TLD", givenTask);
        }
    }
}
