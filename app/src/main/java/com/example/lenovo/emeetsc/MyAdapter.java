package com.example.lenovo.emeetsc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lenovo on 2017/3/25.
 */

public class MyAdapter extends BaseAdapter {
    private LayoutInflater myInflater;
    private List<forumInfo> forumInfos;

    public MyAdapter(Context context, List<forumInfo> forumInfoList2) {
        myInflater = LayoutInflater.from(context);
        this.forumInfos = forumInfoList2;
    }

    private class ViewHolder {
        TextView txtTitle;
        TextView txtContent;
        TextView txtName;
        ImageView imageView;
//        TextView txtTime;

        public ViewHolder(TextView txtTitle, TextView txtContent, TextView txtName,
                          ImageView imageView) {
            this.txtTitle = txtTitle;
            this.txtContent = txtContent;
            this.txtName = txtName;
            this.imageView=imageView;
//            this.txtTime=txtTime;
        }
    }

    @Override
    public int getCount() {
        return forumInfos.size();
    }

    @Override
    public Object getItem(int arg0) {
        return forumInfos.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return forumInfos.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = myInflater.inflate(R.layout.my_listitem, null);
            holder = new ViewHolder(
                    (TextView) convertView.findViewById(R.id.ItemTile),
                    (TextView) convertView.findViewById(R.id.ItemText),
                    (TextView) convertView.findViewById(R.id.nameTxt1),
                    (ImageView) convertView.findViewById(R.id.touxiang)
//                    (TextView) convertView.findViewById(R.id.time)
            );
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        forumInfo forumInfoList2 = (forumInfo) getItem(position);

        //0 = movie, 1 = title, 2 = nine

        holder.txtName.setText(forumInfoList2.getName());
        holder.txtTitle.setText(forumInfoList2.getTitle());
        holder.txtContent.setText(forumInfoList2.getContent());
        holder.imageView.setImageResource(forumInfoList2.getImgId());
//        holder.txtTime.setText(forumInfoList2.getTime());

        return convertView;
    }
}


