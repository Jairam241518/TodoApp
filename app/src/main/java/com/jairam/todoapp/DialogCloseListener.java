package com.jairam.todoapp;

import android.content.DialogInterface;

import androidx.recyclerview.widget.RecyclerView;

public interface DialogCloseListener {

    public void handleDialogClose(DialogInterface dialog);

    void deleteTask(RecyclerView.ViewHolder viewHolder);
}
