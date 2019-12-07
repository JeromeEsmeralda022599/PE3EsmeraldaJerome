package com.lastname.pe3esmeraldajerome;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String[] api, ver, level, date, description;
    ListView list;
    private File file;
    private File folder;

    ArrayList<Version> VersionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api = getResources().getStringArray(R.array.apis);
        ver = getResources().getStringArray(R.array.versions);
        level = getResources().getStringArray(R.array.level);
        date = getResources().getStringArray(R.array.relDate);
        list = findViewById(R.id.lvVersions);
        list = findViewById(R.id.lvVersions);
        VersionAdapter adapter = new VersionAdapter(this, R.layout.item, VersionList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this)
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int j, long id) {

        folder = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        file = new File(folder, "Versions.txt");
        try {

            FileOutputStream outputStream = new FileOutputStream(file);
            String choiceApi = VersionList.get(j).getApis();
            outputStream.write(choiceApi.getBytes());
            String choiceReleaseDate = "\n"+ VersionList.get(j).getRelDate();
            outputStream.write(choiceReleaseDate.getBytes());
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle(VersionList.get(j).getApis());
            dialog.setIcon(VersionList.get(j).getLogo());
            dialog.setMessage(VersionList.get(j).getDescription());
            dialog.setNeutralButton("CLOSE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    String readData = readInput();
                    Toast.makeText(MainActivity.this, readData, Toast.LENGTH_LONG).show();
                }
            });
            dialog.create().show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readInput() {
        FileInputStream stream = null;
        file = new File(folder, "Versions.txt");
        StringBuilder sb = new StringBuilder();

        try {
            stream = new FileInputStream(file);
            int i;
            while ((i = stream.read()) != -1) {
                sb.append((char) i);
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            Log.d("error", "File not found");
        } catch (IOException e) {
            Log.d("error", "IO error");
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}