package com.carselection.carselector.mainActivity.maintype;


import com.carselection.carselector.data.model.ResponseModel;
import com.carselection.carselector.data.repository.CarSelectorRepository;
import com.carselection.carselector.util.Functions;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainTypePresenter implements MainTypeContract.Presenter {

    private CarSelectorRepository mRepository;
    private MainTypeContract.View mView;

    private List<String> mMainTypeList = new ArrayList<>();
    private List<String> filteredList = new ArrayList<>();

    @Inject
    public MainTypePresenter(CarSelectorRepository repository, MainTypeContract.View view) {
        this.mRepository = repository;
        this.mView = view;

        mView.setPresenter(this);
    }

    @Override
    public void start() {
    }

    @Override
    public void updateTitle(String selectedManufacturer) {
        mView.showUpdatedTitle("Select Main Type of " + selectedManufacturer);
    }

    @Override
    public void loadMainType(String manufacturer, ResponseModel manufacturerList) {

        mView.showProgressBar(true);

        Functions.getManufacturerCode(manufacturer, manufacturerList)
                .flatMap(v -> mRepository.getMainTypeList("coding-puzzle-client-449cc9d", v))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        model -> {
                            mMainTypeList = Functions.getItemsList(model);
                            mView.showMainTypeList(mMainTypeList);
                        },
                        e -> mView.showProgressBar(false),
                        () -> mView.showProgressBar(false)
                );
    }

    @Override
    public void mainTypeClicked(String manufacturer, String mainType, ResponseModel manufacturers) {
        mView.showBuiltDate(manufacturer, mainType, manufacturers);
    }

    @Override
    public void filter(CharSequence searchedItem) {
        if (searchedItem.length() > 0) {
            filteredList.clear();
            for (String item : mMainTypeList) {
                if (item.toLowerCase().contains(searchedItem.toString().toLowerCase()))
                    filteredList.add(item);
            }
            mView.showMainTypeList(filteredList);
        } else {
            mView.showMainTypeList(mMainTypeList);
        }
    }

}
