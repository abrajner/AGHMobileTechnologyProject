package com.example.aghmobiletechnologyproject;

import android.app.Application;

import java.util.ArrayList;

public class ApplicationClass extends Application{

    public static ArrayList<Task> listOfTasks;

    @Override
    public void onCreate() {
        super.onCreate();

        listOfTasks = new ArrayList<>();
        listOfTasks.add(new Task("Task1Task1Task1Task1Task1Task1Task1Task1Task1Task1Task1Task1", 1));
        listOfTasks.add(new Task("Task2", 2));
        listOfTasks.add(new Task("Task3", 3));
    }

}
