package com.carselection.carselector.data.repository.source.remoteRepo;


import com.carselection.carselector.data.model.ResponseModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CarSelectorService {

    @GET("v1/car-types/manufacturer")
    Observable<ResponseModel> getManufacturers(
            @Query("wa_key") String wa_key,
            @Query("page") int page,
            @Query("pageSize") int pageSize
    );

    //Removing page&pageSize to show all types with search box
    @GET("v1/car-types/main-types")
    Observable<ResponseModel> getMainTypes(
            @Query("wa_key") String wa_key,
            @Query("manufacturer") String manufacturerCode
    );

    @GET("v1/car-types/built-dates")
    Observable<ResponseModel> getBuiltDays(
            @Query("wa_key") String wa_key,
            @Query("manufacturer") String manufacturerCode,
            @Query("main-type") String mainTypeCode
    );

}
