package com.example.myapplication;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AdapterFileBrowser extends RecyclerView.Adapter<AdapterFileBrowser.ViewHolder> {
    private Activity activity;
    private ImageView fileIcon;
    private TextView fileName;
    private ArrayList<DataFileBrowser> list;
    public AdapterFileBrowser(Activity activity, ArrayList<DataFileBrowser> list) {
        super();
        this.activity = activity;
        this.list = list;
    }
    @NonNull @Override public AdapterFileBrowser.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = activity.getLayoutInflater().inflate(R.layout.file_name, parent, false);
        fileIcon = view.findViewById(R.id.FileIcon);
        fileName = view.findViewById(R.id.FileName);
        return new ViewHolder(view);
    }
    @Override public void onBindViewHolder(@NonNull AdapterFileBrowser.ViewHolder holder, int position) {
        ((ImageView)((LinearLayout)holder.itemView).getChildAt(0)).setImageResource(list.get(position).icon);
        ((TextView)((LinearLayout)holder.itemView).getChildAt(1)).setText(list.get(position).fileName);
    }
    @Override public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
