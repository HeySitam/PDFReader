package com.sitamrock11.pdfreader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE=200;
    private ArrayList<PDFInfo> pdfInfoList=new ArrayList<>();
    private ArrayList<File> pdfFileList=new ArrayList<>();
    RecyclerView rvPdfList;
    private PdfListAdapter adapter=new PdfListAdapter(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvPdfList=(RecyclerView)findViewById(R.id.rvPdfList);
        rvPdfList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rvPdfList.setAdapter(MainActivity.this.adapter);

        if(!checkPermission()){
            requestPermission();
        }else{
            Toast.makeText(this.getApplicationContext(),"Aso khelbo khela hobe !!",Toast.LENGTH_SHORT).show();
            File dir=new File(Environment.getExternalStorageDirectory().getAbsolutePath());
            getfile(dir);
            adapter.updateList(pdfInfoList);
        }

    }

    private void getfile(File dir) {
        File listFile[]=dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {

                if (listFile[i].isDirectory()) {
                    pdfFileList.add(listFile[i]);
                    getfile(listFile[i]);

                } else {
                    if (listFile[i].getName().endsWith(".pdf"))
                    {
                        pdfFileList.add(listFile[i]);
                        System.out.println(listFile[i].getName());
                        pdfInfoList.add(new PDFInfo(listFile[i].getName()));
                    }
                }
            }
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{READ_EXTERNAL_STORAGE,WRITE_EXTERNAL_STORAGE},
                PERMISSION_REQUEST_CODE);
    }

    private boolean checkPermission() {
        int writePermission= ContextCompat.checkSelfPermission(getApplicationContext(),WRITE_EXTERNAL_STORAGE);
        int readPermission= ContextCompat.checkSelfPermission(getApplicationContext(),READ_EXTERNAL_STORAGE);
        return writePermission== PackageManager.PERMISSION_GRANTED && readPermission==PackageManager.PERMISSION_GRANTED;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==PERMISSION_REQUEST_CODE){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this.getApplicationContext(),"BOOYAh!! permission granted.",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this.getApplicationContext(),"JibonSes!! permission denied.",Toast.LENGTH_SHORT).show();
        }
    }

}