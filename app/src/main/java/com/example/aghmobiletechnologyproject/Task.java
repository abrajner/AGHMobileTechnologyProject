package com.example.aghmobiletechnologyproject;

public class Task {
    private String taskName;
    private Integer taskId;

    public Task(String taskName, Integer taskId) {
        this.taskName = taskName;
        this.taskId = taskId;
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

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
}
