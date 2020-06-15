package com.example.aghmobiletechnologyproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.ListFragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainTableActivity extends AppCompatActivity implements TaskAdapter.ItemClicked  {
    public static String TASK_DETAILS_MESSAGE;
    TextView taskName;
    Button buttonAddNewTask;
    EditText newTaskName;
    private TasksListFragment listFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_table);
        this.taskName = findViewById(R.id.chosen_task_name_text);
        this.buttonAddNewTask = findViewById(R.id.add_button);
        this.newTaskName = findViewById(R.id.new_task_name);

        this.fragmentManager = this.getSupportFragmentManager();
        listFragment = (TasksListFragment) fragmentManager.findFragmentById(R.id.list_frag);

        if(!(getResources().getConfiguration().orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)){
            TaskDetailsFragment.getRadioButtonListOfTables(findViewById(R.id.table_list), this);
        }

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        buttonAddNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newTaskName.getText().toString().isEmpty()){
                    Toast.makeText(MainTableActivity.this, "Please enter task name", Toast.LENGTH_SHORT).show();
                }
                else{
                    ApplicationClass.listOfTasks.add(new Task(newTaskName.getText().toString().trim()));
                    Toast.makeText(MainTableActivity.this, "Task added", Toast.LENGTH_SHORT).show();
                    newTaskName.setText(null);
                    listFragment.notifyDataChanged();
                }
            }
        });
    }

    @Override
    public void onItemClicked(int index) {
        int orientation = getResources().getConfiguration().orientation;
        if(!(orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)) {
            this.taskName.setText(ApplicationClass.listOfTasks.get(index).getTaskName());
        }else{
            displayDialogWindowWithTaskDetails(index);
            listFragment.notifyDataChanged();
        }
        //TO-DO set list of available tables from database for task
    }

    public void displayDialogWindowWithTaskDetails(int index){
        Intent intent = new Intent(this, TaskDetailsDialog.class);
        String message = String.valueOf(index);
        intent.putExtra(TASK_DETAILS_MESSAGE, message);
        startActivity(intent);
    }
}
