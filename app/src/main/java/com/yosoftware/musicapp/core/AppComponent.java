package com.yosoftware.musicapp.core;

import com.yosoftware.musicapp.features.home.presenters.SplashModule;
import com.yosoftware.musicapp.features.home.views.Splash;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, SplashModule.class})
public interface AppComponent {
    void injectSplash(Splash target);
    
}
