package com.example.myapplication;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AdapterImageDescription extends RecyclerView.Adapter<AdapterImageDescription.ViewHolder> {
    private ImageView imageView;
    private TextView imageName;
    private TextView imageDescription;
    private Activity activity;
    private ArrayList<DataImageDescription> list;
    AdapterImageDescription(Activity activity, ArrayList<DataImageDescription> list) {
        super();
        this.activity = activity;
        this.list = list;
    }
    @NonNull @Override public AdapterImageDescription.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = activity.getLayoutInflater().inflate(R.layout.description_row, parent, false);
        imageView = view.findViewById(R.id.image_view);
        imageName = view.findViewById(R.id.Nombre);
        imageDescription = view.findViewById(R.id.Descripcion);
        return new ViewHolder(view);
    }
    @Override public void onBindViewHolder(@NonNull AdapterImageDescription.ViewHolder holder, int position) {
        ConstraintLayout constraintLayout = (ConstraintLayout)holder.itemView;
        LinearLayout linearLayoutPrincipal = (LinearLayout)constraintLayout.getChildAt(0);
        imageView = (ImageView)linearLayoutPrincipal.getChildAt(0);
        ScrollView scrollView = (ScrollView)linearLayoutPrincipal.getChildAt(1);
        LinearLayout linearLayoutSecondary = (LinearLayout)scrollView.getChildAt(0);
        imageName = (TextView)linearLayoutSecondary.getChildAt(1);
        imageDescription = (TextView)linearLayoutSecondary.getChildAt(3);
        imageView.setImageBitmap(BitmapFactory.decodeFile(list.get(position).image));
        imageName.setText(list.get(position).name);
        imageDescription.setText(list.get(position).description);
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