package com.carselection.carselector.data.repository.source.remoteRepo;

import dagger.Module;
import dagger.Provides;

@Module
public class RemoteModule {

    @Provides
    RemoteConnection provideRemote() {
        return new RemoteConnection();
    }
}
