package com.demo.customlauncherapp.launcher_2;

import android.app.Application;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

public class AppLauncher extends Application {

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
