package com.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.Bean.SchoolBean;
import com.gay.R;

import java.util.List;

/**
 * Created by 狄飞 on 2016/7/21.
 */
public class SchoolAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context context;
    private List<SchoolBean.ListsBean.DatasBean> ais;


    public SchoolAdapter(Context context, List<SchoolBean.ListsBean.DatasBean> ais)
    {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.ais = ais;

    }

    @Override
    public int getCount() {
        return ais.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return ais.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView=mInflater.inflate(R.layout.schooltem, null);
            holder = new ViewHolder();
//
            holder.childname=(TextView)convertView.findViewById(R.id.textView36);
            convertView.setTag(holder);
        } else {
            holder=(ViewHolder) convertView.getTag();
        }
        Log.e("login1", "123321" + position);
        holder.childname.setText(ais.get(position).getSchoolName());
        return convertView;
    }
    private static class ViewHolder {

        private TextView childname;

    }
}
