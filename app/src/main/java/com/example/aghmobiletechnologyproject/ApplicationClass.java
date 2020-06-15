package com.example.aghmobiletechnologyproject;

import android.app.Application;

import com.example.aghmobiletechnologyproject.model.TableClass;
import com.example.aghmobiletechnologyproject.model.Task;
import com.orm.SugarContext;
import com.orm.dsl.Table;

import java.util.ArrayList;
import java.util.List;

public class ApplicationClass extends Application{

    public static ArrayList<Task> listOfTasks = new ArrayList<>();
    public static ArrayList<TableClass> listOfTableClasses = new ArrayList<>();
    private List<TableClass> tableList;
    TableClass tables;

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(getApplicationContext());
//        listOfTasks.add(new Task("Task1Task1Task1Task1Task1Task1Task1Task1Task1Task1Task1Task1", 1));
//        listOfTasks.add(new Task("Task2", 2));
//        listOfTasks.add(new Task("Task3", 3));
//
//        listOfTableClasses.add(new TableClass("table 1"));
//        listOfTableClasses.add(new TableClass("table 2"));
//        listOfTableClasses.add(new TableClass("table 3"));
        tables = new TableClass();
        tableList = TableClass.listAll(TableClass.class);
        for (int i = 0; i<tableList.size(); i++){
            tables = tableList.get(i);
            listOfTableClasses.add(tables);
        }
    }



    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
