package com.example.aghmobiletechnologyproject.model;

import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Table;

@Table(name = "tasks")
public class Task extends SugarRecord {
    @Column(name = "task_name")
    private String taskName;

    @Column(name = "table_name")
    private String tableName;

    public Task(String taskName, String tableName) {
        this.taskName = taskName;
        this.tableName = tableName;
    }

    public Task() {
    }

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
