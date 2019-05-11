package com.test2.tstuff.List;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.test2.tstuff.MainActivity;
import com.test2.tstuff.R;
import com.test2.tstuff.Water;
import com.test2.tstuff.toolbar.Tmenu;

public class Listmenu extends AppCompatActivity implements View.OnClickListener {

    private CardView addlist, deletelist, needlist, viewl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listmenu);

        addlist = findViewById(R.id.addlist);
        deletelist = findViewById(R.id.deletelist);
        needlist = findViewById(R.id.needlist);
        viewl = findViewById(R.id.viewl);

        addlist.setOnClickListener( this);
        deletelist.setOnClickListener( this);
        needlist.setOnClickListener( this);
        viewl.setOnClickListener( this);


    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch(v.getId()){
            case R.id.addlist:
                i = new Intent(this, Lists.class);
                startActivity(i);
                break;

            case R.id.viewl:
                i = new Intent(this, Viewlist.class);
                startActivity(i);
                break;

            case R.id.deletelist:
                i = new Intent(this, DelList.class);
                startActivity(i);
                break;

            case R.id.needlist:
                i = new Intent(this, NeedList.class);
                startActivity(i);
                break;

        }

    }

}
