package com.test2.tstuff.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.test2.tstuff.DBHelper.DBHelper;
import com.test2.tstuff.List.AdaptersList.ListAdapter;
import com.test2.tstuff.R;
import com.test2.tstuff.toolbar.Tmenu;

import java.util.ArrayList;
import java.util.List;

public class DelList extends Tmenu {

    TextView id;
    TextView name;

    DBHelper sql;
    ListView listView;
    Tlist li;
    private List<Tlist> DelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_del_list, null, false);
        drawerLayout.addView(contentView, 0);
        //setContentView(R.layout.activity_del_list);

        listView = findViewById(R.id.Dellistview);
        sql = new DBHelper(this);

        addListView();

        ListAdapter b = new ListAdapter(this, R.layout.my_custom_list, DelList);
        listView.setAdapter(b);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               li =  DelList.get(position);
                Integer itemID =  li.getID();
               prompt(li.getTlist(),li.getQuantity(),itemID.toString());
            }
        });

    }

    private void addListView() {
        Cursor data = sql.delgetData();

        DelList = new ArrayList<>();
        while (data.moveToNext()) {

                DelList.add((new Tlist(data.getString(1), data.getInt(2), data.getInt(0))));

        }

    }


    private void prompt(final String name, final int Quantity, final String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialog);
        builder.setTitle("Add to list");
        builder.setMessage("Do you an item back to the list");

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sql.addData(name,Quantity);
                sql.DelListDelete(id);
                Toast toast =  Toast.makeText(getApplicationContext(), "item added", Toast.LENGTH_SHORT);
                toast.show();
                finish();
                startActivity(getIntent());


            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
}
