package com.equalexperts.todolist;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by navalkishoreb on 10/3/2017.
 */

class TodoListManager {

    private static TodoListManager manager;
    private final int INITIAL_SIZE = 50;
    private Todo todo;
    private AtomicLong counter = new AtomicLong(INITIAL_SIZE);
    private Lock lock = new ReentrantLock(true);

    private TodoListManager() {
        this.todo = new Todo(new ArrayList<TodoItem>());
    }

    public static TodoListManager get() {
        if (manager == null) {
            synchronized (TodoListManager.class) {
                if (manager == null) {
                    manager = new TodoListManager();
                }
            }
        }
        return manager;
    }

    public void fetchList(final TodoScreen.FetchCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler(Looper.getMainLooper());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                if (todo.isEmpty()) {
                    todo.getTodoList().addAll(mockTodoList().getTodoList());
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null) {
                            callback.onToDoListFetched(todo);
                        }
                    }
                });
                lock.unlock();


            }
        }).start();


    }

    private Todo mockTodoList() {
        return Todo.mock(INITIAL_SIZE);
    }

    public void add(final TodoItem todoItem, final TodoScreen.AddItemCallback callback) throws TodoError {

        if (todoItem == null) {
            throw new TodoError("Cannot add NULL item");
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler(Looper.getMainLooper());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                todoItem.setId(counter.getAndIncrement());
                todo.getTodoList().add(todoItem);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callback != null) {
                            callback.onItemAdded(todoItem);
                        }
                    }
                });
                lock.unlock();

            }
        }).start();

    }
}
