package com.example.aghmobiletechnologyproject;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaskDetailsFragment extends Fragment {

    public TaskDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_details, container, false);
    }


    public static void getRadioButtonListOfTables(View view, Context context){
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.table_list_radio);
        RadioGroup.LayoutParams layoutParams;
        for(int i = 0; i< ApplicationClass.listOfTableClasses.size(); i++){
            RadioButton radioButton = new RadioButton(context);
            radioButton.setText(ApplicationClass.listOfTableClasses.get(i).getName());
            layoutParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.MATCH_PARENT);
            radioGroup.addView(radioButton, layoutParams);
        }
    }

    public static RadioButton getSelectedValue(View view, Context context){
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.table_list_radio);
        int selectedValue = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) view.findViewById(selectedValue);
        return radioButton;
    }
}
