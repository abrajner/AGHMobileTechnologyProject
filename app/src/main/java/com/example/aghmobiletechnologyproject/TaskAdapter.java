package com.example.aghmobiletechnologyproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private ArrayList<Task> taskList;

    private ItemClicked activity;

    public interface ItemClicked{
        void onItemClicked(int index);

    }

    public TaskAdapter(Context context, ArrayList<Task> taskList){
        this.taskList = taskList;
        activity =  (ItemClicked) context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView taskName;
        public RelativeLayout relativeLayout;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            taskName = itemView.findViewById(R.id.task_name);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onItemClicked(taskList.indexOf((Task) itemView.getTag()));
                }
            });
        }

    }

    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_task_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(taskList.get(position));

//        holder.taskName.setText(taskList.get(position).getTaskName());

        final Task myListData = taskList.get(position);
        holder.taskName.setText(taskList.get(position).getTaskName());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "click on item: " + myListData.getTaskName(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
