package com.carselection.carselector.mainActivity.builtdate;


import com.carselection.carselector.BasePresenter;
import com.carselection.carselector.BaseView;
import com.carselection.carselector.data.model.ResponseModel;

import java.util.List;

public interface BuiltDateContract {

    interface View extends BaseView<Presenter> {

        void showBuiltDateList(List<String> builtDateList);

        void showUpdatedTitle(String title);

    }

    interface Presenter extends BasePresenter {

        void updateTitle(String selectedManufacturer, String selectedMainType);

        void loadBuiltDate(String manufacturer, String model, ResponseModel manufacturerList);

    }
}
