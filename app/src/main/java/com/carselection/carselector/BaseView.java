package com.carselection.carselector;


public interface BaseView<T> {
    void setPresenter(T presenter);

    void showProgressBar(boolean visible);
}
