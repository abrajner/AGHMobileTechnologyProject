package com.example.aghmobiletechnologyproject;

import android.app.Application;

import java.util.ArrayList;

public class ApplicationClass extends Application{

    public static ArrayList<Task> listOfTasks = new ArrayList<>();
    public static ArrayList<MyListData> listOfTables = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();

        listOfTasks.add(new Task("Task1Task1Task1Task1Task1Task1Task1Task1Task1Task1Task1Task1", 1));
        listOfTasks.add(new Task("Task2", 2));
        listOfTasks.add(new Task("Task3", 3));

        listOfTables.add(new MyListData("table 1"));
        listOfTables.add(new MyListData("table 2"));
        listOfTables.add(new MyListData("table 3"));

    }

}
