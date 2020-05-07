package edu.csci.todolist;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddTask extends AppCompatActivity
implements View.OnClickListener {

    int hour = 0;
    int minute = 0;
    boolean isAm = true;


    enum Operations{
        ADD, SUB
    }
    enum Operands{
        HOUR, MINUTE
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_create);

        findViewById(R.id.task_add_hour).setOnClickListener(this);
        findViewById(R.id.task_add_minute).setOnClickListener(this);
        findViewById(R.id.task_sub_hour).setOnClickListener(this);
        findViewById(R.id.task_sub_minute).setOnClickListener(this);
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

    public void updateTextViews(){
        TextView hourTV = findViewById(R.id.task_hour_tv);
        TextView minTV = findViewById(R.id.task_minute_tv);

        int tempHour = hour + 1;

        hourTV.setText(((Integer) tempHour).toString());

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
    }

}
