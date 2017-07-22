package com.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.Bean.CommentBean;
import com.Bean.SchoolBean;
import com.gay.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 狄飞 on 2016/7/21.
 */
public class CommentAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context context;
    private List<CommentBean.CommentListBean.DatasBean> ais;


    public CommentAdapter(Context context, List<CommentBean.CommentListBean.DatasBean> ais)
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
            convertView=mInflater.inflate(R.layout.commentltem, null);
            holder = new ViewHolder();
//
            holder.name=(TextView)convertView.findViewById(R.id.name);
            holder.message=(TextView)convertView.findViewById(R.id.comment);
            holder.time=(TextView)convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        } else {
            holder=(ViewHolder) convertView.getTag();
        }
        Log.e("login1", "123321" + position);
        holder.name.setText(ais.get(position).getUserNickName());
        holder.message.setText(ais.get(position).getCommentContent());
        SimpleDateFormat xdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = xdf.format(ais.get(position).getCommentCreateTime());
        holder.time.setText(date);
        return convertView;
    }
    private static class ViewHolder {

        private TextView name;
        private TextView message;
        private TextView time;

    }
}
