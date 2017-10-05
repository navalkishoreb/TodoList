package com.equalexperts.todolist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by navalkishoreb on 10/3/2017.
 */

class TodoViewHolder extends RecyclerView.ViewHolder {
    private TodoItem data;

    private TextView todoDetailsView;

    public TodoViewHolder(View itemView) {
        super(itemView);
        todoDetailsView = (TextView) itemView.findViewById(R.id.todoDetails);
    }

    public void setData(TodoItem data) {
        this.data = data;
        this.todoDetailsView.setText(data.toString());
    }
}
