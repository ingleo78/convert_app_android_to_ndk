package com.example.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class CaptureDataDescription {
    private EditText FileName;
    private EditText FileDescription;
    private Button addDescription;
    private Dialog dialog;
    private Activity activity;
    private FileBrowser fileBrowser;
    public String fileName;
    public String image;
    CaptureDataDescription(Activity activity, FileBrowser fileBrowser, String image, String fileName) {
        this.activity = activity;
        this.image = image;
        this.fileBrowser = fileBrowser;
        this.fileName = fileName;
    }
    public void show() {
        View view = activity.getLayoutInflater().inflate(R.layout.capture_data_description, null);
        dialog = new Dialog(activity);
        dialog.setContentView(view);
        FileName = view.findViewById(R.id.NombreArchivo);
        FileDescription = view.findViewById(R.id.Descripcion);
        addDescription = view.findViewById(R.id.AddDescription);
        addDescription.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if (MainActivity.list == null) MainActivity.list = new ArrayList<>();
                MainActivity.list.add(new DataImageDescription(image, fileName, FileDescription.getText().toString()));
                MainActivity.adapterImageDescription = new AdapterImageDescription(activity, MainActivity.list);
                MainActivity.recyclerView.setAdapter(MainActivity.adapterImageDescription);
                dialog.dismiss();
                fileBrowser.dismiss();
            }
        });
        FileName.setText(fileName);
        dialog.show();
    }
}
