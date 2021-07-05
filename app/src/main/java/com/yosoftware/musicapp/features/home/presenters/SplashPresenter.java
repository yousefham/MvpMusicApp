package com.yosoftware.musicapp.features.home.presenters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.yosoftware.musicapp.features.home.views.MainActivity;
import com.yosoftware.musicapp.features.home.views.Splash;

public class SplashPresenter implements SplashActivityMVP.Presenter {

    @Override
    public void goToHome(Context context) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                context.startActivity(new Intent((Activity) context, MainActivity.class));
            }
        }, 1000);
    }
}
