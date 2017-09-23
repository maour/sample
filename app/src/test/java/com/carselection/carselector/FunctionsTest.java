package com.carselection.carselector;


import com.carselection.carselector.data.model.ResponseModel;
import com.carselection.carselector.util.Functions;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.observers.TestObserver;

public class FunctionsTest {

    private ResponseModel manufacturers;

    @Before
    public void setUpData() {
        manufacturers = new ResponseModel();
        manufacturers.setPage(0);
        manufacturers.setPageSize(15);
        manufacturers.setTotalPageCount(5);
        Map<String, String> listWkda = new HashMap<>();
        listWkda.put("020", "Abarth");
        listWkda.put("040", "Alfa Romeo");
        listWkda.put("042", "Alpina");
        listWkda.put("057", "Aston Martin");
        manufacturers.setWkda(listWkda);
    }

    @Test
    public void getManufactureCode_Returns_CorrectCode() {
        TestObserver<String> testObserver = Functions.getManufacturerCode("Alpina", manufacturers).test();
        testObserver.assertValue("042");
        testObserver.assertComplete();
    }

}
