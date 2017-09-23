package com.carselection.carselector.data.repository;


import com.carselection.carselector.data.model.ResponseModel;
import com.carselection.carselector.data.repository.source.localRepo.LocalConnection;
import com.carselection.carselector.data.repository.source.remoteRepo.RemoteConnection;

import javax.inject.Inject;

import io.reactivex.Observable;

public class CarSelectorRepository implements CarSelectorSource {

    private RemoteConnection mRemote;
    private LocalConnection mLocal;

    @Inject
    public CarSelectorRepository(RemoteConnection remote, LocalConnection local) {
        mRemote = remote;
        mLocal = local;
    }

    @Override
    public Observable<ResponseModel> getManufacturerList(String wa_key, int page, int pageSize) {
        return mRemote.getManufacturerList(wa_key, page, pageSize);
    }

    @Override
    public Observable<ResponseModel> getMainTypeList(String wa_key, String manufacturer) {
        return mRemote.getMainTypeList(wa_key, manufacturer);
    }

    @Override
    public Observable<ResponseModel> getBuiltDateList(String wa_key, String manufacturerCode, String mainTypeCode) {
        return mRemote.getBuiltDateList(wa_key, manufacturerCode, mainTypeCode);
    }

    @Override
    public String getSummary(String selectedManufacturer, String selectedMainType, String selectedBuiltDate) {
        return mLocal.getSummary(selectedManufacturer, selectedMainType, selectedBuiltDate);
    }
}
