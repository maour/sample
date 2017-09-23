package com.carselection.carselector.mainActivity.manufacturer;


import com.carselection.carselector.BasePresenter;
import com.carselection.carselector.BaseView;
import com.carselection.carselector.data.model.ResponseModel;

import java.util.List;

public interface ManufacturerContract {

    interface View extends BaseView<Presenter> {

        void setIsLoading(boolean isLoading);

        void setHasLoadedAll(boolean hasLoaded);

        void showManufacturerList(List<String> manufacturerList, ResponseModel manufacturers);

        void showUpdatedManufacturerList(List<String> newList);

        void showMainTypePage(String manufacturer, ResponseModel manufacturers);
    }

    interface Presenter extends BasePresenter {

        void loadManufacturers(); //int page, int pageSize

        void manufacturerClicked(String manufacturer);

    }
}
