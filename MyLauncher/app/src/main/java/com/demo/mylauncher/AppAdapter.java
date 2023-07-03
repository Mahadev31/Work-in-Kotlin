package com.demo.mylauncher;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutManagerCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.AppViewHolder> {

    private List<AppInfo> appList;
    private AppClickListener clickListener;
    Context context;
    PackageManager packageManager;
    public AppAdapter(Context context,List<AppInfo> appList, AppClickListener clickListener) {
       this.context=context;
        this.appList = appList;
        this.clickListener = clickListener;
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


//        holder.appIcon.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                AppInfo appInfo = appList.get(position);
//                dialogFunction(  holder.appIcon);
//                Toast.makeText(view.getContext(), "long Click" + position+"  ", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
    }


    private void dialogFunction(View appIcon) {
//        Dialog dialog=new Dialog(context);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.dialog_long_click);
//
//
//        LinearLayout layout = (LinearLayout) dialog.findViewById(R.id.linUninstallApp);
//        layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                dialog.dismiss();
//            }
//        });
//
//        dialog.getWindow().setGravity(Gravity.TOP);
//
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
//        dialog.show();

//
//        PopupMenu popup = new PopupMenu(context, appIcon);

//        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.action_delete:
//                        moveFile(recordName.getText().toString(), getAdapterPosition());
//                        return true;
//                    case R.id.action_play:
//                        String valueOfPath = recordName.getText().toString();
//                        Intent intent = new Intent();
//                        intent.setAction(android.content.Intent.ACTION_VIEW);
//                        File file = new File(valueOfPath);
//                        intent.setDataAndType(Uri.fromFile(file), "audio/*");
//                        context.startActivity(intent);
//                        return true;
//                    case R.id.action_share:
//                        String valueOfPath = recordName.getText().toString();
//                        File filee = new File(valueOfPath);
//                        try {
//                            Intent sendIntent = new Intent();
//                            sendIntent.setAction(Intent.ACTION_SEND);
//                            sendIntent.setType("audio/*");
//                            sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(filee));
//                            context.startActivity(sendIntent);
//                        } catch (NoSuchMethodError | IllegalArgumentException | NullPointerException e) {
//                            e.printStackTrace();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        return true;
//                    default:
//                        return false;
//                }
//            }
//        });
        // here you can inflate your menu
//        popup.inflate(R.layout.dialog_long_click);
////        popup.setGravity(Gravity.RIGHT);
//
//        // if you want icon with menu items then write this try-catch block.
////        try {
////            Field mFieldPopup=popup.getClass().getDeclaredField("mPopup");
////            mFieldPopup.setAccessible(true);
////            MenuPopupHelper mPopup = (MenuPopupHelper) mFieldPopup.get(popup);
////            mPopup.setForceShowIcon(true);
////        } catch (Exception e) {
////
////        }
//        popup.show();


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
            layout=itemView.findViewById(R.id.layout);
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
