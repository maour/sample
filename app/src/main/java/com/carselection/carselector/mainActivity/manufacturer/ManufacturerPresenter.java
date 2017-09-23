package com.carselection.carselector.mainActivity.manufacturer;


import com.carselection.carselector.data.model.ResponseModel;
import com.carselection.carselector.data.repository.CarSelectorRepository;
import com.carselection.carselector.util.Functions;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ManufacturerPresenter implements ManufacturerContract.Presenter {

    private final static String TAG = "ManufacturerPresneter";

    private CarSelectorRepository mRepository;
    private ManufacturerContract.View mView;

    private int currentPageToLoad = 0;
    private int totalPageRemained = 1;

    private ResponseModel manufacturersList;

    @Inject
    public ManufacturerPresenter(CarSelectorRepository repository, ManufacturerContract.View view) {
        this.mRepository = repository;
        this.mView = view;

        mView.setPresenter(this);
    }

    @Override
    public void start() {
        resetStatus();
        loadManufacturers();
    }

    private void resetStatus() {
        currentPageToLoad = 0;
        totalPageRemained = 1;
    }

    public void loadManufacturers() {

        if (pagesLeftToLoad()) {
            updateLoadingState(true);
            mRepository.getManufacturerList("coding-puzzle-client-449cc9d", currentPageToLoad, 15)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(model -> {
                                updateManufacturers(model);

                                if (currentPageToLoad >= model.getTotalPageCount())
                                    mView.setHasLoadedAll(true);

                                if (currentPageToLoad == 0) {
                                    totalPageRemained = model.getTotalPageCount();
                                    mView.showManufacturerList(Functions.getItemsList(model), model);
                                } else {
                                    totalPageRemained--;
                                    mView.showUpdatedManufacturerList(Functions.getItemsList(model));
                                }
                            },
                            e -> updateLoadingState(false),
                            () -> {
                                currentPageToLoad++;
                                updateLoadingState(false);
                            });

        } else {
            mView.setHasLoadedAll(true);
            updateLoadingState(false);
        }
    }

    private boolean pagesLeftToLoad() {
        return totalPageRemained > 0;
    }

    private void updateManufacturers(ResponseModel model) {
        if (manufacturersList == null)
            manufacturersList = model;
        else
            manufacturersList.addWkda(model.getWkda());
    }

    private void updateLoadingState(boolean loading) {
        mView.setIsLoading(loading);

        //Show main progress bar just for initial loading
        if (loading && currentPageToLoad == 0)
            mView.showProgressBar(true);
        else
            mView.showProgressBar(false);
    }


    @Override
    public void manufacturerClicked(String manufacturer) {
        mView.showMainTypePage(manufacturer, manufacturersList);
    }
}
