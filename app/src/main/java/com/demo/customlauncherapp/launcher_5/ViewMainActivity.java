package com.demo.customlauncherapp.launcher_5;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.demo.customlauncherapp.R;
import com.demo.customlauncherapp.launcher_4.AppInfo;

import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class ViewMainActivity extends AppCompatActivity {

    private List<AppEntry> installedAppsList;
    ViewPager viewPager;

    CustomPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_main);

        initView();
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        installedAppsList=getInstalledApps();
        adapter = new CustomPagerAdapter(this, installedAppsList);
        viewPager.setAdapter(adapter);

    }

    private List<AppEntry> getInstalledApps() {
        List<AppEntry> apps = new ArrayList<>();
        PackageManager packageManager = getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> resolveInfoList = packageManager.queryIntentActivities(intent, 0);

        for (ResolveInfo resolveInfo : resolveInfoList) {
            String appName = resolveInfo.loadLabel(packageManager).toString();
            String packageName = resolveInfo.activityInfo.packageName;
            Drawable icon = resolveInfo.loadIcon(packageManager);
            apps.add(new AppEntry(appName, packageName, icon));
        }

        return apps;
    }

}