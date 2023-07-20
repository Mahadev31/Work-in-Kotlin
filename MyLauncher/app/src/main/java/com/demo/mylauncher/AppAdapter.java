package com.demo.mylauncher;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


// not use this adapter
public class AppAdapter extends RecyclerView.Adapter<AppAdapter.AppViewHolder> {

    private List<AppInfo> appList;
    private AppClickListener clickListener;
    Context context;
    PackageManager packageManager;


    public AppAdapter(Context context, AppClickListener clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }

    public void update(List<AppInfo> installedAppsList) {
        appList = installedAppsList;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_app, parent, false);
        return new AppViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        AppInfo appInfo = appList.get(position);
        holder.appIcon.setImageDrawable(appInfo.getIcon());
        holder.appName.setText(appInfo.getAppName());


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {


                dialogFunction( position);
                return false;
            }
        });
    }


    private void dialogFunction( int position) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_long_click);

        TextView txtCancel = dialog.findViewById(R.id.txtCancel);
        txtCancel.setOnClickListener(c->{
            dialog.dismiss();
        });

        ImageView appImage = dialog.findViewById(R.id.imgApp);
        appImage.setImageDrawable(appList.get(position).getIcon());

        TextView appName = dialog.findViewById(R.id.txtAppName);
        appName.setText(appList.get(position).getAppName());

        LinearLayout linAppInfo = dialog.findViewById(R.id.linAppInfo);
        linAppInfo.setOnClickListener(a->{

            try {
                //Open the specific App Info page:
                Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + appList.get(position).getPackageName()));
                context.startActivity(intent);

            } catch ( ActivityNotFoundException e ) {
                //e.printStackTrace();

                //Open the generic Apps page:
                Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
                context.startActivity(intent);

            }
            dialog.dismiss();

        });


        LinearLayout linUninstallApp = dialog.findViewById(R.id.linUninstallApp);
        linUninstallApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DELETE);
                intent.setData(Uri.parse("package:" + appList.get(position).getPackageName()));
                context.startActivity(intent);

                dialog.dismiss();
                appList.remove(position);  //app remove array list
                notifyItemRemoved(position);
            }
        });
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.show();


    }

    @Override
    public int getItemCount() {
        return appList.size();
    }


    public interface AppClickListener {
        void onAppClick(AppInfo appInfo);
    }

    public class AppViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView appIcon;
        private TextView appName;
        private LinearLayout layout;

        public AppViewHolder(@NonNull View itemView) {
            super(itemView);
            appIcon = itemView.findViewById(R.id.appIcon);
            appName = itemView.findViewById(R.id.appName);
            layout = itemView.findViewById(R.id.layout);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                AppInfo appInfo = appList.get(position);
                clickListener.onAppClick(appInfo);
            }
        }
    }
}
