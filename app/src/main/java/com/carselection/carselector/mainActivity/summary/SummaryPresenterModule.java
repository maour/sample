package com.carselection.carselector.mainActivity.summary;

import dagger.Module;
import dagger.Provides;

@Module
public class SummaryPresenterModule {
    private final SummaryContract.View mView;

    public SummaryPresenterModule(SummaryContract.View mView) {
        this.mView = mView;
    }

    @Provides
    SummaryContract.View provideSummaryView() {
        return mView;
    }
}
