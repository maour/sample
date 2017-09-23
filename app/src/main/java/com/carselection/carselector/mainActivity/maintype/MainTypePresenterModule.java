package com.carselection.carselector.mainActivity.maintype;


import dagger.Module;
import dagger.Provides;

@Module
public class MainTypePresenterModule {

    private final MainTypeContract.View mView;

    public MainTypePresenterModule(MainTypeContract.View mView) {
        this.mView = mView;
    }

    @Provides
    MainTypeContract.View provideMainTypeView() {
        return mView;
    }
}
