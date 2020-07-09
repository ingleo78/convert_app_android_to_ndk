package com.example.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class FileBrowser {
    private Activity activity;
    private RecyclerView recyclerView;
    private ImageView imageSelected;
    private Dialog dialog;
    private String currentDirectory, _currentDirectory, rootDirectory;
    private int position;
    private boolean isImageSelected;
    public static ArrayList<DataFileBrowser> listData;
    FileBrowser(Activity activity) {  this.activity = activity; }
    void showFileBrowser() {
        dialog = new Dialog(activity);
        View view = activity.getLayoutInflater().inflate(R.layout.find_image, null);
        dialog.setContentView(view);
        recyclerView = view.findViewById(R.id.MemoryContent);
        imageSelected = view.findViewById(R.id.ImageSelected);
        File root = Environment.getStorageDirectory();
        _currentDirectory = root.getAbsolutePath() + File.separator + "self" + File.separator + "primary";
        currentDirectory = _currentDirectory;
        rootDirectory = _currentDirectory;
        root = new File(_currentDirectory);
        listData = getContentCurrentDirectory(root);
        final AdapterFileBrowser adapterFileBrowser = new AdapterFileBrowser(activity, listData);
        recyclerView.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.HORIZONTAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapterFileBrowser);
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override  public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                View view = rv.findChildViewUnder(e.getX(), e.getY());
                position = rv.getChildAdapterPosition(view);
                if (view != null) {
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override  public void onClick(View view) {
                            String fileName = listData.get(position).fileName;
                            if (fileName.equals("..")) {
                                String[] directories = _currentDirectory.split(Pattern.quote(File.separator));
                                _currentDirectory = "";
                                for (int pos = 0; pos < (directories.length - 1); pos++) {
                                    if (pos < (directories.length - 2)) _currentDirectory += directories[pos] + File.separator;
                                    else _currentDirectory += directories[pos];
                                }
                            } else _currentDirectory = currentDirectory + File.separator + fileName;
                            File file = new File(_currentDirectory);
                            if (file.isDirectory()) {
                                listData.clear();
                                listData = getContentCurrentDirectory(file);
                                recyclerView.setAdapter(new AdapterFileBrowser(activity, listData));
                                imageSelected.setImageResource(R.drawable.image_preview);
                                currentDirectory = _currentDirectory;
                            } else {
                                if (fileName.contains(".bmp") || fileName.contains(".jpg") || fileName.contains(".jpeg") || fileName.contains(".gif") ||
                                    fileName.contains(".png")) {
                                    imageSelected.setImageBitmap(BitmapFactory.decodeFile(_currentDirectory));
                                    isImageSelected = true;
                                } else imageSelected.setImageResource(R.drawable.image_preview);
                            }
                        }
                    });
                }
                return false;
            }
            @Override  public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) { }
            @Override  public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) { }
        });
        imageSelected.setOnClickListener(new View.OnClickListener() {
            @Override  public void onClick(View view) {
                if (isImageSelected) {
                    int image = listData.get(position).icon;
                    String fileName = listData.get(position).fileName;
                    CaptureDataDescription captureDataDescription = new CaptureDataDescription(activity, FileBrowser.this, _currentDirectory, fileName);
                    captureDataDescription.show();
                    isImageSelected = false;
                }
            }
        });
        dialog.show();
    }
    public void dismiss() { dialog.dismiss(); }
    private ArrayList<DataFileBrowser> getContentCurrentDirectory(File currentDirectory) {
        String[] _listData = currentDirectory.list();
        ArrayList<DataFileBrowser> fileList = new ArrayList<>();
        if (_listData != null) {
            boolean backFound = false;
            if (_listData.length == 0) fileList.add(new DataFileBrowser(R.drawable.back, ".."));
            else {
                for (String data : _listData) {
                    int icon = 0;
                    File file = new File(currentDirectory + File.separator + data);
                    if (file.isFile()) {
                        if (_listData.length == 1) { fileList.add(new DataFileBrowser(R.drawable.back, "..")); backFound = true; }
                        if (data.contains(".png") || data.contains(".bmp") || data.contains(".jpg") || data.contains(".gif") ||
                            data.contains(".jpge"))  icon = R.drawable.image_file;
                        else if (data.contains(".txt")) icon = R.drawable.text_file;
                        else if (data.contains(".mp3") || data.contains(".wav") || data.contains(".gpp") || data.contains(".aac") ||
                                data.contains(".wma") || data.contains(".ogg") || data.contains(".ac3") || data.contains(".midi")) icon = R.drawable.music_file;
                        else if (data.contains(".dat") || data.contains(".bin") || data.contains(".dll") || data.contains(".so") ||
                                data.contains(".profig")) icon = R.drawable.binary_file;
                        else if (data.contains(".rar") || data.contains(".zip") || data.contains(".ace") || data.contains(".7z")) icon = R.drawable.compress_file;
                        else if (data.contains(".pdf")) icon = R.drawable.pdf_file;
                        else if (data.contains(".docx") || data.contains(".docm") || data.contains(".dotx")) icon = R.drawable.word_file;
                        else if (data.contains(".xlsx") || data.contains(".xlsm") || data.contains(".xlsb")) icon = R.drawable.excel;
                        else if (data.contains(".thmx") || data.contains(".ppsx") || data.contains(".ppsm") || data.contains(".pps")) icon = R.drawable.powerpoint;
                        else if (data.contains(".mp4") || data.contains(".divx") || data.contains(".div") || data.contains(".mpg") ||
                                data.contains(".mpegs") || data.contains(".mkv")) icon = R.drawable.video_file;
                        else if (data.contains(".dba") || data.contains(".dbx") || data.contains(".pdo") || data.contains(".db")) icon = R.drawable.database;
                        else if (data.contains(".iso") || data.contains(".ngr")) icon = R.drawable.disc;
                        else icon = R.drawable.unknow_file;
                    } else {
                        if (data.equals(".")) continue;
                        else if (data.equals("..")) {
                            if (_currentDirectory.equals(rootDirectory)) { backFound = true; continue; }
                            icon = R.drawable.back;
                            backFound = true;
                        } else {
                            if (!_currentDirectory.equals(rootDirectory) && !backFound) {
                                String dataTemp = "..";
                                icon = R.drawable.back;
                                fileList.add(new DataFileBrowser(icon, dataTemp));
                                backFound = true;
                            } else backFound = true;
                            String[] listDataTemp = file.list();
                            boolean fileFound = false;
                            if (listDataTemp != null) {
                                for (String dataTemp : listDataTemp) {
                                    File fileTemp = new File(file.getAbsolutePath() + File.separator + dataTemp);
                                    if (fileTemp.isFile()) {
                                        fileFound = true;
                                        break;
                                    }
                                }
                            }
                            if (fileFound) icon = R.drawable.folder_with_files;
                            else icon = R.drawable.folder_empty;
                        }
                    }
                    fileList.add(new DataFileBrowser(icon, data));
                }
                if (!backFound) fileList.add(new DataFileBrowser(R.drawable.back, ".."));
            }
        } else fileList.add(new DataFileBrowser(R.drawable.back, ".."));
        return fileList;
    }
}
