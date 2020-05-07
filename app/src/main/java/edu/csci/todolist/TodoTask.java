package edu.csci.todolist;

import android.util.Log;

import java.util.Calendar;

public class TodoTask {

    //TODO: store in database
    //TODO: Create class for checking database for next TODO with time
    //TODO: check every 1 or 5 minutes, depending on how I set it up


    //Variables to store when the task is supposed to occur

    private int taskId;
    private int hour;
    private int minute;


    private int date;

    private String task;

    TodoTask(int iHour, int iMinutes, String iTask){
        hour = iHour;
        minute = iMinutes;
        task = iTask;
        date = Calendar.DAY_OF_MONTH;
    }

    TodoTask(){
        hour = 0;
        minute = 0;
        date = Calendar.DAY_OF_MONTH;
    }


    public int getHour()    { return hour;   }
    public int getMinute()  { return minute; }
    public int getDate()    { return date;   }
    public int getTaskId()  { return taskId; }

    public void setTaskId(int taskId)   { this.taskId   = taskId;   }
    public void setHour(int hour)       { this.hour     = hour;     }
    public void setMinute(int minute)   { this.minute   = minute;   }
    public void setDate(int date)       { this.date     = date;     }
    public void setTask(String task)    { this.task     = task;     }

}
