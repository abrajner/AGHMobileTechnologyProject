package com.example.aghmobiletechnologyproject;

import android.app.Application;
import android.content.Context;

import com.example.aghmobiletechnologyproject.model.TableClass;
import com.example.aghmobiletechnologyproject.model.Task;
import com.orm.SugarContext;
import com.orm.dsl.Table;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.ArrayList;
import java.util.List;

public class ApplicationClass extends Application{

    public static ArrayList<Task> listOfTasks = new ArrayList<>();
    public static ArrayList<TableClass> listOfTableClasses = new ArrayList<>();
    TableClass tables;

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(getApplicationContext());

        tables = new TableClass();
        List<TableClass> tableList = TableClass.listAll(TableClass.class);
        for (int i = 0; i< tableList.size(); i++){
            tables = tableList.get(i);
            listOfTableClasses.add(tables);
        }
    }

    public static void addNewTask(String taskName, String tableName){
        Task task = new Task(taskName, tableName);
        listOfTasks.add(task);
        task.save();
    }

    public static void addNewTable(String tableName){
        TableClass table = new TableClass(tableName);
        listOfTableClasses.add(table);
        table.save();
    }

    public static void getAllTasksFromTable(String tableName){
        Task tasks = new Task();
        List<Task> tasksList = Select.from(Task.class)
                .where(Condition.prop("table_name").eq(tableName)).list();
        for (int i = 0; i< tasksList.size(); i++){
            tasks = tasksList.get(i);
            listOfTasks.add(tasks);
        }
    }

    public static void removeTask(int id){
        Task task = listOfTasks.get(id);
        task.delete();
        listOfTasks.remove(id);
    }

    public static void updateTask(int id, String newTableName){
        Task task = listOfTasks.get(id);
        task.setTableName(newTableName);
        task.save();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
