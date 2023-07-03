package com.demo.customlauncherapp.launcher_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;

import com.demo.customlauncherapp.R;

import java.util.ArrayList;

public class RecycleViewLauncherActivity extends AppCompatActivity {

    private PackageManager packageManager;

    private ArrayList<AppInformationModel> appList;

    private RecyclerView rcv_app_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_launcher);
        loadApps();
        loadRecyclerView();

    }

    private void loadApps() {
        packageManager = getPackageManager();
        appList = new ArrayList<AppInformationModel>();
        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        ArrayList<ResolveInfo> availableActivities = (ArrayList<ResolveInfo>) packageManager.queryIntentActivities(i, 0);
        for (ResolveInfo ri : availableActivities) {
            AppInformationModel app = new AppInformationModel();
            app.name = ri.loadLabel(packageManager);
            app.icon = ri.activityInfo.loadIcon(packageManager);
            appList.add(app);

        }
    }

    private void loadRecyclerView() {
        rcv_app_view = (RecyclerView) findViewById(R.id.rcv_app_view);

        ClickListener listener = new ClickListener() {
            @Override
            public void onClick(AppInformationModel appInformationModel) {
                PackageManager packageManager = getPackageManager();
                Intent launchIntent = packageManager.getLaunchIntentForPackage((String) appInformationModel.name);
                if (launchIntent != null) {
                    startActivity(launchIntent);
                }
            }
        };


        AdapterClass adapter = new AdapterClass(this,packageManager,appList,listener);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        rcv_app_view.setLayoutManager(manager);
        rcv_app_view.setAdapter(adapter);
    }

    private void addClickListener() {

    }
}

