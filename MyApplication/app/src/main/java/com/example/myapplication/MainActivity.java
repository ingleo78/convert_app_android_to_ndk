package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private FileBrowser fileBrowser;
    private ImageButton showFileBrowser;
    private Toolbar toolbar;
    public static RecyclerView recyclerView;
    public static ArrayList<DataImageDescription> list;
    public static AdapterImageDescription adapterImageDescription;
    public static int position;
    @Override  protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.Toolbar);
        showFileBrowser = findViewById(R.id.search);
        String[] permissions = { Manifest.permission.READ_EXTERNAL_STORAGE };
        requestPermissions(permissions, 10);
    }
    @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        showFileBrowser.setOnClickListener(new View.OnClickListener() {
            @Override  public void onClick(View view) {
                fileBrowser = new FileBrowser(MainActivity.this);
                fileBrowser.showFileBrowser();
            }
        });
        setTitle("");
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recyclerview);
        list = new ArrayList<>();
        adapterImageDescription = new AdapterImageDescription(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapterImageDescription);
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                View view = rv.findChildViewUnder(e.getX(), e.getY());
                position = rv.getChildAdapterPosition(view);
                return false;
            }
            @Override public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) { }
            @Override public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) { }
        });
    }
}