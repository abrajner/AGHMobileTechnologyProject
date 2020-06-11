package com.example.aghmobiletechnologyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainTableActivity extends AppCompatActivity implements TaskAdapter.ItemClicked  {

    TextView taskName;
    Button buttonAddNewTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_table);
        this.taskName = findViewById(R.id.chosen_task_name_text);
        this.buttonAddNewTask = findViewById(R.id.add_button);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        onItemClicked(0);
    }

    @Override
    public void onItemClicked(int index) {
        this.taskName.setText(ApplicationClass.listOfTasks.get(index).getTaskName());
        //TO-DO set list of available tables from database for task
    }
}
