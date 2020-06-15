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
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        buttonAddNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newTaskName.getText().toString().isEmpty()){
                    Toast.makeText(MainTableActivity.this, "Please enter task name", Toast.LENGTH_SHORT).show();
                }
                else{
                    Task task = new Task(newTaskName.getText().toString().trim());
                    task.save();
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
                    ApplicationClass.listOfTasks.remove(index);
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
                        listFragment.notifyDataChanged();
                        Toast.makeText(MainTableActivity.this, "Table changed to " + radioButton.getText(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            displayDialogWindowWithTaskDetails(index);
            listFragment.notifyDataChanged();
        }
    }

    public void displayDialogWindowWithTaskDetails(int index){
        Intent intent = new Intent(this, TaskDetailsDialog.class);
        String message = String.valueOf(index);
        intent.putExtra(TASK_DETAILS_MESSAGE, message);
        startActivity(intent);
    }
}
