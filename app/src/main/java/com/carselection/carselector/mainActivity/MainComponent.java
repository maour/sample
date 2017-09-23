package com.carselection.carselector.mainActivity;


import com.carselection.carselector.data.repository.RepoComponent;
import com.carselection.carselector.mainActivity.builtdate.BuiltDatePresenterModule;
import com.carselection.carselector.mainActivity.maintype.MainTypePresenterModule;
import com.carselection.carselector.mainActivity.manufacturer.ManufacturerPresenterModule;
import com.carselection.carselector.mainActivity.summary.SummaryPresenterModule;

import dagger.Component;

@MainActivityScope
@Component(dependencies = RepoComponent.class, modules = {ManufacturerPresenterModule.class
        , MainTypePresenterModule.class, BuiltDatePresenterModule.class, SummaryPresenterModule.class})
public interface MainComponent {

    void inject(MainActivity mainActivity);
}
