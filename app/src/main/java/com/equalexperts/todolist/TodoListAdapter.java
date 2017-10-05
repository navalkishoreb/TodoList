package com.equalexperts.todolist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by navalkishoreb on 10/3/2017.
 */

class TodoListAdapter extends android.support.v7.widget.RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private enum Type {
        ITEM,
        EMPTY
    }

    private Todo todo;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Type type = Type.values()[viewType];
        Log.d("EqualExperts", "ViewType " + type.name());
        switch (type) {
            case ITEM:
                View itemView = inflater.inflate(R.layout.todo_item, parent, false);
                return new TodoViewHolder(itemView);
            case EMPTY:
                View emptyView = inflater.inflate(R.layout.todo_empty, parent, false);
                return new EmptyViewHolder(emptyView);
            default:
                throw new UnsupportedOperationException(type.name() + " no defined");
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TodoViewHolder) {
            TodoViewHolder todoViewHolder = (TodoViewHolder) holder;
            todoViewHolder.setData(todo.getTodoList().get(position));
        } else if (holder instanceof EmptyViewHolder) {
            Log.d("EqualExperts", "EmptyView");
        }
    }

    void setTodo(Todo todo) {
        this.todo = todo;
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        if (doesHasItems()) {
            return Type.ITEM.ordinal();
        } else {
            Log.d("EqualExperts", "Empty type");
            return Type.EMPTY.ordinal();
        }
//        return Type.EMPTY.ordinal();
    }

    @Override
    public int getItemCount() {
        if (!doesHasItems()) {
            return 1;
        }
        return todo.getTodoList().size();
    }

    private boolean doesHasItems() {
        return todo != null && !todo.isEmpty();
    }

}
