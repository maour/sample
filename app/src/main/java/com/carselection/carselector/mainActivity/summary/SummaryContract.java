package com.carselection.carselector.mainActivity.summary;


import com.carselection.carselector.BasePresenter;
import com.carselection.carselector.BaseView;

public interface SummaryContract {

    interface View extends BaseView<Presenter> {

        void showSummaryInformation(String manufacturer, String maintype, String builtdate);

    }

    interface Presenter extends BasePresenter {

        void loadSummary(String selectedManufacturer, String selectedMainType, String selectedBuiltDate);
    }
}
