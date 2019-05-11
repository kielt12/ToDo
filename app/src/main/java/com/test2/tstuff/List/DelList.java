package com.test2.tstuff.List;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.test2.tstuff.R;
import com.test2.tstuff.toolbar.Tmenu;

public class DelList extends Tmenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_del_list, null, false);
        drawerLayout.addView(contentView, 0);
        //setContentView(R.layout.activity_del_list);
    }
}
