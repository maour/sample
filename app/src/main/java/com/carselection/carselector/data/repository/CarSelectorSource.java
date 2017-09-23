package com.carselection.carselector.data.repository;


import com.carselection.carselector.data.model.ResponseModel;

import io.reactivex.Observable;

public interface CarSelectorSource {

    Observable<ResponseModel> getManufacturerList(String wa_key, int page, int pageSize);

    Observable<ResponseModel> getMainTypeList(String wa_key, String manufacturer);

    Observable<ResponseModel> getBuiltDateList(String wa_key, String manufacturerCode, String mainTypeCode);

    String getSummary(String selectedManufacturer, String selectedMainType, String selectedBuiltDate);

}
