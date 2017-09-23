package com.carselection.carselector;

import com.carselection.carselector.data.model.ResponseModel;
import com.carselection.carselector.data.repository.CarSelectorRepository;
import com.carselection.carselector.mainActivity.manufacturer.ManufacturerContract;
import com.carselection.carselector.mainActivity.manufacturer.ManufacturerPresenter;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class ManufacturerUnitTest {

    private static ResponseModel manufacturers;

    @Mock
    CarSelectorRepository repository;

    @Mock
    ManufacturerContract.View view;

    ManufacturerPresenter presenter;

    @BeforeClass
    public static void setUpRxSchedulers() {

        Scheduler immediate = new Scheduler() {
            @Override
            public Disposable scheduleDirect(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
                return super.scheduleDirect(run, 0, unit);
            }

            @Override
            public Scheduler.Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run);
            }
        };

        //To make sure IO and MainThread schedulers are immediate
        RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }

    @Before
    public void setUpPresenter() {
        MockitoAnnotations.initMocks(this);

        presenter = new ManufacturerPresenter(repository, view);

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
    public void createPresenter_setOnView() throws Exception {
        verify(view).setPresenter(presenter);
    }

    @Test
    public void loadAllManufacturersFromRepository() {
        when(repository.getManufacturerList("coding-puzzle-client-449cc9d", 0, 15)).thenReturn(Observable.just(manufacturers));

        presenter.loadManufacturers();

        verify(view).showProgressBar(true);
        verify(view).showProgressBar(false);
    }

}