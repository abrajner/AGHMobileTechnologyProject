package com.example.aghmobiletechnologyproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class TaskDetailsDialog extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_window_task_details_layout);

        Intent intent = getIntent();
        final String message = intent.getStringExtra(MainTableActivity.TASK_DETAILS_MESSAGE);

        Button deleteButton = (Button) findViewById(R.id.delete_button);
        Button saveButton = (Button) findViewById(R.id.save_button);
        TextView chosenTaskName = (TextView) findViewById(R.id.chosen_task_name_text);
        chosenTaskName.setText(ApplicationClass.listOfTasks.get(Integer.valueOf(message)).getTaskName());

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationClass.listOfTasks.remove(Integer.valueOf(message));
                backToParentActivity();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                listFragment.notifyDataChanged();
                backToParentActivity();
            }
        });
    }

    public void backToParentActivity(){
        Intent intent = new Intent(this, MainTableActivity.class);
        startActivity(intent);
    }
}
