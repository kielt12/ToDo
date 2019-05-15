package com.test2.tstuff.List;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.test2.tstuff.DBHelper.DBHelper;
import com.test2.tstuff.R;
import com.test2.tstuff.toolbar.Tmenu;

import java.util.ArrayList;
import java.util.List;

public class NeedList extends Tmenu {

    private List<Tlist> NeedList;

    Tlist li;


    ListView listView;
    DBHelper sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_need_list, null, false);
        drawerLayout.addView(contentView, 0);
        //setContentView(R.layout.activity_viewlist);


        listView = findViewById(R.id.Needlistview);
        sql = new DBHelper(this);

        addListView();

        ListAdapter b = new ListAdapter(this,R.layout.my_custom_list, NeedList);
        listView.setAdapter(b);

    }

    private void addListView(){
        Cursor data = sql.getData();

        NeedList = new ArrayList<>();
        while(data.moveToNext()){

            if(data.getInt(2) == 0 || data.getInt(2) == 1) {

                NeedList.add((new Tlist(data.getString(1), data.getInt(2),data.getInt(0))));
            }

        }



    }
}
