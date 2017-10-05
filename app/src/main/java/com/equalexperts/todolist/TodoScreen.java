package com.equalexperts.todolist;

/**
 * Created by navalkishoreb on 10/3/2017.
 */

interface TodoScreen {

    void fetch();

    void showLoading();

    interface FetchCallback {
        void onToDoListFetched(Todo todo);

        void onError(TodoError error);
    }


    void hideLoading();


    interface AddItemCallback {
        void onItemAdded(TodoItem todoItem);

        void onError(TodoError error);
    }

    void add(TodoItem todoItem);


}
