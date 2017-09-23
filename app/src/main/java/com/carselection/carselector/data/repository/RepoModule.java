package com.carselection.carselector.data.repository;


import com.carselection.carselector.data.repository.source.localRepo.LocalConnection;
import com.carselection.carselector.data.repository.source.remoteRepo.RemoteConnection;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepoModule {

    @Provides
    @Singleton
    CarSelectorRepository provideCarSelectorRepository(RemoteConnection remote, LocalConnection local) {
        return new CarSelectorRepository(remote, local);
    }
}
