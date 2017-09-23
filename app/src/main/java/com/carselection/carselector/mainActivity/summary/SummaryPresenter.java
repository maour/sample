package com.carselection.carselector.mainActivity.summary;


import com.carselection.carselector.data.repository.CarSelectorRepository;

import javax.inject.Inject;

public class SummaryPresenter implements SummaryContract.Presenter {

    //In future we can show previous searches from DB :)
    private CarSelectorRepository mCarSelectorRepository;
    private SummaryContract.View mView;

    @Inject
    public SummaryPresenter(CarSelectorRepository carSelectorRepository, SummaryContract.View view) {
        mCarSelectorRepository = carSelectorRepository;
        mView = view;

        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadSummary(String selectedManufacturer, String selectedMainType, String selectedBuiltDate) {
        mView.showSummaryInformation(selectedManufacturer, selectedMainType, selectedBuiltDate);
    }
}
