package com.yosoftware.musicapp.core;

import android.app.Application;

import com.yosoftware.musicapp.features.home.presenters.SplashModule;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).splashModule(new SplashModule()).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
