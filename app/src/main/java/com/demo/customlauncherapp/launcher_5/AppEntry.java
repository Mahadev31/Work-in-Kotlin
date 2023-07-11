package com.demo.customlauncherapp.launcher_5;

import android.graphics.drawable.Drawable;


public class AppEntry {
    private String appName;
    private String packageName;
    private Drawable icon;

    public AppEntry(String appName, String packageName, Drawable icon) {
        this.appName = appName;
        this.packageName = packageName;
        this.icon = icon;
    }

    public String getAppName() {
        return appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public Drawable getIcon() {
        return icon;
    }

}
