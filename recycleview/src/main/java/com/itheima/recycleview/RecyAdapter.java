package com.itheima.recycleview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by LiuKe on 2016/1/29.
 */
public class RecyAdapter extends RecyclerView.Adapter{

    List<String> mList;
    public RecyAdapter(List<String> list) {
        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends  RecyclerView.ViewHolder{
        public TextView tv ;
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
sdfjs;lfjasl;dkjflskdf;lsdjflkjdf