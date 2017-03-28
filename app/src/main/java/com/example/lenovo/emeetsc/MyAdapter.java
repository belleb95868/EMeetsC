package com.example.lenovo.emeetsc;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/3/25.
 */

public class MyAdapter extends BaseAdapter implements Filterable {
    private LayoutInflater myInflater;
    private List<forumInfo> forumInfos;

    private List<forumInfo> mOriginalValues; //保存我最原来传进来的值
    private MyFilter filter;

    public MyAdapter(Context context, List<forumInfo> forumInfoList2) {
        myInflater = LayoutInflater.from(context);
        this.forumInfos = forumInfoList2;
    }

    @Override
    public Filter getFilter() {
        if (filter == null){
            filter  = new MyFilter();
        }
        return filter;
    }

    private class MyFilter extends Filter {

        @Override//把筛选的结果publish
        protected void publishResults(CharSequence constraint, FilterResults results) {
            forumInfos = (ArrayList<forumInfo>) results.values;  //result是筛选后的值，表变成筛选后的值
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();// 自动更新list
            }
        }

        protected FilterResults performFiltering(CharSequence constraint) {
            //filteredItems是我最後要顯示的資料

            constraint = constraint.toString();
            FilterResults result = new FilterResults();
            if (mOriginalValues == null) {   //如果输入框的值为0，复制整个表
                synchronized (this) {
                    mOriginalValues = new ArrayList<forumInfo>(forumInfos);
                }
            }

            if(constraint != null && constraint.toString().length() > 0){
                //搜索列表是否有要查的東西
                ArrayList<forumInfo> filteredItems = new ArrayList<forumInfo>();
                for(int i = 0, l = mOriginalValues.size(); i < l; i++){
                    forumInfo m = mOriginalValues.get(i);
                    if(m.getTitle().contains(constraint)){
                        filteredItems.add(m);
                    }
                }
                result.count = filteredItems.size();
                result.values = filteredItems;
            }else{   //沒有要查的東西
                synchronized(this){
                    ArrayList<forumInfo> list = new ArrayList<forumInfo>(mOriginalValues);
                    result.values = list;
                    result.count = list.size();
                }
            }
            return result;

        }
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


