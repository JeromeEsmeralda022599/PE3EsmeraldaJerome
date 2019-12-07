package com.lastname.pe3esmeraldajerome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class VersionAdapter extends ArrayAdapter<Version> {
    Context mContext;
    int mResource;
    public VersionAdapter(@NonNull Context context, int resource, @NonNull List<Version> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String apis = getItem(position).getApis();
        String versions = getItem(position).getVersions();
        String levels = getItem(position).getLevels();
        String relDate = getItem(position).getRelDate();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);
        TextView tvApis = convertView.findViewById(R.id.editVersion);
        TextView tvVersion = convertView.findViewById(R.id.editVersion);
        TextView tvLevel = convertView.findViewById(R.id.editAPI);
        TextView tvRelDate = convertView.findViewById(R.id.editRelease);
        tvApis.setText(apis);
        tvVersion.setText(versions);
        tvLevel.setText(levels);
        tvRelDate.setText(relDate);
        return convertView;
    }
}