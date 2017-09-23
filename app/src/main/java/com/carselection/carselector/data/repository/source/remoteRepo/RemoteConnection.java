package com.carselection.carselector.data.repository.source.remoteRepo;


import com.carselection.carselector.data.model.ResponseModel;
import com.carselection.carselector.data.repository.CarSelectorSource;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RemoteConnection implements CarSelectorSource{

    private static final String BASE_URL = REMOVED; //replace with Fake Online REST API

    private Retrofit retrofit;
    private CarSelectorService service;

    private OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
    }

    public RemoteConnection() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getClient())
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        service = retrofit.create(CarSelectorService.class);
    }

    @Override
    public Observable<ResponseModel> getManufacturerList(String wa_key, int page, int pageSize) {
        return service.getManufacturers(wa_key, page, pageSize);
    }

    @Override
    public Observable<ResponseModel> getMainTypeList(String wa_key, String manufacturer) {
        return service.getMainTypes(wa_key, manufacturer);
    }

    @Override
    public Observable<ResponseModel> getBuiltDateList(String wa_key, String manufacturerCode, String mainTypeCode) {
        return service.getBuiltDays(wa_key, manufacturerCode, mainTypeCode);
    }

    @Override
    public String getSummary(String selectedManufacturer, String selectedMainType, String selectedBuiltDate) {
        return "";
    }
}
