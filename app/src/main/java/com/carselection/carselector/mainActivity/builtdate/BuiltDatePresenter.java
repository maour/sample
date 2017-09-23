package com.carselection.carselector.mainActivity.builtdate;


import com.carselection.carselector.data.model.ResponseModel;
import com.carselection.carselector.data.repository.CarSelectorRepository;
import com.carselection.carselector.util.Functions;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BuiltDatePresenter implements BuiltDateContract.Presenter {

    private CarSelectorRepository mRepository;
    private BuiltDateContract.View mView;

    @Inject
    public BuiltDatePresenter(CarSelectorRepository repository, BuiltDateContract.View view) {
        this.mRepository = repository;
        this.mView = view;

        mView.setPresenter(this);
    }

    @Override
    public void start() {
    }

    @Override
    public void updateTitle(String selectedManufacturer, String selectedMainType) {
        mView.showUpdatedTitle("Select Built Date of " + selectedManufacturer + " (" + selectedMainType + ")");
    }

    @Override
    public void loadBuiltDate(String manufacturer, String maintype, ResponseModel manufacturerList) {
        mView.showProgressBar(true);

        Functions.getManufacturerCode(manufacturer, manufacturerList)
                .flatMap(v -> mRepository.getBuiltDateList("coding-puzzle-client-449cc9d", v, maintype))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        model -> mView.showBuiltDateList(Functions.getItemsList(model)),
                        e -> mView.showProgressBar(false),
                        () -> mView.showProgressBar(false)
                );
    }

}

