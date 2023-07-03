package com.demo.customlauncherapp.launcher_3;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.demo.customlauncherapp.R;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder> {
    ArrayList<AppInformationModel> appList;
    ClickListener listener;
    Context context;
    PackageManager packageManager;

    public AdapterClass(Context context, PackageManager packageManager, ArrayList<AppInformationModel> appList, ClickListener listener) {
        this.context = context;
        this.packageManager = packageManager;
        this.appList = appList;
        this.listener = listener;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_list, parent, false);

        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        AppInformationModel appInformationModel = appList.get(position);
        listener.onClick(appInformationModel);

        holder.txtName.setText((CharSequence) appList.get(position).name);
        holder.imageView.setImageDrawable(appList.get(position).icon);

//        holder.layClick.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent i = packageManager.getLaunchIntentForPackage(appList.get(position).name.toString());
////
////                if (i != null) {
////                    context.startActivity(i);
////                }
//                listener.onClick(AppInformationModel appInformationModel));
//            }
//
//        });

    }


    @Override
    public int getItemCount() {
        return appList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        ImageView imageView;
        LinearLayout layClick;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            txtName = itemView.findViewById(R.id.txtAppName);
            imageView = itemView.findViewById(R.id.imgAppIcon);
            layClick = itemView.findViewById(R.id.layClick);

        }
    }
}
