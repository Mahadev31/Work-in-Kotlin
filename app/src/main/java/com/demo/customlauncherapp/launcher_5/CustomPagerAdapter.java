package com.demo.customlauncherapp.launcher_5;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.demo.customlauncherapp.R;
import com.demo.customlauncherapp.launcher_4.AppInfo;

import java.util.List;

public class CustomPagerAdapter extends PagerAdapter {
    private static final int ROWS = 4;
    private static final int COLS = 4;

    private Context mContext;
    private List<AppEntry> mData;

    public CustomPagerAdapter(Context context, List<AppEntry> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public int getCount() {
        double ratio = (double) mData.size() / (ROWS * COLS);
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
        table.setBackgroundColor(Color.DKGRAY);

        int start = position * rows * cols;
        int end = Math.min(start + rows * cols, mData.size());

        LayoutInflater inflater = LayoutInflater.from(mContext);

        for (int i = 0; i < rows; i++) {
            TableRow row = new TableRow(mContext);
            row.setLayoutParams(new TableLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
            row.setGravity(Gravity.CENTER);
            for (int j = 0; j < cols; j++) {

                int pos = start + i * cols + j;

                if (pos < end) {

                    AppEntry item = mData.get(pos);

                    View vItem = inflater.inflate(R.layout.item_app, null,
                            false);

                    ImageView image = (ImageView) vItem.findViewById(R.id.appIcon);
                    image.setImageDrawable(item.getIcon());

                    TextView appName=vItem.findViewById(R.id.appName);
                    appName.setText(item.getAppName());

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

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}