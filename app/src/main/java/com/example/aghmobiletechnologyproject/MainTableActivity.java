package com.example.aghmobiletechnologyproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aghmobiletechnologyproject.model.Task;

import java.util.ArrayList;

public class MainTableActivity extends AppCompatActivity implements TaskAdapter.ItemClicked  {
    public static String TASK_DETAILS_MESSAGE;
    TextView taskName;
    Button buttonAddNewTask;
    EditText newTaskName;
    private TasksListFragment listFragment;
    private FragmentManager fragmentManager;
    Button buttonSaveChanges;
    Button buttonDeleteTask;
    Context context;
    int tableIndex;
    String tableName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_table);
        context = this;
        this.taskName = findViewById(R.id.chosen_task_name_text);
        this.buttonAddNewTask = findViewById(R.id.add_button);
        this.newTaskName = findViewById(R.id.new_task_name);
        this.buttonSaveChanges = findViewById(R.id.save_button);
        this.buttonDeleteTask = findViewById(R.id.delete_button);
        this.fragmentManager = this.getSupportFragmentManager();
        ApplicationClass.listOfTasks = new ArrayList<>();

        listFragment = (TasksListFragment) fragmentManager.findFragmentById(R.id.list_frag);

        if(!(getResources().getConfiguration().orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)){
            TaskDetailsFragment.getRadioButtonListOfTables(findViewById(R.id.table_list), this);
            buttonDeleteTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainTableActivity.this, "Task not selected", Toast.LENGTH_SHORT).show();
                }
            });

            buttonSaveChanges.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainTableActivity.this, "Task not selected", Toast.LENGTH_SHORT).show();
                }
            });
        }

        Intent intent = getIntent();
        this.tableIndex = intent.getIntExtra(MainActivity.EXTRA_MESSAGE_TABLE_INDEX, 0);
        this.tableName = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_TABLE_NAME);
        new ApplicationClass.GetAllTableData(tableName, this).execute();

        buttonAddNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newTaskName.getText().toString().isEmpty()){
                    Toast.makeText(MainTableActivity.this, "Please enter task name", Toast.LENGTH_SHORT).show();
                }
                else{
                    ApplicationClass.addNewTask(newTaskName.getText().toString().trim(), tableName);
                    Toast.makeText(MainTableActivity.this, "Task added", Toast.LENGTH_SHORT).show();
                    newTaskName.setText(null);
                    listFragment.notifyDataChanged();
                }
            }
        });
    }

    @Override
    public void onItemClicked(final int index) {
        int orientation = getResources().getConfiguration().orientation;
        if(!(orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)) {
            this.taskName.setText(ApplicationClass.listOfTasks.get(index).getTaskName());
            buttonDeleteTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ApplicationClass.removeTask(index);
                    listFragment.notifyDataChanged();
                }
            });

            buttonSaveChanges.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RadioButton radioButton = TaskDetailsFragment.getSelectedValue(findViewById(R.id.table_list), context);
                    if(radioButton == null){
                        Toast.makeText(MainTableActivity.this, "No changes detected", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        ApplicationClass.updateTask(index, radioButton.getText().toString());
                        listFragment.notifyDataChanged();
                        Toast.makeText(MainTableActivity.this, "Table changed to " + radioButton.getText(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            displayDialogWindowWithTaskDetails(index, this.tableIndex, this.tableName);
            listFragment.notifyDataChanged();
        }
    }

    public void displayDialogWindowWithTaskDetails(int index, int tableIndex, String tableName){
        Intent intent = new Intent(this, TaskDetailsDialog.class);
        intent.putExtra(TASK_DETAILS_MESSAGE, index);
        intent.putExtra(MainActivity.EXTRA_MESSAGE_TABLE_INDEX, tableIndex);
        intent.putExtra(MainActivity.EXTRA_MESSAGE_TABLE_NAME, tableName);
        startActivity(intent);
    }
}
