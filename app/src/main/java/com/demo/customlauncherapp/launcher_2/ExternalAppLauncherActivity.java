package com.demo.customlauncherapp.launcher_2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.customlauncherapp.R;

import java.util.ArrayList;
import java.util.List;

public class ExternalAppLauncherActivity extends ListActivity {

    PackageManager packageManager = null;
    List<ApplicationInfo> appList = null;
    ApplicationAdaptor listAdaptor = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_app_launcher);

        packageManager = getPackageManager();
        new LoadApplicationTask().execute();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_external_app_launcher, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        boolean result = true;
        switch (item.getItemId()) {
            case R.id.menu_about: {
                displayAboutDialog();
                break;
            }
            default: {
                return super.onOptionsItemSelected(item);

            }
        }
        return result;

    }

    private void displayAboutDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.app_name));
        builder.setMessage(getString(R.string.app_desc));

        builder.show();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        ApplicationInfo app = appList.get(position);
        try {
            Intent intent = packageManager.getLaunchIntentForPackage(app.packageName);

            if (null != intent) {

                startActivity(intent);
            }
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list) {
        ArrayList<ApplicationInfo> appList = new ArrayList<ApplicationInfo>();
        for (ApplicationInfo info : list) {
            try {
                if (null != packageManager.getLaunchIntentForPackage(info.packageName)) {
                    appList.add(info);
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        return appList;
    }

    private class LoadApplicationTask extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progress = null;

        @Override
        protected Void doInBackground(Void... params) {

            appList = checkForLaunchIntent(
                    packageManager.getInstalledApplications(packageManager.GET_META_DATA)
            );
            listAdaptor = new ApplicationAdaptor(ExternalAppLauncherActivity.this, R.layout.appview, appList);

            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(Void result) {

            setListAdapter(listAdaptor);
            progress.dismiss();
            super.onPostExecute(result);

        }

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(ExternalAppLauncherActivity.this, null, "Loading application info...");
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    private class ApplicationAdaptor extends ArrayAdapter<ApplicationInfo> {

        private List<ApplicationInfo> objects = null;


        public ApplicationAdaptor(Context context, int textViewResourceId, List<ApplicationInfo> objects) {
            super(context, textViewResourceId, objects);
            this.objects = objects;
        }

        @Override
        public int getCount() {
            return ((null != objects) ? objects.size() : 0);
        }

        @Nullable
        @Override
        public ApplicationInfo getItem(int position) {
            return ((null != objects) ? objects.get(position) : null);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = convertView;
            if (null == view) {
                LayoutInflater vi = (LayoutInflater) ExternalAppLauncherActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                view = vi.inflate(R.layout.appview, null);
            }
            ApplicationInfo data = objects.get(position);
            if (null != data) {
                TextView textName = (TextView) view.findViewById(R.id.app_name);
                ImageView iconView = (ImageView) view.findViewById(R.id.app_icon);
                textName.setText(data.loadLabel(packageManager) + "(" + data.packageName + ")");
                iconView.setImageDrawable(data.loadIcon(packageManager));
            }
            return view;
        }

    }

    ;


}