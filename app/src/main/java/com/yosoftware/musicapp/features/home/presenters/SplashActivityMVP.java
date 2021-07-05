package com.yosoftware.musicapp.features.home.presenters;

import android.content.Context;

public interface SplashActivityMVP {
    interface Presenter {
        void goToHome(Context context);
    }

    interface View {
        void showToast();
    }
}
