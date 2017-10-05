package com.equalexperts.todolist;

/**
 * Created by navalkishoreb on 10/3/2017.
 */

class TodoError extends Exception {
    private String error;

    public TodoError(String error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return error;
    }
}
