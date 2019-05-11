package com.test2.tstuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.test2.tstuff.List.Listmenu;
import com.test2.tstuff.List.Lists;
import com.test2.tstuff.toolbar.Tmenu;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView list, water,test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // finds buttons
        list = findViewById(R.id.list);
        water = findViewById(R.id.fluid);
        test = findViewById(R.id.test);

        // make card view clickable
        list.setOnClickListener(this);
        water.setOnClickListener(this);
        test.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        Intent i; // get intent
        switch (v.getId())
        {
            case R.id.list:
                i = new Intent(this, Listmenu.class);
                startActivity(i);
                break;
            case R.id.fluid:
                i = new Intent(this, Water.class);
                startActivity(i);
                break;
            case R.id.test:
                i = new Intent(this, Tmenu.class);
                startActivity(i);

        }

    }
}
