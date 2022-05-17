package com.jairam.todoapp.Model;

public class ToDoModel {
    private int idTask, status, idInitialValue, idFinishValue;
    private String task;
    private int initialValue, finalValue;

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIdInitialValue() {
        return idInitialValue;
    }

    public void setIdInitialValue(int idInitialValue) {
        this.idInitialValue = idInitialValue;
    }

    public int getIdFinishValue() {
        return idFinishValue;
    }

    public void setIdFinishValue(int idFinishValue) {
        this.idFinishValue = idFinishValue;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(int initialValue) {
        this.initialValue = initialValue;
    }

    public int getFinalValue() {
        return finalValue;
    }

    public void setFinalValue(int finalValue) {
        this.finalValue = finalValue;
    }
}
