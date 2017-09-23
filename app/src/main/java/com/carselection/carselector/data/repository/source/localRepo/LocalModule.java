package com.carselection.carselector.data.repository.source.localRepo;

import dagger.Module;
import dagger.Provides;

@Module
public class LocalModule {

    @Provides
    LocalConnection provideLocal() {
        return new LocalConnection();
    }
}
