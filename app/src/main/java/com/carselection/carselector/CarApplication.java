package com.carselection.carselector;

import android.app.Application;

import com.carselection.carselector.data.repository.DaggerRepoComponent;
import com.carselection.carselector.data.repository.RepoComponent;
import com.carselection.carselector.data.repository.RepoModule;
import com.facebook.stetho.Stetho;

/**
 * @author Majid Ramezanpour (majid.ramezanpour@gmail.com)
 */

public class CarApplication extends Application {

    private RepoComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG)
            Stetho.initializeWithDefaults(this);

        component = DaggerRepoComponent.builder()
                .repoModule(new RepoModule())
                .build();

    }

    public RepoComponent getComponent() {
        return component;
    }
}
