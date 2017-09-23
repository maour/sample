package com.carselection.carselector.mainActivity.manufacturer;


import dagger.Module;
import dagger.Provides;

@Module
public class ManufacturerPresenterModule {

    private final ManufacturerContract.View mView;

    public ManufacturerPresenterModule(ManufacturerContract.View view) {
        this.mView = view;
    }

    @Provides
    ManufacturerContract.View provideManufacturerView() {
        return mView;
    }
}
