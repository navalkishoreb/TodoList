package com.equalexperts.todolist;

import android.app.Application;

/**
 * Created by navalkishoreb on 10/3/2017.
 */

public class BaseApplciation extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TodoListManager.get();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
