package com.example.aghmobiletechnologyproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailsDialog extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_window_task_details_layout);
        context = this;
        Intent intent = getIntent();
        final int chosenTaskIndex = Integer.parseInt(intent.getStringExtra(MainTableActivity.TASK_DETAILS_MESSAGE));

        Button deleteButton = (Button) findViewById(R.id.delete_button);
        Button saveButton = (Button) findViewById(R.id.save_button);
        TextView chosenTaskName = (TextView) findViewById(R.id.chosen_task_name_text);
        chosenTaskName.setText(ApplicationClass.listOfTasks.get(chosenTaskIndex).getTaskName());

        TaskDetailsFragment.getRadioButtonListOfTables(findViewById(R.id.table_list), this);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationClass.listOfTasks.remove(chosenTaskIndex);
                backToParentActivity();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton radioButton = TaskDetailsFragment.getSelectedValue(findViewById(R.id.table_list), context);
                if(radioButton == null){
                    Toast.makeText(TaskDetailsDialog.this, "No changes detected", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(TaskDetailsDialog.this, "Table changed to " + radioButton.getText(), Toast.LENGTH_SHORT).show();
                    backToParentActivity();
                }
            }
        });
    }

    public void backToParentActivity(){
        Intent intent = new Intent(this, MainTableActivity.class);
        startActivity(intent);
    }
}
