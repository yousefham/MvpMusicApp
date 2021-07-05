package com.yosoftware.musicapp.features.home.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.yosoftware.musicapp.R;
import com.yosoftware.musicapp.features.home.presenters.SplashActivityMVP;

public class Splash extends AppCompatActivity implements SplashActivityMVP.View {

    SplashActivityMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        presenter.goToHome(this);
    }

    @Override
    public void showToast() {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }
}