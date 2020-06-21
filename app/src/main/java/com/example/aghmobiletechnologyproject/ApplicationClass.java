package com.example.aghmobiletechnologyproject;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.aghmobiletechnologyproject.model.TableClass;
import com.example.aghmobiletechnologyproject.model.Task;
import com.orm.SugarContext;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.ArrayList;
import java.util.List;

public class ApplicationClass extends Application{

    public static ArrayList<Task> listOfTasks = new ArrayList<>();
    public static ArrayList<TableClass> listOfTableClasses = new ArrayList<>();
    InternetConnectionCheck internetConnectionCheck = new InternetConnectionCheck();

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(getApplicationContext());

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(internetConnectionCheck, filter);

        new GetAllTables().execute();
    }

    @SuppressLint("StaticFieldLeak")
    class GetAllTables extends AsyncTask<Void, TableClass, Void>{
        List<TableClass> tableList;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tableList = TableClass.listAll(TableClass.class);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i< tableList.size(); i++){
                publishProgress(tableList.get(i));
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(TableClass... values) {
            super.onProgressUpdate(values);
            listOfTableClasses.add(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(ApplicationClass.this, "All tables get successfully from database", Toast.LENGTH_SHORT).show();
        }
    }

    public static void getAllTasksFromTable(String tableName){
        List<Task> tasksList;
        tasksList = Select.from(Task.class)
                .where(Condition.prop("table_name").eq(tableName)).list() ;

        listOfTasks.addAll(tasksList);
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
        unregisterReceiver(internetConnectionCheck);
        SugarContext.terminate();
    }
}
