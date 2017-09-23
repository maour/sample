package com.carselection.carselector.util;


import com.carselection.carselector.data.model.ResponseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class Functions {

    public static List<String> getItemsList(ResponseModel model) {
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, String> item : model.getWkda().entrySet()) {
            list.add(item.getValue());
        }
        return list;
    }

    public static Observable<String> getManufacturerCode(String manufacturer, ResponseModel manufacturerList) {
        return Observable.create(em -> {
            for (Map.Entry<String, String> item : manufacturerList.getWkda().entrySet()) {
                if (item.getValue().equals(manufacturer)) {
                    em.onNext(item.getKey());
                    em.onComplete();
                }
            }
        });
    }

}
