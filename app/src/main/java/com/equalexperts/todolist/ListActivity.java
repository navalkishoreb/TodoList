package com.equalexperts.todolist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by navalkishoreb on 10/3/2017.
 */

public class ListActivity extends AppCompatActivity implements TodoScreen, TodoScreen.FetchCallback, TodoScreen.AddItemCallback {

    private RecyclerView listView;
    private ProgressBar progressBar;

    private TodoListAdapter adapter;
    private int counter = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        setTitle(R.string.todoScreenTitle);
        setViews();
        fetch();
    }

    private void setViews() {
        listView = (RecyclerView) findViewById(R.id.listView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        adapter = new TodoListAdapter();
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setAdapter(adapter);
    }

    @Override
    public void fetch() {
        showLoading();
        TodoListManager.get().fetchList(this);
    }

    @Override
    public void showLoading() {
        counter++;
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        counter--;
        if (counter == 0) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void add(TodoItem todoItem) {
        try {
            showLoading();
            TodoListManager.get().add(todoItem, this);
        } catch (TodoError todoError) {
            todoError.printStackTrace();
        }
    }

    @Override
    public void onToDoListFetched(Todo todo) {
        adapter.setTodo(todo);
        hideLoading();
    }

    @Override
    public void onItemAdded(TodoItem todoItem) {
        adapter.notifyDataSetChanged();
        Snackbar.make(listView, "Added: " + todoItem.toString(), Snackbar.LENGTH_LONG).show();
        hideLoading();

    }

    @Override
    public void onError(TodoError error) {
        hideLoading();
        Snackbar.make(listView, error.getErrorMessage(), Snackbar.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.todo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addTask:
                TodoItem todoItem = new TodoItem("Task from options menu");
                add(todoItem);
                return true;
            default:
                return false;

        }
    }
}
