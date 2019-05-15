package com.test2.tstuff.List;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.test2.tstuff.R;
import com.test2.tstuff.toolbar.Tmenu;

public class DelList extends Tmenu {

    TextView id;
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_del_list, null, false);
        drawerLayout.addView(contentView, 0);
        //setContentView(R.layout.activity_del_list);

        id = findViewById(R.id.textView3);
        name = findViewById(R.id.textView4);

        Intent fromLastActivity = getIntent();
        int Sid = fromLastActivity.getIntExtra("id",-1);
        String Sname = fromLastActivity.getStringExtra("name");

        id.setText("" + Sid );
        name.setText(Sname);

    }
}
