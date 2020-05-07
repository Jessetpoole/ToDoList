package edu.csci.todolist;

import android.util.Log;

import java.util.Calendar;

public class TodoTask {

    //TODO: store in database
    //TODO: Create class for checking database for next TODO with time
    //TODO: check every 1 or 5 minutes, depending on how I set it up


    //Variables to store when the task is supposed to occur
    private int hour;
    private int minute;
    private int date;

    TodoTask(int iHour, int iMinutes){
        hour = iHour;
        minute = iMinutes;
        date = Calendar.DAY_OF_MONTH;
    }

    TodoTask(){
        hour = 23;
        minute = 59;
        date = Calendar.DAY_OF_MONTH;
    }


    public int getHour()    { return hour; }
    public int getMinute()  { return minute; }
    public int getDate()    { return date; }

}
