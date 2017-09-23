package com.carselection.carselector.mainActivity.maintype;


import com.carselection.carselector.BasePresenter;
import com.carselection.carselector.BaseView;
import com.carselection.carselector.data.model.ResponseModel;

import java.util.List;

public interface MainTypeContract {

    interface View extends BaseView<Presenter> {

        void showMainTypeList(List<String> mainTypeList);

        void showBuiltDate(String manufacturer, String mainType, ResponseModel manufacturers);

        void showUpdatedTitle(String title);

    }

    interface Presenter extends BasePresenter {

        void updateTitle(String selectedManufacturer);

        void loadMainType(String manufacturer, ResponseModel manufacturerList);

        void mainTypeClicked(String manufacturer, String mainType, ResponseModel manufacturers);

        void filter(CharSequence searchedItem);

    }
}
