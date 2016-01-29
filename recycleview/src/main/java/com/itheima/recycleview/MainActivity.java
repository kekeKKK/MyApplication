package com.itheima.recycleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private List<String> mList;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }


    private void initView() {
   //     mListView = (ListView) findViewById(R.id.lv);
        mRecyclerView = (RecyclerView) findViewById(R.id.rcv_main);
    }

    private void initData() {
        mList = new ArrayList<String>();
        for (int i = 0; i <80 ; i++) {
            mList.add("这是数据:"+i);
        }
      //  MyAdapter myAdapter = new MyAdapter(mList);
     //   mListView.setAdapter(myAdapter);

        RecyAdapter recyAdapter = new RecyAdapter(mList);

        //recyclyView布局与数据分离,可以很好的管理UI布局
        //实现各种效果
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true));

        mRecyclerView.setAdapter(recyAdapter);
    }
}
