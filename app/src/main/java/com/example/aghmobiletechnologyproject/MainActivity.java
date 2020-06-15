package com.example.aghmobiletechnologyproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aghmobiletechnologyproject.model.TableClass;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    MyListAdapter adapter = new MyListAdapter(ApplicationClass.listOfTableClasses, this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button_add_table = findViewById(R.id.buttonAddTable);
        final EditText nameOfNewTable = findViewById(R.id.nameOfNewTable); //.getText().toString()
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.tablesList);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        button_add_table.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String newTableName = nameOfNewTable.getText().toString();
                TableClass table = new TableClass(newTableName);
                table.save();
                notifyDataChanged();
                nameOfNewTable.setText(null);
            }
        });

    }

    public void moveToTableView(View view, String tableName){
        Intent intent = new Intent(this, MainTableActivity.class);
        String message = tableName;
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void notifyDataChanged(){
        adapter.notifyDataSetChanged();
    }
}
