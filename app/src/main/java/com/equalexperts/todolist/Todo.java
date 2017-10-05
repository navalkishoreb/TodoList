package com.equalexperts.todolist;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by navalkishoreb on 10/3/2017.
 */

class Todo {
    private List<TodoItem> todoList;

    public Todo(List<TodoItem> list) {
        this.todoList = list;
    }

    public List<TodoItem> getTodoList() {
        return todoList;
    }

    public boolean isEmpty() {
        return this.getTodoList() == null || this.getTodoList().isEmpty();
    }

    public static Todo mock(int size) {
        List<TodoItem> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(TodoItem.mock(i));
        }
        return new Todo(list);
    }
}
