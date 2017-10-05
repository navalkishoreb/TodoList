package com.equalexperts.todolist;

/**
 * Created by navalkishoreb on 10/3/2017.
 */

class TodoItem {
    private long id;
    private final String taskDetails;

    public TodoItem(int id, String taskDetails) {
        this.id = id;
        this.taskDetails = taskDetails;
    }

    public TodoItem(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public String getTodoDetails() {
        return taskDetails;
    }

    public static TodoItem mock(int id) {
        return new TodoItem(id, "Mock item #" + id);
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Task #" + id + "\n" + taskDetails;
    }
}
