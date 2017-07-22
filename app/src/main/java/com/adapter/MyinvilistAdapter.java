package com.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.Bean.CommentBean;
import com.Bean.MyinvilistBean;
import com.gay.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by 狄飞 on 2016/7/21.
 */
public class MyinvilistAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context context;
    private List<MyinvilistBean.InviListBean.DatasBean> ais;


    public MyinvilistAdapter(Context context, List<MyinvilistBean.InviListBean.DatasBean> ais)
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
            convertView=mInflater.inflate(R.layout.myinvilistltem, null);
            holder = new ViewHolder();
//
            holder.name=(TextView)convertView.findViewById(R.id.name);
            holder.collect=(TextView)convertView.findViewById(R.id.collect);
            holder.time=(TextView)convertView.findViewById(R.id.time);
            holder.praiseNum=(TextView)convertView.findViewById(R.id.praiseNum);
            holder.hot=(TextView)convertView.findViewById(R.id.hot);
            convertView.setTag(holder);
        } else {
            holder=(ViewHolder) convertView.getTag();
        }
        Log.e("login1", "123321" + position);
        holder.name.setText(ais.get(position).getTopic());
        holder.collect.setText("被收藏量："+ais.get(position).getCollectionNum());
        holder.praiseNum.setText("被赞量："+ais.get(position).getPraiseNum());
        if(ais.get(position).getHot()<50)
        {
            holder.hot.setText("热度：低");
        }else if(ais.get(position).getHot()>=50&&ais.get(position).getHot()<100)
        {
            holder.hot.setText("热度：一般");
        }
        else {
            holder.hot.setText("热度：高");
        }
        SimpleDateFormat xdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = xdf.format(ais.get(position).getCreatetime());
        holder.time.setText(date);
        return convertView;
    }
    private static class ViewHolder {

        private TextView name;
        private TextView time;
        private TextView collect;
        private TextView praiseNum;
        private TextView hot;

    }
}
