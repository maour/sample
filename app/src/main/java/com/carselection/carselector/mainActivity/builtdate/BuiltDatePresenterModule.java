package com.carselection.carselector.mainActivity.builtdate;

import dagger.Module;
import dagger.Provides;

@Module
public class BuiltDatePresenterModule {

    BuiltDateContract.View mView;

    public BuiltDatePresenterModule(BuiltDateContract.View mView) {
        this.mView = mView;
    }

    @Provides
    BuiltDateContract.View provideBuiltDateView() {
        return mView;
    }
}
