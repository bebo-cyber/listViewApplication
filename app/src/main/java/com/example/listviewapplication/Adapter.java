package com.example.listviewapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter implements View.OnClickListener {

    private ArrayList dataset;
    Context mContext;

    private static class ViewHolder {
        TextView tName;
        TextView tType;
        TextView tVersion;
        ImageView info;

    }

    public Adapter(ArrayList data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataset = data;
        this.mContext = context;
    }

    @Override
    public void onClick(View v) {
        int position = (int)v.getTag();
        Object object = getItem(position);
        DataModel dataModel = (DataModel) object;

        switch (v.getId()) {
            case R.id.item_info:
                Snackbar.make(v, "Release date" + dataModel.getFeature(), Snackbar.Lenght_Long).setAction("No Action", null).show();
                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Object dataModel = getItem(position);
        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.tName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.tType = (TextView) convertView.findViewById(R.id.type);
            viewHolder.tVersion = (TextView) convertView.findViewById(R.id.version_number);
            viewHolder.info = (ImageView) convertView.findViewById(R.id.item_info);

            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top );
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.tName.setText(dataModel.getName());
        viewHolder.tType.setText(dataModel.getType());
        viewHolder.tVersion.setText(dataModel.getVersion_number());
        viewHolder.info.setOnClickListener(this);
        viewHolder.info.setTag(position);
        return convertView;
    }
}