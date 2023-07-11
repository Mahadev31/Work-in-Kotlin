package com.demo.mylauncher;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomPagerAdapter extends PagerAdapter {
    private static final int ROWS = 5;
    private static final int COLS = 4;

    private Context mContext;
    private List<AppInfo> appsList;


    public CustomPagerAdapter(Context context) {
        mContext = context;
    }

    public void updateList(List<AppInfo> appsList) {
        this.appsList=appsList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        double ratio = (double) appsList.size() / (ROWS * COLS);
        return (int) Math.ceil(ratio);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((TableLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        int cols = COLS, rows = ROWS;
        switch (mContext.getResources().getConfiguration().orientation) {
            case Configuration.ORIENTATION_PORTRAIT:
                rows++;
                break;
            default:
                cols++;
        }

        TableLayout table = new TableLayout(mContext);
        table.setStretchAllColumns(true);
        table.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        table.setBackgroundColor(Color.TRANSPARENT);

        int start = position * rows * cols;
        int end = Math.min(start + rows * cols, appsList.size());

        LayoutInflater inflater = LayoutInflater.from(mContext);

        for (int i = 0; i < rows; i++) {
            TableRow row = new TableRow(mContext);
            row.setLayoutParams(new TableLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0.2f));
            row.setGravity(Gravity.CENTER);
            for (int j = 0; j < cols; j++) {

                int pos = start + i * cols + j;

                if (pos < end) {

                    AppInfo item = appsList.get(pos);

                    View vItem = inflater.inflate(R.layout.item_app, null,
                            false);

                    ImageView appIcon = (ImageView) vItem.findViewById(R.id.appIcon);
                    appIcon.setImageDrawable(item.getIcon());

                    TextView appName = vItem.findViewById(R.id.appName);
                    appName.setText(item.getAppName());

                    vItem.setOnClickListener(a -> {

                        // Handle the app launch here.
                        PackageManager packageManager = mContext.getPackageManager();
                        Intent launchIntent = packageManager.getLaunchIntentForPackage(item.getPackageName());
                        if (launchIntent != null) {
                            mContext.startActivity(launchIntent);
                        }
                    });

                    vItem.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {


                            dialogFunction(position, item);
                            return false;
                        }
                    });

                    row.addView(vItem);
                } else {
                    TextView vPlaceHolder = new TextView(mContext);
                    vPlaceHolder.setText(" ");
                    row.addView(vPlaceHolder);
                }

            }
            table.addView(row);
        }

        container.addView(table);

        return table;

    }

    private void dialogFunction(int position, AppInfo item) {
        Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_long_click);

        TextView txtCancel = dialog.findViewById(R.id.txtCancel);
        txtCancel.setOnClickListener(c -> {
            dialog.dismiss();
        });

        ImageView appImage = dialog.findViewById(R.id.imgApp);
        appImage.setImageDrawable(item.getIcon());

        TextView appName = dialog.findViewById(R.id.txtAppName);
        appName.setText(item.getAppName());

        LinearLayout linAppInfo = dialog.findViewById(R.id.linAppInfo);
        linAppInfo.setOnClickListener(a -> {

            try {
                //Open the specific App Info page:
                Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + item.getPackageName()));
                mContext.startActivity(intent);

            } catch (ActivityNotFoundException e) {
                //e.printStackTrace();

                //Open the generic Apps page:
                Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
                mContext.startActivity(intent);

            }
            dialog.dismiss();

        });


        LinearLayout linUninstallApp = dialog.findViewById(R.id.linUninstallApp);
        linUninstallApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DELETE);
                intent.setData(Uri.parse("package:" + item.getPackageName()));
                mContext.startActivity(intent);

                dialog.dismiss();
                appsList.remove(position);
                notifyDataSetChanged();

            }
        });
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.show();


    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }



}