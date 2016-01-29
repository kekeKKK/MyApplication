package com.itheima.slidingmenu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.slidingmenu.R;
import com.itheima.slidingmenu.view.SlidingView;

public class MainActivity extends Activity {

    private SlidingView mSlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        mSlv = (SlidingView) findViewById(R.id.slv);
    }

    public void onBackClick(View v){
        mSlv.toggle();
    }

    public void onItemClick(View v){
        TextView tv = (TextView)v;
        Toast.makeText(this, tv.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}
