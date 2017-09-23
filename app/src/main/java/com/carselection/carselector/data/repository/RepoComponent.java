package com.carselection.carselector.data.repository;


import com.carselection.carselector.data.repository.source.localRepo.LocalModule;
import com.carselection.carselector.data.repository.source.remoteRepo.RemoteModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { RepoModule.class, LocalModule.class, RemoteModule.class})
public interface RepoComponent {

    CarSelectorRepository getRepository();

}
