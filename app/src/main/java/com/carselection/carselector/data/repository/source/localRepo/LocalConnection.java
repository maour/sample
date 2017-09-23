package com.carselection.carselector.data.repository.source.localRepo;


import com.carselection.carselector.data.model.ResponseModel;
import com.carselection.carselector.data.repository.CarSelectorSource;

import io.reactivex.Observable;

public class LocalConnection implements CarSelectorSource {

    public LocalConnection() {
    }

    @Override
    public String getSummary(String selectedManufacturer, String selectedMainType, String selectedBuiltDate) {
        return selectedManufacturer + " : " + selectedMainType + " : " + selectedBuiltDate;
    }

    @Override
    public Observable<ResponseModel> getManufacturerList(String wa_key, int page, int pageSize) {
        //Not necessary
        return null;
    }

    @Override
    public Observable<ResponseModel> getMainTypeList(String wa_key, String manufacturer) {
        //Not necessary
        return null;
    }

    @Override
    public Observable<ResponseModel> getBuiltDateList(String wa_key, String manufacturerCode, String mainTypeCode) {
        //Not necessary
        return null;
    }

}
